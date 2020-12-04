package com.ymeng.tank.fireStrategy;

import com.ymeng.tank.Bullet;
import com.ymeng.tank.Dir;
import com.ymeng.tank.Tank;

public class FourDirFireStrategy implements FireStrategy{
    private static final FourDirFireStrategy Instance = new FourDirFireStrategy();

    private FourDirFireStrategy() {}

    public static FourDirFireStrategy getInstance() {
        return Instance;
    }

    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for (Dir dir: dirs) {
            new Bullet(bx, by, dir, tank.getTf(), tank.getGroup());
        }
    }
}
