# 3. Improving Quick Sort

## Problem Description

**Task.** To force the given implementation of the quick sort algorithm to efficiently process sequences with few unique elements, your goal is replace a 2-way partition with a 3-way partition. That is, your new partition procedure should partition the array into three parts: < _x_ part, = _x_ part, and > _x_ part.

**Input Format.** The frst line of the input contains an integer _n_. The next line contains a sequence of _n_ integers _a_<sub>_0_</sub>, _a_<sub>_1_</sub>, . . . , _a_<sub>_n-1_</sub>.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>5</sup>; 1 ≤ _a_<sub>_i_</sub> ≤ 10,<sup>9</sup> for all 0 ≤ _i_ < _n_.

**Output Format.** Output this sequence sorted in non-decreasing order.

**Sample 1.**

```
    Input
        5
        2 3 9 2 2
    Output
        2 2 2 3 9
```

## Solution

**Approach 1: Quick sort with 2-way partitioning**

```java
private static int partition2(int[] a, int lo, int hi) {
    int x = a[lo];
    int j = lo;
    for (int i = lo + 1; i <= hi; i++) {
        if (a[i] <= x) {
            exch(a, i, ++j);
        }
    }
    exch(a, lo, j);
    return j;
}

private static void randomizedQuickSort(int[] a, int lo, int hi) {
    if (lo >= hi) {
        return;
    }
    int k = random.nextInt(hi - lo + 1) + lo;
    exch(a, lo, k);

    int m = partition2(a, lo, hi);

    randomizedQuickSort(a, lo, m - 1);
    randomizedQuickSort(a, m + 1, hi);
}
```

**Approach 2: Quick sort with 3-way partitioning**


```java
private static int[] partition3(int[] a, int lo, int hi) {
    int x = a[lo];  //
    int lt = lo, gt = hi;
    int i = lo + 1;

    while (i <= gt) {
        if      (a[i] < x)  exch(a, lt++, i++);
        else if (a[i] > x)  exch(a, i, gt--);
        else                i++;
    }
    return new int[] {lt, gt};
}

private static void randomizedQuickSort(int[] a, int lo, int hi) {
    if (lo >= hi) {
        return;
    }
    int k = random.nextInt(hi - lo + 1) + lo;
    exch(a, lo, k);

    // int m = partition2(a, lo, hi);
    int[] p = partition3(a, lo, hi);

    randomizedQuickSort(a, lo, p[0] - 1);
    randomizedQuickSort(a, p[1] + 1, hi);
}
```

**Helper Methods**
```java
private static void exch(int[] a, int i, int j) {
    int swap = a[i];
    a[i] = a[j];
    a[j] = swap;
}
```

**Random Number Generation**
```java
private static Random random = new Random();
```

## Test

Compile with `javac Sorting.java` and run with `java Sorting`.

```java
import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

    private static void randomizedQuickSort(int[] a, int lo, int hi) {
        /* see previous code */
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
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
```


**This is only for discussion and communication. Please don't use this for submission of assignments.**