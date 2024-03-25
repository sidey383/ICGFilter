package ru.nsu.group21208.filter.ponomarev;

import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.OptionChooseParam;
import ru.nsu.group21208.filter.kernel.KernelFilter;
import ru.nsu.group21208.filter.kernel.edge.CopyEdgeSupplier;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmbossingFilter extends KernelFilter<BaseFilterParams> {

    private final BaseFilterEditor editor;

    private final CopyEdgeSupplier<?> copy = new CopyEdgeSupplier<>();

    enum Directions {
        LEFT(-1, 0), RIGHT(1, 0), TOP(0, -1), BOTTOM(0, 1),
        LEFT_TOP(-1, -1), RIGHT_TOP(1, -1),
        LEFT_BOTTOM(-1, 1), RIGHT_BOTTOM(1, 1);

        public final int x;

        public final int y;

        Directions(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public EmbossingFilter() {
        editor = new BaseFilterEditor(
                        new OptionChooseParam(
                                "Direction",
                                Directions.LEFT_BOTTOM,
                                List.of(Directions.values()),
                                Stream.of(Directions.values()).map(Enum::name).collect(Collectors.toList())
                        )
                );
    }

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    protected int[][] kernel(BaseFilterParams parameters) {
        Directions dir = parameters.getValue("Direction", Directions.class);
        int[][] kernel = new int[3][3];
        kernel[dir.x+1][dir.y+1] = 1;
        kernel[-dir.x+1][-dir.y+1] = -1;
        return kernel;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new EmbossingTransformation(parameters);
    }

    @Override
    protected int kernelDivider(BaseFilterParams parameters) {
        return 1;
    }

    @Override
    protected int getEdgeColor(BufferedImage image, int x, int y, BaseFilterParams parameters) {
        return copy.getEdgeColor(image, x, y, null);
    }

    @Override
    protected boolean isWorkOnEdge(BaseFilterParams parameters) {
        return true;
    }

    protected class EmbossingTransformation extends KernelTransformation {

        public EmbossingTransformation(BaseFilterParams params) {
            super(params);
        }

        @Override
        protected int applyConvolution(BufferedImage image, int x, int y, int[][] kernel, int kernelDivider) {
            int rgb = super.applyConvolution(image, x, y, kernel, kernelDivider);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;
            r = Math.min(Math.max(r + 128, 0), 255);
            g = Math.min(Math.max(g + 128, 0), 255);
            b = Math.min(Math.max(b + 128, 0), 255);
            return (r << 16) | (g << 8) | b;
        }
    }


}
