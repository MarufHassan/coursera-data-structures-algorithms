import java.util.*;
import java.io.*;

public class Knapsack {
    static int optimalWeightGreedy(int W, int[] w) {
        int result = 0;
        for (int i = 0; i < w.length; i++) {
          if (result + w[i] <= W) {
            result += w[i];
          }
        }
        return result;
    }

    static int optimalWeightDP(int W, int[] wt) {
    	int n = wt.length;
    	int[][] K = new int[n + 1][W + 1];

    	for (int i = 0; i <= n; i++) {
    		for (int w = 0; w <= W; w++) {
    			if (i == 0 || W == 0)
    				K[i][w] = 0;
    			else if (wt[i - 1] <= w)
    				K[i][w] = Math.max(wt[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
    			else 
    				K[i][w] = K[i - 1][w];
    		}
    	}
    	return K[n][W];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeightDP(W, w));
    }
}
class FastScanner {
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

