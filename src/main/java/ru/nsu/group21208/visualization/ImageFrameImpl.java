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

    public ImageFrameImpl() {
        panel = new ImagePanel(scrollPane);
    }

    @Override
    public JComponent getShowComponent() {
        return scrollPane;
    }

    public ImagePanel getImagePanel() {
        return panel;
    }

    @Override
    public void setOriginalImage(@Nullable BufferedImage image) {
        originalImage = image;
        modifiedImage = transformation.transformation(originalImage);
        panel.setImage(modifiedImage);
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
    public void setAdaptive(boolean isAdaptive) {
        panel.setAdaptive(isAdaptive);
    }

    @Override
    public void setImageTransformation(@NotNull ImageTransformation transformation) {
        this.transformation = transformation;
        modifiedImage = transformation.transformation(originalImage);
        panel.setImage(modifiedImage);
    }
}
