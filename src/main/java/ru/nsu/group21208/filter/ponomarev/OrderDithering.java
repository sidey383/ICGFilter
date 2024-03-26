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

    private static class DitheringMatrix {

        public final int[][] matrix;

        public final int offset;

        public DitheringMatrix(int size) {
            int len = 1 << size;
            offset = len*len;
            matrix = new int[len][len];
            int move = (Integer.BYTES << 3) - size;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    int ii = Integer.reverse(i) >>> move;
                    int ij = Integer.reverse(j) >>> move;
                    matrix[i][j] = interleave(ii^ij ,ii);
                }
            }
        }

        private static DitheringMatrix createByColorCount(int count) {
            return new DitheringMatrix(32 - Integer.numberOfLeadingZeros(count));
        }

        private static int interleave(int a, int b) {
            return (spaceOut(a) << 1) | spaceOut(b);
        }

        private static int spaceOut(int x) {
            x = (x | (x << 16)) & 0x0000FFFF;
            x = (x | (x <<  8)) & 0x00FF00FF;
            x = (x | (x <<  4)) & 0x0F0F0F0F;
            x = (x | (x <<  2)) & 0x33333333;
            x = (x | (x <<  1)) & 0x55555555;
            return x;
        }

    }

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
        return new Transformation(r, g, b);
    }

    private static class Transformation implements ImageTransformation {

        private final RGBPalette palette;

        private final DitheringMatrix rMatrix;
        private final DitheringMatrix gMatrix;
        private final DitheringMatrix bMatrix;

        private final int rTh;

        private final int gTh;
        private final int bTh;

        Transformation(int r, int g, int b) {
            this.palette = new RGBPalette(r, g, b);
            this.rMatrix = DitheringMatrix.createByColorCount(Math.max(Math.max(r, g), b));
            this.gMatrix = this.rMatrix;
            this.bMatrix = this.gMatrix;
            this.rTh = 256 / (r - 1);
            this.gTh = 256 / (g - 1);
            this.bTh = 256 / (b - 1);
        }

        @Override
        public BufferedImage transformation(BufferedImage bi) {
            ColorModel cm = bi.getColorModel();
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            WritableRaster raster = bi.copyData(null);
            BufferedImage image = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
            int[][] rm = rMatrix.matrix;
            int rqs = rMatrix.offset;
            int rs = rMatrix.matrix.length;
            int[][] gm = gMatrix.matrix;
            int gqs = gMatrix.offset;
            int gs = gMatrix.matrix.length;
            int[][] bm = bMatrix.matrix;
            int bqs = bMatrix.offset;
            int bs = bMatrix.matrix.length;
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    int rv = rm[x % rs][y % rs] - rqs / 2;
                    int gv = gm[x % gs][y % gs] - gqs / 2;
                    int bv = bm[x % bs][y % bs] - bqs / 2;
                    int r = (((rgb >> 16) & 0xff) + (rv * rTh) / rqs);
                    int g = (((rgb >> 8) & 0xff) + (gv * gTh) / gqs);
                    int b = ((rgb & 0xff) + (bv * bTh) / bqs);
                    rgb = palette.findCloset(r, g, b);
                    image.setRGB(x, y, rgb);
                }
            }
            return image;
        }

    }

}
