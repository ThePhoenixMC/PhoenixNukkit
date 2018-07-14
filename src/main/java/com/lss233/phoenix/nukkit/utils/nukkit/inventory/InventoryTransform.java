package com.lss233.phoenix.nukkit.utils.nukkit.inventory;

import cn.nukkit.item.Item;
import com.lss233.phoenix.item.inventory.Inventory;
import com.lss233.phoenix.item.inventory.InventoryType;
import com.lss233.phoenix.item.inventory.ItemStack;
import com.lss233.phoenix.item.inventory.ItemType;
import com.lss233.phoenix.item.inventory.property.InventoryProperty;
import com.lss233.phoenix.text.Text;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
}
