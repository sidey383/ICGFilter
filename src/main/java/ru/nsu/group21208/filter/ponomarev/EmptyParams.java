package ru.nsu.group21208.filter.ponomarev;

import ru.nsu.group21208.filter.FilterSettings;
import ru.nsu.group21208.filter.IntegerParameter;

import java.util.Collection;
import java.util.List;

public class EmptyParams implements FilterSettings {
    @Override
    public Collection<IntegerParameter> integerParameters() {
        return List.of();
    }
}
