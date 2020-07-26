import java.util.*;

public class FibonacciSumSquares {
    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmpPrevious = previous;
            previous = current;
            current = tmpPrevious + current;
            sum += current * current;
        }

        return sum % 10;
    }

    // sum of squares of n-th fibonacci numbers
    // (f0)^2 + (f1)^2 + (f2)^2 + (f3)^2 + (f4)^2 + .... + (fn)^2
    // = fib(n) * fib(n + 1)
    private static int getFibonacciSumSquaresFast(long n) {
        int sum = 0;

        n = (int) (n % 60); // pisanoLength(10) = 60

        int current = 0;
        int next = 1;

        for (int i = 0; i < n; i++) {
        	int tmpCurrent = next;
        	next = (current + next) % 10;
        	current = tmpCurrent;
        }

        sum = (current * next) % 10;
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(getFibonacciSumSquaresFast(n));
    }
}

