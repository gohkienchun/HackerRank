package projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Two core manipulations:
 * 1. First loop increments steadily find the Nth fibonacci sequence.
 * 2. Second loop, split to left and right parts according to fibonacci formula,
 * then decrements steadily to find out which part contains the respective char.
 *
 * @see "https://www.hackerrank.com/contests/projecteuler/challenges/euler230/problem"
 */
public class FibonacciWords {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        while (number-- > 0) {
            String first = scan.next();
            String second = scan.next();
            BigInteger position = scan.nextBigInteger();
            Fibonacci fibo = new Fibonacci(first, second, position);
            char r = fibo.getByInitPos();
            System.out.println(r);
        }
    }

    static class Fibonacci {
        String firstSeed;
        String secondSeed;
        BigInteger weightage;

        Fibonacci(String firstSeed, String secondSeed, BigInteger weightage) {
            this.firstSeed = firstSeed;
            this.secondSeed = secondSeed;
            this.weightage = weightage;
        }

        char getByInitPos() {
            List<BigInteger> list = listFiboSeqSizes();
            return getValue(list);
        }

        /**
         * 1. Initialize sizes of first 2 seeds.
         * 2. Compute the sizes of fibonacci sequence.
         * 3. Stop computing when the increment size is greater or equals to position input.
         *
         * @return {@link List} of fibonacci sequence sizes
         */
        private List<BigInteger> listFiboSeqSizes() {
            BigInteger firstSize = BigInteger.valueOf(firstSeed.length());
            BigInteger secondSize = BigInteger.valueOf(secondSeed.length());

            List<BigInteger> list = new ArrayList<>();
            list.add(firstSize);
            list.add(secondSize);

            do {
                list.add(firstSize.add(secondSize));
                firstSize = secondSize;
                secondSize = list.get(list.size() - 1);
            } while (secondSize.compareTo(weightage) < 0);

            return list;
        }

        /**
         * Fibonacci formula: F(currentIndex - 2) + F(currentIndex - 1) = F(currentIndex)
         * Decompose list according to the fibonacci formula, and perform backward tracking.
         *
         * @param list {@link List} of fibonacci sequence sizes
         * @return Nth character in fibonacci sequence
         */
        private char getValue(List<BigInteger> list) {
            int index = list.size() - 1;

            while (index >= 0) {
                BigInteger leftWeightage = list.get(index - 2);
                if (weightage.compareTo(leftWeightage) <= 0) {
                    // belongs to left part

                    index -= 2;
                } else {
                    // belongs to right part

                    index--;

                    // left is always prefixed
                    weightage = weightage.subtract(leftWeightage);
                }

                if (index == 0) {
                    return firstSeed.charAt(weightage.intValue() - 1);
                }
                if (index == 1) {
                    return secondSeed.charAt(weightage.intValue() - 1);
                }
            }

            return '\0';
        }
    }
}
