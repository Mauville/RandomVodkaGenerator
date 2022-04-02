package com.example.randomvodkagenerator.rngs;

import java.util.ArrayList;
import java.util.Collections;

public class Validator {
    public static boolean ChiSq(ArrayList<Float> vals, float a) {
        Collections.sort(vals);
        int n = vals.size();
        int range = Math.round(Collections.max(vals) - Collections.min(vals));
        int k = Math.round((float) (1 + 3.222 * Math.log10(n)));
        float _class = (float) range / k;
        // TODO is lambda the correct way for this thing?
        float lambda = (float) vals.stream().mapToDouble(x -> x).sum() / n;
        // segment array into classes
        // homogenize classes
        // create the Prob col
        // create the freq col
        // create the FE col
        // create the chi col
        // get sum(chi)
        // get chi
        // return sum(chi)<chi
        return false;
    }

    public static boolean Smirnov(ArrayList<Float> vals, float a) {
        Collections.sort(vals);
        int n = vals.size();
        // find max D+ and max d-
        float dMinus = Float.NEGATIVE_INFINITY;
        float dPlus = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            // |r(i) - i-1/n
            dMinus = Math.max(dMinus, Math.abs(vals.get(i) - ((i - 1f) / n)));
            // |(i/n) - r(i)|
            dPlus = Math.max(dPlus, ((float) i / n) - vals.get(i));
        }
        // find MaxD
        float d = Math.max(dMinus, dPlus);
        // TODO Check a,N on table/function
        // float ks = ValidatorMath.KS(a,n);
        // return d<ks

        return false;
    }
}
