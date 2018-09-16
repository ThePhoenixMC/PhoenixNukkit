package com.lss233.phoenix.nukkit.utils;

import com.lss233.phoenix.nukkit.utils.nukkit.PlayerTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.VectorTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.entity.EntityHumanTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.entity.EntityTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.inventory.InventoryTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.inventory.InventoryTypeTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.item.ItemTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.item.enchantment.EnchantmentTransform;
import com.lss233.phoenix.nukkit.utils.nukkit.level.ChunkTransform;
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
        EntityHumanTransform,
        PlayerTransform,
        /* level */
        LevelTransform,
        LocationTransform,
        PositionTransform,
        ChunkTransform,
        /* inventory */
        InventoryTransform,
        InventoryTypeTransform,
        /* Others */
        VectorTransform {
}
