package ru.nsu.group21208.panel.menuBar;

import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.interaction.ShowModeInteractions;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;

public class ShowModeMenuToggleActor extends JMenu implements ToggleActor<ShowModeInteractions.Mode> {
    private final MenuButton<ShowModeInteractions.Mode> realModeButton;
    private final MenuButton<ShowModeInteractions.Mode> adaptableModeButton;
    private final ButtonGroup buttonGroup;

    public ShowModeMenuToggleActor(ShowModeInteractions showModeToggle) {
        showModeToggle.addToggleActor(this);
        realModeButton = new MenuButton<>(this, showModeToggle.realModeInteraction());
        adaptableModeButton = new MenuButton<>(this, showModeToggle.adaptableModeInteraction());

        buttonGroup = new ButtonGroup();

        buttonGroup.add(realModeButton);
        buttonGroup.add(adaptableModeButton);

        add(realModeButton);
        add(adaptableModeButton);
    }

    @Override
    public void toggle(ShowModeInteractions.@Nullable Mode item) {
        if (item == null) {
            buttonGroup.clearSelection();
            return;
        }

        switch (item) {
            case REAL:
                buttonGroup.setSelected(realModeButton.getModel(), true);
                break;
            case ADAPTABLE:
                buttonGroup.setSelected(adaptableModeButton.getModel(), true);
                break;
        }
    }
}
