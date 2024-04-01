package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RobertsTransformation implements ImageTransformation {
    private final double threshold;

    public RobertsTransformation(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = Filter.copyOfImage(image);
        Graphics g = newImage.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        applyForColor(image, newImage, ColorUtils::getRed, ColorUtils::setRed, threshold);
        applyForColor(image, newImage, ColorUtils::getGreen, ColorUtils::setGreen, threshold);
        applyForColor(image, newImage, ColorUtils::getBlue, ColorUtils::setBlue, threshold);
        return newImage;
    }

    private static void applyForColor(
            BufferedImage og, BufferedImage nw,
            Function<Integer, Integer> get,
            BiFunction<Integer, Integer, Integer> set,
            double threshold)
    {
        final int MAX = 255 * 2;
        final int THRESHOLD = (int) (threshold * MAX);
        int width = og.getWidth();
        int height = og.getHeight();
        int value = 0;
        for (int h = 0; h < height-1; ++h) {
            for (int w = 0; w < width-1; ++w) {
                value = Math.abs(get.apply(og.getRGB(w, h)) - get.apply(og.getRGB(w + 1, h + 1)));
                value += Math.abs(get.apply(og.getRGB(w + 1, h)) - get.apply(og.getRGB(w, h + 1)));
                int rgb = nw.getRGB(w,h) | 0xff000000;
                if (value > THRESHOLD) {
                    nw.setRGB(w, h, set.apply(rgb, 0xff));
                }
                else {
                    nw.setRGB(w, h, set.apply(rgb, 0));
                }
            }
        }
    }
}
