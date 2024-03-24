package ru.nsu.group21208.panel.toolBar;

import ru.nsu.group21208.interaction.FileInteractions;
import ru.nsu.group21208.panel.menuBar.InteractionMenuItem;

import javax.swing.*;

public class FileInteraction extends JComponent {
    public FileInteraction(FileInteractions fileInteractions) {
        add(new InteractionToolItem(fileInteractions.openFileInteraction()));
        add(new InteractionToolItem(fileInteractions.saveFileInteraction()));
    }
}
