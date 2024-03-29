package ru.nsu.group21208.interaction;

import ru.nsu.group21208.interaction.toggle.Toggle;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;

/**
 * Изменение режима отображение картинки<br/>
 * Два доступных состояния для {@link Toggle} реальный режим и адаптивный режим
 * **/
public interface ShowModeInteractions extends Toggle<ShowModeInteractions.Mode> {

    enum Mode {
        REAL(false), ADAPTABLE(true);

        private final boolean isAdaptable;

        Mode(boolean isAdaptable) {
            this.isAdaptable = isAdaptable;
        }

        public boolean isAdaptable() {
            return isAdaptable;
        }

    }

    InteractionToggle<Mode> realModeInteraction();

    InteractionToggle<Mode> adaptableModeInteraction();

}
