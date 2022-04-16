package com.example.randomvodkagenerator.rngs;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class CombinedMultiplicativeNumberGenerator implements FloatifyBehaviour, NumberGenerator {

    private final ArrayList<Integer> seeds;
    private final ArrayList<Integer> multiplicators;
    private final ArrayList<Integer> modulos;
    private final int self_modulo;
    private ArrayList<List<Integer>> table = new ArrayList<>();


    public CombinedMultiplicativeNumberGenerator(Integer[] seeds, Integer[] multiplicators, Integer[] modulos, int self_modulo) {
        if (multiplicators.length != modulos.length) {
            throw new InvalidParameterException("Mismatched number of constants: Multiplicators=" + multiplicators.length + " Modulos=" + modulos.length);
        }
        this.multiplicators = new ArrayList<>(Arrays.asList(multiplicators));
        this.modulos = new ArrayList<>(Arrays.asList(modulos));
        this.seeds = new ArrayList<>(Arrays.asList(seeds));
        this.self_modulo = self_modulo;
    }

    public BigInteger getPeriod() {
        BigInteger top = BigInteger.valueOf(0);
        for (Integer m : modulos
        ) {
            top = top.multiply(BigInteger.valueOf(m - 1));
        }
        return top.divide(BigInteger.valueOf(2).pow(modulos.size()));
    }

    @Override
    public ArrayList<Float> floatify(ArrayList<Integer> result) {
        Float m1 = Float.valueOf(modulos.get(0));
        return result.stream().map(xi -> xi == 0 ? ((m1 - 1) / m1) :  (xi / m1)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Float> generate(int n) {
        return floatify(rawGenerate(n));
    }

    @Override
    public ArrayList<Integer> rawGenerate(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        populateTable(n);
        // TODO bound with period
        for (int i = 0; i < n; i++) {
            int temp = table.get(0).get(i);
            for (int j = 1; j < table.size(); j++) {
                temp -= table.get(j).get(i);
            }
            // FIXME teacher's modulo doesn't work as it should in Java
            result.add(temp % self_modulo);
        }
        return result;
    }

    private void populateTable(int entries) {
        ArrayList<NumberGenerator> generators = new ArrayList<>();
        for (int i = 0; i < multiplicators.size(); i++) {
            generators.add(new MultiplicativeCongruenceGenerator(seeds.get(i), multiplicators.get(i), modulos.get(i)));
            table.add(generators.get(i).rawGenerate(entries));
        }
    }
}
