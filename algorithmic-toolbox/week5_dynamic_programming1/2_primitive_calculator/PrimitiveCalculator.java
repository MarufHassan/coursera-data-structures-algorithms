import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimalSequenceDP(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int[] minOperation = new int[n + 1];
        int[] path = new int[n + 1];

        for (int op = 2; op <= n; op++) {
            int min = Integer.MAX_VALUE;
            if (op % 3 == 0 && min > minOperation[op / 3] + 1) {
                min = minOperation[op / 3] + 1;
                path[op] = op / 3;
            } 
            if (op % 2 == 0 && min > minOperation[op / 2] + 1) {
                min = minOperation[op / 2] + 1;
                path[op] = op / 2;
            }
            if (min > minOperation[op - 1] + 1) {
                min = minOperation[op - 1] + 1;
                path[op] = op - 1;
            }
            minOperation[op] = min;
        }

        while (n >= 1) {
            sequence.add(n);
            n = path[n];
        }
        Collections.reverse(sequence);

        return sequence;
    }

    private static List<Integer> optimalSequenceGreedy(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimalSequenceDP(n);
        System.out.println(sequence.size() - 1);
        for (int x : sequence) {
            System.out.print(x + " ");
        }
    }
}

