package com.lss233.phoenix.nukkit.utils.nukkit.entity;

import cn.nukkit.Server;
import com.lss233.phoenix.entity.Entity;
import com.lss233.phoenix.entity.EntityType;
import com.lss233.phoenix.math.Vector;
import com.lss233.phoenix.world.Location;

import javax.annotation.Nullable;
import java.util.*;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

/**
 *
 */
public interface EntityTransform {
    default Entity toPhoenix(cn.nukkit.entity.Entity entity){
        return new Entity() {
            @Override
            public EntityType getType() {
                return EntityType.valueOf(entity.getEntityType().toString());
            }

            @Override
            public boolean hasPassenger(Entity passenger) {
                return false;
            }

            @Override
            public List<Entity> getPassengers() {
                return Collections.emptyList();
            }

            @Override
            public boolean addPassenger(Entity entity) {
                return false;
            }

            @Override
            public void clearPassengers() {

            }

            @Override
            public void removePassenger(Entity entity) {

            }

            @Override
            public Optional<Entity> getVehicle() {
                return Optional.empty();
            }

            @Override
            public boolean setVehicle(@Nullable Entity entity) {
                return false;
            }

            @Override
            public Entity getBaseVehicle() {
                return null;
            }

            @Override
            public Vector getVelocity() {
                return getTransformer().toPhoenix(entity.getDirectionVector());
            }

            @Override
            public boolean gravity() {
                return true;
            }

            @Override
            public boolean teleport(Location location) {
                return entity.teleport(getTransformer().toNukkit(location));
            }

            @Override
            public boolean teleport(Entity target) {
                return teleport(target.getLocation());
            }

            @Override
            public UUID getUniqueId() {
                return entity.getUniqueId();
            }

            @Override
            public Location getLocation() {
                return getTransformer().toPhoenix(entity.getLocation());
            }
        };
    }

    default cn.nukkit.entity.Entity toNukkit(Entity entity){
            for (cn.nukkit.entity.Entity valueEntity : Server
                    .getInstance()
                    .getLevelByName(entity
                            .getLocation()
                            .getWorld()
                            .getName())
                    .getEntities()) {
                if(valueEntity.getUniqueId().equals(entity.getUniqueId()))
                    return valueEntity;
            }
            return null;
        }
}
