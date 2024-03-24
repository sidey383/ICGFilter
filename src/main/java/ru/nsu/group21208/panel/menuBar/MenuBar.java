package ru.nsu.group21208.panel.menuBar;

import ru.nsu.group21208.panel.PanelInteractionStorage;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar(PanelInteractionStorage panelInteractionStorage) {
        ShowModeMenuToggleActor showModeMenuToggleActor = new ShowModeMenuToggleActor(panelInteractionStorage.getShowModeInteractions());
        add(showModeMenuToggleActor);



    }
}
