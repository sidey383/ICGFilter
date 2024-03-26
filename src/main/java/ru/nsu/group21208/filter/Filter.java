package ru.nsu.group21208.filter;

import java.awt.image.BufferedImage;

public interface Filter<T extends FilterParams> {

    FilterEditor<T> createFilterEditor();

    ImageTransformation apply(T parameters);

    /**
     * Создает копию изображения без картинки
     * **/
    static BufferedImage copyOfImage(BufferedImage image) {
        return new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
    }

}
