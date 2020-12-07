package com.ymeng.tank.cor;

import com.ymeng.tank.*;

public class BulletTankCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            if(b.getGroup() == t.getGroup()) {
                return false;
            }
            if(b.rect.intersects(t.rect)) {
                t.die();
                b.die();
                int ex = t.getX() + Tank.WIDTH/2 - Explosion.WIDTH/2;
                int ey = t.getY() + Tank.HEIGHT/2 - Explosion.HEIGHT/2;
                GameModel.getInstance().add(new Explosion(ex, ey, GameModel.getInstance()));
                return false;
            }
        } else if(o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }

        return true;

    }
}
