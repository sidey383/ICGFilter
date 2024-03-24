package ru.nsu.group21208.panel.toolBar;

import ru.nsu.group21208.interaction.Interaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionToolItem extends JButton implements ActionListener {
    private final Interaction interaction;

    public InteractionToolItem(Interaction interaction) {
        this.interaction = interaction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.apply(this);
    }
}
