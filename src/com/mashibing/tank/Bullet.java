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

    //methods
    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) {
            return;
        }
        //TODO: uee only one Rec to record the position of bullets
        Rectangle rectBullet = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGHT);
        if(rectBullet.intersects(rectTank)) {
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
