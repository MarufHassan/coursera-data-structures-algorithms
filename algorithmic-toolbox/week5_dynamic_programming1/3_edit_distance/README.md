# 3. Edit Distance

## Problem Description

**Task.** The goal of this problem is to implement the algorithm for computing the edit distance between two strings.

**Input Format.** Each of the two lines of the input contains a string consisting of lower case latin letters.

**Constraint.** The length of both strings is at least 1 and at most 100.

**Output Format.** Output the edit distance between the given two strings.

**Sample 1.**

```
    Input
        ab
        ab
    Output
        0
```

**Sample 2.**

```
    Input
        short
        ports
    Output
        3
```

**Sample 3.**

```
    Input
        editing
        distance
    Output
        5
```

## Solution

```java
public static int editDistance(String s, String t) {
    int m = s.length();
    int n = t.length();

    int[][] D = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
        D[i][0] = i;
    }

    for (int j = 0; j <= n; j++) {
        D[0][j] = j;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            int insertion = D[i - 1][j] + 1;
            int deletion = D[i][j - 1] + 1;
            int match = D[i - 1][j - 1];
            int mismatch = D[i - 1][j - 1] + 1;

            if (s.charAt(i - 1) == t.charAt(j - 1))
                D[i][j] = Math.min(insertion, Math.min(deletion, match));
            else
                D[i][j] = Math.min(insertion, Math.min(deletion, mismatch));
        }
    }

    return D[m][n];
}
```

## Test

Compile with `javac EditDistance.java` and run with `java EditDistance`.

```java
import java.util.*;

class EditDistance {

    public static int editDistance(String s, String t) {
        /* see previous code */
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(editDistance(s, t));
    }

}
```


**This is only for discussion and communication. Please don't use this for submission of assignments.**