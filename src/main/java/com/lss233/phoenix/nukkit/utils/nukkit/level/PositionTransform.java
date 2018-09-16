package com.lss233.phoenix.nukkit.utils.nukkit.level;

import cn.nukkit.level.Position;
import com.lss233.phoenix.math.Vector;

/**
 *
 */
public interface PositionTransform {
    default Position toNukkit(Vector location){
        return new Position(location.getX(), location.getY(), location.getZ());
    }

    default Vector toPhoenix(Position location){
        return new Vector(location.getX(), location.getY(), location.getZ());
    }
}
