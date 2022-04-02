package com.example.randomvodkagenerator.rngs;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MidSquaresNumberGenerator extends BasicNumberGeneratorBehaviour {
    int seed;

    public MidSquaresNumberGenerator(int seed) {
        int lenN = (int) (Math.log10(seed) + 1);
        if (lenN != 4) {
            throw new InvalidParameterException("Seed must be 4 digit.");
        }
        this.seed = seed;
    }

    public int nextInteger(int seed) {
        // Square the seed
        int n = (int) Math.pow(seed, 2);
        // Reduce length to 8 nums
        int lenN = (int) (Math.log10(n) + 1);
        while (lenN > 8) {
            n /= 10;
            lenN = (int) (Math.log10(n) + 1);
        }
        // Trim 2 right digits
        n /= 100;
        // Get the next 4 digits
        n %= 10000;
        // Convert to float
        return n;
    }

    /**
     * @param ints An Integer arraylist to convert to floats
     * @return The ArrayList, converted to Floats
     */
    public ArrayList<Float> floatify(ArrayList<Integer> ints) {
        return (ArrayList<Float>) ints.stream().map(Float::valueOf).collect(Collectors.toList());
    }
}
