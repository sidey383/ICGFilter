package ru.nsu.group21208.visualization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.interaction.OriginalToggleInteraction;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class ImageFrameImpl implements ImageFrame, ToggleActor<OriginalToggleInteraction.Mode>, MouseListener {
    private final JScrollPane scrollPane = new JScrollPane();
    private final ImagePanel panel;
    private BufferedImage originalImage = null;
    private BufferedImage modifiedImage = originalImage;
    private ImageTransformation transformation = ImageFrame.identicalImageTransformation();
    private boolean showOriginal = false;

    private OriginalToggleInteraction interaction;

    public ImageFrameImpl() {
        panel = new ImagePanel(scrollPane);
        panel.addMouseListener(this);
    }

    @Override
    public JComponent getShowComponent() {
        return scrollPane;
    }

    @Override
    public void setOriginalToggle(OriginalToggleInteraction interaction) {
        this.interaction = interaction;
        interaction.addToggleActor(this);
    }

    @Override
    public void toggle(OriginalToggleInteraction.@Nullable Mode item) {}

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
        if (originalImage != null) {
            modifiedImage = transformation.transformation(originalImage);
            setPanelImage();
        }
    }

    private void setPanelImage() {
        if (showOriginal) {
            panel.setImage(originalImage);
        }
        else {
            panel.setImage(modifiedImage);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (interaction == null)
            return;
        if (showOriginal) {
            interaction.filterInteraction().toggle(panel, this);
        } else {
            interaction.originalInteraction().toggle(panel, this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
