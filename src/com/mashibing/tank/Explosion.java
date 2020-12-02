package com.mashibing.tank;

import java.awt.*;

public class Explosion {
    public  static int WIDTH = ResourceManager.explosion[0].getWidth();
    public  static int HEIGHT = ResourceManager.explosion[0].getHeight();

    private int x, y;

//    private  boolean living = true;
    TankFrame tf = null;

    private int step = 0;

    public Explosion(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explosion[step++], x, y, null);
        if(step >= ResourceManager.explosion.length) {
            tf.explosions.remove(this);
        }
    }
}
