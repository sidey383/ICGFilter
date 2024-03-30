package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;
import ru.nsu.group21208.filter.kernel.KernelFilter;
import ru.nsu.group21208.filter.kernel.edge.CopyEdgeSupplier;

import java.awt.image.BufferedImage;

public class SharpeningFilter extends KernelFilter<BaseFilterParams> {
    private final BaseFilterEditor editor = new BaseFilterEditor();

    private final CopyEdgeSupplier<?> copy = new CopyEdgeSupplier<>();

    private final int[][] kernel = {
            {0, -1, 0},
            {-1, 8, -1},
            {0, -1, 0}};

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    protected int[][] kernel(BaseFilterParams parameters) {
        return kernel;
    }

    @Override
    protected int kernelDivider(BaseFilterParams parameters) {
        return 4;
    }

    @Override
    protected int getEdgeColor(BufferedImage image, int x, int y, BaseFilterParams parameters) {
        return copy.getEdgeColor(image, x, y, null);
    }

    @Override
    protected boolean isWorkOnEdge(BaseFilterParams parameters) {
        return true;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new SharpeningFilterTransformation(parameters);
    }

    protected class SharpeningFilterTransformation extends KernelTransformation {
        public SharpeningFilterTransformation(BaseFilterParams params) {
            super(params);
        }

        @Override
        protected int applyConvolution(BufferedImage image, int x, int y, int[][] kernel, int kernelDivider) {
            return super.applyConvolution(image, x, y, kernel, kernelDivider);
        }
    }
}
