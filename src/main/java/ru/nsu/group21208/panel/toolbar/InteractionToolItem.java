package ru.nsu.group21208.panel.toolbar;

import ru.nsu.group21208.interaction.Interaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class InteractionToolItem extends JButton implements ActionListener {
    private final Interaction interaction;

    public InteractionToolItem(Interaction interaction) {
        this.interaction = interaction;
        setName(interaction.name());
        Dimension size = new Dimension(40, 40);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setIcon(new ImageIcon(ToolBar.getScaledImage(interaction.actionImage(), size, 10)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.apply(this);
    }
}
