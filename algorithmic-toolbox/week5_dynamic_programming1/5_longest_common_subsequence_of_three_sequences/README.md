# 5. Longest Common Subsequence of Three Sequences

## Problem Description

**Task.** Compute the length of a longest common subsequence of three sequences.

**Input Format.** First line: _n_. Second line: _a_<sub>_1_</sub>, _a_<sub>_2_</sub>, . . . , _a_<sub>_n_</sub>. Third line: _m_. Fourth line: _b_<sub>_1_</sub>, _b_<sub>_2_</sub>, . . . , _b_<sub>_m_</sub>. Fifth line: _l_. Sixth line: _c_<sub>_1_</sub>, _c_<sub>_2_</sub>, . . . , _c_<sub>_l_</sub>.

**Constraint.** 1 ≤ _n_, _m_, _l_ ≤ 100; −10<sup>9</sup> < _a_<sub>_i_</sub>, _b_<sub>_i_</sub>, _c_<sub>_i_</sub> < 10<sup>9</sup>.

**Output Format.** Output _p_.

**Sample 1.**

```
    Input
        3
        1 2 3
        3
        2 1 3
        3
        1 3 5
    Output
        2
```

**Sample 2.**

```
    Input
        5
        8 3 2 1 7
        7
        8 2 1 3 8 10 7
        6
        6 8 3 1 4 7
    Output
        3
```

## Solution

```java
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
                    table[i][j][k] =    Math.max(table[i - 1][j][k], 
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
```

## Test

Compile with `javac LCS3.java` and run with `java LCS3`.

```java
import java.util.*;
import java.io.*;

public class LCS3 {
    private static int[][][] table(int[] x, int[] y, int[] z) {
        /* see previous code */
    }

    private static int lcsLength(int[] a, int[] b, int[] c) {
        /* see previous code */
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
```

**Faster I/O**

```java
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
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**