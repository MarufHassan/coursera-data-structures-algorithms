# 8. Last Digit of the Sum of Squares of Fibonacci Numbers

## Problem Description

**Task.** Compute the last digit of _F_<sub>0</sub><sup>2</sup> + _F_<sub>1</sub><sup>2</sup> + · · · + _F_<sub>n</sub><sup>2</sup>.

**Input Format.** Integer _n_

**Constraints.** 1 ≤ _n_ ≤ 10<sup>18</sup>.

**Output Format.** The last digit of _F_<sub>0</sub><sup>2</sup> + _F_<sub>1</sub><sup>2</sup> + · · · + _F_<sub>n</sub><sup>2</sup>.

**Sample 1.**

```
    Input
        7
    Output
        3
```

**Sample 2.**

```
    Input
        73
    Output
        1
```

**Sample 3.**

```
    Input
        1234567890
    Output
        0
```


## Solution

**Approach 1: Naive**


```java
private static long getFibonacciSumSquaresNaive(long n) {
    if (n <= 1)
        return n;

    long previous = 0;
    long current  = 1;
    long sum      = 1;

    for (long i = 0; i < n - 1; ++i) {
        long tmpPrevious = previous;
        previous = current;
        current = tmpPrevious + current;
        sum += current * current;
    }

    return sum % 10;
}
```
**Approach 2: Fast**


```java
// sum of squares of n-th fibonacci numbers
// (f0)^2 + (f1)^2 + (f2)^2 + (f3)^2 + (f4)^2 + .... + (fn)^2
// = fib(n) * fib(n + 1)
private static int getFibonacciSumSquaresFast(long n) {
    int sum = 0;

    n = (int) (n % 60); // pisanoLength(10) = 60

    int current = 0;
    int next = 1;

    for (int i = 0; i < n; i++) {
        int tmpCurrent = next;
        next = (current + next) % 10;
        current = tmpCurrent;
    }

    sum = (current * next) % 10;
    return sum;
}
```
## Test

Compile with `javac FibonacciSumSquares.java` and run with `java FibonacciSumSquares`.

```java
import java.util.*;

public class FibonacciSumSquares {
    private static int getFibonacciSumSquaresFast(long n) {
        /* see previous code */
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(getFibonacciSumSquaresFast(n));
    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**