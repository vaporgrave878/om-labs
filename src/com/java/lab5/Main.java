package com.java.lab5;



public class Main {
    public static void main(String[] args) {
        Eiler e = new Eiler();
        e.eilerStraight();
        System.out.println("---------------------------------");
        e.eilerReverse(0, 2, 0.1, 0);
        System.out.println("---------------------------------");

        e.eilerCorrected();
    }
}
