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
        setIcon(new ImageIcon(ToolBar.getScaledImage(interaction.actionImage(), new Dimension(40, 40), 10)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.apply(this);
    }
}
