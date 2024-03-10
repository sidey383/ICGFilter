package ru.nsu.group21208.interaction;

import ru.nsu.group21208.interaction.toggle.Toggle;

/**
 * Изменение режима отображение картинки<br/>
 * Два доступных состояния для {@link Toggle} реальный режим и адаптивный режим
 * **/
public interface ShowModeInteractions<T extends Interaction> extends Toggle<T> {

    /**
     * @return константа
     * **/
    Interaction realModeInteraction();

    /**
     * @return константа
     * **/
    Interaction adaptableModeInteraction();

}
