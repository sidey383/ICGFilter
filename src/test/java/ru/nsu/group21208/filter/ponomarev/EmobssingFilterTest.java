package ru.nsu.group21208.filter.ponomarev;

import ru.nsu.group21208.filter.ImageTransformation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EmobssingFilterTest {

    public static void main(String[] args) throws IOException {
        File f = new File("test.jpg");
        BufferedImage image = ImageIO.read(f);
        EmbossingFilter filter = new EmbossingFilter();
        ImageTransformation transformation = filter.apply(filter.createFilterEditor().build());
        image = transformation.transformation(image);
        ImageIO.write(image, "jpg", new File("result.jpg"));
    }

}
