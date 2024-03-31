package ru.nsu.group21208.filter.naida;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

public class OrderedDitheringFilter  implements Filter<BaseFilterParams> {
    private final String redName = "red color";
    private final String greenName = "green color";
    private final String blueName = "blue color";
    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return new BaseFilterEditor(
                new IntegerParam(redName, 2, 2, 128),
                new IntegerParam(greenName, 2, 2, 128),
                new IntegerParam(blueName, 2, 2, 128)
        );
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new OrderedDitheringTransformation(
                parameters.getValue(redName, Integer.class),
                parameters.getValue(greenName, Integer.class),
                parameters.getValue(blueName, Integer.class)
        );
    }
}
