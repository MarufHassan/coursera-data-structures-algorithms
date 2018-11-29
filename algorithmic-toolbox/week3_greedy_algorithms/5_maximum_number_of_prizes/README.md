# 5. Maximum Number of Prizes

## Problem Description

**Task.** The goal of this problem is to represent a given positive integer _n_ as a sum of as many pairwise distinct positive integers as possible. That is, to find the maximum _k_ such that _n_ can be written as _a_<sub>1</sub> + _a_<sub>2</sub> + ... + _a_<sub>k</sub> where _a_<sub>1</sub>, . . . , _a_<sub>k</sub> are positive integers and _a_<sub>i</sub> ̸= _a_<sub>j</sub> for all 1 ≤ _i_ < _j_ ≤ _k_.

**Input Format.** The input consists of a single integer _n_.

**Constraints.**  1 ≤ _n_ ≤ 10<sup>9</sup>.

**Output Format.** In the frst line, output the maximum number _k_ such that _n_ can be represented as a sum of _k_ pairwise distinct positive integers. In the second line, output _k_ pairwise distinct positive integers that sum up to _n_ (if there are many such representations, output any of them).

**Sample 1.**

```
    Input
        6
    Output:
        3
        1 2 3
```

**Sample 2.**

```
    Input
        8
    Output:
        3
        1 2 5
```

**Sample 3.**

```
    Input
        2
    Output:
        1
        2
```

## Solution

**Hints**
``` 
18
1+2+3+4+5 < 18
+6 = 21 > 18
18 : 1+2+3+4+(5+6-(21-18))
```

```java
private static List<Integer> optimalSummands(int n) {
    List<Integer> summands = new ArrayList<Integer>();
    int sum = 0;
    int current = 1;

    while (sum < n) {
        sum += current;
        summands.add(current);
        current += 1;
    }
    if (sum > n) { // exceeded
        int size = summands.size();
        int last = summands.remove(size - 1);
        size = summands.size();
        int exceed = (sum - n);
        int element = (summands.get(size - 1) + last) - exceed;

        summands.set(size - 1, element);
    }

    return summands;
}
```

## Test

Compile with `javac DifferentSummands.java` and run with `java DifferentSummands`.

```java
import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        /* see previous code */
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**