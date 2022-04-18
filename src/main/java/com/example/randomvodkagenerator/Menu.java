package com.example.randomvodkagenerator;


import com.example.randomvodkagenerator.rngs.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("********************");
        System.out.println("RandomVodkaGenerator");
        System.out.println("********************");
        System.out.println("1) Mid Squares");
        System.out.println("2) Linear Congruence");
        System.out.println("3) Mixed Congruence");
        System.out.println("4) Multiplicative Congruence");
        System.out.println("5) Combined Congruence");
        System.out.println("********************");
        int choice = scanner.nextInt();
        System.out.println("Input seed:");
        int seed = scanner.nextInt();
        NumberGenerator numberGenerator;
        int a, c, m;
        boolean proof = true;
        switch (choice) {
            case 1:
                numberGenerator = new MidSquaresNumberGenerator(seed);
                proof = false;
                break;
            case 2:
                System.out.println("Input a,c,m:");
                a = scanner.nextInt();
                c = scanner.nextInt();
                m = scanner.nextInt();
                numberGenerator = new LinearCongruenceGenerator(seed, a, c, m);
                break;
            case 3:
                System.out.println("Input a,c,m:");
                a = scanner.nextInt();
                c = scanner.nextInt();
                m = scanner.nextInt();
                numberGenerator = new MixedCongruenceGenerator(seed, a, c, m);
                break;
            case 4:
                System.out.println("Input a,m:");
                a = scanner.nextInt();
                m = scanner.nextInt();
                numberGenerator = new MultiplicativeCongruenceGenerator(seed, a, m);
                break;
            default:
                System.out.println("Input number of generators:");
                int generators = scanner.nextInt();
                Integer[] seeds = new Integer[10];
                Integer[] mults = new Integer[10];
                Integer[] mods = new Integer[10];
                int selfmod = scanner.nextInt();
                for (int i = 0; i < generators; i++) {
                    System.out.println("Input seed, mult, and mod for generator " + i);
                    seeds[i] = scanner.nextInt();
                    mults[i] = scanner.nextInt();
                    mods[i] = scanner.nextInt();
                }
                numberGenerator = new CombinedMultiplicativeNumberGenerator(seeds, mults, mods, selfmod);
                break;

        }
        System.out.println("Input n:");
        int n = scanner.nextInt();
        ArrayList<Float> vals = numberGenerator.generate(n);
        System.out.println(vals);

        if (!proof) {
            return;
        }
        System.out.println("Input test type:");
        System.out.println("1) Kolgomorov");
        System.out.println("2) ChiSq");

        int validation = scanner.nextInt();
        System.out.println("Input alpha (0.5):");
        double alpha = scanner.nextFloat();
        switch (validation) {
            case 1:
                System.out.println(Validator.Smirnov(vals, alpha));
                break;
            case 2:
                System.out.println(Validator.ChiSq(vals, alpha));
                break;
        }
    }
}
