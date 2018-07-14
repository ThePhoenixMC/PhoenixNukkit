package com.lss233.phoenix.nukkit.utils;

import com.lss233.phoenix.nukkit.utils.nukkit.PlayerTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.entity.EntityTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.inventory.InventoryTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.item.ItemTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.item.enchantment.EnchantmentTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.level.LevelTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.level.LocationTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.level.PositionTransform;

/**
 *
 */
public interface Transform extends
        /* item */
        ItemTransform,
        /* item.enchantment */
        EnchantmentTransform,
        /* entity */
        EntityTransform,
        PlayerTransform,
        /* level */
        LevelTransform,
        LocationTransform,
        PositionTransform,
        /* inventory */
        InventoryTransform {
}
