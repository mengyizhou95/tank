package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private int x, y;
    private Dir dir;
    private Group group = Group.BAD;
    private  boolean living = true;
    private TankFrame tf;
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceManager.bulletD.getWidth();
    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    Rectangle rect = new Rectangle();

    //methods
    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if(!living) {
            tf.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) {
            return;
        }
        if(rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.WIDTH/2 - Explosion.WIDTH/2;
            int ey = tank.getY() + Tank.HEIGHT/2 - Explosion.HEIGHT/2;
            tf.explosions.add(new Explosion(ex, ey, tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
