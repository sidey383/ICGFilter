package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;

public class InvertFilterTransformation implements ImageTransformation {
    @Override
    public BufferedImage transformation(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                int invRgb = (0x00FFFFFF - image.getRGB(j, i)) | 0xFF000000;
                newImg.setRGB(j, i, invRgb);
            }
        }
        return newImg;
    }
}
