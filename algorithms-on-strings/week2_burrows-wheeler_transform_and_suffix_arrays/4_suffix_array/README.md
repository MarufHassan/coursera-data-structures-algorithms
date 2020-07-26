# 4. Construct the Suffix Array of a String

## Problem Description

**Task.** Construct the suffix array of a string.

**Input Format.** A string Text ending with a “$” symbol.

**Constraints.** 1 ≤ |Text| ≤ _10_<sup>4</sup>; except for the last symbol, Text contains symbols A, C, G, T only.

**Output Format.** SuffixArray(Text), that is, the list of starting positions (0-based) of sorted suffixes separated by spaces.

**Sample 1.**

```
    Input
        GAC$
    Output
        3 1 2 0
```

**Sample 2.**

```
    Input
        GAGAGAGA$
    Output
        8 7 5 3 1 6 4 2 0
```

**Sample 3.**

```
    Input
        AACGATAGCGGTAGA$
    Output
        15 14 0 1 12 6 4 2 8 13 3 7 9 10 11 5
```

## Solution

```java
public Integer[] computeSuffixArray(String text) {
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
    return indices;
}
```

## Test

Compile with `javac SuffixArray.java` and run with `java SuffixArray`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**