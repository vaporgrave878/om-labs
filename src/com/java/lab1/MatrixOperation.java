package com.java.lab1;

import java.util.Scanner;

public class MatrixOperation {
    public static void gausMethod () {
        int n;
        double k;
        double D;

        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter the size of matrix: ");
        n = userInput.nextInt();

        double[][] A = new double[n][n];
        double[] B = new double[n];
        double[] x = new double[n];

        System.out.println("Enter matrix 1 elements");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = userInput.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Enter matrix 2 elements");
        for (int i = 0; i < n; i++) {
                B[i] = userInput.nextInt();
        }


        for (int i = 0; i < n; i++) {
            System.out.println(B[i]);
        }


        for (int l = 0; l < n; l++)
        {
            for (int i = l + 1; i < n; i++)
            {
                k = A[i][l] / A[l][l];
                B[i] -= k * B[l];
                for (int j = l; j < n; j++)
                {
                    A[i][j] -= k * A[l][j];
                }
            }
        }
        //reverse
        for (int i = n - 1; i >= 0; i--)
        {
            D = 0.0;
            for (int j = i + 1; j < n; j++)
            {
                D += A[i][j] * x[j] ;
            }
            x[i] = (B[i] - D) / A[i][i];
        }


        for(int i = 0; i < n; i ++)//printing the answer
        {
            System.out.println(x[i] + " ");
        }

    }
}
