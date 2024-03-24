package ru.nsu.group21208.panel.menuBar;

import ru.nsu.group21208.interaction.Interaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionMenuItem extends JMenuItem implements ActionListener {
    private final Interaction interaction;

    public InteractionMenuItem(Interaction interaction) {
        this.interaction = interaction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.apply(this);
    }
}
