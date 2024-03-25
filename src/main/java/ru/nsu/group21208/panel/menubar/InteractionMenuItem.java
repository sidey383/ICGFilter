package ru.nsu.group21208.panel.menubar;

import ru.nsu.group21208.interaction.Interaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionMenuItem extends JMenuItem implements ActionListener {
    private final Interaction interaction;

    public InteractionMenuItem(Interaction interaction) {
        super(interaction.name());
        this.interaction = interaction;
        setName(interaction.name());
        setToolTipText(interaction.description());
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.apply(this);
    }
}
