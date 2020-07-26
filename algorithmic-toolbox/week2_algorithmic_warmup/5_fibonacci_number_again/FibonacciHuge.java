import java.util.*;

public class FibonacciHuge {
    private static int pisanoLength(int m) {
        int l = 1;
        int previousR = 0;	// previous remainder
        int currentR = 1;	// current remainder

        while (true) {
        	int tmpPreviousR = previousR;
        	previousR = currentR;
        	currentR = (tmpPreviousR + currentR) % m;
        	if (previousR == 0 && currentR == 1)
        		break;
        	l++;
        }
        
        return l;
    }

    private static int getFibonacciHugeFast(long n, int m) {
    	if (n <= 1)
            return (int) n;

        int limit = (int) (n % (long) pisanoLength(m));
        if (limit <= 0)
        	return 0;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < limit - 1; ++i) {
            int tmpPrevious = previous;
            previous = current;
            current = (tmpPrevious + current) % m;
        }

        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(getFibonacciHugeFast(n, m));

    }
}

