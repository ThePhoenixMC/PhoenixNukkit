package com.lss233.phoenix.nukkit.utils.nukkit;

import cn.nukkit.entity.EntityHuman;
import com.lss233.phoenix.entity.Entity;
import com.lss233.phoenix.entity.EntityType;
import com.lss233.phoenix.entity.living.Player;
import com.lss233.phoenix.item.inventory.CarriedInventory;
import com.lss233.phoenix.item.inventory.Carrier;
import com.lss233.phoenix.item.inventory.Inventory;
import com.lss233.phoenix.item.inventory.ItemStack;
import com.lss233.phoenix.item.inventory.equipment.EquipmentType;
import com.lss233.phoenix.math.Vector;
import com.lss233.phoenix.module.Module;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.lss233.phoenix.nukkit.NukkitMain.getNukkitServer;
import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

/**
 *
 */
public interface PlayerTransform {
    /**
     * Convert a Bukkit player instance to a Phoenix player instance.
     * @param player The Bukkit player instance.
     * @return The Phoenix player instance.
     */
    default Player toPhoenix(cn.nukkit.Player player){
        Entity _super = getTransformer().toPhoenix((EntityHuman) player);
        return new Player() {

            @Override
            public boolean hasPermission(String s) {
                return player.hasPermission(s);
            }

            @Override
            public CarriedInventory<? extends Carrier> getInventory() {
                //TODO
                return null;
            }

            @Override
            public boolean equip(EquipmentType equipmentType, @Nullable ItemStack itemStack) {
                return false;
            }

            @Override
            public Optional<ItemStack> getEquipped(EquipmentType equipmentType) {
                return Optional.empty();
            }

            @Override
            public EntityType getType() {
                return EntityType.PLAYER;
            }

            @Override
            public boolean hasPassenger(Entity entity) {
                return _super.hasPassenger(entity);
            }

            @Override
            public List<Entity> getPassengers() {
                return _super.getPassengers();
            }

            @Override
            public boolean addPassenger(Entity entity) {
                return _super.addPassenger(entity);
            }

            @Override
            public void clearPassengers() {
                _super.clearPassengers();
            }

            @Override
            public void removePassenger(Entity entity) {
                _super.removePassenger(entity);
            }

            @Override
            public Optional<Entity> getVehicle() {
                return _super.getVehicle();
            }

            @Override
            public boolean setVehicle(Entity entity) {
                return _super.setVehicle(entity);
            }

            @Override
            public Entity getBaseVehicle() {
                return _super.getBaseVehicle();
            }

            @Override
            public Vector getVelocity() {
                return _super.getVelocity();
            }

            @Override
            public boolean gravity() {
                return _super.gravity();
            }

            @Override
            public boolean teleport(com.lss233.phoenix.world.Location location) {
                return _super.teleport(location);
            }

            @Override
            public boolean teleport(Entity entity) {
                return _super.teleport(entity);
            }

            @Override
            public double getHealth() {
                return player.getHealth();
            }

            @Override
            public void setHealth(double health) {
                player.setHealth((float) health);
            }

            @Override
            public double getMaxHealth() {
                return player.getMaxHealth();
            }

            @Override
            public com.lss233.phoenix.world.Location getLocation() {
                return _super.getLocation();
            }

            @Override
            public com.lss233.phoenix.world.World getWorld() {
                return _super.getWorld();
            }

            @Override
            public UUID getUniqueId() {
                return _super.getUniqueId();
            }

            @Override
            public int hashCode() {
                return _super.hashCode();
            }

            @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
            @Override
            public boolean equals(Object object) {
                return _super.equals(object);
            }

            @Override
            public void sendPluginMessage(Module source, String channel, byte[] message) {
                throw new UnsupportedOperationException("Not implement yet");
            }


            public String getName() {
                return player.getName();
            }

            @Override
            public void kick() {
                player.kick();
            }

            @Override
            public Optional<Inventory> openInventory(Inventory inventory) {
                player.addWindow(getTransformer().toNukkit(inventory));
                return Optional.of(inventory);
            }

            @Override
            public Optional<Inventory> getOpenInventory() {
                if(player.getInventory() == null)
                    return Optional.empty();
                return Optional.of(getTransformer().toPhoenix(player.getInventory()));
            }

            @Override
            public boolean closeInventory() {
                player.getCursorInventory().close(player);
                return true;
            }

            @Override
            public void sendMessage(String message) {
                player.sendMessage(message);
            }

            @Override
            public void sendMessage(String[] message) {
                for(String str: message)
                    sendMessage(str);
            }

        };
    }
    default cn.nukkit.Player toNukkit(Player player){
        return getNukkitServer().getPlayer(player.getUniqueId().toString()); // TODO Implement later.
    }
}