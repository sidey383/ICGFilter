package ru.nsu.group21208.filter.kernel.edge;

import java.awt.image.BufferedImage;

public interface EdgeSupplier<T> {

    int getEdgeColor(BufferedImage image, int x, int y, T parameters);

}
