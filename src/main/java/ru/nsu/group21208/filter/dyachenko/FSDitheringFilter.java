package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

public class FSDitheringFilter implements Filter<BaseFilterParams> {
    private final String redColorsName = "Red colors";
    private final String greenColorsName = "Green colors";
    private final String blueColorsName = "Blue colors";

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return new BaseFilterEditor(
                new IntegerParam(redColorsName, 2, 2, 128),
                new IntegerParam(greenColorsName, 2, 2, 128),
                new IntegerParam(blueColorsName, 2, 2, 128)
        );
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new FSDitheringTransformation(
                parameters.getValue(redColorsName, Integer.class),
                parameters.getValue(greenColorsName, Integer.class),
                parameters.getValue(blueColorsName, Integer.class)
        );
    }
}
