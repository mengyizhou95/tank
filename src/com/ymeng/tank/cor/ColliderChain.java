package com.ymeng.tank.cor;

import com.ymeng.tank.GameObject;
import com.ymeng.tank.cor.BulletTankCollider;
import com.ymeng.tank.cor.Collider;
import com.ymeng.tank.cor.TankTankCollider;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
    private List<Collider> colliderList = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }

    public void add(Collider c) {
        colliderList.add(c);
    }

    public void remove(Collider c) {
        colliderList.remove(c);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for(Collider c: colliderList) {
            if(!c.collide(o1 ,o2)) {
                return false;
            }
        }
        return true;
    }
}
