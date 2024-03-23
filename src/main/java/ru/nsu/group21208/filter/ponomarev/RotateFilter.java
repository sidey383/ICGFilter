package ru.nsu.group21208.filter.ponomarev;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.EmptyEditor;
import ru.nsu.group21208.filter.base.EmptyParams;

import java.awt.image.BufferedImage;

public class RotateFilter implements Filter<EmptyParams> {

    @Override
    public FilterEditor<EmptyParams> createFilterEditor() {
        return EmptyEditor.getInstance();
    }

    @Override
    public ImageTransformation apply(EmptyParams parameters) {
        return (i) -> {
            BufferedImage n = new BufferedImage(i.getHeight(), i.getWidth(), i.getType());
            for (int y = 0; y < n.getHeight(); y++)
                for (int x = 0; x < n.getWidth(); x++) {
                    n.setRGB(x, y, i.getRGB(y, x));
                }
            return n;
        };
    }

    private static class RotateTransformation implements ImageTransformation {

        @Override
        public BufferedImage transformation(BufferedImage image) {
            BufferedImage n = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            for (int y = 0; y < n.getHeight(); y++)
                for (int x = 0; x < n.getWidth(); x++) {
                    n.setRGB(x, y, image.getRGB(y, x));
                }
            return n;
        }
    }
}
