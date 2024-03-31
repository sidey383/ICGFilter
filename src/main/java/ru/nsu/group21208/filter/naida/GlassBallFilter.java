package ru.nsu.group21208.filter.naida;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.DoubleParam;
import ru.nsu.group21208.filter.base.param.IntegerParam;

public class GlassBallFilter implements Filter<BaseFilterParams> {
    private final String centerX = "center x";
    private final String centerY = "center y";
    private final String radius = "radius";
    private final String strength = "strength";
    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return new BaseFilterEditor(
                new DoubleParam(centerX, 0.5, 0, 1, 100),
                new DoubleParam(centerY, 0.5, 0, 1, 100),
                new DoubleParam(radius, 0.25, 0.1, 1, 100),
                new DoubleParam(strength, 0.5, 0.2, 0.6, 100)
        );
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new GlassBallTransformation(
                parameters.getValue(centerX, Double.class),
                parameters.getValue(centerY, Double.class),
                parameters.getValue(radius, Double.class),
                parameters.getValue(strength, Double.class)
        );
    }
}
