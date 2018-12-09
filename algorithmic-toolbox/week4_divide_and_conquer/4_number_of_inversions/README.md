# 4. Number of Inversions

## Problem Description

**Task.** The goal in this problem is to count the number of inversions of a given sequence.

**Input Format.** The frst line contains an integer _n_, the next one contains a sequence of integers _a_<sub>_0_</sub>, _a_<sub>_1_</sub>, . . . , _a_<sub>_n-1_</sub>.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>5</sup>, 1 ≤  _a_<sub>_i_</sub> ≤ 10<sup>9</sup> for all 0 ≤ _i_ < _n_.

**Output Format.** Output the number of inversions in the sequence.

**Sample 1.**

```
    Input
        5
        2 3 9 2 9
    Output
        2
```

## Solution

**Approach 1: Brute Force**

```java
private static long getNumberOfInversionsNaive(int[] a) {
    long numberOfInversions = 0;
    for (int i = 0; i < a.length; i++) {
        for (int j = i + 1; j < a.length; j++) {
            if (a[i] > a[j])
                numberOfInversions++;
        }
    }
    return numberOfInversions;
}
```

**Approach 2: Divide and Conquer**


```java
private static long count(int[] a, int[] aux, int left, int mid, int right) {
    long numberOfInversions = 0;

    for (int k = left; k <= right; k++) 
        aux[k] = a[k];

    int i = left, j = mid + 1;
    for (int k = left; k <= right; k++) {
        if      (i > mid)           a[k] = aux[j++];
        else if (j > right)         a[k] = aux[i++];
        else if (aux[i] <= aux[j])  a[k] = aux[i++];
        else {
            a[k] = aux[j++];
            numberOfInversions += (mid - i + 1);
        }
    }
    return numberOfInversions;
}

private static long getNumberOfInversions(int[] a, int[] aux, int left, int right) {
    long numberOfInversions = 0;
    if (right <= left) {
        return numberOfInversions;
    }
    int mid = left + (right - left) / 2;
    numberOfInversions += getNumberOfInversions(a, aux, left, mid);
    numberOfInversions += getNumberOfInversions(a, aux, mid + 1, right);
    numberOfInversions += count(a, aux, left, mid, right);
    return numberOfInversions;
}

private static long getNumberOfInversions(int[] a) {
    int[] aux = new int[a.length];
    return getNumberOfInversions(a, aux, 0, a.length - 1);
}
```


**Stress Testing**

```java
private static void stressTesting() {
    Random r = new Random();
    int[] a = new int[10];
    while (true) {
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        long number1 = getNumberOfInversionsNaive(a);
        long number2 = getNumberOfInversions(a);
        System.out.println(number1 + " " + number2);
        if (number1 != number2) {
            for (int i = 0; i < a.length; i++) {
                System.out.printf("%d ", a[i]);
            }
            break;
        }
    }
}
```

## Run

Compile with `javac Inversions.java` and run with `java Inversions`.

```java
import java.util.*;
import java.io.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] aux, int left, int right) {
        /* see previous code */
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getNumberOfInversionsNaive(a));
    }

    static class FastScanner {
        /* see in the code */
    }
}
```


**This is only for discussion and communication. Please don't use this for submission of assignments.**