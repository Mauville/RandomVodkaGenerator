package com.example.randomvodkagenerator.rngs;

import java.util.ArrayList;

public interface NumberGenerator {
    /**
     * Generate n random numbers, between [0,1]
     */
    public ArrayList<Float> generate(int n);

    /**
     * Generate n random numbers
     */
    public ArrayList<Integer> rawGenerate(int n);
}
