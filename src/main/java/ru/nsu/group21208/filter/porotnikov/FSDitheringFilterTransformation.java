package ru.nsu.group21208.filter.porotnikov;

import ru.nsu.group21208.filter.ImageTransformation;
import java.awt.image.BufferedImage;

public class FSDitheringFilterTransformation implements ImageTransformation {
    private final int quantRed;
    private final int quantGreen;
    private final int quantBlue;

    public FSDitheringFilterTransformation(int quantRed, int quantGreen, int quantBlue) {
        this.quantRed = quantRed;
        this.quantGreen = quantGreen;
        this.quantBlue = quantBlue;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        int[][] errBufR = new int[h + 1][w + 2];
        int[][] errBufG = new int[h + 1][w + 2];
        int[][] errBufB = new int[h + 1][w + 2];
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                int oldRgb = image.getRGB(j, i);
                int oldR = getR(oldRgb);
                int oldG = getG(oldRgb);
                int oldB = getB(oldRgb);

                int newR = findClosest(oldR + errBufR[i][j + 1], quantRed);
                int newG = findClosest(oldG + errBufG[i][j + 1], quantGreen);
                int newB = findClosest(oldB + errBufB[i][j + 1], quantBlue);
                int newRgb = rgb(newR, newG, newB);
                newImg.setRGB(j, i, newRgb);

                int errR = oldR + errBufR[i][j + 1] - newR;
                int errG = oldG + errBufG[i][j + 1] - newG;
                int errB = oldB + errBufB[i][j + 1] - newB;
                error(errBufR, errR, j + 1, i);
                error(errBufG, errG, j + 1, i);
                error(errBufB, errB, j + 1, i);
            }
        }

        return newImg;
    }

    private void error(int[][] errBuf, int err, int x, int y) {
        errBuf[y    ][x + 1] += err * 7 / 16;
        errBuf[y + 1][x    ] += err * 5 / 16;
        errBuf[y + 1][x - 1] += err * 3 / 16;
        errBuf[y + 1][x + 1] += err / 16;
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

    private int findClosest(int color, int quant) {
        if (color < 0) {
            return 0;
        }
        if (color > 255) {
            return 255;
        }
        return (color * quant / 256) * 255 / (quant - 1);
    }
}
