package com.java.lab7;

import java.util.List;

public class SplineInterpolation {
    /*private final List<Double> mX;
    private final List<Double> mY;
    private final double[] mM;

    private SplineInterpolation(List<Double> x, List<Double> y, double[] m){
        mX = x;
        mY = y;
        mM = m;
    }

    public static SplineInterpolation createCubicSpline(List<Double> x, List<Double> y){
        if (x == null || y == null || x.size() != y.size() || x.size() < 2){
            throw new IllegalArgumentException("There must be at least two control points and the arrays must be of equal length. ");
        }
        final int n = x.size();
        double[] d = new double[n - 1];
        double[] m = new double[n];
        return null;
    }*/
    public List<Float> mX;
    public List<Float> mY;
    public float[] mM;

    SplineInterpolation(List<Float> x, List<Float> y, float[] m) {
        mX = x;
        mY = y;
        mM = m;
    }

    /**
     * Creates a monotone cubic spline from a given set of control points.
     *
     * The spline is guaranteed to pass through each control point exactly. Moreover, assuming the control points are
     * monotonic (Y is non-decreasing or non-increasing) then the interpolated values will also be monotonic.
     *
     * This function uses the Fritsch-Carlson method for computing the spline parameters.
     * http://en.wikipedia.org/wiki/Monotone_cubic_interpolation
     *
     * @param x
     *            The X component of the control points, strictly increasing.
     * @param y
     *            The Y component of the control points
     * @return
     *
     * @throws IllegalArgumentException
     *             if the X or Y arrays are null, have different lengths or have fewer than 2 values.
     */
    public static SplineInterpolation createMonotoneCubicSpline(List<Float> x, List<Float> y) {
        if (x == null || y == null || x.size() != y.size() || x.size() < 2) {
            throw new IllegalArgumentException("There must be at least two control "
                    + "points and the arrays must be of equal length.");
        }

        final int n = x.size();
        float[] d = new float[n - 1]; // could optimize this out
        float[] m = new float[n];

        // Compute slopes of secant lines between successive points.
        for (int i = 0; i < n - 1; i++) {
            float h = x.get(i + 1) - x.get(i);
            if (h <= 0f) {
                throw new IllegalArgumentException("The control points must all "
                        + "have strictly increasing X values.");
            }
            d[i] = (y.get(i + 1) - y.get(i)) / h;
        }

        // Initialize the tangents as the average of the secants.
        m[0] = d[0];
        for (int i = 1; i < n - 1; i++) {
            m[i] = (d[i - 1] + d[i]) * 0.5f;
        }
        m[n - 1] = d[n - 2];

        // Update the tangents to preserve monotonicity.
        for (int i = 0; i < n - 1; i++) {
            if (d[i] == 0f) { // successive Y values are equal
                m[i] = 0f;
                m[i + 1] = 0f;
            } else {
                float a = m[i] / d[i];
                float b = m[i + 1] / d[i];
                float h = (float) Math.hypot(a, b);
                if (h > 3f) {
                    float t = 3f / h;
                    m[i] = t * a * d[i];
                    m[i + 1] = t * b * d[i];
                }
            }
        }
        return new SplineInterpolation(x, y, m);
    }

    /**
     * Interpolates the value of Y = f(X) for given X. Clamps X to the domain of the spline.
     *
     * @param x
     *            The X value.
     * @return The interpolated Y = f(X) value.
     */
    public float interpolate(float x) {
        // Handle the boundary cases.
        final int n = mX.size();
        if (Float.isNaN(x)) {
            return x;
        }
        if (x <= mX.get(0)) {
            return mY.get(0);
        }
        if (x >= mX.get(n - 1)) {
            return mY.get(n - 1);
        }

        // Find the index 'i' of the last point with smaller X.
        // We know this will be within the spline due to the boundary tests.
        int i = 0;
        while (x >= mX.get(i + 1)) {
            i += 1;
            if (x == mX.get(i)) {
                return mY.get(i);
            }
        }

        // Perform cubic Hermite spline interpolation.
        float h = mX.get(i + 1) - mX.get(i);
        float t = (x - mX.get(i)) / h;
        return (mY.get(i) * (1 + 2 * t) + h * mM[i] * t) * (1 - t) * (1 - t)
                + (mY.get(i + 1) * (3 - 2 * t) + h * mM[i + 1] * (t - 1)) * t * t;
    }

    // For debugging.
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        final int n = mX.size();
        str.append("[");
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                str.append(", ");
            }
            str.append("(").append(mX.get(i));
            str.append(", ").append(mY.get(i));
            str.append(": ").append(mM[i]).append(")");
        }
        str.append("]");
        return str.toString();
    }

    public void main(String[] args) {

        createMonotoneCubicSpline(mX, mY);
        System.out.println("Y = " + interpolate(3));
    }
}
