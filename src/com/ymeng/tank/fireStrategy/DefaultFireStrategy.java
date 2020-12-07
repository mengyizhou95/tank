package com.ymeng.tank.fireStrategy;

import com.ymeng.tank.Bullet;
import com.ymeng.tank.Tank;

public class DefaultFireStrategy implements FireStrategy{

    private static final DefaultFireStrategy Instance = new DefaultFireStrategy();

    private DefaultFireStrategy() {}

    public static DefaultFireStrategy getInstance() {
        return Instance;
    }

    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        new Bullet(bx, by, tank.getDir(), tank.gm, tank.getGroup());
    }
}
