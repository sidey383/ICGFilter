package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class WaterColorFilterTransformation implements ImageTransformation {
    private final int winSize;

    WaterColorFilterTransformation(int winSize) {
        this.winSize = winSize;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                int startY = Math.max(i - winSize / 2, 0);
                int stopY = Math.min(i + winSize / 2 + 1, h);
                int startX = Math.max(j - winSize / 2, 0);
                int stopX = Math.min(j + winSize / 2 + 1, w);
                int[] tempArrR = new int[winSize * winSize];
                int[] tempArrG = new int[winSize * winSize];
                int[] tempArrB = new int[winSize * winSize];
                for (int y = startY; y < stopY; ++y) {
                    for (int x = startX; x < stopX; ++x) {
                        int rgb = image.getRGB(x, y);
                        int pos = 5 * (x - startX) + y - startY;
                        tempArrR[pos] = (rgb & 0x00FF0000) >> 16;
                        tempArrG[pos] = (rgb & 0x0000FF00) >> 8;
                        tempArrB[pos] = rgb & 0x000000FF;
                    }
                }
                Arrays.sort(tempArrR);
                Arrays.sort(tempArrG);
                Arrays.sort(tempArrB);
                int newRgb = ((tempArrR[winSize / 2] & 0xFF) << 16) | ((tempArrG[winSize / 2] & 0xFF) << 8) |
                        (tempArrB[winSize / 2] & 0xFF);
                newImg.setRGB(j, i, newRgb);
            }
        }
        SharpeningFilter sf = new SharpeningFilter();
        newImg = sf.apply(null).transformation(newImg);
        return newImg;
    }
}
