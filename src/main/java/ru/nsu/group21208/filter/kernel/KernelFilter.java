package ru.nsu.group21208.filter.kernel;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterParams;
import ru.nsu.group21208.filter.ImageTransformation;

import java.awt.image.BufferedImage;

public abstract class KernelFilter<T extends FilterParams> implements Filter<T> {

    public ImageTransformation apply(T parameters) {
        return new KernelTransformation(parameters);
    }

    protected abstract int[][] kernel(T parameters);

    protected abstract int kernelDivider(T parameters);

    protected abstract int getEdgeColor(BufferedImage image, int x, int y, T parameters);

    protected abstract boolean isWorkOnEdge(T parameters);

    protected class KernelTransformation implements ImageTransformation {

        private final T params;

        public KernelTransformation(T params) {
            this.params = params;
        }

        @Override
        public BufferedImage transformation(BufferedImage image) {
            int[][] kernel = kernel(params);
            int kernelDivider = kernelDivider(params);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage result = Filter.copyOfImage(image);

            // Iterate over each pixel of the image
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Apply convolution
                    int newPixel = applyConvolution(image, x, y, kernel, kernelDivider);
                    result.setRGB(x, y, newPixel);
                }
            }

            return result;
        }

        protected int applyConvolution(BufferedImage image, int x, int y, int[][] kernel, int kernelDivider) {
            int width = image.getWidth();
            int height = image.getHeight();

            int halfKernelSize = kernel.length / 2;
            if (!isWorkOnEdge(params) && (
                    x - halfKernelSize < 0 ||
                    y - halfKernelSize < 0 ||
                    x + halfKernelSize >= image.getWidth() ||
                    y + halfKernelSize >= image.getHeight())
            ) {
                return image.getRGB(x, y);
            }
            int r = 0, g = 0, b = 0;

            boolean ignore = false;
            // Iterate over the kernel
            for (int i = 0; i < kernel.length; i++) {
                for (int j = 0; j < kernel.length; j++) {
                    int pixelX = x - halfKernelSize + i;
                    int pixelY = y - halfKernelSize + j;

                    final int rgb;

                    // Handle border cases by replicating edge pixels
                    if (pixelX < width && pixelY < height && pixelX > 0 && pixelY > 0) {
                        rgb = image.getRGB(pixelX, pixelY);
                    } else {
                        rgb = getEdgeColor(image, pixelX, pixelY, params);
                    }

                    // Extract color components
                    int currentR = (rgb >> 16) & 0xFF;
                    int currentG = (rgb >> 8) & 0xFF;
                    int currentB = rgb & 0xFF;

                    // Apply kernel weight
                    r += currentR * kernel[i][j];
                    g += currentG * kernel[i][j];
                    b += currentB * kernel[i][j];
                }
            }

            // Clamp values to the valid range [0, 255]
            r = Math.min(Math.max(r / kernelDivider, 0), 255);
            g = Math.min(Math.max(g / kernelDivider, 0), 255);
            b = Math.min(Math.max(b / kernelDivider, 0), 255);

            // Compose new pixel value
            return (r << 16) | (g << 8) | b;
        }

    }

}
