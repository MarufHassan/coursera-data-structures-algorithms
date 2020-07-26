# 1. Construct the Burrows–Wheeler Transform of a String

## Problem Description

**Task.** Construct the Burrows–Wheeler transform of a string

**Input Format.** A string Text ending with a “$” symbol.

**Constraints.** 1 ≤ |Text| ≤ 1 000; except for the last symbol, Text contains symbols A, C, G, T only.

**Output Format.** BWT(Text).

**Sample 1.**

```
    Input
        AA$
    Output
        AA$
```

**Sample 2.**

```
    Input
        ACACACAC$
    Output
        CCCC$AAAA
```

**Sample 3.**

```
    Input
        AGACATA$
    Output
        ATG$CAAA
```

## Solution

**Approach 1: Typical Implementation**

```java
String BWTNaive(String text) {
    StringBuilder result = new StringBuilder();
    int n = text.length();
    String[] suffixes = new String[n];

    for (int i = n - 1; i >= 0; i--) {
        suffixes[n - i - 1] = text.substring(i, n) + text.substring(0, i);
    }
    Arrays.sort(suffixes);
    for (int i = 0; i < n; i++) {
        result.append(suffixes[i].charAt(n - 1));
    }

    return result.toString();
}
```

**Approach 2: Efficient**

```java
String BWT(String text) {
    StringBuilder result = new StringBuilder();
    int n = text.length();

    Integer[] indices = new Integer[n];
    for (int i = 0; i < n; i++) {
        indices[i] = i;
    }

    Arrays.sort(indices, new Comparator<Integer>() {
        public int compare(Integer idx1, Integer idx2) {
            for (int i = 0; i < n; i++) {
                int n1 = (idx1 + i) % n;
                int n2 = (idx2 + i) % n;

                if (text.charAt(n1) < text.charAt(n2))
                    return -1;
                else if (text.charAt(n1) > text.charAt(n2))
                    return +1;
            }
            return 0;
        }
    });

    for (int i = 0; i < n; i++) {
        int idx = (indices[i] - 1);
        if (idx < 0)
            idx += n;
        result.append(text.charAt(idx));
    }

    return result.toString();
}
```
## Test

Compile with `javac BurrowsWheelerTransform.java` and run with `java BurrowsWheelerTransform`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**