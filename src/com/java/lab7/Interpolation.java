package com.java.lab7;

public class Interpolation {
    private int N = 5;
    public void lagrange(){
        /*double[] X ={0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] Y = {-1, 2, 17, 50, 107, 194, 317, 482, 695, 962, 1289};*/
        double [] X = {-2, 1, 2, 5, 7};
        double [] Y = {10, 2, 4, -6, 10};
        double x = 3;
        double yx = 0, Pr;
        for (int i = 0; i < N; i++){
            Pr = 1;
            for (int j = 0; j < N; j++){
                if (i != j){
                    Pr = Pr * ((x - X[j]) / (X[i] - X[j]));
                }
            }
            yx = yx + Y[i] * Pr;
        }
        System.out.println("x = " + x + " y = " + yx);
    }

    private double y(double x){ return Math.pow(x, 3) + 3 * Math.pow(x, 2) - x - 1; }
    private double right(double [] y, int p, int i){
        if (p == 1){
            return y[i + 1] - y[i];
        }
        else {
            return right(y, p - 1, i + 1) - right(y, p - 1, i);
        }
    }

    public void newtonFirst(){
        double [] X = {-3, -1, 1, 3, 5};
        double [] Y = {10, 2, 4, -6, 10};
        double h = 2, d = 1, Pr;
        double x = 3;
        double P = Y[0];
        for (int i = 1; i < N - 1; i++){
            Pr = 1;
            for (int j = 0; j < i; j++){
                Pr = Pr * (x - X[j]);
            }
            d = d * i * h;
            P = P + (right(Y, i, 0) * Pr / d);
        }
        System.out.println("x = " + x + " y = " + P);
    }
}

