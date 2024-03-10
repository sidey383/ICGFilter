package ru.nsu.group21208.filter;

public class IntegerParameter extends FilterParameter<Integer> {

    private final Integer minimal;

    private final Integer maximum;

    public IntegerParameter(String name, Integer minimal, Integer maximum , Integer value) {
        super(name, value);
        this.maximum = maximum;
        this.minimal = minimal;
    }

    public Integer getMinimalValue() {
        return this.minimal;
    }

    public Integer getMaximumValue() {
        return this.maximum;
    }

}
