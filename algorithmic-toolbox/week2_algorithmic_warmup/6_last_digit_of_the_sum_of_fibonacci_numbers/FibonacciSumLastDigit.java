import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmpPrevious = previous;
            previous = current;
            current = tmpPrevious + current;
            sum += current;
        }

        return sum % 10;
    }

    // sum(fib(n)) = fib(n + 2) - 1
    private static int getFibonacciSumFast(long n) {
        if (n <= 1)
            return (int) n;

        int limit = (int) (n % 60); // pisanoLength(10) = 60

        int previous = 0;
        int current  = 1;
        int sum      =-1;

        for (int i = 0; i <= limit; ++i) {
            int tmpPrevious = previous;
            previous = current;
            current = (tmpPrevious + current) % 10;
        }

        sum = current != 0 ? current - 1 : 9; // safety guard if last digit of sum(fib(n + 2)) is 0
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(getFibonacciSumFast(n));
    }
}

