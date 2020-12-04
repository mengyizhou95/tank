package com.ymeng.tank;

import com.ymeng.tank.manager.PropertyManager;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        TankFrame tf = new TankFrame();

        int initialTankCount = Integer.parseInt((String) PropertyManager.get("initialTankCount"));

        //init enemy tanks
        for(int i = 0; i < initialTankCount; i++) {
            tf.tanks.add(new Tank(50 + i * 60, 200, Dir.DOWN, tf, Group.BAD));
        }

        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
