package ru.nsu.group21208.filter.ponomarev;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

import java.awt.image.BufferedImage;

import static ru.nsu.group21208.filter.ponomarev.RGBUtils.*;

public class FloydSteinbergDithering implements Filter<BaseFilterParams> {

    private final BaseFilterEditor filterEditor = new BaseFilterEditor(
            new IntegerParam("r", 24, 2, 128),
            new IntegerParam("g", 24, 2, 128),
            new IntegerParam("b", 24, 2, 128)
    );

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return filterEditor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        int r = parameters.getValue("r", Integer.class);
        int g = parameters.getValue("g", Integer.class);
        int b = parameters.getValue("b", Integer.class);
        return new Transformer(r, g, b);
    }

    private static class Transformer implements ImageTransformation {

        private final RGBPalette palette;

        public Transformer(int r, int g, int b) {
            this.palette = new RGBPalette(r, g, b);
        }

        @Override
        public BufferedImage transformation(BufferedImage bi) {
            int height = bi.getHeight();
            int width = bi.getWidth();
            BufferedImage image = new BufferedImage(width, height, bi.getType());
            int[][] rerror = new int[height + 1][width + 2];
            int[][] gerror = new int[height + 1][width + 2];
            int[][] berror = new int[height + 1][width + 2];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int color = bi.getRGB(x, y);
                    int r = fixOverflow(getR(color) + rerror[y][x+1]);
                    int g = fixOverflow(getG(color) + gerror[y][x+1]);
                    int b = fixOverflow(getB(color) + berror[y][x+1]);
                    int rn = palette.findClosetR(r);
                    int gn = palette.findClosetG(g);
                    int bn = palette.findClosetB(b);
                    int re = r - rn;
                    int ge = g - gn;
                    int be = b - bn;
                    applyError(rerror, x, y, re);
                    applyError(gerror, x, y, ge);
                    applyError(berror, x, y, be);
                    image.setRGB(x, y, getRGB(rn, gn, bn));
                }
            }
            return image;
        }

        private void applyError(int[][] errors, int x, int y, int e) {
            errors[y+1][x] += (3 * e) / 16;
            errors[y+1][x+1] += (5 * e) / 16;
            errors[y+1][x+2] += e / 16;
            errors[y][x+2] += (7 * e) / 16;
        }

    }

}
