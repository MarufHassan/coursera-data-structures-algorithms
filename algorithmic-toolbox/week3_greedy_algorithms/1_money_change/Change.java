import java.util.*;

public class Change {
    private static int getChange(int m) {
    	int count = m / 10;
    	m = m % 10;
    	count += m / 5;
    	m = m % 5;
    	count += m / 1;
        return count;
    }

    private static int getChangeGreedy(int m) {
    	int changes = 0, count = 0;
    	int[] denominations = {1, 5, 10};
    	int max = denominations.length - 1; // index pointing to next max denominations

    	while (changes < m) {
    		while (changes <= m && (m - changes) >= denominations[max]) {
    			changes += denominations[max];
    			count++;
    		}
    		max = max - 1;
    	}
    	return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

