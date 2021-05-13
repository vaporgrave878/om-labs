package com.java.lab5;
import java.lang.Math;

public class Eiler {
    private double f(double x, double y){
        return 2 + 3 * Math.pow(x, 2);
    }
    private double h = 0.1, start = 0;
    private double a = 0, b = 2;
    private double x = a, y = start;
    public void eilerStraight() {//Ейлера прямий
        while (x < b) {
            y = y + h * f(x + (h / 2), y + (h / 2 * f(x, y)));
            x += h;
            System.out.println("x= " + x + "\t" + "y= " + y);
        }
    }

    public void eilerReverse(double x, double b, double h, double y){//Ейлера зворотній
        double d, c, xd = 0, yd = 1, yd1 = 0, hd = h/2;
        for (; x < b; x += h){
            y += h * f(x, y );
            for (int j = 0; j < 1; j ++){
                yd1 = yd + hd * f(xd, yd);
                yd = yd1;
                xd += hd;
            }
            c = (yd1 - y) / (h * h - h * h / 4);
            d = c * h * h;
            System.out.println("x = " + x + " \ny = " + y + " \nyd = " + yd1 + " \nc =  " + d);
            System.out.println("---------------------------------------------");
        }
    }
    public void eilerCorrected(){//Ейлера виправлений
        while (x < b){
            y = y + (h/2) * Math.abs(f(x, y) + f(x + h, y + h * f(x, y)));
            x += h;
            System.out.println("x= " + x + "\t" + "y= " + y);
        }
    }
}
