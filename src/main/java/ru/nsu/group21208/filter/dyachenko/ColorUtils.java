package ru.nsu.group21208.filter.dyachenko;

public final class ColorUtils {
    private ColorUtils() {

    }

    public static int combineColors(int r, int g, int b) {
        return 0xff000000 | (r << 16) | (g << 8) | b;
    }
    public static int getRed(int color) {
        int RED_MASK = 0x00ff0000;
        return (color & RED_MASK) >> 16;
    }
    public static int getGreen(int color) {
        int GREEN_MASK = 0x0000ff00;
        return (color & GREEN_MASK) >> 8;
    }
    public static int getBlue(int color) {
        int BLUE_MASK = 0x000000ff;
        return (color & BLUE_MASK);
    }
    public static int setRed(int color, int c) {
        int RED_MASK = 0x00ff0000;
        return (color & ~RED_MASK) | ((0xff & c) << 16);
    }
    public static int setGreen(int color, int c) {
        int GREEN_MASK = 0x0000ff00;
        return (color & ~GREEN_MASK) | ((0xff & c) << 8);
    }
    public static int setBlue(int color, int c) {
        int BLUE_MASK = 0x000000ff;
        return (color & ~BLUE_MASK) | (0xff & c);
    }
    public static int trunc(int value, int grads) {
        return (value * grads / 256) * 255 / (grads - 1);
    }
    public static int applyError(int c, int err) {
        int res = c + err;
        if (res < 0) {
            return 0;
        }
        else if ((res & ~0xff) != 0) {
            return 0xff;
        }
        else {
            return res;
        }
    }
}
