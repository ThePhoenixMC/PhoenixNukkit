package com.lss233.phoenix.nukkit.utils.nukkit.item.enchantment;

import com.lss233.phoenix.item.enchantment.Enchantment;
import com.lss233.phoenix.item.enchantment.EnchantmentType;

public interface EnchantmentTransform {
    default Enchantment toPhoenix(cn.nukkit.item.enchantment.Enchantment enchantment){
        return new Enchantment() {
            @Override
            public int getLevel() {
                return enchantment.getLevel();
            }

            @Override
            public EnchantmentType getType() {
                return EnchantmentTypeHelper.getEnchantmentType(enchantment.id);
            }
        };
    }
    default cn.nukkit.item.enchantment.Enchantment toNukkit(Enchantment enchantment){
        return cn.nukkit.item.enchantment.Enchantment.get(EnchantmentTypeHelper.getEnchantmentId(enchantment.getType())).setLevel(enchantment.getLevel());
    }
}
