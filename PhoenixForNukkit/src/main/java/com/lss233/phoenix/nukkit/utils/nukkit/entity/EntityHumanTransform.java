package com.lss233.phoenix.nukkit.utils.nukkit.entity;

import cn.nukkit.entity.EntityHuman;
import com.lss233.phoenix.entity.Entity;
import com.lss233.phoenix.entity.EntityTypes;
import com.lss233.phoenix.entity.living.Player;
import com.lss233.phoenix.world.Location;
import com.lss233.phoenix.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Vector;

/**
 *
 */
public interface EntityHumanTransform {
    /**
     * Convert a Nukkit EntityHuman instance to a Phoenix player instance.
     * @param entityHuman The Nukkit EntityHuman instance.
     * @return The Phoenix player instance.
     */
    default Entity toPhoenix(EntityHuman entityHuman){
        return new Entity() {
            @Override
            public EntityTypes getType() {
                return EntityTypes.PLAYER;
            }

            @Override
            public boolean hasPassenger(Entity entity) {
                return false;
            }

            @Override
            public List<Entity> getPassengers() {
                return null;
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
                return null;
            }

            @Override
            public boolean gravity() {
                return false;
            }

            @Override
            public boolean teleport(Location location) {
                return entityHuman.teleport(toNukkit(location));
            }

            @Override
            public boolean teleport(Entity entity) {
                return false;
            }

            @Override
            public UUID getUniqueId() {
                return null;
            }

            @Override
            public Location getLocation() {
                return null;
            }

            @Override
            public World getWorld() {
                return null;
            }
        }
    }
}
