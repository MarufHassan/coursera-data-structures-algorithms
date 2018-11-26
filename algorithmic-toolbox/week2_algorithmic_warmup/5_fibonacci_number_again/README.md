# 5. Fibonacci Number Again

## Problem Description

**Task.** Given two integers _n_ and _m_, output _F_<sub>_n_</sub> mod _m_ (that is, the remainder of _F_<sub>_n_</sub> when divided by _m_).

**Input Format.** The input consists of two integers _n_ and _m_ given on the same line (separated by a space).

**Constraints.** 1 ≤ _n_ ≤ 10<sup>18</sup>, 2 ≤ _m_ ≤ 10<sup>3</sup>.

**Output Format.** Output _F_<sub>_n_</sub> mod _m_

**Sample 1.**

```
    Input
        239 1000
    Output
        161
```

**Sample 2.**

```
    Input
        2816213588 239
    Output
        151
```

## Solution

**Approach 1: Naive**


```java
private static long getFibonacciHugeNaive(long n, long m) {
    if (n <= 1)
        return n;

    long previous = 0;
    long current  = 1;

    for (long i = 0; i < n - 1; ++i) {
        long tmpPrevious = previous;
        previous = current;
        current = tmpPrevious + current;
    }

    return current % m;
}
```
**Approach 2: Fast**


```java
private static int pisanoLength(int m) {
    int l = 1;
    int previousR = 0;  // previous remainder
    int currentR = 1;   // current remainder

    while (true) {
        int tmpPreviousR = previousR;
        previousR = currentR;
        currentR = (tmpPreviousR + currentR) % m;
        if (previousR == 0 && currentR == 1)
            break;
        l++;
    }
    
    return l;
}

private static int getFibonacciHugeFast(long n, int m) {
    if (n <= 1)
        return (int) n;

    int limit = (int) (n % (long) pisanoLength(m));
    if (limit <= 0)
        return 0;

    int previous = 0;
    int current  = 1;

    for (int i = 0; i < limit - 1; ++i) {
        int tmpPrevious = previous;
        previous = current;
        current = (tmpPrevious + current) % m;
    }

    return current;
}
```
## Test

Compile with `javac FibonacciHuge.java` and run with `java FibonacciHuge`.

```java
import java.util.*;

public class FibonacciHuge {
    private static int pisanoLength(int m) {
        /* see previous code */
    }

    private static int getFibonacciHugeFast(long n, int m) {
        /* see previous code */
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(getFibonacciHugeFast(n, m));

    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**