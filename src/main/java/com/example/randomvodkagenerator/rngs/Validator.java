package com.example.randomvodkagenerator.rngs;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Validator {

    private static final HashMap<Double, double[]> ksTable = new HashMap<>();
    private static final HashMap<Double, double[]> chisqTable = new HashMap<>();
    // The max number of N supported by our tables
    private static final int MAXN = 40;

    static {
        ksTable.put(.20, new double[]{
                0.900, 0.684, 0.565, 0.493, 0.447, 0.410, 0.381, 0.358, 0.339, 0.323, 0.308, 0.296, 0.285, 0.275, 0.266, 0.258, 0.250, 0.244, 0.237, 0.232, 0.226, 0.221, 0.216, 0.212, 0.208, 0.204, 0.200, 0.197, 0.193, 0.190, 0.187, 0.184, 0.182, 0.179, 0.177, 0.174, 0.172, 0.170, 0.168, 0.165
        });
        ksTable.put(.10, new double[]{
                0.950, 0.776, 0.636, 0.656, 0.509, 0.468, 0.436, 0.410, 0.387, 0.369, 0.352, 0.338, 0.325, 0.314, 0.304, 0.295, 0.286, 0.279, 0.271, 0.265, 0.259, 0.253, 0.247, 0.242, 0.238, 0.233, 0.229, 0.225, 0.221, 0.218, 0.214, 0.211, 0.208, 0.205, 0.202, 0.199, 0.196, 0.194, 0.191, 0.189
        });
        ksTable.put(.05, new double[]{
                0.975, 0.842, 0.708, 0.624, 0.563, 0.519, 0.483, 0.454, 0.430, 0.409, 0.391, 0.375, 0.361, 0.349, 0.338, 0.327, 0.318, 0.309, 0.301, 0.294, 0.287, 0.281, 0.275, 0.269, 0.264, 0.259, 0.254, 0.250, 0.246, 0.242, 0.238, 0.234, 0.231, 0.227, 0.224, 0.221, 0.218, 0.215, 0.213, 0.210
        });
        ksTable.put(.02, new double[]{
                0.990, 0.900, 0.785, 0.689, 0.627, 0.577, 0.538, 0.507, 0.480, 0.457, 0.437, 0.419, 0.404, 0.390, 0.377, 0.366, 0.355, 0.346, 0.337, 0.329, 0.321, 0.314, 0.307, 0.301, 0.295, 0.290, 0.284, 0.279, 0.275, 0.270, 0.266, 0.262, 0.258, 0.254, 0.251, 0.247, 0.244, 0.241, 0.238, 0.235
        });
        ksTable.put(.01, new double[]{
                0.995, 0.929, 0.829, 0.734, 0.669, 0.617, 0.576, 0.542, 0.513, 0.489, 0.468, 0.449, 0.432, 0.418, 0.404, 0.392, 0.381, 0.371, 0.361, 0.352, 0.344, 0.337, 0.330, 0.323, 0.317, 0.311, 0.305, 0.300, 0.295, 0.290, 0.285, 0.281, 0.277, 0.273, 0.269, 0.265, 0.262, 0.258, 0.255, 0.252
        });

        chisqTable.put(0.995, new double[]{
                0, 0.01, 0.072, 0.207, 0.412, 0.676, 0.989, 1.344, 1.735, 2.156, 2.603, 3.074, 3.565, 4.075, 4.601, 5.142, 5.697, 6.265, 6.844, 7.434, 8.034, 8.643, 9.26, 9.886, 10.52, 11.16, 11.808, 12.461, 13.121, 13.787, 20.707, 27.991, 35.534, 43.275, 51.172, 59.196, 67.328
        });

        chisqTable.put(0.99, new double[]{
                0, 0.02, 0.115, 0.297, 0.554, 0.872, 1.239, 1.646, 2.088, 2.558, 3.053, 3.571, 4.107, 4.66, 5.229, 5.812, 6.408, 7.015, 7.633, 8.26, 8.897, 9.542, 10.196, 10.856, 11.524, 12.198, 12.879, 13.565, 14.256, 14.953, 22.164, 29.707, 37.485, 45.442, 53.54, 61.754, 70.065
        });

        chisqTable.put(0.975, new double[]{
                0.001, 0.051, 0.216, 0.484, 0.831, 1.237, 1.69, 2.18, 2.7, 3.247, 3.816, 4.404, 5.009, 5.629, 6.262, 6.908, 7.564, 8.231, 8.907, 9.591, 10.283, 10.982, 11.689, 12.401, 13.12, 13.844, 14.573, 15.308, 16.047, 16.791, 24.433, 32.357, 40.482, 48.758, 57.153, 65.647, 74.222
        });

        chisqTable.put(0.95, new double[]{
                0.004, 0.103, 0.352, 0.711, 1.145, 1.635, 2.167, 2.733, 3.325, 3.94, 4.575, 5.226, 5.892, 6.571, 7.261, 7.962, 8.672, 9.39, 10.117, 10.851, 11.591, 12.338, 13.091, 13.848, 14.611, 15.379, 16.151, 16.928, 17.708, 18.493, 26.509, 34.764, 43.188, 51.739, 60.391, 69.126, 77.929
        });

        chisqTable.put(0.9, new double[]{
                0.016, 0.211, 0.584, 1.064, 1.61, 2.204, 2.833, 3.49, 4.168, 4.865, 5.578, 6.304, 7.042, 7.79, 8.547, 9.312, 10.085, 10.865, 11.651, 12.443, 13.24, 14.041, 14.848, 15.659, 16.473, 17.292, 18.114, 18.939, 19.768, 20.599, 29.051, 37.689, 46.459, 55.329, 64.278, 73.291, 82.358
        });

        chisqTable.put(0.1, new double[]{
                2.706, 4.605, 6.251, 7.779, 9.236, 10.645, 12.017, 13.362, 14.684, 15.987, 17.275, 18.549, 19.812, 21.064, 22.307, 23.542, 24.769, 25.989, 27.204, 28.412, 29.615, 30.813, 32.007, 33.196, 34.382, 35.563, 36.741, 37.916, 39.087, 40.256, 51.805, 63.167, 74.397, 85.527, 96.578, 107.565, 118.498
        });

        chisqTable.put(0.05, new double[]{
                3.841, 5.991, 7.815, 9.488, 11.07, 12.592, 14.067, 15.507, 16.919, 18.307, 19.675, 21.026, 22.362, 23.685, 24.996, 26.296, 27.587, 28.869, 30.144, 31.41, 32.671, 33.924, 35.172, 36.415, 37.652, 38.885, 40.113, 41.337, 42.557, 43.773, 55.758, 67.505, 79.082, 90.531, 101.879, 113.145, 124.342
        });

        chisqTable.put(0.025, new double[]{
                5.024, 7.378, 9.348, 11.143, 12.833, 14.449, 16.013, 17.535, 19.023, 20.483, 21.92, 23.337, 24.736, 26.119, 27.488, 28.845, 30.191, 31.526, 32.852, 34.17, 35.479, 36.781, 38.076, 39.364, 40.646, 41.923, 43.195, 44.461, 45.722, 46.979, 59.342, 71.42, 83.298, 95.023, 106.629, 118.136, 129.561
        });

        chisqTable.put(0.01, new double[]{
                6.635, 9.21, 11.345, 13.277, 15.086, 16.812, 18.475, 20.09, 21.666, 23.209, 24.725, 26.217, 27.688, 29.141, 30.578, 32, 33.409, 34.805, 36.191, 37.566, 38.932, 40.289, 41.638, 42.98, 44.314, 45.642, 46.963, 48.278, 49.588, 50.892, 63.691, 76.154, 88.379, 100.425, 112.329, 124.116, 135.807
        });

        chisqTable.put(0.005, new double[]{
                7.879, 10.597, 12.838, 14.86, 16.75, 18.548, 20.278, 21.955, 23.589, 25.188, 26.757, 28.3, 29.819, 31.319, 32.801, 34.267, 35.718, 37.156, 38.582, 39.997, 41.401, 42.796, 44.181, 45.559, 46.928, 48.29, 49.645, 50.993, 52.336, 53.672, 66.766, 79.49, 91.952, 104.215, 116.321, 128.299, 140.169
        });
    }

    /**
     * Bins an array into classes according to ChiSq procedure.
     *
     * @param vals An arraylist containing the RNG output
     * @return A HashMap, with MAX of the class as the key and the matches as the value.
     */
    private static HashMap<Float, Long> binArray(ArrayList<Float> vals) {
        Collections.sort(vals);
        int n = vals.size();
        int range = Math.round(Collections.max(vals) - Collections.min(vals));
        // FIXME Bug when range is == 1
        // this happens both 0 and 1 are in the dist.
        int k = Math.round((float) (1 + 3.222 * Math.log10(n)));
        float _class = (float) range / k;

        HashMap<Float, Long> bins = new HashMap<>();
        float currentMax = Collections.min(vals) + _class;
        for (Float f : vals
        ) {
            if (f > currentMax) {
                // FIXME Shitty fix for less than 5 matches per class
                // Expected probability will not be uniform for the merged bins.
                // This requires doubling the probability for them.
                // so expected_bin_prob = n/class
                // m_merged_expected_bin_prob = n/class * m
//                if (bins.get(currentMax) < 5) {
//                    bins.put(currentMax + _class, bins.get(currentMax));
//                }
                currentMax += _class;
                continue;
            }
            if (bins.containsKey(currentMax)) {
                bins.put(currentMax, bins.get(currentMax) + 1L);
            } else
                bins.put(currentMax, 1L);

        }
        return bins;

    }

    private static double[] buildExpectedArray(int len) {
        float expectedsize = 1f / len;
        double[] expected = new double[len];
        for (int i = 0; i < len; i++) {
            expected[i] = expectedsize;
        }
        return expected;
    }

    public static boolean ChiSq(ArrayList<Float> vals, double a) {
        // Bin the array into classes.
        // TODO This will not fix classes with less than 5 hits
        HashMap<Float, Long> bins = binArray(vals);
        long[] observed = Arrays.stream(bins.values().toArray(new Long[0])).mapToLong(Long::longValue).toArray();

        // Create the expected distribution. Since we're using RNGs, we use the uniform distribution.
        // This means each bin has the same probability of happenstance.
        double[] expected = buildExpectedArray(bins.keySet().size());

        ChiSquareTest chiSquareTest = new ChiSquareTest();
        return chiSquareTest.chiSquareTest(expected, observed, a);
    }


    /**
     * Lookup a value on the Smirnov Tables
     * Will calculate on values exceeding 40
     */
    private static double smirnovLookup(double alpha, int n) {
        if (!ksTable.containsKey(alpha)) {
            throw new IllegalArgumentException("Unsupported Alpha");
        }
        // Calculate our own vals
        if (n > MAXN) {
            if (alpha == .20)
                return 1.07 / Math.sqrt(n);
            if (alpha == .10)
                return 1.22 / Math.sqrt(n);
            if (alpha == .05)
                return 1.36 / Math.sqrt(n);
            if (alpha == .02)
                return 1.52 / Math.sqrt(n);
            if (alpha == .01)
                return 1.63 / Math.sqrt(n);
        }

        return ksTable.get(alpha)[n - 1];

    }


    /**
     * Smirnov Validation for a set of generated values.
     */
    public static boolean Smirnov(ArrayList<Float> vals, double a) {
        Collections.sort(vals);
        int n = vals.size();
        // find max D+ and max d-
        float dMinus = Float.NEGATIVE_INFINITY;
        float dPlus = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            // |r(i) - i-1/n
            dMinus = Math.max(dMinus, Math.abs(vals.get(i) - ((i - 1f) / n)));
            // |(i/n) - r(i)|
            dPlus = Math.max(dPlus, ((float) i / n) - vals.get(i));
        }
        // find MaxD
        float d = Math.max(dMinus, dPlus);
        double ks = smirnovLookup(a, n);
        return d < ks;
    }
}
