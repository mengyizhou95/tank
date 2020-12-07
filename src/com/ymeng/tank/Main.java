package com.ymeng.tank;

import com.ymeng.tank.manager.PropertyManager;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        TankFrame tf = new TankFrame();

        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
