package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.DoubleParam;
import ru.nsu.group21208.filter.base.param.IntegerParam;
import ru.nsu.group21208.filter.kernel.KernelFilter;
import ru.nsu.group21208.filter.kernel.edge.CopyEdgeSupplier;

import java.awt.image.BufferedImage;

import static java.lang.Math.*;

public class GaborFilter extends KernelFilter<BaseFilterParams> {
    private final BaseFilterEditor editor = new BaseFilterEditor(
            new DoubleParam("wavelength", 1, 0.01, 10, 10000),
            new IntegerParam("theta", 0, -180, 180),
            new DoubleParam("sigma", 1, 0.01, 10, 10000),
//            new IntegerParam("phase", 0, 0, 360),
            new IntegerParam("size", 1, 1, 10)
    );

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    private int divider = 1;

    @Override
    protected int[][] kernel(BaseFilterParams parameters) {
        double wavelength = parameters.getValue("wavelength", Double.class);
        double theta = toRadians(parameters.getValue("theta", Integer.class));
        double sigma = parameters.getValue("sigma", Double.class);
//        double phase = toRadians(parameters.getValue("phase", Integer.class));
        int size = parameters.getValue("size", Integer.class);

        double[][] ker = GaborKernel.gaborKernel(wavelength, theta, sigma, 0, size);
        double min = Double.MAX_VALUE;
        for (int i = 0; i < 2 * size + 1; i++) {
            for (int j = 0; j < 2 * size + 1; j++) {
                if (abs(ker[i][j]) < min) {
                    min = abs(ker[i][j]);
                }
            }
        }
        divider = (int) (1 / min);
        for (int i = 0; i < 2 * size + 1; i++) {
            for (int j = 0; j < 2 * size + 1; j++) {
                ker[i][j] = ker[i][j] / min;
            }
        }
        int[][] g = new int[2 * size + 1][2 * size + 1];
        for (int i = 0; i < 2 * size + 1; i++) {
            for (int j = 0; j < 2 * size + 1; j++) {
                g[i][j] = (int) ker[i][j];
            }
        }
        return g;
    }

    @Override
    protected int kernelDivider(BaseFilterParams parameters) {
        return divider;
    }

    private final CopyEdgeSupplier<?> copy = new CopyEdgeSupplier<>();
    @Override
    protected int getEdgeColor(BufferedImage image, int x, int y, BaseFilterParams parameters) {
        return copy.getEdgeColor(image, x, y, null);
    }

    @Override
    protected boolean isWorkOnEdge(BaseFilterParams parameters) {
        return true;
    }

    private static final class GaborKernel {
        public static double[][] gaborKernel(
                double wavelength, double theta,
                double sigma, double phase,
                int size
        ) {
            final double cosT = cos(theta);
            final double sinT = sin(theta);
            final double sigma2 = sigma * sigma;

            double mean = 0;
            double[][] g = new double[2 * size + 1][2 * size + 1];
            for (int i = 0; i < 2 * size + 1; i++) {
                for (int j = 0; j < 2 * size + 1; j++) {
                    int ii = (i - size);
                    int jj = (j - size);
                    double x = ii * cosT + jj * sinT;
                    double y = -ii * sinT + jj * cosT;
                    double Gaussian = exp(-0.5 * (x * x / sigma2 + y * y / sigma2));
                    double Re = cos(2 * PI * x / wavelength + phase);
                    Re *= Gaussian;
                    g[i][j] = Re;
//                    double Im = sin(2 * PI * x / wavelength + phase);
//                    Im *= Gaussian;
//                    g[i][j] = sqrt(pow(Re, 2) + pow(Im, 2));
                    mean += g[i][j];
                }
            }
            mean /= (2 * size + 1) * (2 * size + 1);
            for (int i = 0; i < 2 * size + 1; i++) {
                for (int j = 0; j < 2 * size + 1; j++) {
                    g[i][j] -= mean;
                }
            }

            return g;
        }
    }

}
