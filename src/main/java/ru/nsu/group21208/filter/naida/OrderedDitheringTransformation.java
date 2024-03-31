package ru.nsu.group21208.filter.naida;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OrderedDitheringTransformation implements ImageTransformation {
    private final int redQuantum;
    private final int greenQuantum;
    private final int blueQuantum;

    public OrderedDitheringTransformation(int redQuantum, int greenQuantum, int blueQuantum) {
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

        colorOrderedDithering(newImage, 0x00ff0000, 16, redQuantum, width, height);
        colorOrderedDithering(newImage, 0x0000ff00, 8, greenQuantum, width, height);
        colorOrderedDithering(newImage, 0x000000ff, 0, blueQuantum, width, height);

        return newImage;
    }

    private void colorOrderedDithering(BufferedImage newImage, int colorMask, int shift, int quantum, int width, int height) {
        final int[][] matrix = MAT_2X2.clone();
        int matrixSize = 2;

        prepareMatrix(matrix, quantum);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int oldColor = newImage.getRGB(x, y);
                int oldColorComponent = ((oldColor & colorMask) >> shift);

                int matrixValue = (256 * matrix[x % matrixSize][y % matrixSize] - 128 * (matrixSize * matrixSize)) / (matrixSize * matrixSize);
                int newColorComponent = getNearColor(getColor(oldColorComponent + matrixValue), quantum);
                int newColor = (oldColor & (~colorMask)) | (newColorComponent << shift);
                newImage.setRGB(x, y, newColor);
            }
        }
    }

    private void prepareMatrix(int[][] matrix, int quantum) {
        int n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                //matrix[j][i] =
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

    private final int[][] MAT_2X2 = {
            { 0, 2 },
            { 3, 1 }
    };
    private final int[][] MAT_4X4 = {
            {  0,  8,  2, 10 },
            { 12,  4, 14,  6 },
            {  3, 11,  1,  9 },
            { 15,  7, 13,  5 }
    };
}
