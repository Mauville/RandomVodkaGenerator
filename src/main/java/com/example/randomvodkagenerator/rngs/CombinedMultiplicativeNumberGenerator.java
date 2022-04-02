package com.example.randomvodkagenerator.rngs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class CombinedMultiplicativeNumberGenerator implements FloatifyBehaviour, CheckableNumberGenerator {

    private final ArrayList<Integer> multiplicators;
    private final ArrayList<Integer> modulos;
    private ArrayList<ArrayList<Integer>> table;

    int m1;

    public CombinedMultiplicativeNumberGenerator(ArrayList<Integer> multiplicators, ArrayList<Integer> modulos) {
        if (multiplicators.size() != modulos.size()) {
            throw new InvalidParameterException("Mismatched number of constants: Multiplicators=" + multiplicators.size() + " Modulos=" + modulos.size());
        }
        m1 = modulos.get(0);
        this.multiplicators = multiplicators;
        this.modulos = modulos;
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
        return result.stream().map(xi -> xi == 0 ? (float) ((m1 - 1) / m1) : (float) (xi / m1)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean validate(CheckTypes c) {
        return false;
    }

    @Override
    public ArrayList<Float> generate(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        populateTable();
        // TODO TEST
        for (int i = 0; i < 100; i++) {
            int temp = table.get(0).get(i);
            for (int j = 1; j < multiplicators.size(); j++) {
                temp -= table.get(j).get(i);
            }
            result.add(temp);
        }
        return floatify(result);

    }

    // FIXME Nasty function
    private void populateTable() {
        // TODO how do u pick a seed for these generators?
        ArrayList<NumberGenerator> generators = new ArrayList<>();
        for (int i = 0; i < multiplicators.size(); i++) {
            int seed = (ThreadLocalRandom.current().nextInt(0, 10 + 1));
            generators.add(new MultiplicativeCongruenceGenerator(seed, multiplicators.get(i), modulos.get(i)));
            //FIXME hardcoded 100 value
            //FIXME values need to be re-int'ed
            table.add((ArrayList<Integer>) generators.get(i).generate(100).stream().mapToInt(x -> (int) (x * 1000)).boxed().collect(Collectors.toList()));
        }
    }
}
