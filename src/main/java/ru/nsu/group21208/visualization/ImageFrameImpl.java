package ru.nsu.group21208.visualization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.filter.ImageTransformation;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageFrameImpl implements ImageFrame {
    private final JScrollPane scrollPane = new JScrollPane();
    private final ImagePanel panel;
    private BufferedImage originalImage = null;
    private BufferedImage modifiedImage = originalImage;
    private ImageTransformation transformation = ImageFrame.identicalImageTransformation();
    private boolean showOriginal = false;

    public ImageFrameImpl() {
        panel = new ImagePanel(scrollPane);
    }

    @Override
    public JComponent getShowComponent() {
        return scrollPane;
    }

    @Override
    public void setOriginalImage(@Nullable BufferedImage image) {
        originalImage = image;
        modifiedImage = transformation.transformation(originalImage);
        setPanelImage();
    }

    @Override
    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    @Override
    public BufferedImage getModifiedImage() {
        return modifiedImage;
    }

    @Override
    public void showOriginal(boolean value) {
        showOriginal = value;
        setPanelImage();
    }

    @Override
    public void setAdaptive(boolean isAdaptive) {
        panel.setAdaptive(isAdaptive);
    }

    @Override
    public void setDraggingEnabled(boolean draggingEnabled) {
        panel.setDraggingEnabled(draggingEnabled);
    }

    @Override
    public void setInterpolationMode(Object interpolationMode) {
        panel.setInterpolationMode(interpolationMode);
    }

    @Override
    public void setImageTransformation(@NotNull ImageTransformation transformation) {
        this.transformation = transformation;
        modifiedImage = transformation.transformation(originalImage);
        setPanelImage();
    }

    private void setPanelImage() {
        if (showOriginal) {
            panel.setImage(originalImage);
        }
        else {
            panel.setImage(modifiedImage);
        }
    }
}
