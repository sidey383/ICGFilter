package ru.nsu.group21208.filter.kernel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.FilterParams;
import ru.nsu.group21208.filter.kernel.edge.EdgeSupplier;

import java.awt.image.BufferedImage;
import java.util.function.Function;

public class KernelFilterInserter<T extends FilterParams> extends KernelFilter<T> {

    private final FilterEditor<T> editor;

    private final Function<T, int[][]> kernel;

    private final Function<T, Integer> divider;

    private final EdgeSupplier<T> edge;

    public KernelFilterInserter(@NotNull FilterEditor<T> editor, @NotNull Function<T, int[][]> kernel, @NotNull Function<T, Integer> divider, @Nullable EdgeSupplier<T> edge) {
        this.editor = editor;
        this.kernel = kernel;
        this.divider = divider;
        this.edge = edge;
    }

    @Override
    public FilterEditor<T> createFilterEditor() {
        return editor;
    }

    @Override
    protected int[][] kernel(T parameters) {
        return kernel.apply(parameters);
    }

    @Override
    protected int kernelDivider(T parameters) {
        return divider.apply(parameters);
    }

    @Override
    protected int getEdgeColor(BufferedImage image, int x, int y, T parameters) {
        if (edge == null)
            throw new UnsupportedOperationException();
        return edge.getEdgeColor(image, x, y, parameters);
    }

    @Override
    protected boolean isWorkOnEdge() {
        return edge != null;
    }

}
