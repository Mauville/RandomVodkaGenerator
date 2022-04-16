package com.example.randomvodkagenerator;

import com.example.randomvodkagenerator.rngs.CombinedMultiplicativeNumberGenerator;
import com.example.randomvodkagenerator.rngs.NumberGenerator;
import com.example.randomvodkagenerator.rngs.Validator;

import java.util.ArrayList;

public class Menu {
    public static void display() {
        System.out.println("Menu loop goes here :)");

        // example code
        NumberGenerator ng = new CombinedMultiplicativeNumberGenerator(new Integer[]{1, 3}, new Integer[]{3, 5}, new Integer[]{5, 7}, 7);
        ArrayList<Float> vals = ng.generate(20);
        System.out.println(vals);
        System.out.println(Validator.ChiSq(vals, .05f));
    }
}
