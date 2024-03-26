package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

import java.awt.image.BufferedImage;

public class ColorShiftFilter implements Filter<BaseFilterParams> {
    private final BaseFilterEditor editor = new BaseFilterEditor(
            new IntegerParam("Horizontal shift", 5, 0, 100),
            new IntegerParam("Vertical shift", 5, 0, 100)
    );

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        int vshift = parameters.getValue("Vertical shift", Integer.class);
        int hshift = parameters.getValue("Horizontal shift", Integer.class);
        return (image) -> {
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage newImage = Filter.copyOfImage(image);
            for (int h = vshift; h < height - vshift; ++h) {
                for (int w = hshift; w < width - hshift; ++w) {
                    int r = image.getRGB(w + hshift, h + vshift) & 0x00ff0000;
                    int g = image.getRGB(w - hshift, h - vshift) & 0x0000ff00;
                    newImage.setRGB(w, h,
                            (0xff0000ff & image.getRGB(w,h)) | r | g
                    );
                }
            }

            return newImage;
        };
    }
}
