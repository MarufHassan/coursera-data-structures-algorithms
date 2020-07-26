import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        int sum = 0;
        int current = 1;

        while (sum < n) {
        	sum += current;
        	summands.add(current);
        	current += 1;
        }
        if (sum > n) { // exceeded
        	int size = summands.size();
        	int last = summands.remove(size - 1);
        	size = summands.size();
        	int exceed = (sum - n);
        	int element = (summands.get(size - 1) + last) - exceed;

        	summands.set(size - 1, element);
        }

        return summands;
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

