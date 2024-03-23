package ru.nsu.group21208.interaction.impl.filter;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterParams;

import java.awt.image.BufferedImage;

public class FilterInfo<T extends FilterParams> {

    private final Filter<T> filter;

    private final BufferedImage image;

    private final String name;

    private final String description;

    public FilterInfo(Filter<T> filter, BufferedImage image, String name, String description) {
        this.filter = filter;
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public Filter<T> getFilter() {
        return filter;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
