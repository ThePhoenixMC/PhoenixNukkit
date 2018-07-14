package com.lss233.phoenix.nukkit.utils.nukkit.level;

import cn.nukkit.Server;
import com.lss233.phoenix.world.Location;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

/**
 *
 */
public interface LocationTransform {
    default Location toPhoenix(cn.nukkit.level.Location location){
        return new Location(getTransformer().toPhoenix(location.getLevel()),location.getX(), location.getY(), location.getZ());
    }
    default cn.nukkit.level.Location toNukkit(Location location){
        return new cn.nukkit.level.Location(location.getX(),
                location.getY(),
                location.getZ(),
                Server.getInstance().getLevelByName(location.getWorld().getName()));
    }
}
