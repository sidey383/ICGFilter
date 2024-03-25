package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RotationTransformation implements ImageTransformation {
    private final double angle;
    private final int angle_deg;

    public RotationTransformation(int angle) {
        this.angle = Math.toRadians(-angle);
        angle_deg = -angle;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        if (angle_deg == 90) {
            return rotate90(image);
        }
        else if (angle_deg == 180 || angle_deg == -180) {
            return rotate180(image);
        } else if (angle_deg == -90) {
            return rotate270(image);
        }
        int width = image.getWidth();
        int height = image.getHeight();

        int newWidth = (int) Math.ceil(
                Math.abs(width * Math.cos(angle)) + Math.abs(height * Math.sin(angle))
        );
        int newHeight = (int) Math.ceil(
                Math.abs(width * Math.sin(angle)) + Math.abs(height * Math.cos(angle))
        );
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        double cx = width / 2.0;
        double cy = height / 2.0;
        double newCx = newWidth / 2.0;
        double newCy = newHeight / 2.0;
        double cosTheta = Math.cos(angle);
        double sinTheta = Math.sin(angle);

        for (int y = 0; y < newHeight; ++y) {
            for (int x = 0; x < newWidth; ++x) {
                double ogX = (x - newCx) * cosTheta + (y - newCy) * sinTheta + cx;
                double ogY = -(x - newCx) * sinTheta + (y - newCy) * cosTheta + cy;

                if (ogX >= 0 && ogX < width && ogY >= 0 && ogY < height) {
                    int x1 = (int) ogX;
                    int y1 = (int) ogY;
                    int rgb1 = image.getRGB(x1, y1);
                    newImage.setRGB(x, y, rgb1);
                } else {
                    newImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }

        return newImage;
    }

    public static BufferedImage rotate90(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage result = new BufferedImage(height, width, image.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result.setRGB(height - 1 - j, i, image.getRGB(i, j));
            }
        }

        return result;
    }

    public static BufferedImage rotate180(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage result = new BufferedImage(width, height, image.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result.setRGB(width - 1 - i, height - 1 - j, image.getRGB(i, j));
            }
        }

        return result;
    }

    public static BufferedImage rotate270(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage result = new BufferedImage(height, width, image.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result.setRGB(j, width - 1 - i, image.getRGB(i, j));
            }
        }

        return result;
    }

}
