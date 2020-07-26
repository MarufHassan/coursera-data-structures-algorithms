import java.util.*;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long newCurrent = next;
            next = next + current;
            current = newCurrent;
        }

        return sum % 10;
    }

    // uses the fibonacci property that 
    // last digit of the sum of 60 consecutive Fibonacci numbers is always 0
    private static long getFibonacciPartialSumFast(long from, long to) {
        long sum = 0;

        // Simplify the input arguments, as the last digit pattern repeats with a period of 60, 
        // and the sum of 60 such consecutive numbers is 0 mod 10
        int m = (int) (from % 60); // pisanoLength(10) = 60
        int n = (int) (to % 60);

        // make sure n is greater than m
        if (n < m)
            n += 60;

        long current = 0;
        long next  = 1;

        for (int i = 0; i <= n; ++i) {
            if (i >= m) {
                sum += current;
            }

            long newCurrent = next;
            next = next + current;
            current = newCurrent;
        }

        return (int) (sum % 10);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumFast(from, to));
    }
}

