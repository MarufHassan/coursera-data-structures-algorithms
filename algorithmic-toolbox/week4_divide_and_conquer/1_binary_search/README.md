# 1. Binary Search

## Problem Description

**Task.** The goal in this code problem is to implement the binary search algorithm.

**Input Format.** The frst line of the input contains an integer _n_ and a sequence _a_<sub>_0_</sub> < _a_<sub>_1_</sub> < . . . < _a_<sub>_n-1_</sub> of _n_ pairwise distinct positive integers in increasing order. The next line contains an integer _k_ and _k_ positive integers _b_<sub>_0_</sub>, _b_<sub>_1_</sub>, ..., _b_<sub>_k-1_</sub>.

**Constraints.** 1 ≤ _n_, _k_ ≤ 10<sup>4</sup>; 1 ≤ _a_<sub>_i_</sub> ≤ 10<sup>9</sup> for all 0 ≤ _i_ < _n_; 1 ≤ _b_<sub>_j_</sub> ≤ 10<sup>9</sup> for all 0 ≤ _j_ < _k_;

**Output Format.** For all _i_ from 0 to _k_ − 1, output an index 0 ≤ _j_ ≤ _n_ − 1 such that _a_<sub>_j_</sub> = _b_<sub>_i_</sub> or −1 if there is no such index.

**Sample 1.**

```
    Input
        5 1 5 8 12 13
        5 8 1 23 1 11
    Output
        2 0 -1 0 -1
```

## Solution

**Approach 1: Linear Search**


```java
static int linearSearch(int[] a, int x) {
    for (int i = 0; i < a.length; i++) {
        if (a[i] == x) return i;
    }
    return -1;
}
```

**Approach 2: Recursive Binary Search**


```java
static int binarySearchRecursive(int[] a, int low, int high, int key) {
    if (low > high)     return -1;

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
```

**Approach 3: Iterative Binary Search**


```java
static int binarySearch(int[] a, int key) {
    int low = 0, high = a.length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;

        if      (key < a[mid])  high = mid - 1;
        else if (key > a[mid])  low = mid + 1;
        else                    return mid;
    }
    return -1;
}
```

## Test

Compile with `javac BinarySearch.java` and run with `java BinarySearch`.

```java
import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearch(int[] a, int key) {
        /* see previous code */
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
```



**This is only for discussion and communication. Please don't use this for submission of assignments.**