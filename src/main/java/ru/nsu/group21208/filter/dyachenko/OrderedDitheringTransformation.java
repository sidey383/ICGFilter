package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;
import java.util.function.BiFunction;
import java.util.function.Function;

import static ru.nsu.group21208.filter.dyachenko.ColorUtils.*;

public class OrderedDitheringTransformation implements ImageTransformation {
    private final int redColors;
    private final int blueColors;
    private final int greenColors;
    private final double gamma;

    public OrderedDitheringTransformation(int redColors, int greenColors, int blueColors, double gamma) {
        this.redColors = redColors;
        this.blueColors = blueColors;
        this.greenColors = greenColors;
        this.gamma = gamma;
    }

    @Override
    public BufferedImage transformation(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage corr_image = new GammaTransformation(gamma).transformation(image);
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        ditherColor(corr_image, newImage, ColorUtils::getRed, ColorUtils::setRed, redColors);
        ditherColor(newImage, newImage, ColorUtils::getGreen, ColorUtils::setGreen, greenColors);
        ditherColor(newImage, newImage, ColorUtils::getBlue, ColorUtils::setBlue, blueColors);
        return new GammaTransformation(1. / gamma).transformation(newImage);
    }

    private void ditherColor(
            BufferedImage og, BufferedImage nw,
            Function<Integer, Integer> get,
            BiFunction<Integer, Integer, Integer> set,
            int tones
    ) {
        int width = og.getWidth();
        int height = og.getHeight();
        int size = 2;
        int[][] mat = MAT_2X2;
        if (256 > 4 * tones) { // 256 / redColors > 2 * 2 - если потеря тонов больше чем разброс в матрице 2x2
            size = 4;
            mat = MAT_4X4;
        }
        if (256 > 16 * tones) { // 256 / redColors > 4 * 4
            size = 8;
            mat = MAT_8X8;
        }
        if (256 > 64 * tones) { // 256 / redColors > 8 * 8
            size = 16;
            mat = MAT_16X16;
        }
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int rgb;
                try {
                    rgb = og.getRGB(x,y);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    throw new RuntimeException(e);
                }
                int c = get.apply(rgb);
                int err = (256 * mat[x % size][y % size] - 128*(size * size)) / (size * size);
//                err -= 128;
                int res = trunc(applyError(c, err), tones);
                nw.setRGB(x, y, set.apply(rgb, res));
            }
        }
    }

    private final int[][] MAT_2X2 = {
            { 0, 2 },
            { 3, 1 }
    };
    private final int[][] MAT_4X4 = {
            {  0,  8,  2, 10 },
            { 12,  4, 14,  6 },
            {  3, 11,  1,  9 },
            { 15,  7, 13,  5 }
    };
    private final int[][] MAT_8X8 = {
            {  0, 32,  8, 40,  2, 34, 10, 42 },
            { 48, 16, 56, 24, 50, 18, 58, 26 },
            { 12, 44,  4, 36, 14, 46,  6, 38 },
            { 60, 28, 52, 20, 62, 30, 54, 22 },
            {  3, 35, 11, 43,  1, 33,  9, 41 },
            { 51, 19, 59, 27, 49, 17, 57, 25 },
            { 15, 47,  7, 39, 13, 45,  5, 37 },
            { 63, 31, 55, 23, 61, 29, 53, 21 }
    };
    private final int[][] MAT_16X16 = {
            {  0, 128,  32, 160,   8, 136,  40, 168,   2, 130,  34, 162,  10, 138,  42, 170 },
            { 192,  64, 224,  96, 200,  72, 232, 104, 194,  66, 226,  98, 202,  74, 234, 106 },
            {  48, 176,  16, 144,  56, 184,  24, 152,  50, 178,  18, 146,  58, 186,  26, 154 },
            { 240, 112, 208,  80, 248, 120, 216,  88, 242, 114, 210,  82, 250, 122, 218,  90 },
            {  12, 140,  44, 172,   4, 132,  36, 164,  14, 142,  46, 174,   6, 134,  38, 166 },
            { 204,  76, 236, 108, 196,  68, 228, 100, 206,  78, 238, 110, 198,  70, 230, 102 },
            {  60, 188,  28, 156,  52, 180,  20, 148,  62, 190,  30, 158,  54, 182,  22, 150 },
            { 252, 124, 220,  92, 244, 116, 212,  84, 254, 126, 222,  94, 246, 118, 214,  86 },
            {   3, 131,  35, 163,  11, 139,  43, 171,   1, 129,  33, 161,   9, 137,  41, 169 },
            { 195,  67, 227,  99, 203,  75, 235, 107, 193,  65, 225,  97, 201,  73, 233, 105 },
            {  51, 179,  19, 147,  59, 187,  27, 155,  49, 177,  17, 145,  57, 185,  25, 153 },
            { 243, 115, 211,  83, 251, 123, 219,  91, 241, 113, 209,  81, 249, 121, 217,  89 },
            {  15, 143,  47, 175,   7, 135,  39, 167,  13, 141,  45, 173,   5, 133,  37, 165 },
            { 207,  79, 239, 111, 199,  71, 231, 103, 205,  77, 237, 109, 197,  69, 229, 101 },
            {  63, 191,  31, 159,  55, 183,  23, 151,  61, 189,  29, 157,  53, 181,  21, 149 },
            { 255, 127, 223,  95, 247, 119, 215,  87, 253, 125, 221,  93, 245, 117, 213,  85 }
    };


}
