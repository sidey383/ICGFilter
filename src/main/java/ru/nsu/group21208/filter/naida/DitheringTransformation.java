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
        int[][] redErrorArray = new int[width + 2][2];
        int[][] greenErrorArray = new int[width + 2][2];
        int[][] blueErrorArray = new int[width + 2][2];
        for (int y = 0; y < height; y++) {
            for (int x = 1; x < width + 1; x++) {
                int oldColor = newImage.getRGB(x, y);
                byte oldRed = (byte) ((oldColor >> 16) & 0x000000ff);
                byte oldGreen = (byte) ((oldColor >> 16) & 0x000000ff);
                byte oldBlue = (byte) oldColor;
                byte newRed = getNearColor((byte) (oldRed + redErrorArray[x][y % 2]), (byte) redQuantum);
                byte newGreen = getNearColor((byte) (oldGreen + greenErrorArray[x][y % 2]), (byte) greenQuantum);
                byte newBlue = getNearColor((byte) (oldBlue + blueErrorArray[x][y % 2]), (byte) blueQuantum);
                int newColor = 0xff000000 | (newRed << 16) | (newGreen << 8) | newBlue;
                newImage.setRGB(x, y, newColor);
                int redError = oldRed - newRed;
                int greenError = oldGreen - newGreen;
                int blueError = oldBlue - newBlue;

                distributionError(redErrorArray, redError, x);
                distributionError(greenErrorArray, greenError, x);
                distributionError(blueErrorArray, blueError, x);
            }
            Arrays.fill(redErrorArray[y % 2], 0);
        }

        return newImage.getSubimage(1, 0, width, height);
    }

    private byte getNearColor(byte value, byte quantum) {
        return (byte) (((value * quantum / 256) * 255) / (quantum - 1));
    }

    private void distributionError(int[][] errorArray, int error, int x) {
        errorArray[x + 1][0] += ((error * 7) >> 4);
        errorArray[x - 1][1] += ((error * 3) >> 4);
        errorArray[x][1] += ((error * 5) >> 4);
        errorArray[x + 1][1] += (error >> 4);
    }
}
