package com.example.randomvodkagenerator.rngs;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CongruentialGenerator implements CheckableGenerator {
    int seed;
    int a;
    int c;
    int m;
    boolean multiplicative;

    @Override
    public boolean validate(CheckTypes c) {
        // TODO
        return false;
    }

    /**
     * Get Greatest Common Divisor n1 and n2 using Euclydean method
     *
     */
    int GCD(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return GCD(n2, n1 % n2);
    }

    /**
     * Uses Erasthostenes sieve for generating primes from [0,n] efficiently
     *
     * @param n Number of primes
     * @return ArrayList containing primes [0,n]
     */
    public static ArrayList<Integer> generatePrimesTillN(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    /**
     * Execute a Hull Dobel Test
     * Will throw an InvalidParameterException whenever one of the following tests fails:
     * Check 4%m == 0 and 4%(a-1) == 0
     * Assert GCD(m,a) == 1
     * For all the prime numbers from 0 to max(a,m), check if any p%m == 0 and p%(a-1) == 0
     *
     * @param a Addition constant
     * @param m Modulo constant
     */
    private void HullDobelTest(int a, int m) {
        // Check 4%m == 0 and 4%a == 0
        if (!(4 % m == 0 && 4 % (a - 1) == 0))
            throw new InvalidParameterException("Hull Dobel Failed: Division by 4");
        // Assert GCD == 1
        if (GCD(a, m) != 1)
            throw new InvalidParameterException("Hull Dobel Failed: GCD");
        // Generate primes until max(a,m) and check rule for every prime. Fail if rule not satisfied
        ArrayList<Integer> primes = generatePrimesTillN(Math.max(a, m));
        for (Integer prime : primes
        ) {
            if ((prime % m == 0 && prime % (a - 1) == 0))
                return;

        }
        throw new InvalidParameterException("Hull Dobel Failed: Prime");
    }

    public CongruentialGenerator(int seed, int a, int c, int m, boolean HullDobell, boolean multiplicative) {
        if (HullDobell) {
            HullDobelTest(a, c);
        }
        this.seed = seed;
        this.a = a;
        this.c = c;
        this.m = m;
        this.multiplicative = multiplicative;
    }

    @Override
    public ArrayList<Float> generate(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(seed);
        // Define the function to use for iterations. Either Multiplicative or Simple Congruence
        Function<Integer, Integer> op = multiplicative ? (x1 -> (a * x1) % m) : (x1 -> (a * x1 + c));
        for (int i = 0; i < n; i++) {
            int next = op.apply(result.get(i));
            if (next == seed) {
                return floatify(result);
            }
            result.add(next);
        }
        return floatify(result);
    }

    private ArrayList<Float> floatify(ArrayList<Integer> result) {
        return (ArrayList<Float>) result.stream().map(x -> (float) x / m).collect(Collectors.toList());
    }
}
