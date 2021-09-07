package project.euler;

import java.util.Scanner;

/**
 * @see "https://www.hackerrank.com/contests/projecteuler/challenges/euler001/problem"
 */
public class MultiplesOfThreeAndFive {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        while (number-- > 0) {
            long n = scan.nextLong();
            Arithmetic arithmetic = new Arithmetic(n - 1);
            long sum = arithmetic.sumOfMultiplesOf3And5();
            System.out.println(sum);
        }
    }

    static class Arithmetic {
        long number;

        Arithmetic(long number) {
            this.number = number;
        }

        /**
         * Sum of multiple 3 and 5 may contain duplicate value
         * cause some values are divisible by both 3 and 5.
         * Therefore, the total need to be deducted from sum of multiple 15.
         *
         * @return
         */
        long sumOfMultiplesOf3And5() {
            return sumOfMultiple(3) + sumOfMultiple(5) - sumOfMultiple(15);
        }

        /**
         * Sum of multiple of value x from 1 to N
         *
         * @param x
         * @return
         */
        long sumOfMultiple(long x) {
            return x * sum(number / x);
        }

        /**
         * Triangle formula: sum of 1 to N
         *
         * @param n
         * @return
         */
        long sum(long n) {
            return n * (n + 1) / 2;
        }
    }
}
