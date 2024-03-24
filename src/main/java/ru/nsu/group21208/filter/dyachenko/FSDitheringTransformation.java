package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.nsu.group21208.filter.dyachenko.ColorUtils.*;

public class FSDitheringTransformation implements ImageTransformation {
    private final int redColors;
    private final int blueColors;
    private final int greenColors;

    public FSDitheringTransformation(int redColors, int greenColors, int blueColors) {
        this.redColors = redColors;
        this.blueColors = blueColors;
        this.greenColors = greenColors;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width + 2, height + 1, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = newImage.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width + 2, height + 1);
        graphics.drawImage(image, 1, 0, null);
        graphics.dispose();
        for (int h = 0; h < height; ++h) {
            int og = newImage.getRGB(1, h);
            int r = getRed(og);
            int g = getGreen(og);
            int b = getBlue(og);
            for (int w = 1; w < width + 1; ++w) {
                int nr = trunc(r, redColors);
                int ng = trunc(g, greenColors);
                int nb = trunc(b, blueColors);
                newImage.setRGB(w, h, combineColors(nr, ng, nb));
                int er = r - nr;
                int eg = g - ng;
                int eb = b - nb;
                int bottom = newImage.getRGB(w, h + 1);
                int bottom_right = newImage.getRGB(w + 1, h + 1);
                int bottom_left = newImage.getRGB(w - 1, h + 1);
                newImage.setRGB(w - 1, h + 1, combineColors(
                        applyError(getRed(bottom_left),3*(er>>4)),
                        applyError(getGreen(bottom_left),3*(eg>>4)),
                        applyError(getBlue(bottom_left),3*(eb>>4)))
                ); // 3/16
                newImage.setRGB(w, h + 1, combineColors(
                        applyError(getRed(bottom),5*(er>>4)),
                        applyError(getGreen(bottom),5*(eg>>4)),
                        applyError(getBlue(bottom),5*(eb>>4)))
                ); // 5/16
                newImage.setRGB(w + 1, h + 1, combineColors(
                        applyError(getRed(bottom_right), er>>4),
                        applyError(getGreen(bottom_right), eg>>4),
                        applyError(getBlue(bottom_right), eb>>4))
                ); // 1/16
                og = newImage.getRGB(w + 1, h);
                r = applyError(getRed(og), 7*(er>>4));
                g = applyError(getGreen(og), 7*(eg>>4));
                b = applyError(getBlue(og), 7*(eb>>4));
                // 7/16
            }
        }

        BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = finalImage.createGraphics();
        graphics.drawImage(newImage, -1, 0, null);
        graphics.dispose();

        return finalImage;
    }
}
