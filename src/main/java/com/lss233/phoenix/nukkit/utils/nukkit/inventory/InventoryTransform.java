package com.lss233.phoenix.nukkit.utils.nukkit.inventory;

import cn.nukkit.Player;
import cn.nukkit.inventory.BaseInventory;
import cn.nukkit.inventory.InventoryHolder;
import cn.nukkit.item.Item;
import com.lss233.phoenix.item.inventory.Inventory;
import com.lss233.phoenix.item.inventory.InventoryType;
import com.lss233.phoenix.item.inventory.ItemStack;
import com.lss233.phoenix.item.inventory.ItemType;
import com.lss233.phoenix.item.inventory.property.InventoryProperty;
import com.lss233.phoenix.item.inventory.property.InventoryTitle;
import com.lss233.phoenix.text.Text;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
        return new BaseInventory(null, getTransformer().toNukkit(inventory.getInventoryType())) {
            @Override
            public int getSize() {
                return inventory.size();
            }

            @Override
            public void setSize(int size) {
                super.setSize(size);
            }

            @Override
            public int getMaxStackSize() {
                return inventory.getMaxStackSize();
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
            public Item getItem(int index) {
                if(inventory.getItem(index).isPresent())
                    return getTransformer().toNukkit(inventory.getItem(index).get());
                else
                    return Item.get(0);
            }

            @Override
            public Map<Integer, Item> getContents() {
                //TODO I'm sure here will be a bug.
                Map<Integer, Item> contents = new HashMap<>();
                AtomicInteger integer = new AtomicInteger(0);
                inventory.iterator().forEachRemaining(itemStack -> contents.put(integer.getAndIncrement(), getTransformer().toNukkit(itemStack)));
                return contents;
            }

            @Override
            public void setContents(Map<Integer, Item> items) {
                super.setContents(items);
                items.forEach((index, item) -> inventory.setItem(index, getTransformer().toPhoenix(item)));
            }

            @Override
            public boolean setItem(int index, Item item, boolean send) {
                inventory.setItem(index, getTransformer().toPhoenix(item));
                return super.setItem(index, item, send);
            }

            @Override
            public void setMaxStackSize(int maxStackSize) {
                super.setMaxStackSize(maxStackSize);
                inventory.setMaxStackSize(maxStackSize);
            }
        };
    }
}
