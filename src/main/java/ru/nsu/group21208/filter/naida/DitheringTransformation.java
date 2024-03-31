package ru.nsu.group21208.filter.naida;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class DitheringTransformation implements ImageTransformation {
    private final int redQuantum;
    private final int greenQuantum;
    private final int blueQuantum;

    public DitheringTransformation(int redQuantum, int greenQuantum, int blueQuantum) {
        this.redQuantum = redQuantum;
        this.greenQuantum = greenQuantum;
        this.blueQuantum = blueQuantum;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = newImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);

        colorDithering(newImage, 0x00ff0000, 16, redQuantum, width, height);
        colorDithering(newImage, 0x0000ff00, 8, greenQuantum, width, height);
        colorDithering(newImage, 0x000000ff, 0, blueQuantum, width, height);

        return newImage;
    }

    private void colorDithering(BufferedImage newImage, int colorMask, int shift, int quantum, int width, int height) {
        int[][] errorArray = new int[height + 1][width + 2];
        int j = getNearColor(82, 3);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int oldColor = newImage.getRGB(x, y);
                int oldColorComponent = ((oldColor & colorMask) >> shift);
                int oldColorComponentWithError = oldColorComponent + errorArray[y][x + 1];
                int newColorComponent = getColor(getNearColor(oldColorComponentWithError, quantum));

                int error = oldColorComponentWithError - newColorComponent;

                int newColor = (oldColor & (~colorMask)) | (newColorComponent << shift);
                newImage.setRGB(x, y, newColor);

                distributionError(errorArray, error, x, y);
            }
        }
    }

    private int getNearColor(int value, int quantum) {
        return (((value * (quantum - 1) + 128) / 255) * 255) / (quantum - 1);
    }

    private int getColor(int value) {
        if (value < 0) {
            return 0;
        }
        else if (value > 255) {
            return 255;
        }
        else {
            return value;
        }
    }

    private void distributionError(int[][] errorArray, int error, int x, int y) {
        errorArray[y][x + 2] += ((error * 7) >> 4);
        errorArray[y + 1][x] += ((error * 3) >> 4);
        errorArray[y + 1][x + 1] += ((error * 5) >> 4);
        errorArray[y + 1][x + 2] += (error >> 4);
    }
}
