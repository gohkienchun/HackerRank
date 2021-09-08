package projecteuler;

import java.util.Scanner;

/**
 * @see "https://www.hackerrank.com/contests/projecteuler/challenges/euler003/problem"
 */
public class LargestPrimeFactor {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = scan.nextInt();
        while (count-- > 0) {
            long number = scan.nextLong();
            PrimeFactor primeFactor = new PrimeFactor();
            long largest = primeFactor.getLargestPrimeFactor(number);
            System.out.println(largest);
        }
        scan.close();
    }

    static class PrimeFactor {

        /**
         * The Fundamental Theorem of Arithmetic says that every whole
         * number greater than one is either a prime number, or the product
         * of two or more prime numbers. No matter how, or in what order,
         * you break the number down into its factors you will end up with
         * exactly the same prime factors.
         *
         * @param number
         * @return
         */
        long getLargestPrimeFactor(long number) {
            for (long factor = 2; factor <= Math.sqrt(number); factor++) {
                while (number % factor == 0 && number != factor) {
                    number /= factor;
                }
            }
            return number;
        }
    }
}
