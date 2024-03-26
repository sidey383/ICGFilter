package ru.nsu.group21208.filter.ponomarev;

public class RGBPalette {

    private final int rCount;
    private final int gCount;
    private final int bCount;


    public RGBPalette(int rCount, int gCount, int bCount) {
        this.rCount = rCount;
        this.gCount = gCount;
        this.bCount = bCount;
    }

    public int findCloset(int r, int g, int b) {
        r = (r * rCount / 256) * 255 / (rCount - 1);
        g = (g * gCount / 256) * 255 / (gCount - 1);
        b = (b * bCount / 256) * 255 / (bCount - 1);
        r = Math.max( 0, Math.min(r, 255));
        g = Math.max( 0, Math.min(g, 255));
        b = Math.max( 0, Math.min(b, 255));
        return (r << 16) | (g << 8) | b;
    }

    public int findClosetR(int r) {
        return  (r * rCount / 256) * 255 / (rCount - 1);
    }

    public int findClosetG(int g) {
        return  (g * gCount / 256) * 255 / (gCount - 1);
    }

    public int findClosetB(int b) {
        return  (b * bCount / 256) * 255 / (bCount - 1);
    }

}
