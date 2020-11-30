package test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImageTest {

    @Test
    public void test() {
        BufferedImage image = null, image2 = null;
        try {
            image = ImageIO.read(new File("/Users/meng/IdeaProjects/tank/src/images/0.gif"));
            image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("/images/0.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(image);
        assertNotNull(image2);
    }
}
