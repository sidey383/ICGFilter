package ru.nsu.group21208.filter.porotnikov;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;

public class OrderedDitheringFilterTransformation implements ImageTransformation {
    private final int redQuant;
    private final int greenQuant;
    private final int blueQuant;
    private int[][] matrixR;
    private int[][] matrixG;
    private int[][] matrixB;

    private final int[][] M2X2 = {
            {0, 2},
            {3, 1}};

    private final int[][] M4X4 = {
            {0, 8, 2, 10},
            {12, 4, 14, 6},
            {3, 11, 1, 9},
            {15, 7, 13, 5}};

    private final int[][] M8X8 = {
            {0, 32, 8, 40, 2, 34, 10, 42},
            {48, 16, 56, 24, 50, 18, 58, 26},
            {12, 44, 4, 36, 14, 46, 6, 38},
            {60, 28, 52, 20, 62, 30, 54, 22},
            {3, 35, 11, 43, 1, 33, 9, 41},
            {51, 19, 59, 27, 49, 17, 57, 25},
            {15, 47, 7, 39, 13, 45, 5, 37},
            {63, 31, 55, 23, 61, 29, 53, 21}
    };

    public OrderedDitheringFilterTransformation(int rq, int gq, int bq) {
        redQuant = rq;
        greenQuant = gq;
        blueQuant = bq;

        matrixR = M2X2;
        if (rq < 64) {
            matrixR = M4X4;
        }
        if (rq < 4) {
            matrixR = M8X8;
        }

        matrixG = M2X2;
        if (gq < 64) {
            matrixG = M4X4;
        }
        if (gq < 4) {
            matrixG = M8X8;
        }

        matrixB = M2X2;
        if (bq < 64) {
            matrixB = M4X4;
        }
        if (bq < 4) {
            matrixB = M8X8;
        }
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        int rSize = matrixR.length;
        int gSize = matrixG.length;
        int bSize = matrixB.length;
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                int oldRgb = image.getRGB(j, i);
                int oldR = getR(oldRgb);
                int oldG = getG(oldRgb);
                int oldB = getB(oldRgb);

                int newR = findClosest((int) (oldR + 256 / (redQuant - 1) *
                        (matrixR[i % rSize][j % rSize] - rSize * rSize * 0.5) / (rSize * rSize)), redQuant);
                int newG = findClosest((int) (oldG + 256 / (greenQuant - 1) *
                        (matrixG[i % gSize][j % gSize] - 0.5 * gSize * gSize) / (gSize * gSize)), greenQuant);
                int newB = findClosest((int) (oldB + 256 / (blueQuant - 1) *
                        (matrixB[i % bSize][j % bSize] - 0.5 * bSize * bSize) / (bSize * bSize)), blueQuant);

                newImg.setRGB(j, i, rgb(newR, newG, newB));
            }
        }
        return newImg;
    }

    private int findClosest(int color, int quant) {
        if (color < 0) {
            return 0;
        }
        if (color > 255) {
            return 255;
        }
        return (color * quant / 256) * 255 / (quant - 1);
    }

    private int rgb(int r, int g, int b) {
        return 0xFF000000 | ((r & 0x000000FF) << 16) | ((g & 0x000000FF) << 8) | (b & 0x000000FF);
    }

    private int getR(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }

    private int getG(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    private int getB(int rgb) {
        return (rgb & 0x000000FF);
    }
}
