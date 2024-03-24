package ru.nsu.group21208.filter.kernel.edge;

import java.awt.image.BufferedImage;

public class ConstantEdgeSupplier<T> implements EdgeSupplier<T> {

    private final int color;

    public ConstantEdgeSupplier(int color) {
        this.color = color;
    }

    @Override
    public int getEdgeColor(BufferedImage image, int x, int y, T parameters) {
        return color;
    }
}
