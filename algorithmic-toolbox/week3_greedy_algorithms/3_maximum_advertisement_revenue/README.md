# 3. Maximum Advertisement Revenue

## Problem Description

**Task.** Given two sequences _a_<sub>1</sub>, _a_<sub>2</sub>, . . . , _a_<sub>n</sub> (_a_<sub>i</sub> is the profit per click of the _i_-th ad) and _b_<sub>1</sub>, _b_<sub>2</sub>, . . . , _b_<sub>n</sub> (_b_<sub>i</sub> is the average number of clicks per day of the _i_-th slot), we need to partition them into _n_ pairs (_a_<sub>i</sub>, _b_<sub>j</sub>) such that the sum of their products is maximized.

**Input Format.** The frst line contains an integer _n_, the second one contains a sequence of integers _a_<sub>1</sub>, _a_<sub>2</sub>, . . . , _a_<sub>n</sub>, the third one contains a sequence of integers _b_<sub>1</sub>, _b_<sub>2</sub>, . . . , _b_<sub>n</sub>.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>3</sup>; −10<sup>5</sup> ≤ _a_<sub>i</sub>, _b_<sub>i</sub> ≤ 10<sup>5</sup> for all 1 ≤ _i_ ≤ _n_.

**Output Format.** Output the maximum value of _sum_(_a_<sub>i</sub>_c_<sub>i</sub>), where _c_<sub>1</sub>, _c_<sub>2</sub>, . . . , _c_<sub>n</sub> is a permutation of _b_<sub>1</sub>, _b_<sub>2</sub>, . . . , _b_<sub>n</sub>.

**Sample 1.**

```
    Input
        1
        23
        39
    Output
        897
```

**Sample 2.**

```
    Input
        3
        1 3 -5
        -2 4 1
    Output
        23
    Explanation
        23 = 3 · 4 + 1 · 1 + (−5) · (−2).
```

## Solution

```java
// ppc = profit per click
// cpd = click per day
private static long maxDotProduct(Integer[] ppc, Integer[] cpd) {
    long revenue = 0;
    int slots = ppc.length;

    Arrays.sort(ppc, Collections.reverseOrder());
    Arrays.sort(cpd, Collections.reverseOrder());

    for (int slot = 0; slot < slots; slot++) { // for every slot
        revenue += ((long)ppc[slot] * cpd[slot]);
    }
    return revenue;
}
```

## Test

Compile with `javac DotProduct.java` and run with `java DotProduct`.

```java
import java.util.*;
public class DotProduct {
    private static long maxDotProduct(Integer[] ppc, Integer[] cpd) {
        /* see previous code */
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**