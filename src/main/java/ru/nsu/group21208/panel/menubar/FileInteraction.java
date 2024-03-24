package ru.nsu.group21208.panel.menubar;

import ru.nsu.group21208.interaction.FileInteractions;

import javax.swing.*;

public class FileInteraction extends JMenu {
    public FileInteraction(FileInteractions fileInteractions) {
        add(new InteractionMenuItem(fileInteractions.openFileInteraction()));
        add(new InteractionMenuItem(fileInteractions.saveFileInteraction()));
    }
}
