package ru.nsu.group21208.filter.ponomarev;

public class RGBUtils {

    private RGBUtils() {}

    public static int fixOverflow(int c) {
        return Math.max( 0, Math.min(c, 255));
    }

    public static int getR(int color) {
        return (color >> 16) & 0xff;
    }

    public static int getG(int color) {
        return (color >> 8) & 0xff;
    }


    public static int getB(int color) {
        return color & 0xff;
    }

    public static int getRGB(int r, int g, int b) {
        return ((r & 0xff) << 16) | ((g & 0xff) << 8) | b & 0xff;
    }

}
