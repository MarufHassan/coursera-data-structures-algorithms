# 1. Maximum Amount of Gold

## Problem Description

**Task.** Given _n_ gold bars, find the maximum weight of gold that fits into a bag of capacity _W_.

**Input Format.** The first line of the input contains the capacity _W_ of a knapsack and the number _n_ of bars of gold. The next line contains _n_ integers _w_<sub>_0_</sub>, _w_<sub>_1_</sub>, . . . , _w_<sub>_n-1_</sub> defning the weights of the bars of gold.

**Constraints.** 1 ≤ _W_ ≤ 10<sup>4</sup>; 1 ≤ _n_ ≤ 300; 0 ≤ _w_<sub>_0_</sub>, . . . , _w_<sub>_n-1_</sub> ≤ 10<sup>5</sup>.

**Output Format.** Output the maximum weight of gold that fts into a knapsack of capacity _W_.

**Sample 1.**

```
    Input
        10 3
        1 4 8
    Output
        9
```


## Solution

```java
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
```

```java
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
```

## Test

Compile with `javac Knapsack.java` and run with `java Knapsack`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**