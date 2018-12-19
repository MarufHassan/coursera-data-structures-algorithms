# 4. Longest Common Subsequence of Two Sequences

## Problem Description

**Task.** Compute the length of a longest common subsequence of two sequences.

**Input Format.** First line: _n_. Second line: _a_<sub>_1_</sub>, _a_<sub>_2_</sub>, . . . , _a_<sub>_n_</sub>. Third line: _m_. Fourth line: _b_<sub>_1_</sub>, _b_<sub>_1_</sub>, . . . , _a_<sub>_m_</sub>.

**Constraint.** 1 ≤ _n_, _m_ ≤ 100; −10<sup>9</sup> < _a_<sub>_i_</sub>, _b_<sub>_i_</sub> < 10<sup>9</sup>.

**Output Format.** Output _p_.

**Sample 1.**

```
    Input
        3
        2 7 5
        2
        2 5
    Output
        2
```

**Sample 2.**

```
    Input
        1
        7
        4
        1 2 3 4
    Output
        0
```

**Sample 3.**

```
    Input
        4
        2 7 8 3
        4
        5 2 8 7
    Output
        2
```

## Solution

```java
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
```

## Test

Compile with `javac LCS2.java` and run with `java LCS2`.

```java
import java.util.*;
import java.io.*;

public class LCS2 {
    private static int[][] table(int[] x, int[] y) {
        /* see previous code */
    }

    private static int lcsLength(int[] x, int[] y) {
        /* see previous code */
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