package ru.nsu.group21208.panel.toolbar;
import ru.nsu.group21208.panel.PanelInteractionStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class ToolBar extends JToolBar {

    public ToolBar(PanelInteractionStorage panelInteractionStorage) {
        FileInteraction fileInteraction = new FileInteraction(panelInteractionStorage.getFileInteractions());
        addButtons(fileInteraction.getButtons());

        OriginalToggleActor originalToggleActor = new OriginalToggleActor(panelInteractionStorage.getOriginalToggleInteraction());
        addButtons(originalToggleActor.getButtons());

        ShowModeToggleActor showModeToggleActor = new ShowModeToggleActor(panelInteractionStorage.getShowModeInteractions());
        addButtons(showModeToggleActor.getButtons());

        InterpolationModeToggleActor interpolationModeToggleActor = new InterpolationModeToggleActor(panelInteractionStorage.getInterpolationModInteractions());
        addButtons(interpolationModeToggleActor.getButtons());

        new FilterToggleActor<>(panelInteractionStorage.getFilterInteractions(), this);
    }

    private <T extends AbstractButton> void addButtons(Collection<T> buttons) {
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
