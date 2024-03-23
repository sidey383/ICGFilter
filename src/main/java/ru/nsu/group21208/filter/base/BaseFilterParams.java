package ru.nsu.group21208.filter.base;

import ru.nsu.group21208.filter.FilterParams;

import java.util.Map;

public class BaseFilterParams implements FilterParams {

    private final Map<String, Object> values;

    public BaseFilterParams(Map<String, Object> values) {
        this.values = values;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(String name, Class<T> type) {
        return (T) values.get(name);
    }

}
