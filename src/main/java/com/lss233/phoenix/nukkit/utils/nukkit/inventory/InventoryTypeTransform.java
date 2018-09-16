package com.lss233.phoenix.nukkit.utils.nukkit.inventory;

import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.item.inventory.InventoryType;

public interface InventoryTypeTransform {
    default InventoryType toPhoenix(cn.nukkit.inventory.InventoryType inventoryType){
        switch (inventoryType){

            case CHEST:
            case ENDER_CHEST:
            case SHULKER_BOX:
                return InventoryType.CHEST;
            case DOUBLE_CHEST:
                return InventoryType.DOUBLE_CHEST;
            case PLAYER:
                return InventoryType.PLAYER;
            case FURNACE:
                return InventoryType.FURNACE;
            case CRAFTING:
                return InventoryType.CRAFTING;
            case WORKBENCH:
                return InventoryType.WORKBENCH;
            case BREWING_STAND:
                return InventoryType.BREWING_STAND;
            case ANVIL:
                return InventoryType.ANVIL;
            case ENCHANT_TABLE:
                return InventoryType.ENCHANTING_TABLE;
            case DISPENSER:
            case DROPPER:
                return InventoryType.DISPENSER;
            case HOPPER:
                return InventoryType.HOPPER;
            case BEACON:
                return InventoryType.BEACON;
            default:
                Phoenix.getLogger("Phoenix").warn("Unsupported InventoryType to Phoenix: " + inventoryType.toString());
                return InventoryType.CHEST;
        }
    }

    default cn.nukkit.inventory.InventoryType toNukkit(InventoryType inventoryType){
        switch (inventoryType){

            case ANVIL:
                return cn.nukkit.inventory.InventoryType.ANVIL;
            case BEACON:
                return cn.nukkit.inventory.InventoryType.BEACON;
            case BREWING_STAND:
                return cn.nukkit.inventory.InventoryType.BREWING_STAND;
            case CHEST:
                return cn.nukkit.inventory.InventoryType.CHEST;
            case CRAFTING:
                return cn.nukkit.inventory.InventoryType.CRAFTING;
            case DISPENSER:
                return cn.nukkit.inventory.InventoryType.DISPENSER;
            case DOUBLE_CHEST:
                return cn.nukkit.inventory.InventoryType.DOUBLE_CHEST;
            case ENCHANTING_TABLE:
                return cn.nukkit.inventory.InventoryType.ENCHANT_TABLE;
            case FURNACE:
                return cn.nukkit.inventory.InventoryType.FURNACE;
            case HOPPER:
                return cn.nukkit.inventory.InventoryType.HOPPER;
            case PLAYER:
                return cn.nukkit.inventory.InventoryType.PLAYER;
            case WORKBENCH:
                return cn.nukkit.inventory.InventoryType.WORKBENCH;
            default:
                Phoenix.getLogger("Phoenix").warn("Unsupported InventoryType to Nukkit: " + inventoryType.toString());
                return cn.nukkit.inventory.InventoryType.CHEST;
        }
    }
}
