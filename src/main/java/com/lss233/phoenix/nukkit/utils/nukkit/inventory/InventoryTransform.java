package com.lss233.phoenix.nukkit.utils.nukkit.inventory;

import cn.nukkit.Player;
import cn.nukkit.inventory.InventoryHolder;
import cn.nukkit.item.Item;
import com.lss233.phoenix.item.inventory.Inventory;
import com.lss233.phoenix.item.inventory.InventoryType;
import com.lss233.phoenix.item.inventory.ItemStack;
import com.lss233.phoenix.item.inventory.ItemType;
import com.lss233.phoenix.item.inventory.property.InventoryProperty;

import java.util.*;
import java.util.stream.Collectors;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

public interface InventoryTransform {
    default Inventory toPhoenix(cn.nukkit.inventory.Inventory inventory){
        return new Inventory() {
            @Override
            public void clear() {
                inventory.clearAll();
            }

            @Override
            public boolean contains(ItemStack itemStack) {
                return false;
            }

            @Override
            public boolean contains(ItemType itemType) {
                return false;
            }

            @Override
            public boolean containsAny(ItemStack itemStack) {
                return contains(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return inventory.getMaxStackSize();
            }

            @Override
            public void setMaxStackSize(int i) {
                inventory.setMaxStackSize(i);
            }

            @Override
            public int capacity() {
                return inventory.getMaxStackSize();
            }

            @Override
            public int size() {
                return inventory.getSize();
            }

            @Override
            public InventoryType getInventoryType() {
                return null;
            }

            @Override
            public Optional<? extends InventoryProperty<?, ?>> getInventoryProperty(Class<? extends InventoryProperty> aClass) {
                return Optional.empty();
            }

            @Override
            public void setItem(int i, ItemStack itemStack) {
                inventory.setItem(i, getTransformer().toNukkit(itemStack));
            }

            @Override
            public Optional<ItemStack> getItem(int i) {
                Item item = inventory.getItem(i);
                if(item == null)
                    return Optional.empty();
                else
                    return Optional.of(getTransformer().toPhoenix(item));
            }

            @Override
            public ListIterator<ItemStack> iterator() {
                return inventory.getContents().entrySet().stream().map(i -> getTransformer().toPhoenix(i.getValue())).collect(Collectors.toList()).listIterator();
            }

            @Override
            public String getName() {
                return inventory.getName();
            }
        };
    }

    default cn.nukkit.inventory.Inventory toNukkit(Inventory inventory){
        return new cn.nukkit.inventory.Inventory() {
            @Override
            public int getSize() {
                return inventory.capacity();
            }

            @Override
            public int getMaxStackSize() {
                return inventory.getMaxStackSize();
            }

            @Override
            public void setMaxStackSize(int i) {
                inventory.setMaxStackSize(i);
            }

            @Override
            public String getName() {
                return inventory.getName();
            }

            @Override
            public String getTitle() {
                return inventory.getName();
            }

            @Override
            public Item getItem(int i) {
                return null;
            }

            @Override
            public boolean setItem(int i, Item item, boolean b) {
                return false;
            }

            @Override
            public Item[] addItem(Item... items) {
                return new Item[0];
            }

            @Override
            public boolean canAddItem(Item item) {
                return false;
            }

            @Override
            public Item[] removeItem(Item... items) {
                return new Item[0];
            }

            @Override
            public Map<Integer, Item> getContents() {
                return null;
            }

            @Override
            public void setContents(Map<Integer, Item> map) {

            }

            @Override
            public void sendContents(Player player) {

            }

            @Override
            public void sendContents(Player... players) {

            }

            @Override
            public void sendContents(Collection<Player> collection) {

            }

            @Override
            public void sendSlot(int i, Player player) {

            }

            @Override
            public void sendSlot(int i, Player... players) {

            }

            @Override
            public void sendSlot(int i, Collection<Player> collection) {

            }

            @Override
            public boolean contains(Item item) {
                return false;
            }

            @Override
            public Map<Integer, Item> all(Item item) {
                return null;
            }

            @Override
            public int first(Item item, boolean b) {
                return 0;
            }

            @Override
            public int firstEmpty(Item item) {
                return 0;
            }

            @Override
            public void decreaseCount(int i) {

            }

            @Override
            public void remove(Item item) {

            }

            @Override
            public boolean clear(int i, boolean b) {
                return false;
            }

            @Override
            public void clearAll() {
                inventory.clear();
            }

            @Override
            public boolean isFull() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Set<Player> getViewers() {
                return null;
            }

            @Override
            public cn.nukkit.inventory.InventoryType getType() {
                return null;
            }

            @Override
            public InventoryHolder getHolder() {
                return null;
            }

            @Override
            public void onOpen(Player player) {

            }

            @Override
            public boolean open(Player player) {
                return false;
            }

            @Override
            public void close(Player player) {

            }

            @Override
            public void onClose(Player player) {

            }

            @Override
            public void onSlotChange(int i, Item item, boolean b) {

            }
        };
    }
}
