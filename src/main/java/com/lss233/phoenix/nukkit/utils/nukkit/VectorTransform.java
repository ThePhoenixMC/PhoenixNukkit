package com.lss233.phoenix.nukkit.utils.nukkit;

import cn.nukkit.math.Vector3;
import com.lss233.phoenix.math.Vector;

public interface VectorTransform {
    default Vector toPhoenix(Vector3 vector3){
        return new Vector(vector3.getX(), vector3.getY(), vector3.getZ());
    }

    /*
    Use `toNukkit(Position).asVector3f().asVector3();` instead.
    default Vector3 toNukkit(Vector vector){
        return new Vector3(vector.getX(), vector.getY(), vector.getZ());
    }
    */
}
