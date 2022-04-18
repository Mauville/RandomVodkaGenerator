package com.example.randomvodkagenerator.rngs;

import java.util.ArrayList;

public class Tests {
    public static void main(String[] args) {
        System.out.println("Mid Squares");
        NumberGenerator ng = new MidSquaresNumberGenerator(3708);
        ArrayList<Float> vals = ng.generate(7);
        System.out.println(vals);

        System.out.println("Linear Congruence");
        ng = new LinearCongruenceGenerator(4, 5, 7, 8);
        vals = ng.generate(7);
        System.out.println(vals);
        System.out.println("KS:" + Validator.Smirnov(vals, .05d));
        System.out.println("CHISQ: " + Validator.ChiSq(vals, .05f));

        System.out.println("Mixed Congruence:");
        ng = new MixedCongruenceGenerator(4, 5, 7, 8);
        vals = ng.generate(7);
        System.out.println(vals);
        System.out.println("KS:" + Validator.Smirnov(vals, .05d));
        System.out.println("CHISQ: " + Validator.ChiSq(vals, .05f));

        System.out.println("Multiplicative Congruence");
        ng = new MultiplicativeCongruenceGenerator(4, 5, 7);
        vals = ng.generate(7);
        System.out.println(vals);
        System.out.println("KS:" + Validator.Smirnov(vals, .05d));
        System.out.println("CHISQ: " + Validator.ChiSq(vals, .05f));

        System.out.println("Combined Multiplicative:");
        ng = new CombinedMultiplicativeNumberGenerator(new Integer[]{1, 3}, new Integer[]{3, 5}, new Integer[]{5, 7}, 7);
        vals = ng.generate(20);
        System.out.println(vals);
        System.out.println("KS:" + Validator.Smirnov(vals, .05d));
        System.out.println("CHISQ: " + Validator.ChiSq(vals, .05f));

        System.out.println("Failed HullDobel");
        // Fail Hull Dobell
        ng = new MultiplicativeCongruenceGenerator(5, 5, 7);
        vals = ng.generate(7);
        System.out.println(vals);
        System.out.println("KS:" + Validator.Smirnov(vals, .05d));
        System.out.println("CHISQ: " + Validator.ChiSq(vals, .05f));

    }
}
