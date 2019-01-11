# 3. Maximum Value of an Arithmetic Expression

## Problem Description

**Task.** Find the maximum value of an arithmetic expression by specifying the order of applying its arithmetic operations using additional parentheses.

**Input Format.** The only line of the input contains a string _s_ of length 2_n_ + 1 for some _n_, with symbols _s_<sub>_0_</sub>, _s_<sub>_1_</sub>, . . . , _s_<sub>_2n_</sub>. Each symbol at an even position of _s_ is a digit (that is, an integer from 0 to 9) while each symbol at an odd position is one of three operations from {+,-, *}.

**Constraints.** 1 ≤ _n_ ≤ 14 (hence the string contains at most 29 symbols).

**Output Format.** Output the maximum possible value of the given arithmetic expression among diﬀerent orders of applying arithmetic operations.

**Sample 1.**

```
    Input
        1+5
    Output
        6
```

**Sample 2.**

```
    Input
        5-8+7*4-8+9
    Output
        200
```

## Solution

```java
private static long getMaximValue(String exp) {
    int len = exp.length();
    int size = (int) Math.ceil(len / 2.0);
    char[] op = new char[len / 2];
    int[] dg = new int[size];

    for (int i = 0, d = 0, e = 0; i < len; i++) {
        char c = exp.charAt(i);
        if (Character.isDigit(c))
            dg[d++] = c - '0';
        else
            op[e++] = c;
    }

    long[][] M = new long[size][size];
    long[][] m = new long[size][size];

    for (int i = 0; i < dg.length;i++) {
        M[i][i] = dg[i];
        m[i][i] = dg[i];
    }

    for (int s = 0; s < size; s++) {
        for (int i = 0; i < size - s; i++) {
            int j = i + s;
            minAndmax(op, M, m, i, j);
        }
    }
    return M[0][size - 1];
}
```

```java
private static void minAndmax(char[] op, long[][] M, long[][] m, int i, int j) {
    long min = Long.MAX_VALUE;
    long max = Long.MIN_VALUE;
    for (int k = i; k < j; k++) {
        long a = eval(M[i][k], M[k + 1][j], op[k]);
        long b = eval(M[i][k], m[k + 1][j], op[k]);
        long c = eval(m[i][k], M[k + 1][j], op[k]);
        long d = eval(m[i][k], m[k + 1][j], op[k]);
        min = min(min, a, b, c, d);
        max = max(max, a, b, c, d);
    }
    if (max != Long.MIN_VALUE)
        M[i][j] = max;
    if (min != Long.MAX_VALUE)
        m[i][j] = min;
}
```

```java
private static long eval(long a, long b, char op) {
    if (op == '+') {
        return a + b;
    } else if (op == '-') {
        return a - b;
    } else if (op == '*') {
        return a * b;
    } else {
        assert false;
        return 0;
    }
}
```

```java
private static long min(long...value) {
    long min = Long.MAX_VALUE;
    for (long i : value) {
        if (i < min) 
            min = i;
    }
    return min;
}

private static long max(long...value) {
    long max = Long.MIN_VALUE;
    for (long i : value) {
        if (i > max) 
            max = i;
    }
    return max;
}
```

## Test

Compile with `javac PlacingParentheses.java` and run with `java PlacingParentheses`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**