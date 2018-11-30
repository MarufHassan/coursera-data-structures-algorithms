# 1. Sum of Two Digits

## Problem Description

**Task.** Compute the sum of two single digit numbers.

**Input Format.** Integers _a_ and _b_ on the same line (separated by a space).

**Constraints.** 0 ≤ _a_,_b_ ≤ 9.

**Output Format.** The sum of _a_ and _b_.

**Sample 1.**

```
    Input
        9 7
    Output
        16
```

## Solution

```java
private static int sumOfTwoDigits(int firstDigit, int secondDigit) {
    return firstDigit + secondDigit;
}
```

## Test

Compile with `javac APlusB.java` and run with `java APlusB`.

```java
import java.util.Scanner;

class APlusB {
    private static int sumOfTwoDigits(int firstDigit, int secondDigit) {
        return firstDigit + secondDigit;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        System.out.println(sumOfTwoDigits(a, b));
    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**