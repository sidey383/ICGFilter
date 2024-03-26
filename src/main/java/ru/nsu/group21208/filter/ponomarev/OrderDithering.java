package ru.nsu.group21208.filter.ponomarev;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class OrderDithering implements Filter<BaseFilterParams> {

    private enum DitheringMatrix {
        SMALL(
                new int[][]{
                        {0, 2},
                        {3, 1}
                }, 4),
        MEDIUM(
                new int[][]{
                        {0, 8, 2, 10},
                        {12, 4, 14, 6},
                        {3, 11, 1, 9},
                        {15, 7, 13, 5}
                }
                , 16),
        LARGE(new int[][]{
                {0, 32, 8, 40, 2, 34, 10, 42},
                {48, 16, 56, 24, 50, 18, 58, 26},
                {12, 44, 4, 36, 14, 46, 6, 38},
                {60, 28, 52, 20, 62, 30, 54, 22},
                {3, 35, 11, 43, 1, 33, 9, 41},
                {51, 19, 59, 27, 49, 17, 57, 25},
                {15, 47, 7, 39, 13, 45, 5, 37},
                {63, 31, 55, 23, 61, 29, 53, 21}
        }
                , 64);


        private final int[][] matrix;

        private final int offset;

        DitheringMatrix(int[][] matrix, int divider) {
            this.matrix = matrix;
            this.offset = divider;
        }

        public int getOffset() {
            return offset;
        }

        public int[][] getMatrix() {
            return matrix;
        }
    }

    private BaseFilterEditor filterEditor = new BaseFilterEditor(
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
        int minSize = 256 / Math.max(r, Math.max(g, b));
        DitheringMatrix matrix = minSize <= DitheringMatrix.SMALL.offset ? DitheringMatrix.SMALL :
                minSize <= DitheringMatrix.MEDIUM.offset ? DitheringMatrix.MEDIUM :
                        DitheringMatrix.LARGE;
        return new Transformation(matrix, r, g, b);
    }

    private class Transformation implements ImageTransformation {

        private final DitheringMatrix matrix;

        private final int rCount;
        private final int gCount;
        private final int bCount;

        private final int rTh;

        private final int gTh;
        private final int bTh;

        Transformation(DitheringMatrix matrix, int r, int g, int b) {
            this.matrix = matrix;
            rCount = r;
            gCount = g;
            bCount = b;
            this.rTh = 256/r;
            this.gTh = 256/g;
            this.bTh = 256/b;
        }

        @Override
        public BufferedImage transformation(BufferedImage bi) {
            ColorModel cm = bi.getColorModel();
            int[][] m = matrix.matrix;
            int mqs = matrix.offset;
            int ms = matrix.matrix.length;
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            WritableRaster raster = bi.copyData(null);
            BufferedImage image = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    int v = m[x%ms][y%ms] - mqs;
                    int r = (((rgb >> 16) & 0xff) + v * rTh / (mqs * 2));
                    int g = (((rgb >> 8) & 0xff) + v * gTh / (mqs * 2));
                    int b = ((rgb & 0xff) + v * bTh / (mqs * 2));
                    rgb = imageColorClosest(r, g, b);
                    image.setRGB(x, y, rgb);
                }
            }
            return image;
        }

        int imageColorClosest(int r, int g, int b) {
            r = (r * rCount / 256) * 255 / (rCount - 1);
            g = (g * gCount / 256) * 255 / (gCount - 1);
            b = (b * bCount / 256) * 255 / (bCount - 1);
            return ((r & 0xff) << 16) | ((g & 0xff) << 8) | b & 0xff;
        }

    }

}
