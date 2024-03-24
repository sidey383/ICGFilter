package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.DoubleParam;

import java.awt.image.BufferedImage;

import static ru.nsu.group21208.filter.dyachenko.ColorUtils.*;

public class GammaFilter implements Filter<BaseFilterParams> {
    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return new BaseFilterEditor(
                new DoubleParam("gamma", 1, 0.1, 10)
        );
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        double gamma = parameters.getValue("gamma", Double.class);
        System.out.println(gamma);
        return (image) -> {
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = getRed(rgb);
                    int green = getGreen(rgb);
                    int blue = getBlue(rgb);

                    red = (int) (255 * Math.pow(red / 255.0, gamma));
                    green = (int) (255 * Math.pow(green / 255.0, gamma));
                    blue = (int) (255 * Math.pow(blue / 255.0, gamma));

                    int newPixel = combineColors(red, green, blue);

                    newImage.setRGB(x, y, newPixel);
                }
            }

            return newImage;
        };
    }
}
