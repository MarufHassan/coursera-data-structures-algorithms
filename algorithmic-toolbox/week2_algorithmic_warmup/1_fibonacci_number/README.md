# 1. Fibonacci Number

## Problem Description

**Task.** Given an integer _n_, find the _n_-th Fibonacci number _F_<sub>n</sub>.

**Input Format.** The input consists of a single integer _n_.

**Constraints.** 0 ≤ _n_ ≤ 45

**Output Format.** Output _F_<sub>n</sub>

**Sample 1.**

```
    Input
        10
    Output
        55
```

## Solution

**Approach 1: Recursive (Naive)**


```java
private static long calcFib(int n) {
    if (n <= 1)
        return n;

    return calcFib(n - 1) + calcFib(n - 2);
}
```
**Approach 2: Iterative (Fast)**


```java
private static int calcFib(int n) {
    if (n <= 1)
        return n;

    int previous = 0;
    int current  = 1;

    for (int i = 0; i < n - 1; ++i) {
        int tmpPrevious = previous;
        previous = current;
        current = tmpPrevious + current;
    }

    return current;
}
```
## Test

Compile with `javac Fibonacci.java` and run with `java Fibonacci`.

```java
import java.util.Scanner;

public class Fibonacci {
    private static int calcFib(int n) {
        /* see previous code */
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calcFib(n));
    }
}

```

**This is only for discussion and communication. Please don't use this for submission of assignments.**