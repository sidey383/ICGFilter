package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.EmptyEditor;
import ru.nsu.group21208.filter.base.EmptyParams;
import ru.nsu.group21208.filter.kernel.KernelFilter;
import ru.nsu.group21208.filter.kernel.edge.CopyEdgeSupplier;

import java.awt.image.BufferedImage;

public class SharpeningFilter extends KernelFilter<EmptyParams> {

    private final CopyEdgeSupplier<?> copy = new CopyEdgeSupplier<>();

    private final int[][] kernel = {
            {0, -1, 0},
            {-1, 8, -1},
            {0, -1, 0}};

    @Override
    public FilterEditor<EmptyParams> createFilterEditor() {
        return EmptyEditor.getInstance();
    }

    @Override
    protected int[][] kernel(EmptyParams parameters) {
        return kernel;
    }

    @Override
    protected int kernelDivider(EmptyParams parameters) {
        return 4;
    }

    @Override
    protected int getEdgeColor(BufferedImage image, int x, int y, EmptyParams parameters) {
        return copy.getEdgeColor(image, x, y, null);
    }

    @Override
    protected boolean isWorkOnEdge(EmptyParams parameters) {
        return true;
    }

    @Override
    public ImageTransformation apply(EmptyParams parameters) {
        return new SharpeningFilterTransformation(parameters);
    }

    public class SharpeningFilterTransformation extends KernelTransformation {
        public SharpeningFilterTransformation(EmptyParams params) {
            super(params);
        }

        @Override
        protected int applyConvolution(BufferedImage image, int x, int y, int[][] kernel, int kernelDivider) {
            return super.applyConvolution(image, x, y, kernel, kernelDivider);
        }
    }
}
