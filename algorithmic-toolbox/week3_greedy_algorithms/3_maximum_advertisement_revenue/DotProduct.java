import java.util.*;

public class DotProduct {
	// ppc = profit per click
	// cpd = click per day
    private static long maxDotProduct(Integer[] ppc, Integer[] cpd) {
    	long revenue = 0;
    	int slots = ppc.length;

    	Arrays.sort(ppc, Collections.reverseOrder());
    	Arrays.sort(cpd, Collections.reverseOrder());

    	for (int slot = 0; slot < slots; slot++) { // for every slot
    		revenue += ((long)ppc[slot] * cpd[slot]);
    	}
        return revenue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

