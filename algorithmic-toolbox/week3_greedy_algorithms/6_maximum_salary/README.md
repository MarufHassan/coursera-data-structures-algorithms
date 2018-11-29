# 6. Maximum Salary

## Problem Description

**Task.** Compose the largest number out of a set of integers.

**Input Format.** The first line of the input contains an integer _n_. The second line contains integers _a_<sub>1</sub>, _a_<sub>2</sub>, . . . , _a_<sub>n</sub>.

**Constraints.**  1 ≤ _n_ ≤ 100; 1 ≤ _a_<sub>i</sub> ≤ 10<sup>3</sup> for all 1 ≤ _i_ ≤ _n_.

**Output Format.** Output the largest number that can be composed out of _a_<sub>1</sub>, _a_<sub>2</sub>, . . . , _a_<sub>n</sub>.

**Sample 1.**

```
    Input
        2
        21 2
    Output:
        221
```

**Sample 2.**

```
    Input
        5
        9 4 6 1 9
    Output:
        99641
```

**Sample 3.**

```
    Input
        3
        23 39 92
    Output:
        923923
```

## Solution

```java
private static class CustomReverseNumberComparator implements Comparator<String> {
    public int compare(String a, String b) {
        a += b;
        b += a;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) > b.charAt(i))
                return -1;
            else if (a.charAt(i) < b.charAt(i))
                return 1;
        }
        return 0;
    }
}

private static String largestNumber(String[] a) {
    Arrays.sort(a, new CustomReverseNumberComparator());
    StringBuilder result = new StringBuilder();
    for (String s : a) {
        result.append(s);
    }
    return result.toString();
}
```

## Test

Compile with `javac LargestNumber.java` and run with `java LargestNumber`.

```java
import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        /* see previous code */
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**