package com.ymeng.tank;

import com.ymeng.tank.manager.PropertyManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameMode {


    Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.GOOD);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> tanks = new ArrayList<>();
    List<Explosion> explosions = new ArrayList<>();

    public GameMode(){
        int initialTankCount = Integer.parseInt((String) PropertyManager.get("initialTankCount"));

        //init enemy tanks
        for(int i = 0; i < initialTankCount; i++) {
            this.tanks.add(new Tank(50 + i * 60, 200, Dir.DOWN, this, Group.BAD));
        }
    }

    public void paint(Graphics g) {
        //draw # of bullets/enemies
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Number of bullets: " + bullets.size(), 10, 60);
        g.setColor(c);

        c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Number of enemies: " + tanks.size(), 10, 80);
        g.setColor(c);

        c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Number of explosions: " + explosions.size(), 10, 100);
        g.setColor(c);
        //draw tank
        myTank.paint(g);
        //draw bullets
        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //draw enemies
        for(int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //explosion
        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).paint(g);
        }
        //if tanks collide with bullets, remove the tanks
        for(int i = 0; i < bullets.size(); i++) {
            for(int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    };
}
