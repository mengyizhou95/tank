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

    GameMode gm = new GameMode();

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
        gm.paint(g);

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
                    gm.getMyTank().fire(DefaultFireStrategy.getInstance());
                    break;

                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bl && !br && !bu && !bd) {
                gm.getMyTank().setMoving(false);
            }
            else{
                gm.getMyTank().setMoving(true);

                if(bl) gm.getMyTank().setDir(Dir.LEFT);
                if(br) gm.getMyTank().setDir(Dir.RIGHT);
                if(bu) gm.getMyTank().setDir(Dir.UP);
                if(bd) gm.getMyTank().setDir(Dir.DOWN);
            }
        }
    }
}
