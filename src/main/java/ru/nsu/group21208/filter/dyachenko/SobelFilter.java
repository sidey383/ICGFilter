package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.DoubleParam;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SobelFilter implements Filter<BaseFilterParams> {
    private BaseFilterEditor editor = new BaseFilterEditor(
            new DoubleParam("Threshold", 0.5, 0, 1, 100)
    );

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        double threshold = parameters.getValue("Threshold", Double.class);
        return (image) -> {
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage newImage = Filter.copyOfImage(image);
            Graphics g = newImage.getGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0,0,width,height);

            applyForColor(image, newImage, ColorUtils::getRed, ColorUtils::setRed, threshold);
            applyForColor(image, newImage, ColorUtils::getGreen, ColorUtils::setGreen, threshold);
            applyForColor(image, newImage, ColorUtils::getRed, ColorUtils::setRed, threshold);
            return newImage;
        };
    }

    private static void applyForColor(
            BufferedImage og, BufferedImage nw,
            Function<Integer, Integer> get,
            BiFunction<Integer, Integer, Integer> set,
            double threshold)
    {
        final int MAX = 255 * 4;
        final int THRESHOLD = (int) (threshold * MAX);
        int width = og.getWidth();
        int height = og.getHeight();
        int value = 0;
        int[][] vals = new int[3][3];
        for (int h = 1; h < height-1; ++h) {
            vals[0][1] = get.apply(og.getRGB(0, h-1));
            vals[1][1] = get.apply(og.getRGB(0, h));
            vals[2][1] = get.apply(og.getRGB(0, h+1));
            vals[0][2] = get.apply(og.getRGB(1, h-1));
            vals[1][2] = get.apply(og.getRGB(1, h));
            vals[2][2] = get.apply(og.getRGB(1, h+1));
//            vals[0][2] = get.apply(og.getRGB(2, h-1));
//            vals[1][2] = get.apply(og.getRGB(2, h));
//            vals[2][2] = get.apply(og.getRGB(2, h+1));
            for (int w = 1; w < width-1; ++w) {
                vals[0][0] = vals[0][1];
                vals[1][0] = vals[1][1];
                vals[2][0] = vals[2][1];
                vals[0][1] = vals[0][2];
                vals[1][1] = vals[1][2];
                vals[2][1] = vals[2][2];
                vals[0][2] = get.apply(og.getRGB(w + 1, h-1));
                vals[1][2] = get.apply(og.getRGB(w + 1, h));
                vals[2][2] = get.apply(og.getRGB(w + 1, h+1));

                value = Math.abs(
                        vals[0][0] + 2*vals[0][1] + vals[0][2]
                                - (vals[2][0] + 2*vals[2][1] + vals[2][2])
                );
                value += Math.abs(
                        vals[0][0] + 2*vals[1][0] + vals[2][0]
                                - (vals[0][2] + 2*vals[1][2] + vals[2][2])
                );
                int rgb = nw.getRGB(w,h) | 0xff000000;
                if (value > THRESHOLD) {
                    nw.setRGB(w, h, set.apply(rgb, 0xff));
                }
                else {
                    nw.setRGB(w, h, set.apply(rgb, 0));
                }
            }
        }
    }
}
