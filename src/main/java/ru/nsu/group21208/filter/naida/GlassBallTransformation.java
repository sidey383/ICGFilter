package ru.nsu.group21208.filter.naida;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GlassBallTransformation implements ImageTransformation {
    private final double centerX;
    private final double centerY;
    private final double radius;
    private final double strength;

    public GlassBallTransformation(double centerX, double centerY, double radius, double strength) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.strength = strength;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Создание нового изображения для эффекта стеклянного шара
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Параметры эффекта стеклянного шара
        double sphereRadius = Math.min(width, height) * radius; // Радиус шара

        double sphereCenterX = width * centerX;
        double sphereCenterY = height * centerY;

        // Проходимся по каждому пикселю изображения
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Вычисляем расстояние от текущего пикселя до центра изображения
                double distanceToCenter = Math.sqrt(Math.pow(x - sphereCenterX, 2) + Math.pow(y - sphereCenterY, 2));
                if (distanceToCenter > sphereRadius) {
                    newImage.setRGB(x, y, image.getRGB(x, y));
                    continue;
                }
                double distortionFactor = Math.pow(Math.max(0, sphereRadius - distanceToCenter) / sphereRadius, strength);

                // Находим новые координаты пикселя с учетом искажения
                int newX = (int) (x + (x - sphereCenterX) * distortionFactor);
                int newY = (int) (y + (y - sphereCenterY) * distortionFactor);

                newX = Math.min(Math.max(newX, 0), width - 1);
                newY = Math.min(Math.max(newY, 0), height - 1);

                newImage.setRGB(x, y, image.getRGB(newX, newY));
            }
        }
        return newImage;
    }
}
