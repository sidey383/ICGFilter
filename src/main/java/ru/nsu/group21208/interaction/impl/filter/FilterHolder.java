package ru.nsu.group21208.interaction.impl.filter;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterParams;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;
import java.util.function.Consumer;

public class FilterHolder<T extends FilterParams> implements InteractionToggle<FilterHolder<?>> {

    private final BufferedImage image;

    private final String name;

    private final String description;

    private final Filter<T> filter;

    private final FilterInteractionImpl parent;

    public FilterHolder(FilterInteractionImpl parent, FilterInfo<T> info) {
        this.parent = parent;
        this.image = info.getImage();
        this.name = info.getName();
        this.description = info.getDescription();
        this.filter = info.getFilter();
    }

    @Override
    public @NotNull BufferedImage actionImage() {
        return image;
    }

    @Override
    public @NotNull String name() {
        return name;
    }

    @Override
    public @NotNull String description() {
        return description;
    }

    @Override
    public void toggle(JComponent component, ToggleActor<FilterHolder<?>> actor) {
        try {
            Consumer<ImageTransformation> consumer = parent.startTransformation(this, actor);
            new FilterDialogHolder<>(component, filter, consumer);
        } catch (ConcurrentModificationException e) {
            SwingUtilities.invokeLater(() -> actor.toggle(parent.active()));
        }
    }
}
