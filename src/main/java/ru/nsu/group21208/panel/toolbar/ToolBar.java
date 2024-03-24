package ru.nsu.group21208.panel.toolbar;
import ru.nsu.group21208.panel.PanelInteractionStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class ToolBar extends JToolBar {

    public ToolBar(PanelInteractionStorage panelInteractionStorage) {
        ShowModeToggleActor showModeToggleActor = new ShowModeToggleActor(panelInteractionStorage.getShowModeInteractions());
        OriginalToggleActor originalToggleActor = new OriginalToggleActor(panelInteractionStorage.getOriginalToggleInteraction());
        InterpolationModeToggleActor interpolationModeToggleActor = new InterpolationModeToggleActor(panelInteractionStorage.getInterpolationModInteractions());
        new FilterToggleActor<>(panelInteractionStorage.getFilterInteractions(), this);
        FileInteraction fileInteraction = new FileInteraction(panelInteractionStorage.getFileInteractions());

        addButtons(showModeToggleActor.getButtons());
        addButtons(originalToggleActor.getButtons());
        addButtons(interpolationModeToggleActor.getButtons());
        addButtons(fileInteraction.getButtons());

    }

    private <T extends JButton> void addButtons(Collection<T> buttons) {
        for (T button : buttons) {
            add(button);
        }

        add(new Separator());
    }

    public static Image getScaledImage(BufferedImage image, Dimension size, int totalBorder) {
        size.width -= totalBorder;
        size.height -= totalBorder;
        if (size.width > size.height) {
            size.width = -1;
        } else {
            size.height = -1;
        }
        return image.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
    }
}
