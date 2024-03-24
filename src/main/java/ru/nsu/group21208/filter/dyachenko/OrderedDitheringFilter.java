package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.DoubleParam;
import ru.nsu.group21208.filter.base.param.IntegerParam;

public class OrderedDitheringFilter implements Filter<BaseFilterParams> {
    private final String redColorsName = "Red colors";
    private final String greenColorsName = "Green colors";
    private final String blueColorsName = "Blue colors";
    private final String gammaName = "Gamma correction";
    private final BaseFilterEditor editor = new BaseFilterEditor(
            new IntegerParam(redColorsName, 2, 2, 128),
            new IntegerParam(greenColorsName, 2, 2, 128),
            new IntegerParam(blueColorsName, 2, 2, 128),
            new DoubleParam(gammaName, 1, 0.1, 10, 1000)
    );

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new OrderedDitheringTransformation(
                parameters.getValue(redColorsName, Integer.class),
                parameters.getValue(greenColorsName, Integer.class),
                parameters.getValue(blueColorsName, Integer.class),
                parameters.getValue(gammaName, Double.class)
        );
    }
}
