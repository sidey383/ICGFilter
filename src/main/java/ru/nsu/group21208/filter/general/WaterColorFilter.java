package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;
import ru.nsu.group21208.filter.base.param.OptionChooseParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaterColorFilter implements Filter<BaseFilterParams> {
    enum Dim {
        M3X3(3),
        M5X5(5),
        M7X7(7),
        M9X9(9);

        int size;

        Dim(int s) {
            size = s;
        }
    }
    private final BaseFilterEditor editor = new BaseFilterEditor(
            new OptionChooseParam(
                    "Size",
                    Dim.M3X3,
                    List.of(Dim.values()),
                    Stream.of(Dim.values()).map(Enum::name).collect(Collectors.toList())
            )
    );
    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new WaterColorFilterTransformation(
                parameters.getValue("Size", Dim.class).size
        );
    }
}
