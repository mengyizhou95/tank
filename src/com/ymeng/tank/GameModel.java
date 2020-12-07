package com.ymeng.tank;

import com.ymeng.tank.cor.ColliderChain;
import com.ymeng.tank.manager.PropertyManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static final GameModel Instance = new GameModel();
    public static GameModel getInstance() {
        return Instance;
    }

    ColliderChain colliderChain = new ColliderChain();

    Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.GOOD);
//    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> tanks = new ArrayList<>();
//    List<Explosion> explosions = new ArrayList<>();

    private List<GameObject> objects = new ArrayList<>();

    private GameModel(){
        int initialTankCount = Integer.parseInt((String) PropertyManager.get("initialTankCount"));

        //init enemy tanks
        for(int i = 0; i < initialTankCount; i++) {
            this.add(new Tank(50 + i * 60, 200, Dir.DOWN, this, Group.BAD));
        }
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        //draw # of bullets/enemies
//        Color c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("Number of bullets: " + bullets.size(), 10, 60);
//        g.setColor(c);
//
//        c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("Number of enemies: " + tanks.size(), 10, 80);
//        g.setColor(c);
//
//        c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("Number of explosions: " + explosions.size(), 10, 100);
//        g.setColor(c);
        //draw tank
        myTank.paint(g);
        //draw objects
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //collision
        for(int i = 0; i < objects.size(); i++) { //comparator.compare(o1, o2)
            for(int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                colliderChain.collide(o1, o2);
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    };
}
