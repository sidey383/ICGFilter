package ru.nsu.group21208.panel.toolbar;

import ru.nsu.group21208.interaction.Interaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionToolItem extends JButton implements ActionListener {
    private final Interaction interaction;

    public InteractionToolItem(Interaction interaction) {
        super();
        this.interaction = interaction;
        setName(interaction.name());
        Dimension size = new Dimension(40, 40);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setIcon(new ImageIcon(ToolBar.getScaledImage(interaction.actionImage(), new Dimension(40, 40), 10)));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.apply(this);
    }
}
