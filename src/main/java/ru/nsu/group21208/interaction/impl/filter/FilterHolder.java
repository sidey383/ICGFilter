package ru.nsu.group21208.interaction.impl.filter;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.FilterParams;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;
import java.util.function.Consumer;

public class FilterHolder<T extends FilterParams> implements InteractionToggle<FilterHolder<?>> {

    private final BufferedImage image;

    private final String name;

    private final String description;

    private final Filter<T> filter;

    private final FilterInteractionImpl parent;

    private final Component baseComponent;

    public FilterHolder(FilterInteractionImpl parent, FilterInfo<T> info, Component baseComponent) {
        this.baseComponent = baseComponent;
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
        Consumer<ImageTransformation> consumer;

        try {
            consumer = parent.startTransformation(this, actor);
        } catch (ConcurrentModificationException e) {
            SwingUtilities.invokeLater(() -> actor.toggle(parent.active()));
            return;
        }

        FilterEditor<T> editor = filter.createFilterEditor();
        JComponent editorComponent = editor.parameterEditor();
        if (editorComponent == null) {
            consumer.accept(filter.apply(editor.build()));
            return;
        }

        new FilterDialogHolder<>(baseComponent.getLocation(), component, filter, editor, editorComponent, consumer);
    }
}
