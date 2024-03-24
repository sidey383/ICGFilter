package ru.nsu.group21208.filter.kernel.edge;

import java.awt.image.BufferedImage;

public class CopyEdgeSupplier<T> implements EdgeSupplier<T> {
    @Override
    public int getEdgeColor(BufferedImage image, int x, int y, T parameters) {
        return image.getRGB(
                Math.min(Math.max(x, 0), image.getWidth() - 1),
                Math.min(Math.max(y, 0), image.getHeight() - 1)
        );
    }
}
