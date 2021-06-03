package com.java.lab8;

public class Splines {
    private static double[][] AB = {
            {-1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, -8, 0, 10},
            {1, 1, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 2},
            {0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 2},
            {0, 0, 1, 4, 0, 0, 0, 0, 2, 0, 0, 0, 8, 0, 0, 0, 4},
            {0, 0, 0, 0, 1, 4, 0, 0, 0, 2, 0, 0, 0, 8, 0, 0, 4},
            {0, 0, 0, 0, 1, 25, 0, 0, 0, 5, 0, 0, 0, 125, 0, 0, -6},
            {0, 0, 0, 0, 0, 0, 1, 25, 0, 0, 5, 0, 0, 0, 0, 125, -6},
            {0, 0, 0, 0, 0, 0, 1, 49, 0, 0, 7, 0, 0, 0, 0, 343, 10},
            {0, 1, 0, -2, 0, 0, 0, 0, -1, 0, 0, 2, -3, 0, 3, 0, 0},
            {0, 0, 0, 4, 0, -4, 0, 0, 1, 1, 0, 0, 12, -12, 0, 0, 0},
            {0, 0, 0, 0, 0, 10, 0, 10, 1, 1, 1, 0, 0, 75, 0, -75, 0},
            {0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 2, -6, 0, 6, 0, 0},
            {0, 0, 0, 2, 0, -2, 0, 0, 0, 0, 0, 0, 12, -12, 0, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, -2, 0, 0, 0, 0, 0, 30, 0, -30, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, -12, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 42, 0}
    };
    static double solvedMatrix[] = gaus(AB);
    public static double[] gaus(double[][] AB){
        double m;
        for (int k = 1; k < AB.length; k++){
            for (int i = k; i < AB.length; i++){
                m = AB[i][k - 1] / AB[k - 1][k - 1];
                for (int j = 0; j <= AB.length; j++){
                    AB[i][j] = AB[i][j] - m * AB[k - 1][j];
                }
            }
        }
        double [] x = new double[AB.length];
        for (int i = AB.length - 1; i >= 0; i--){
            for (int j = i + 1; j < AB.length; j++){
                x[i] += AB[i][j] * x[j];
            }
            x[i] = (AB[i][AB.length] - x[i]) / AB[i][i];
        }
        return x;
    }

    public static void main(String[] args) {
        double[] result = solvedMatrix;

        System.out.print("A1 = " + result[14] + "  ");
        System.out.print("A2 = " + result[12] + "  ");
        System.out.print("A3 = " + result[13] + "  ");
        System.out.println("A4 = " + result[15]);

        System.out.print("B1 = " + result[11] + " ");
        System.out.print("B2 = " + result[3] + " ");
        System.out.print("B3 = " + result[5] + "  ");
        System.out.println("B4 = " + result[7]);

        System.out.print("C1 = " + result[1] + "  ");
        System.out.print("C2 = " + result[8] + "  ");
        System.out.print("C3 = " + result[9] + "  ");
        System.out.println("C4 = " + result[10]);

        System.out.print("D1 = " + result[0] + "  ");
        System.out.print("D2 = " + result[2] + "  ");
        System.out.print("D3 = " + result[4] + "  ");
        System.out.print("D4 = " + result[6]);

        System.out.println("_______________Solution_________________");
        double x = 3;
        double resultY = f(x);
        System.out.println("x = " + x + "y = " + resultY);
    }
    public static Double f(double x){
        double[] result = solvedMatrix;
        if (-2 <= x && x <= 1){
            return result[14] * x * x *x + result[11] * x * x + result[1] * x + result[0];
        }
        if (1 <= x && x <= 2){
            return result[12] * x * x *x + result[3] * x * x + result[8] * x + result[2];
        }
        if (2 <= x && x <= 5){
            return result[13] * x * x *x + result[5] * x * x + result[9] * x + result[4];
        }
        if (5 <= x && x <= 7){
            return result[15] * x * x *x + result[7] * x * x + result[10] * x + result[6];
        }
        return null;
    }
}
