package ru.nsu.group21208.panel.menu;

import ru.nsu.group21208.interaction.ShowModeInteractions;
import ru.nsu.group21208.interaction.toggle.ToggleActor;
import ru.nsu.group21208.interaction.toggle.ToggleInteraction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileButton extends JButton implements ActionListener {

    private final ToggleInteraction<ShowModeInteractions.Mode> interations;

    private final ToggleActor<ShowModeInteractions.Mode> actor;

    public OpenFileButton(ToggleActor<ShowModeInteractions.Mode> actor, ToggleInteraction<ShowModeInteractions.Mode> interation) {
        super("Open");
        this.interations = interation;
        this.actor = actor;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interations.toggle(this, actor);
    }
}
