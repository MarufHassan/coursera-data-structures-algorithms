# 6. Last Digit of the Sum of Fibonacci Numbers

## Problem Description

**Task.** Given an integer _n_, fnd the last digit of the sum _F_<sub>0</sub> + _F_<sub>1</sub> + · · · + _F_<sub>n</sub>.

**Input Format.** The input consists of a single integer _n_.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>18</sup>.

**Output Format.** Output the last digit of _F_<sub>0</sub> + _F_<sub>1</sub> + · · · + _F_<sub>n</sub>.

**Sample 1.**

```
    Input
        3
    Output
        4
```

**Sample 2.**

```
    Input
        100
    Output
        5
```
## What To Do


Instead of computing this sum in a loop, try to come up with a formula for _F_<sub>0</sub> + _F_<sub>1</sub> + · · · + _F_<sub>n</sub>. For this, play with small values of n. Then, use a solution for the previous problem.

## Solution

**Approach 1: Naive**


```java
private static long getFibonacciSumNaive(long n) {
    if (n <= 1)
        return n;

    long previous = 0;
    long current  = 1;
    long sum      = 1;

    for (long i = 0; i < n - 1; ++i) {
        long tmpPrevious = previous;
        previous = current;
        current = tmpPrevious + current;
        sum += current;
    }

    return sum % 10;
}
```
**Approach 2: Fast**


```java
// sum(fib(n)) = fib(n + 2) - 1
private static int getFibonacciSumFast(long n) {
    if (n <= 1)
        return (int) n;

    int limit = (int) (n % 60); // pisanoLength(10) = 60

    int previous = 0;
    int current  = 1;
    int sum      =-1;

    for (int i = 0; i <= limit; ++i) {
        int tmpPrevious = previous;
        previous = current;
        current = (tmpPrevious + current) % 10;
    }

    sum = current != 0 ? current - 1 : 9; // safety guard if last digit of sum(fib(n + 2)) is 0
    return sum;
}
```
## Test

Compile with `javac FibonacciSumLastDigit.java` and run with `java FibonacciSumLastDigit`.

```java
import java.util.*;

public class FibonacciSumLastDigit {
    private static int getFibonacciSumFast(long n) {
        /* see previous code */
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(getFibonacciSumFast(n));
    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**