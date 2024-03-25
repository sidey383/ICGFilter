package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.OptionChooseParam;
import ru.nsu.group21208.filter.kernel.KernelFilter;
import ru.nsu.group21208.filter.kernel.edge.CopyEdgeSupplier;
import ru.nsu.group21208.filter.ponomarev.EmbossingFilter;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlurFilter extends KernelFilter<BaseFilterParams> {
    private final CopyEdgeSupplier<?> copy = new CopyEdgeSupplier<>();

    private static final int[][] MAT3X3 = new int[][]{
            {1,2,1},
            {2,4,2},
            {1,2,1}
    };

    private static final int[][] MAT5X5 = new int[][]{
            {1,4,7,4,1},
            {4,16,26,16,4},
            {7,26,41,26,7},
            {4,16,26,16,4},
            {1,4,7,4,1}
    };

    private static final int[][] MAT7X7 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1}
    };

    private static final int[][] MAT9X9 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private static final int[][] MAT11X11 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private enum Dims {
        M3X3(16, MAT3X3),
        M5X5(273, MAT5X5),
        M7X7(49, MAT7X7),
        M9X9(9, MAT9X9),
        M11X11(11, MAT11X11);

        public final int divider;
        public final int[][] mat;
        Dims(int d, int[][] m) {
            divider = d;
            mat = m;
        }
    }
    private static BaseFilterEditor editor = new BaseFilterEditor(
            new OptionChooseParam(
                    "Dims",
                    Dims.M3X3,
                    List.of(Dims.values()),
                    Stream.of(Dims.values()).map(Enum::name).collect(Collectors.toList())
            )
    );

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    protected int[][] kernel(BaseFilterParams parameters) {
        return parameters.getValue("Dims", Dims.class).mat;
    }

    @Override
    protected int kernelDivider(BaseFilterParams parameters) {
        return parameters.getValue("Dims", Dims.class).divider;
    }

    @Override
    protected int getEdgeColor(BufferedImage image, int x, int y, BaseFilterParams parameters) {
        return copy.getEdgeColor(image, x, y, null);
    }

    @Override
    protected boolean isWorkOnEdge(BaseFilterParams parameters) {
        return true;
    }
}
