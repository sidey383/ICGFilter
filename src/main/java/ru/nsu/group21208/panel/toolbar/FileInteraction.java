package ru.nsu.group21208.panel.toolbar;

import ru.nsu.group21208.interaction.FileInteractions;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Vector;

public class FileInteraction {
    private Collection<JButton> buttons = new Vector<>();

    public FileInteraction(FileInteractions fileInteractions) {
        buttons.add(new InteractionToolItem(fileInteractions.openFileInteraction()));
        buttons.add(new InteractionToolItem(fileInteractions.saveFileInteraction()));
    }

    public Collection<JButton> getButtons() {
        return buttons;
    }
}
