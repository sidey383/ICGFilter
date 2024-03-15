package ru.nsu.group21208.interaction;

import ru.nsu.group21208.interaction.toggle.Toggle;
import ru.nsu.group21208.interaction.toggle.ToggleInteraction;

/**
 * Изменение режима отображение картинки<br/>
 * Два доступных состояния для {@link Toggle} реальный режим и адаптивный режим
 * **/
public interface ShowModeInteractions extends Toggle<ShowModeInteractions.Mode> {

    enum Mode {
        REAL, ADAPTABLE
    }

    ToggleInteraction<Mode> realModeInteraction();

    ToggleInteraction<Mode> adaptableModeInteraction();

}
