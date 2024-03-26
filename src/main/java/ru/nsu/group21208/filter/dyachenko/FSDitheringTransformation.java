package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;
import java.util.function.BiFunction;
import java.util.function.Function;

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
        BufferedImage newImage = Filter.copyOfImage(image);
        ditherColor(image, newImage, ColorUtils::getRed, ColorUtils::setRed, redColors);
        ditherColor(newImage, newImage, ColorUtils::getGreen, ColorUtils::setGreen, greenColors);
        ditherColor(newImage, newImage, ColorUtils::getBlue, ColorUtils::setBlue, blueColors);
        return newImage;
    }

    private static void ditherColor(
            BufferedImage og, BufferedImage nw,
            Function<Integer, Integer> get,
            BiFunction<Integer, Integer, Integer> set,
            int tones
    ) {
        int width = og.getWidth();
        int height = og.getHeight();
        int[][] errors = new int[height + 1][width + 2];
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                int rgb = og.getRGB(w, h);
                int c = applyError(get.apply(rgb), errors[h][w + 1]);
                int newC = trunc(c, tones);
                int err = c - newC;
                nw.setRGB(w, h, set.apply(rgb, newC));
                errors[h+1][w] += 3 * (err >> 4);
                errors[h+1][w+1] += 5 * (err >> 4);
                errors[h+1][w+2] += (err >> 4);
                errors[h][w+2] += 7 * (err >> 4);
            }
        }
    }
}
