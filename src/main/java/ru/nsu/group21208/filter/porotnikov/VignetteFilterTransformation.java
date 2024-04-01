package ru.nsu.group21208.filter.porotnikov;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;

public class VignetteFilterTransformation implements ImageTransformation {
    private final int sigma;

    VignetteFilterTransformation(int sigma) {
        this.sigma = sigma;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        double[] normalVert = new double[h];
        double[] normalHor = new double[w];
        for (int i = 0; i < h; ++i) {
            normalVert[i] = Math.exp(-Math.pow((i - (h - 1) / 2.0), 2) / (2 * sigma * sigma));
        }
        for (int i = 0; i < w; ++i) {
            normalHor[i] = Math.exp(-Math.pow((i - (w - 1) / 2.0), 2) / (2 * sigma * sigma));
        }
        double[][] normalVertHor = new double[h][w];
        double m = 0;
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                normalVertHor[i][j] = normalVert[i] * normalHor[j];
                if (normalVertHor[i][j] > m) {
                    m = normalVertHor[i][j];
                }
            }
        }
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                normalVertHor[i][j] /= m;
            }
        }
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                int rgb = image.getRGB(j, i);
                int r = (int) (((rgb >> 16) & 0xFF) * normalVertHor[i][j]);
                int g = (int) (((rgb >> 8) & 0xFF) * normalVertHor[i][j]);
                int b = (int) ((rgb & 0xFF) * normalVertHor[i][j]);
                int newRgb = ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
                newImg.setRGB(j, i, newRgb);
            }
        }
        return newImg;
    }
}
