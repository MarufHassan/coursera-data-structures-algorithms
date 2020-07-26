import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmpPrevious = previous;
            previous = current;
            current = tmpPrevious + current;
        }

        return current % 10;
    }

    private static int getFibonacciLastDigitAccurate(int n) {
        if (n <= 1)
            return n;
        
        int previous = 0;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            int tmpPrevious = previous;
            previous = current;
            current = (current + tmpPrevious) % 10;
        }
        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitAccurate(n);
        System.out.println(c);
    }
}

