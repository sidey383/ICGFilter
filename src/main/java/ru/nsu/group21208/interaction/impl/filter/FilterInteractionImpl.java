package ru.nsu.group21208.interaction.impl.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.filter.FilterParams;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.interaction.filter.FilterInteractions;
import ru.nsu.group21208.interaction.filter.FilterInteractionsGroup;
import ru.nsu.group21208.interaction.impl.AbstractToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;
import ru.nsu.group21208.visualization.ImageFrame;

import java.util.*;
import java.util.function.Consumer;

public class FilterInteractionImpl extends AbstractToggle<FilterHolder<?>> implements FilterInteractions<FilterHolder<?>> {

    private final ImageFrame frame;

    private final Collection<FilterHolder<?>> filterHolders;

    private final Collection<FilterInteractionsGroup<FilterHolder<?>>> interactionGroups;

    private FilterHolder<?> active;

    private FilterHolder<?> edited;

    public FilterInteractionImpl(ImageFrame frame, Collection<FilterGroup> groups) {
        this.frame = frame;
        List<FilterHolder<?>> filterHolders = new ArrayList<>();
        List<FilterInteractionsGroup<FilterHolder<?>>> interactionGroups = new ArrayList<>();
        for (FilterGroup group : groups) {
            FilterInteractionsGroup<FilterHolder<?>> interactionsGroup = new FilterInteractionGroupImpl(this, group, frame.getShowComponent());
            interactionGroups.add(interactionsGroup);
            filterHolders.addAll(interactionsGroup.filterInteractions());
        }
        setAvailableItems(filterHolders, null);
        this.filterHolders = filterHolders;
        this.interactionGroups = interactionGroups;
    }

    @Override
    public Collection<FilterInteractionsGroup<FilterHolder<?>>> filterGroups() {
        return interactionGroups;
    }

    @Override
    public Collection<FilterHolder<?>> filterInteractions() {
        return filterHolders;
    }

    @Override
    public void addToggleActor(@NotNull ToggleActor<FilterHolder<?>> actor) {
        super.addToggleActor(actor);
    }

    @Override
    public void toggle(@Nullable ToggleActor<FilterHolder<?>> source, @Nullable FilterHolder<?> item) {
        super.toggle(source, item);
    }

    @NotNull
    public synchronized <T extends FilterParams> Consumer<ImageTransformation> startTransformation(FilterHolder<T> holder, ToggleActor<FilterHolder<?>> actor) throws ConcurrentModificationException {
        if (edited != null)
            throw new ConcurrentModificationException();
        edited = holder;
        return (t) -> {
            synchronized (FilterInteractionImpl.this) {
                edited = null;
                if (t != null) {
                    active = holder;
                    frame.setImageTransformation(Objects.requireNonNullElseGet(t, ImageFrame::identicalImageTransformation));
                    toggle(actor, holder);
                } else {
                    actor.toggle(active);
                }
            }
        };
    }

    @Nullable
    public synchronized FilterHolder<?> active() {
        return active;
    }

}
