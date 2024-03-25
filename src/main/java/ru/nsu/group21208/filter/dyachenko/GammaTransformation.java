package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;

import static ru.nsu.group21208.filter.dyachenko.ColorUtils.*;
import static ru.nsu.group21208.filter.dyachenko.ColorUtils.combineColors;

public class GammaTransformation implements ImageTransformation {
    private final double gamma;

    public GammaTransformation(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int red = getRed(rgb);
                int green = getGreen(rgb);
                int blue = getBlue(rgb);

                red = (int) (255 * Math.pow(red / 255.0, gamma));
                green = (int) (255 * Math.pow(green / 255.0, gamma));
                blue = (int) (255 * Math.pow(blue / 255.0, gamma));

                int newPixel = combineColors(red, green, blue);

                newImage.setRGB(x, y, newPixel);
            }
        }

        return newImage;
    }
}
