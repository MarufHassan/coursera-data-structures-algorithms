# 2. Maximum Pairwise Product

## Problem Description

**Task.** Find the maximum product of two distinct numbers in a sequence of non-negative integers.

**Input Format.** The first line contains an integer _n_. The next line contains _n_ non-negative integers _a_<sub>1</sub>,...,_a_<sub>_n_</sub> (separated by spaces).

**Constraints.** 2 ≤ _n_ ≤ 2 · 10<sup>5</sup>; 0 ≤ _a_<sub>1</sub>,...,_a_<sub>_n_</sub> ≤ 2 · 10<sup>5</sup>.

**Output Format.** The maximum pairwise product.

**Sample 1.**

```
    Input
        3
        1 2 3
    Output
        6
```

**Sample 2.**

```
    Input
        10
        7 5 14 2 8 8 10 1 2 3
    Output
        140
```

## Solution

**Approach 1:**


```java
private static long getMaxPairwiseProduct(int[] numbers) {
    long maxProduct = 0;
    long product;
    int n = numbers.length;
    for (int first = 0; first < n; ++first) {
        for (int second = first + 1; second < n; ++second) {
            product = (long) numbers[first] * numbers[second];
            maxProduct = Math.max(maxProduct, product);
        }
    }

    return maxProduct;
}
```
**Approach 2:**


```java
private static long getMaxPairwiseProductFast(int[] numbers) {
    int n = numbers.length;

    int index = 0;
    for (int i = 1; i < n; i++) {
        if (numbers[index] < numbers[i])
            index = i;
    }
    swap(numbers, index, n - 1);

    index = 0;
    for (int i = 1; i < n - 1; i++) {
        if (numbers[index] < numbers[i])
            index = i;
    }
    swap(numbers, index, n - 2);
    
    return (long) numbers[n - 1] * numbers[n - 2];
}

private static void swap(int[] numbers, int i, int j) {
    int t = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = t;
}
```
## Test

Compile with `javac MaxPairwiseProduct.java` and run with `java MaxPairwiseProduct`.

```java
public class MaxPairwiseProduct {
    private static long getMaxPairwiseProductFast(int[] numbers) {
        /* see previous code */
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductFast(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
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