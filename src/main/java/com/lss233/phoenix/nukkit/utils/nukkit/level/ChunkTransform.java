package com.lss233.phoenix.nukkit.utils.nukkit.level;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.SimpleAxisAlignedBB;
import com.lss233.phoenix.entity.Entity;
import com.lss233.phoenix.entity.EntityType;
import com.lss233.phoenix.math.Vector;
import com.lss233.phoenix.world.Chunk;
import com.lss233.phoenix.world.World;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

public interface ChunkTransform {
    default Chunk toPhoenix(FullChunk chunk, World  world){
        return new Chunk() {
            @Override
            public World getWorld() {
                return world;
            }

            @Override
            public int getX() {
                return chunk.getX();
            }

            @Override
            public int getZ() {
                return chunk.getZ();
            }

            @Override
            public boolean isLoaded() {
                return chunk.isLoaded();
            }

            @Override
            public boolean load(boolean b) {
                try {
                    return chunk.load(b);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public boolean unload() {
                try {
                    return chunk.unload();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public Optional<Entity> createEntity(EntityType entityType, Vector vector) {
                return world.createEntity(entityType,
                        new Vector(vector.getX() + getX() * 16,
                                vector.getY(),
                                vector.getZ() + getZ() * 16));
            }

            @Override
            public List<Entity> getNearbyEntities(Vector vector, double v) {
                return world.getNearbyEntities(new Vector(vector.getX() + getX() * 16,
                        vector.getY(),
                        vector.getZ() + getZ() * 16), v);
            }

            @Override
            public List<Entity> getEntities() {
                return chunk.getEntities()
                        .values()
                        .stream()
                        .map(entity -> getTransformer().toPhoenix(entity))
                        .collect(Collectors.toList());
            }

            @Override
            public Optional<Entity> getEntity(UUID uuid) {
                return world.getEntity(uuid);
            }
        };
    }

    default FullChunk toNukkit(Chunk chunk){
        return getTransformer().toNukkit(chunk.getWorld())
                .getChunk(chunk.getX(), chunk.getZ());
    }
}
