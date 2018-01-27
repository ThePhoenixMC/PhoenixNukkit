package com.lss233.phoenix.nukkit.utils.nukkit.level;

import com.lss233.phoenix.world.Location;

/**
 *
 */
public interface LocationTransform {
    default Location toPhoenix(cn.nukkit.level.Location location){
        return new Location(toPhoenix(location.level),location.getX(), location.getY(), location.getZ());
    }
}
