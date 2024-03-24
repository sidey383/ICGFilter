package ru.nsu.group21208.panel;

import ru.nsu.group21208.interaction.FileInteractions;
import ru.nsu.group21208.interaction.InterpolationModInteractions;
import ru.nsu.group21208.interaction.OriginalToggleInteraction;
import ru.nsu.group21208.interaction.ShowModeInteractions;
import ru.nsu.group21208.interaction.filter.FilterInteractions;

public class PanelInteractionStorage {
    private ShowModeInteractions showModeInteractions;

    private OriginalToggleInteraction originalToggleInteraction;

    private InterpolationModInteractions interpolationModInteractions;

    private FileInteractions fileInteractions;

    private FilterInteractions<?> filterInteractions;

    public void setFileInteractions(FileInteractions fileInteractions) {
        this.fileInteractions = fileInteractions;
    }

    public void setInterpolationModInteractions(InterpolationModInteractions interpolationModInteractions) {
        this.interpolationModInteractions = interpolationModInteractions;
    }

    public void setOriginalToggleInteraction(OriginalToggleInteraction originalToggleInteraction) {
        this.originalToggleInteraction = originalToggleInteraction;
    }

    public void setShowModeInteractions(ShowModeInteractions showModeInteractions) {
        this.showModeInteractions = showModeInteractions;
    }

    public FileInteractions getFileInteractions() {
        return fileInteractions;
    }

    public InterpolationModInteractions getInterpolationModInteractions() {
        return interpolationModInteractions;
    }

    public OriginalToggleInteraction getOriginalToggleInteraction() {
        return originalToggleInteraction;
    }

    public ShowModeInteractions getShowModeInteractions() {
        return showModeInteractions;
    }

    public FilterInteractions<?> getFilterInteractions() {
        return filterInteractions;
    }

    public void setFilterInteractions(FilterInteractions<?> filterInteractions) {
        this.filterInteractions = filterInteractions;
    }
}
