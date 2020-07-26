import java.util.*;
import java.io.*;

public class LCS3 {
	private static int[][][] table(int[] x, int[] y, int[] z) {
        int m = x.length;
        int n = y.length;
        int l = z.length;

        int[][][] table = new int[m + 1][n + 1][l + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= l; k++) {
                	if (i == 0 || j == 0 || k == 0)
                		table[i][j][k] = 0;
                	else if (x[i - 1] == y[j - 1] && y[j - 1] == z[k - 1])
                		table[i][j][k] = table[i - 1][j - 1][k - 1] + 1;
                	else
                		table[i][j][k] = 	Math.max(table[i - 1][j][k], 
                							Math.max(table[i][j - 1][k], table[i][j][k - 1]));
                }
            }
        }
        return table;
    }

    private static int lcsLength(int[] a, int[] b, int[] c) {
    	int[][][] lcs = table(a, b, c);
    	return lcs[a.length][b.length][c.length];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcsLength(a, b, c));
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