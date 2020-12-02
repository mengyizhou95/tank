package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceManager {
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explosion = new BufferedImage[16];
    static {
        try {
            tankL = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/tankL.gif"));
            tankU = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/tankU.gif"));
            tankR = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/tankR.gif"));
            tankD = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/tankD.gif"));

            bulletL = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/bulletL.gif"));
            bulletU = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/bulletU.gif"));
            bulletR = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/bulletR.gif"));
            bulletD = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/bulletD.gif"));

            for(int i = 0; i < explosion.length; i++) {
                explosion[i] = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
