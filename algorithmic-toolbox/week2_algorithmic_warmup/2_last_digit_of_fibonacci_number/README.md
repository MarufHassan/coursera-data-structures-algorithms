# 2. Last Digit of a Large Fibonacci Number

## Problem Description

**Task.** Given an integer _n_, find the last digit of the _n_-th Fibonacci number _F_<sub>n</sub> (that is, _F_<sub>n</sub> mod 10).

**Input Format.** The input consists of a single integer _n_.

**Constraints.** 0 ≤ _n_ ≤ 10<sup>7</sup>

**Output Format.** Output the last digit of _F_<sub>n</sub>.

**Sample 1.**

```
    Input
        3
    Output
        2
```

**Sample 2.**

```
    Input
        331
    Output
        9
```

**Sample 3.**

```
    Input
        327305
    Output
        5
```

## Solution

**Approach 1: Naive**


```java
private static int getFibonacciLastDigitNaive(int n) {
    if (n <= 1)
        return n;

    int previous = 0;
    int current  = 1;

    for (int i = 0; i < n - 1; ++i) {
        int tmpPrevious = previous;
        previous = current;
        current = tmpPrevious + current;
    }

    return current % 10;
}
```
**Approach 2: Fast**


```java
private static int getFibonacciLastDigitAccurate(int n) {
    if (n <= 1)
        return n;
    
    int previous = 0;
    int current = 1;

    for (int i = 2; i <= n; i++) {
        int tmpPrevious = previous;
        previous = current;
        current = (current + tmpPrevious) % 10;
    }
    return current;
}
```
## Test

Compile with `javac FibonacciLastDigit.java` and run with `java FibonacciLastDigit`.

```java
import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitAccurate(int n) {
        /* see previous code */
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitAccurate(n);
        System.out.println(c);
    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**