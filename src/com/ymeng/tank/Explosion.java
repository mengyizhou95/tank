package com.ymeng.tank;

import com.ymeng.tank.manager.ResourceManager;

import java.awt.*;

public class Explosion extends GameObject{
    public  static int WIDTH = ResourceManager.explosion[0].getWidth();
    public  static int HEIGHT = ResourceManager.explosion[0].getHeight();

    private int x, y;

//    private  boolean living = true;
    TankFrame tf = null;
    private GameModel gm;

    private int step = 0;

    public Explosion(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explosion[step++], x, y, null);
        if(step >= ResourceManager.explosion.length) {
            gm.remove(this);
        }
    }
}
