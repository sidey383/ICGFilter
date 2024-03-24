package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    private int getRed(int color) {
        int RED_MASK = 0x00ff0000;
        return (color & RED_MASK) >> 16;
    }
    private int getGreen(int color) {
        int GREEN_MASK = 0x0000ff00;
        return (color & GREEN_MASK) >> 8;
    }
    private int getBlue(int color) {
        int BLUE_MASK = 0x000000ff;
        return (color & BLUE_MASK);
    }
    private int combineColors(int r, int g, int b) {
        return 0xff000000 | (r << 16) | (g << 8) | b;
    }
    private int trunc(int value, int grads) {
        return (value * grads / 256) * 255 / (grads - 1);
    }
    private int applyError(int c, int err) {
        int res = c + err;
        if (res < 0) {
            return 0;
        }
        else if ((res & ~0xff) != 0) {
            return 0xff;
        }
        else {
            return res;
        }
    }
}
