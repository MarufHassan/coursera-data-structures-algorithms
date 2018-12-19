# 2. Primitive Calculator

## Problem Description

**Task.** You are given a primitive calculator that can perform the following three operations with the current number _x_: multiply _x_ by 2, multiply _x_ by 3, or add 1 to _x_. Your goal is given a positive integer _n_, find the minimum number of operations needed to obtain the number _n_ starting from the number 1.

**Input Format.** The input consists of a single integer 1 ≤ _n_ ≤ 10<sup>6</sup>.

**Output Format.** In the first line, output the minimum number _k_ of operations needed to get _n_ from 1. In the second line output a sequence of intermediate numbers. That is, the second line should contain positive integers _a_<sub>_0_</sub>, _a_<sub>_2_</sub>, . . . , _a_<sub>_k-1_</sub> such that _a_<sub>_0_</sub> = 1, _a_<sub>_k-1_</sub> = _n_ and for all 0 ≤ _i_ < _k_ − 1, _a_<sub>_i+1_</sub> is equal to either _a_<sub>_i_</sub> + 1, 2_a_<sub>_i_</sub>, or 3_a_<sub>_i_</sub>. If there are many such sequences, output any one of them.

**Sample 1.**

```
    Input
        1
    Output
        0
        1
```

**Sample 2.**

```
    Input
        5
    Output
        3
        1 2 4 5
```

**Sample 3.**

```
    Input
        96234
    Output
        14
        1 3 9 10 11 22 66 198 594 1782 5346 16038 16039 32078 96234
    Another valid output
        14
        1 3 9 10 11 33 99 297 891 2673 8019 16038 16039 48117 96234
```

## Solution

```java
private static List<Integer> optimalSequenceDP(int n) {
    List<Integer> sequence = new ArrayList<Integer>();
    int[] minOperation = new int[n + 1];
    int[] path = new int[n + 1];

    for (int op = 2; op <= n; op++) {
        int min = Integer.MAX_VALUE;
        if (op % 3 == 0 && min > minOperation[op / 3] + 1) {
            min = minOperation[op / 3] + 1;
            path[op] = op / 3;
        } 
        if (op % 2 == 0 && min > minOperation[op / 2] + 1) {
            min = minOperation[op / 2] + 1;
            path[op] = op / 2;
        }
        if (min > minOperation[op - 1] + 1) {
            min = minOperation[op - 1] + 1;
            path[op] = op - 1;
        }
        minOperation[op] = min;
    }

    while (n >= 1) {
        sequence.add(n);
        n = path[n];
    }
    Collections.reverse(sequence);

    return sequence;
}
```

## Test

Compile with `javac PrimitiveCalculator.java` and run with `java PrimitiveCalculator`.

```java
import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimalSequenceDP(int n) {
        /* see previous code */
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimalSequenceDP(n);
        System.out.println(sequence.size() - 1);
        for (int x : sequence) {
            System.out.print(x + " ");
        }
    }
}
```


**This is only for discussion and communication. Please don't use this for submission of assignments.**