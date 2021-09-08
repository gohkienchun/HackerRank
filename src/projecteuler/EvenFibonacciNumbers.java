package projecteuler;

import java.util.Scanner;

/**
 * @see "https://www.hackerrank.com/contests/projecteuler/challenges/euler002/problem"
 */
public class EvenFibonacciNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = scan.nextInt();
        while (count-- > 0) {
            long number = scan.nextLong();
            Fibonacci fibonacci = new Fibonacci(number);
            long sum = fibonacci.sumOfEven();
            System.out.println(sum);
        }
        scan.close();
    }

    static class Fibonacci {
        long number;

        Fibonacci(long number) {
            this.number = number;
        }

        /**
         * Given the first seed is 0 and second seed is 2,
         * the Nth even number is determined as follows:
         *  E(n) = 4E(n - 1) + E(n - 2)
         *
         * @return
         */
        long sumOfEven() {
            long sum = 0;
            long first = 0;
            long second = 2;

            while (second <= number) {
                sum += second;

                long next = 4 * second + first;
                first = second;
                second = next;
            }
            return sum;
        }
    }
}
