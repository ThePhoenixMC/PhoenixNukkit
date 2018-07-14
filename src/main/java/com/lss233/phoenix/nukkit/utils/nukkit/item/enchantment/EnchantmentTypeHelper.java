package com.lss233.phoenix.nukkit.utils.nukkit.item.enchantment;

import com.lss233.phoenix.item.enchantment.EnchantmentType;


public class EnchantmentTypeHelper {
    private static final EnchantmentType[] enchantmentTypes = new EnchantmentType[27];
    static {
        enchantmentTypes[0]  = EnchantmentType.PROTECTION;
        enchantmentTypes[1]  = EnchantmentType.FIRE_PROTECTION;
        enchantmentTypes[2]  = EnchantmentType.FEATHER_FALLING;
        enchantmentTypes[3]  = EnchantmentType.BLAST_PROTECTION;
        enchantmentTypes[4]  = EnchantmentType.PROJECTILE_PROTECTION;
        enchantmentTypes[5]  = EnchantmentType.THORNS;
        enchantmentTypes[6]  = EnchantmentType.RESPIRATION;
        enchantmentTypes[7]  = EnchantmentType.DEPTH_STRIDER;
        enchantmentTypes[8]  = EnchantmentType.AQUA_AFFINITY;
        enchantmentTypes[9]  = EnchantmentType.SHARPNESS;
        enchantmentTypes[10] = EnchantmentType.SMITE;
        enchantmentTypes[11] = EnchantmentType.BANE_OF_ARTHROPODS;
        enchantmentTypes[12] = EnchantmentType.KNOCKBACK;
        enchantmentTypes[13] = EnchantmentType.FIRE_ASPECT;
        enchantmentTypes[14] = EnchantmentType.LOOTING;
        enchantmentTypes[15] = EnchantmentType.EFFICIENCY;
        enchantmentTypes[16] = EnchantmentType.SILK_TOUCH;
        enchantmentTypes[17] = EnchantmentType.UNBREAKING;
        enchantmentTypes[18] = EnchantmentType.FORTUNE;
        enchantmentTypes[19] = EnchantmentType.POWER;
        enchantmentTypes[20] = EnchantmentType.PUNCH;
        enchantmentTypes[21] = EnchantmentType.FLAME;
        enchantmentTypes[22] = EnchantmentType.INFINITY;
        enchantmentTypes[23] = EnchantmentType.LUCK_OF_THE_SEA;
        enchantmentTypes[24] = EnchantmentType.LURE;
        enchantmentTypes[25] = EnchantmentType.FROST_WALKER;
        enchantmentTypes[26] = EnchantmentType.MENDING;

    }

    /**
     * Gets a EnchantmentType by id.
     * See <a href="https://minecraft.gamepedia.com/Enchanting#Summary_of_enchantments">here</a>.
     * @param id The id of a enchantment type
     * @return The Enchantment type
     */
    public static EnchantmentType getEnchantmentType(int id) {
        if(id >= enchantmentTypes.length)
            return null;
        else
            return enchantmentTypes[id];
    }

    /**
     * Gets the id of a EnchantmentType.
     * If the type is unsupported in Bedrock Edition, it will return -1.
     * @param enchantmentType The enchantment type
     * @return The id of enchantment type in Bedrock Edition if supported. Or -1 if unsupported.
     */
    public static int getEnchantmentId(EnchantmentType enchantmentType){
        for (int i = 0; i < enchantmentTypes.length; i++) {
            if(enchantmentTypes[i].equals(enchantmentType))
                return i;
        }
        return -1;
    }
}
