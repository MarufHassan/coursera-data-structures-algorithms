# 1. Money Change

## Problem Description

**Task.** The goal in this problem is to find the minimum number of coins needed to change the input value (an integer) into coins with denominations 1, 5, and 10.

**Input Format.** The input consists of a single integer _m_.

**Constraints.** 1 ≤ _m_ ≤ 10<sup>3</sup>.

**Output Format.** Output the minimum number of coins with denominations 1, 5, 10 that changes _m_.

**Sample 1.**

```
    Input
        2
    Output
        2
```

**Sample 2.**

```
    Input
        28
    Output
        6
```

## Solution

**Approach 1: Simple Arithmetic**


```java
private static int getChange(int m) {
    int count = m / 10;
    m = m % 10;
    count += m / 5;
    m = m % 5;
    count += m / 1;
    return count;
}
```

**Approach 2: Greedy**


```java
private static int getChangeGreedy(int m) {
    int changes = 0, count = 0;
    int[] denominations = {1, 5, 10};
    int max = denominations.length - 1; // index pointing to next max denominations

    while (changes < m) {
        while (changes <= m && (m - changes) >= denominations[max]) {
            changes += denominations[max];
            count++;
        }
        max = max - 1;
    }
    return count;
}
```
## Test

Compile with `javac Change.java` and run with `java Change`.

```java
import java.util.*;

public class Change {
    private static int getChange(int m) {
        /* see previous code */
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

```

**This is only for discussion and communication. Please don't use this for submission of assignments.**