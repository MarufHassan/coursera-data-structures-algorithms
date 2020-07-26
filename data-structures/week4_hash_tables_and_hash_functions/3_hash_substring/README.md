# 3. Find pattern in text

## Problem Description

In this problem, your goal is to implement the Rabin–Karp’s algorithm.

**Task.** In this problem your goal is to implement the Rabin–Karp’s algorithm for searching the given pattern in the given text.

**Input Format.** There are two strings in the input: the pattern _P_ and the text _T_.

**Constraints.**  1 ≤ |_P_| ≤ |_T_| ≤ 5 · 10<sup>5</sup>. The total length of all occurrences of _P_ in _T_ doesn’t exceed 10<sup>8</sup>. The pattern and the text contain only latin letters.

**Output Format.** Print all the positions of the occurrences of _P_ in _T_ in the ascending order. Use 0-based indexing of positions in the the text _T_.

**Sample 1.**

```
    Input
        aba
        abacaba
    Output
        0 4
```

**Sample 2.**

```
    Input
        Test
        testTesttesT
    Output
        4
```

**Sample 3.**

```
    Input
        aaaaa
        baaaaaaa
    Output
        1 2 3
```

## Solution

This implementation is different from the lectures given in the course.

```java
private static int R = 256; // radix
private static long Q = 1597018849L; // a large prime, small enough to avoid long overflow

// Compute hash for key[0..m-1]. 
private static long hash(String key, int m) { 
    long h = 0;
    for (int j = 0; j < m; j++) 
        h = (R * h + key.charAt(j)) % Q;
    return h;
}

private static boolean check(String txt, String pat, int i) {
    int m = pat.length();
    for (int j = 0; j < m; j++) {
        if (pat.charAt(j) != txt.charAt(i + j)) 
            return false; 
    }
    return true;
}

private static List<Integer> getOccurrences(Data input) {
    String pat = input.pattern, txt = input.text;
    int m = pat.length(), n = txt.length();

    List<Integer> occurrences = new ArrayList<Integer>();
    long patHash = hash(pat, m); // pattern hash
    long txtHash = hash(txt, m); // text hash

    if ((patHash == txtHash) && check(txt, pat, 0))
        occurrences.add(0);
    
    // precompute R^(m-1) % Q for use in removing leading char
    long RM = 1;
    for (int i = 1; i <= m-1; i++)
        RM = (R * RM) % Q;

    // check for hash match; if hash match, check for exact match
    for (int i = m; i < n; i++) {
        // Remove leading char, add trailing char, check for match.
        txtHash = (txtHash + Q - RM*txt.charAt(i-m) % Q) % Q; 
        txtHash = (txtHash*R + txt.charAt(i)) % Q; 

        // match
        int offset = i - m + 1;
        if ((patHash == txtHash) && check(txt, pat, offset))
            occurrences.add(offset);
    }

    return occurrences;
}
```

## Test

Compile with `javac HashSubstring.java` and run with `java HashSubstring`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**