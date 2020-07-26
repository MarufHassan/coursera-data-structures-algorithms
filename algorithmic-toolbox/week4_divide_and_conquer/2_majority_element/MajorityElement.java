import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int lo, int hi) {
    	if (hi <= lo)
    		return a[lo];
    	int mid = lo + (hi - lo) / 2;
    	int lElement = getMajorityElement(a, lo, mid);
    	int rElement = getMajorityElement(a, mid + 1, hi);

    	if (lElement == rElement)
    		return lElement;

    	int lcount = frequency(a, lElement, lo, hi);
    	int rcount = frequency(a, rElement, lo, hi);

    	if (lcount > Math.ceil((hi - lo + 1) / 2))
    		return lElement;
    	if (rcount > Math.ceil((hi - lo + 1) / 2))
    		return rElement;

    	return -1;	// no majority element
    }

    private static int frequency(int[] a, int element, int lo, int hi) {
    	int cnt = 0;
    	for (int i = lo; i <= hi; i++) {
    		if (a[i] == element)
    			cnt++;
    	}
    	return cnt;
    }

    private static int getMajorityElement(int[] a) {
    	int ans = -1;
    	ans = getMajorityElement(a, 0, a.length - 1);
    	return ans;
    }

	private static int getMajorityElementNonRecursive(int[] a) {
    	Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
    	int ans = -1;

    	for (int i = 0; i < a.length; i++) {
    		frequency.put(a[i], frequency.getOrDefault(a[i], 0) + 1);
    	}

    	for (int key : frequency.keySet()) {
    		int value = frequency.get(key);
    		if (value > a.length / 2) {
    			ans = value;
    			break;
    		}
    	}
    	return ans;
    }

    static void stressTest() {
    	Random r = new Random();

    	int n = 10;
        int[] a = new int[n];
        while (true) {
        	for (int i = 0; i < n; i++) {
	            a[i] = r.nextInt(5);
	        }
	        int result1 = getMajorityElementNonRecursive(a) != -1 ? 1 : 0;
	        int result2 = getMajorityElement(a) != -1 ? 1 : 0;
	        if (result1 != result2) {
	        	for (int i = 0; i < n; i++) {
	        		System.out.printf("%d ", a[i]);
	        	}
	        	System.out.println();
	        	System.out.printf("Correct Answer: %d\n", result1);
	        	System.out.printf("Found Answer  : %d\n", result2);
	        	break;
	        }
	        System.out.println("Correct");
        }
    }

    public static void main(String[] args) {
    	//stressTest();
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

