package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;

public class GrayFilterTransformation implements ImageTransformation {
    @Override
    public BufferedImage transformation(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                int rgb = image.getRGB(j, i);
                int gray = (int) (((0x00FF0000 & rgb) >> 16) * 0.299 +
                        ((0x0000FF00 & rgb) >> 8) * 0.587 +
                        (0x000000FF & rgb) * 0.114);
                int newRgb = 0xFF000000 | (gray << 16) | (gray << 8) | gray;
                newImg.setRGB(j, i, newRgb);
            }
        }
        return newImg;
    }
}
