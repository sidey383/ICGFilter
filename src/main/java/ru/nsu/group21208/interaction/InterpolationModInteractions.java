package ru.nsu.group21208.interaction;

import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.Toggle;

import java.awt.*;

public interface InterpolationModInteractions extends Toggle<InterpolationModInteractions.Mode> {

    enum Mode {
        BILINEAR(RenderingHints.VALUE_INTERPOLATION_BILINEAR), BICUBIC(RenderingHints.VALUE_INTERPOLATION_BICUBIC), NEAREST_NEIGHBOR(RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        private final Object hints;

        Mode(Object hints) {
            this.hints = hints;
        }

        public Object getHints() {
            return hints;
        }

    }

    InteractionToggle<Mode> bilinearInteraction();

    InteractionToggle<Mode> bicubicInteraction();

    InteractionToggle<Mode> nearestNeighborInteraction();

}
