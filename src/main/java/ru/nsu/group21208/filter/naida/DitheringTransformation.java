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
        BufferedImage newImage = new BufferedImage(width + 2, height + 1, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = newImage.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width + 2, height + 1);
        graphics.drawImage(image, 1, 0, null);
        int[][] redErrorArray = new int[2][width + 2];
        int[][] greenErrorArray = new int[2][width + 2];
        int[][] blueErrorArray = new int[2][width + 2];
        for (int y = 0; y < height; y++) {
            for (int x = 1; x < width + 1; x++) {
                int oldColor = newImage.getRGB(x, y);
                int oldRed = ((oldColor >> 16) & 0xff);
                int oldGreen =((oldColor >> 8) & 0xff);
                int oldBlue = (oldColor & 0xff);
                int newRed = getNearColor((oldRed + redErrorArray[y % 2][x]), redQuantum);
                int newGreen = getNearColor((oldGreen + greenErrorArray[y % 2][x]), greenQuantum);
                int newBlue = getNearColor((oldBlue + blueErrorArray[y % 2][x]), blueQuantum);
                int newColor = 0xff000000 | (newRed << 16) | (newGreen << 8) | newBlue;
                newImage.setRGB(x, y, newColor);
                int redError = oldRed - newRed;
                int greenError = oldGreen - newGreen;
                int blueError = oldBlue - newBlue;

                distributionError(redErrorArray, redError, x, y);
                distributionError(greenErrorArray, greenError, x, y);
                distributionError(blueErrorArray, blueError, x, y);
            }
            Arrays.fill(redErrorArray[y % 2], 0);
            Arrays.fill(greenErrorArray[y % 2], 0);
            Arrays.fill(blueErrorArray[y % 2], 0);
        }

        return newImage.getSubimage(1, 0, width, height);
    }

    private int getNearColor(int value, int quantum) {
        return (value * quantum / 256) * 255 / (quantum - 1);
    }

    private void distributionError(int[][] errorArray, int error, int x, int y) {
        errorArray[y % 2][x + 1] += ((error * 7) >> 4);
        errorArray[(y + 1) % 2][x - 1] += ((error * 3) >> 4);
        errorArray[(y + 1) % 2][x] += ((error * 5) >> 4);
        errorArray[(y + 1) % 2][x + 1] += (error >> 4);
    }
}
