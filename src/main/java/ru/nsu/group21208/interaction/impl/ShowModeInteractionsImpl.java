package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.ShowModeInteractions;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;
import ru.nsu.group21208.visualization.ImageFrame;

import javax.swing.*;
import java.util.List;

public class ShowModeInteractionsImpl extends AbstractToggle<ShowModeInteractions.Mode> implements ShowModeInteractions {
    private final ImageFrame imageFrame;

    private final InteractionToggle<Mode> realModeInteraction;

    private final InteractionToggle<Mode> adaptableModeInteraction;

    public ShowModeInteractionsImpl(@NotNull ImageFrame imageFrame, ButtonInfo real, ButtonInfo adaptable) {
        this.imageFrame = imageFrame;
        this.realModeInteraction = new ModeInteractionToggle(real, Mode.REAL);
        this.adaptableModeInteraction = new ModeInteractionToggle(adaptable, Mode.ADAPTABLE);
        setAvailableItems(List.of(Mode.values()), Mode.REAL);
    }
    @Override
    public InteractionToggle<Mode> realModeInteraction() {
        return realModeInteraction;
    }

    @Override
    public InteractionToggle<Mode> adaptableModeInteraction() {
        return adaptableModeInteraction;
    }

    public class ModeInteractionToggle extends InteractionToggleImpl<Mode> {

        private final Mode mode;

        public ModeInteractionToggle(ButtonInfo info, Mode mode) {
            super(info);
            this.mode = mode;
        }

        @Override
        public void toggle(JComponent component, ToggleActor<Mode> actor) {
            ShowModeInteractionsImpl.super.toggle(actor, mode);
            imageFrame.setAdaptive(mode.isAdaptable());
        }
    }

}
