# 3. Implement BetterBWMatching

## Problem Description

**Task.** Implement BetterBWMatching algorithm.

**Input Format.** A string BWT(Text), followed by an integer _n_ and a collection of _n_ strings _Patterns_ = {_p_<sub>_1_</sub>, . . . , _p_<sub>_n_</sub>} (on one line separated by spaces).

**Constraints.** 1 ≤ |BWT(Text)| ≤ 10<sup>6</sup>; except for the one $ symbol, BWT(Text) contains symbols A, C, G, T only; 1 ≤ _n_ ≤ 5 000; for all 1 ≤ _i_ ≤ _n_, _p_<sub>_i_</sub> is a string over A, C, G, T; 1 ≤ |_p_<sub>_i_</sub>| ≤ 1 000.

**Output Format.** A list of integers, where the _i_-th integer corresponds to the number of substring matches of the _i_-th member of _Patterns_ in Text.

**Sample 1.**

```
    Input
        AGGGAA$
        1
        GA
    Output
        3
```

**Sample 2.**

```
    Input
        ATT$AA
        2
        ATA A
    Output
        2 3
```

**Sample 3.**

```
    Input
        AT$TCTATG
        2
        TCT TATG
    Output
        0 0
```

## Solution

```java
private void PreprocessBWT(String bwt, Map<Character, Integer> starts, Map<Character, int[]> occ_counts_before) {
    char[] firstColumn = bwt.toCharArray();
    Arrays.sort(firstColumn);

    for (int i = 0; i < firstColumn.length; i++) {
        starts.putIfAbsent(firstColumn[i], i);
    }

    for (int i = 0; i < bwt.length(); i++) {
        char c = bwt.charAt(i);
        if (occ_counts_before.get(c) == null)
            occ_counts_before.put(c, new int[bwt.length() + 1]);
        int[] count = occ_counts_before.get(c);
        count[i + 1]++;
    }
    for (char c : occ_counts_before.keySet()) {
        int[] count = occ_counts_before.get(c);
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
    }
}
```

```java
int CountOccurrences(String pattern, String bwt, Map<Character, Integer> starts, Map<Character, int[]> occ_counts_before) {
    int top = 0, bottom = bwt.length() -1;
    int i = pattern.length() - 1;
    while (top <= bottom) {
        if (i >= 0) {
            char symbol = pattern.charAt(i);
            int[] count = occ_counts_before.get(symbol);
            if (count == null)
                return 0;
            int firstOccur = starts.get(symbol);
            top = firstOccur + count[top];
            bottom = firstOccur + count[bottom + 1] - 1;
            i--;
        } else {
            return bottom - top + 1;
        }
    }
    return 0;
}
```

## Test

Compile with `javac BWMatching.java` and run with `java BWMatching`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**