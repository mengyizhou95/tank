package com.ymeng.tank;

import com.ymeng.tank.fireStrategy.DefaultFireStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame{

    Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.GOOD);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explosion> explosions = new ArrayList<>();

    static final int GAME_WIDTH = 1080;
    static final int GAME_HEIGHT = 960;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //override update to solve the flicker on screen
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics goffScreen = offScreenImage.getGraphics();
        Color c = goffScreen.getColor();
        goffScreen.setColor(Color.BLACK);
        goffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        goffScreen.setColor(c);
        paint(goffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
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

    class MyKeyListener extends KeyAdapter {

        boolean bl = false;
        boolean bu = false;
        boolean br = false;
        boolean bd = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                //move
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                //fire
                case KeyEvent.VK_SPACE:
                    myTank.fire(DefaultFireStrategy.getInstance());
                    break;

                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bl && !br && !bu && !bd) {
                myTank.setMoving(false);
            }
            else{
                myTank.setMoving(true);

                if(bl) myTank.setDir(Dir.LEFT);
                if(br) myTank.setDir(Dir.RIGHT);
                if(bu) myTank.setDir(Dir.UP);
                if(bd) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
