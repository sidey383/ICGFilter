package ru.nsu.group21208.panel.toolbar;

import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleButton<T> extends JToggleButton implements ActionListener {

    private final InteractionToggle<T> interaction;

    private final ToggleActor<T> actor;

    public ToggleButton(ToggleActor<T> actor, InteractionToggle<T> interaction) {
        super();
        setToolTipText(interaction.description());
        Dimension size = new Dimension(40, 40);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setIcon(new ImageIcon(ToolBar.getScaledImage(interaction.actionImage(), new Dimension(40, 40), 10)));
        this.interaction = interaction;
        this.actor = actor;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.toggle(this, actor);
    }
}
