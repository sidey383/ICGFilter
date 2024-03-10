package ru.nsu.group21208.visualization;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Основной компонент отображения.<br/>
 * Создает и модифицирует {@link Component}, отображающий редактируемое изображение.<br/>
 * Также содержит оригинальное и модифицированное изображение.
 * **/
public interface ImageFrame {

    /**
     * Возвращает готовый компонент, содержащий изображение в пунктирных рамках
     * **/
    Component getShowComponent();

    void setOriginalImage(BufferedImage image);

    BufferedImage getOriginalImage();

    BufferedImage getModifiedImage();

    void setAdaptive(boolean isAdaptive);

    void setImageTransformation(ImageTransformation transformation);

    static ImageTransformation identicalImageTransformation() {
        return (bi) -> {
            ColorModel cm = bi.getColorModel();
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            WritableRaster raster = bi.copyData(null);
            return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        };
    }

}
