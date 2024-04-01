package ru.nsu.group21208.filter.porotnikov;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

public class OrderedDitheringFilter implements Filter<BaseFilterParams> {
    private final String redQuant = "Red quant";
    private final String greenQuant = "Green quant";
    private final String blueQuant = "Blue quant";
    private final BaseFilterEditor editor = new BaseFilterEditor(
            new IntegerParam(redQuant, 3, 2, 128),
            new IntegerParam(greenQuant, 3, 2, 128),
            new IntegerParam(blueQuant, 3, 2, 128)
    );
    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new OrderedDitheringFilterTransformation(
                parameters.getValue(redQuant, Integer.class),
                parameters.getValue(greenQuant, Integer.class),
                parameters.getValue(blueQuant, Integer.class)
        );
    }
}
