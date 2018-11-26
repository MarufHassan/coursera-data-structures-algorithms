# 3. Greatest Common Divisor

## Problem Description

**Task.** Given two integers _a_ and _b_, find their greatest common divisor.

**Input Format.** The two integers _a_, _b_ are given in the same line separated by space.

**Constraints.** 1 ≤ _a_, _b_ ≤ 2 · 10<sup>9</sup>.

**Output Format.** Output GCD(_a_, _b_)

**Sample 1.**

```
    Input
        18 35
    Output
        1
```

**Sample 2.**

```
    Input
        28851538 1183019
    Output
        17657
```

## Solution

**Approach 1: Iterative (Naive)**


```java
private static int gcdNaive(int a, int b) {
    int currentGcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
        if (a % d == 0 && b % d == 0) {
            if (d > currentGcd) {
                currentGcd = d;
            }
        }
    }
    return currentGcd;
}
```
**Approach 2: Recursive (Fast)**


```java
private static int gcdFast(int a, int b) {
    if (b <= 0)
        return a;
    return gcdFast(b, a % b);
}
```
## Test

Compile with `javac GCD.java` and run with `java GCD`.

```java
import java.util.*;

public class GCD {

    private static int gcdFast(int a, int b) {
        /* see previous code */
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(gcdFast(a, b));
    }
}

```

**This is only for discussion and communication. Please don't use this for submission of assignments.**