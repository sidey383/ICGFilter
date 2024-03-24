package ru.nsu.group21208.panel;

import ru.nsu.group21208.panel.menubar.MenuBar;
import ru.nsu.group21208.panel.toolbar.ToolBar;

public class Panel {

    private final MenuBar menuBar;

    private final ToolBar toolBar;

    public Panel(PanelInteractionStorage panelInteractionStorage) {
        menuBar = new MenuBar(panelInteractionStorage);
        toolBar = new ToolBar(panelInteractionStorage);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }
}
