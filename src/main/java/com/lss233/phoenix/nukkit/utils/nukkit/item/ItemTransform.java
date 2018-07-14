package com.lss233.phoenix.nukkit.utils.nukkit.item;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import com.lss233.phoenix.data.key.Key;
import com.lss233.phoenix.data.key.Keys;
import com.lss233.phoenix.item.inventory.ItemStack;
import com.lss233.phoenix.item.inventory.ItemType;
import com.lss233.phoenix.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

public interface ItemTransform {
    default ItemStack toPhoenix(cn.nukkit.item.Item item){
        return new ItemStack() {
            @Override
            public ItemType getType() {
                return ItemStackHelper.getItemType(item.getId());
            }

            @Override
            public void setType(ItemType itemType) {
                //Unsupported ?.
            }

            @Override
            public int getQuantity() {
                return item.getCount();
            }

            @Override
            public void setQuantity(int i) {
                item.setCount(i);
            }

            @Override
            public int getMaxStackQuantity() {
                return item.getMaxStackSize();
            }

            @Override
            public <E> Optional<E> get(Key<E> key) {
                if(key.equals(Keys.ITEM_ENCHANTMENTS))
                    return Optional.of((E)Arrays.stream(item.getEnchantments())
                            .map(i -> getTransformer().toPhoenix(i))
                            .collect(Collectors.toList()));
                 else if(key.equals(Keys.ITEM_LORE))
                    return Optional.of((E)Arrays.stream(item.getLore())
                            .map(Text::of)
                            .collect(Collectors.toList()));
                else if(key.equals(Keys.ITEM_DURABILITY))
                    return Optional.of((E)Integer.valueOf(item.getDamage()));
                else if(key.equals(Keys.DISPLAY_NAME))
                    return Optional.of((E)Text.of(item.getCustomName()));
                return Optional.empty();
            }

            @Override
            public <E> void set(Key<E> key, E e) {
                if(key.equals(Keys.DISPLAY_NAME))
                    item.setCustomName(e.toString());
                else if(key.equals(Keys.ITEM_DURABILITY))
                    item.setDamage((Integer)e);
                else if(key.equals(Keys.ITEM_LORE))
                    item.setLore(((List<Text>) e).stream().map(Text::toString).toArray(String[]::new));
                else if(getKeys().equals(Keys.ITEM_ENCHANTMENTS)){
                    Enchantment[] enchantments = ((List<com.lss233.phoenix.item.enchantment.Enchantment>) e).stream().map(i -> getTransformer().toNukkit(i)).toArray(Enchantment[]::new);
                    CompoundTag tag;
                    if (!item.hasCompoundTag()) {
                        tag = new CompoundTag();
                    } else {
                        tag = item.getNamedTag();
                    }
                    ListTag ench = new ListTag("ench");
                    for(int var6 = 0; var6 < enchantments.length; ++var6) {
                        ench.add(var6, (new CompoundTag()).putShort("id", enchantments[var6].getId()).putShort("lvl", enchantments[var6].getLevel()));
                    }
                    tag.putList(ench);
                    item.setCompoundTag(tag);

                }
            }
        };
    }
    default Item toNukkit(ItemStack itemStack){
        return new Item(ItemStackHelper.getItemId(itemStack.getType()), 0, itemStack.getQuantity()){
            @Override
            public Item setLore(String... lines) {
                itemStack.set(Keys.ITEM_LORE, Arrays.stream(lines).map(Text::of).collect(Collectors.toList()));
                return super.setLore(lines);
            }

            @Override
            public Item setCustomName(String name) {
                itemStack.set(Keys.DISPLAY_NAME, Text.of(name));
                return super.setCustomName(name);
            }

            @Override
            public void setDamage(Integer meta) {
                itemStack.set(Keys.ITEM_DURABILITY, meta);
                super.setDamage(meta);
            }

            @Override
            public void addEnchantment(Enchantment... enchantments) {
                super.addEnchantment(enchantments);
                itemStack.set(Keys.ITEM_ENCHANTMENTS, Arrays.stream(super.getEnchantments())
                        .map(i -> getTransformer().toPhoenix(i))
                        .collect(Collectors.toList()));
            }

            @Override
            public Enchantment[] getEnchantments() {
                return itemStack.get(Keys.ITEM_ENCHANTMENTS)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(i -> getTransformer().toNukkit(i))
                        .toArray(Enchantment[]::new);
            }
        };
    }
}
