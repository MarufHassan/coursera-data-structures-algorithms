import java.util.*;
import java.io.*;

public class LCS2 {
    private static int[][] table(int[] x, int[] y) {
        int m = x.length;
        int n = y.length;

        int[][] table = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            table[i][0] = 0;
        }

        for (int j = 1; j <= n; j++) {
            table[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i - 1] == y[j - 1])
                    table[i][j] = table[i - 1][j - 1] + 1;
                else if (table[i - 1][j] >= table[i][j - 1])
                    table[i][j] = table[i - 1][j];
                else
                    table[i][j] = table[i][j - 1];
            }
        }
        return table;
    }

    private static int lcsLength(int[] x, int[] y) {
        int[][] lcs = table(x, y);
        return lcs[x.length][y.length];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
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

        System.out.println(lcsLength(a, b));
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

