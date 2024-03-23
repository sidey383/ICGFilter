package ru.nsu.group21208.visualization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.filter.ImageTransformation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Основной компонент отображения.<br/>
 * Создает и модифицирует {@link JComponent}, отображающий редактируемое изображение.<br/>
 * Также содержит оригинальное и модифицированное изображение.
 * **/
public interface ImageFrame {

    /**
     * Возвращает готовый компонент, содержащий изображение в пунктирных рамках
     * **/
    JComponent getShowComponent();

    void setOriginalImage(@Nullable BufferedImage image);

    BufferedImage getOriginalImage();

    BufferedImage getModifiedImage();

    /**
     * Sets adaptive mode <br>
     * If adaptive, then image is fully visible on panel<br>
     * If it is not, then picture is shown pixel-to-pixel
     * @param isAdaptive - default value is false
     */
    void setAdaptive(boolean isAdaptive);

    /**
     * Sets dragging mode, if panel is dragging, than user can drag picture with LMB
     * @param draggingEnabled - default value is true
     */
    void setDraggingEnabled(boolean draggingEnabled);

    /**
     * Sets interpolation method that will be used for resampling image when adaptive mode is enabled
     * <br> Values should be one of {@link RenderingHints}'s VALUE_INTERPOLATION_* values:
     * <br> RenderingHints.VALUE_INTERPOLATION_BILINEAR
     * <br> RenderingHints.VALUE_INTERPOLATION_BICUBIC
     * <br> RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
     * @param interpolationMode - default value is VALUE_INTERPOLATION_BILINEAR
     */
    void setInterpolationMode(Object interpolationMode);

    void setImageTransformation(@NotNull ImageTransformation transformation);

    static ImageTransformation identicalImageTransformation() {
        return (bi) -> {
            ColorModel cm = bi.getColorModel();
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            WritableRaster raster = bi.copyData(null);
            return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        };
    }
}
