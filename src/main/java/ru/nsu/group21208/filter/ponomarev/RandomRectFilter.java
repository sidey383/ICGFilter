package ru.nsu.group21208.filter.ponomarev;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

import java.awt.image.BufferedImage;
import java.util.Random;

import static ru.nsu.group21208.filter.ponomarev.RGBUtils.*;

public class RandomRectFilter implements Filter<BaseFilterParams> {

    private static final String COUNT = "Count";

    private static final String SEED = "Seed";

    private static final String SIZE = "Size";
    private final BaseFilterEditor editor = new BaseFilterEditor(
            new IntegerParam(COUNT, 100*100, 10, 1000*10000),
            new IntegerParam(SEED, 0, 0, Integer.MAX_VALUE),
            new IntegerParam(SIZE, 3, 2, 10)
    );

    @Override
    public BaseFilterEditor createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams p) {
        return new RandomRectTransformation(p.getValue(COUNT, Integer.class), p.getValue(SIZE, Integer.class), p.getValue(SEED, Integer.class));
    }

    private static class RandomRectTransformation implements ImageTransformation {

        private final int count;

        private final int size;

        private final int seed;

        private RandomRectTransformation(int count, int size, int seed) {
            this.count = count;
            this.size = size;
            this.seed = seed;
        }

        @Override
        public BufferedImage transformation(BufferedImage o) {
            BufferedImage n = Filter.copyOfImage(o);
            Random r = new Random(seed);
            for (int i = 0; i < count; i++) {
                int x = r.nextInt(o.getWidth() - size - 1);
                int y = r.nextInt(o.getHeight() - size - 1);
                int color1 = o.getRGB(x, y);
                int color2 = o.getRGB(x + size, y + size);
                int color = getRGB((getR(color1) + getR(color2))/2,(getG(color1) + getG(color2))/2,(getB(color1) + getB(color2))/2 );
                for (int dy = 0; dy < size; dy++) {
                    for (int dx = 0; dx < size; dx++) {
                        n.setRGB(x + dx, y + dy, color);
                    }
                }
            }
            return n;
        }
    }
}
