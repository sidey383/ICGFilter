package ru.nsu.group21208.panel.toolbar;

import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton<T> extends JButton implements ActionListener {

    private final InteractionToggle<T> interaction;

    private final ToggleActor<T> actor;

    public ToolButton(ToggleActor<T> actor, InteractionToggle<T> interaction) {
        super();
        setName(interaction.name());
        setToolTipText(interaction.description());
        setIcon(new ImageIcon(ToolBar.getScaledImage(interaction.actionImage(), new Dimension(40, 40), 10)));
        this.interaction = interaction;
        this.actor = actor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.toggle(this, actor);
    }
}
