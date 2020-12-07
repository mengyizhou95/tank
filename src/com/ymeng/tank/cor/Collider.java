package com.ymeng.tank.cor;

import com.ymeng.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
