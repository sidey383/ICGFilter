package ru.nsu.group21208.panel.toolBar;

import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.interaction.ShowModeInteractions;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;

public class ShowModeToolToggleActor extends JToolBar implements ToggleActor<ShowModeInteractions.Mode> {
    private final ToolButton<ShowModeInteractions.Mode> realModeButton;
    private final ToolButton<ShowModeInteractions.Mode> adaptableModeButton;
    private final ButtonGroup buttonGroup;

    public ShowModeToolToggleActor(ShowModeInteractions showModeToggle) {
        showModeToggle.addToggleActor(this);
        realModeButton = new ToolButton<>(this, showModeToggle.realModeInteraction());
        adaptableModeButton = new ToolButton<>(this, showModeToggle.adaptableModeInteraction());

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
