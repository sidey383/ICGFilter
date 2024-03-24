package ru.nsu.group21208.panel.toolBar;

import ru.nsu.group21208.panel.PanelInteractionStorage;

import javax.swing.*;

public class ToolBar extends JToolBar {

    public ToolBar(PanelInteractionStorage panelInteractionStorage) {
        ShowModeToolToggleActor showModeToolToggleActor = new ShowModeToolToggleActor(panelInteractionStorage.getShowModeInteractions());
        add(showModeToolToggleActor);



    }
}
