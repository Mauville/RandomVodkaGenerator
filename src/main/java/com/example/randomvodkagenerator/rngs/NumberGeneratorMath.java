package com.example.randomvodkagenerator.rngs;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class NumberGeneratorMath {
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
     * Get Greatest Common Divisor n1 and n2 using Euclydean method
     */
    static int GCD(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return GCD(n2, n1 % n2);
    }

    /**
     * Execute a Hull Dobel Test
     * <p>
     * Will throw an InvalidParameterException whenever one of the following tests fails:
     * Check 4%m == 0 and 4%(a-1) == 0
     * Assert GCD(m,a) == 1
     * For all the prime numbers from 0 to max(a,m), check if any p%m == 0 and p%(a-1) == 0
     *
     * @param a Addition constant
     * @param m Modulo constant
     */
    protected static void HullDobelTest(int a, int c, int m) {
        // Check 4%m == 0 and 4%a == 0
        if (!(m % 4 == 0 && (a - 1) % 4 == 0))
            throw new InvalidParameterException("Hull Dobel Failed: Division by 4");
        // Assert GCD == 1
        if (NumberGeneratorMath.GCD(c, m) != 1)
            throw new InvalidParameterException("Hull Dobel Failed: GCD");
        // Generate primes until max(a,m) and check rule for every prime. Fail if rule not satisfied
        ArrayList<Integer> primes = NumberGeneratorMath.generatePrimesTillN(Math.max(a, m));
        for (Integer prime : primes
        ) {
            if ((m % prime == 0 && (a - 1) % prime == 0))
                return;

        }
        throw new InvalidParameterException("Hull Dobel Failed: Prime");
    }
}
