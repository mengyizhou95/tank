package com.ymeng.tank;

import com.ymeng.tank.fireStrategy.DefaultFireStrategy;
import com.ymeng.tank.fireStrategy.FireStrategy;
import com.ymeng.tank.manager.ResourceManager;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{
    private int x, y;
    private int prevX, prevY;
    private Dir dir = Dir.DOWN;
    private Group group = Group.BAD;
    private static final int SPEED = 5;
    public static final int WIDTH = ResourceManager.tankD.getWidth();
    public static final int HEIGHT = ResourceManager.tankD.getHeight();

    private boolean moving = true;
    private boolean living = true;

    public Rectangle rect = new Rectangle();

    private Random random = new Random();
    private TankFrame tf;
    public GameModel gm;

    public Tank(int x, int y, Dir dir, GameModel gm, Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    //methods
    public void paint(Graphics g) {
        if(!living) {
            gm.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceManager.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        prevX = x;
        prevY = y;

        if(!moving) {
            return;
        }
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

        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire(DefaultFireStrategy.getInstance());
        }
        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        boundCheck();

        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    public void comeBack() {
        x = prevX;
        y = prevY;
    }

    private void boundCheck() {
        if(this.x < 0) {
            x = 0;
        }
        if(this.y < 30) {
            y = 30;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire(FireStrategy fs) {
        fs.fire(this);
    }

    public void die() {
        this.living = false;
    }

    public void stop() {
        this.moving = false;
    }

    //getters & setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }
}
