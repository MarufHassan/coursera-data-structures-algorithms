import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearch(int[] a, int key) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
        	int mid = low + (high - low) / 2;

        	if 		(key < a[mid])	high = mid - 1;
        	else if (key > a[mid])  low = mid + 1;
        	else 					return mid;
        }
        return -1;
    }

    static int binarySearchRecursive(int[] a, int low, int high, int key) {
    	if (low > high) 	return -1;

    	int mid = low + (high - low) / 2;
    	if (key < a[mid])
    		return binarySearchRecursive(a, low, mid - 1, key);
    	else if (key > a[mid])
    		return binarySearchRecursive(a, mid + 1, high, key);
    	else
    		return mid;
    }

    static int binarySearchRecursive(int[] a, int key) {
    	return binarySearchRecursive(a, 0, a.length - 1, key);
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            pw.print(binarySearch(a, b[i]) + " ");
        }
        pw.close();
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
