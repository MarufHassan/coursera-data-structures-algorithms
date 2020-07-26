# 2. Reconstruct a String from its Burrows–Wheeler Transform

## Problem Description

**Task.** Reconstruct a string from its Burrows–Wheeler transform.

**Input Format.** A string Transform with a single “$” sign.

**Constraints.** 1 ≤ |Transform| ≤ 1 000 000; except for the last symbol, Text contains symbols A, C, G, T only.

**Output Format.** The string Text such that BWT(Text) = Transform. (There exists a unique such string.)

**Sample 1.**

```
    Input
        AC$A
    Output
        ACA$
```

**Sample 2.**

```
    Input
        AGGGAA$
    Output
        GAGAGA$
```

## Solution

```java
String inverseBWT(String bwt) {
    char[] front = bwt.toCharArray();
    Arrays.sort(front);
    int first = bwt.indexOf("$");

    int[] next = next(bwt, front);
    char[] result = new char[next.length];
    for (int i = 0, x = first; i < next.length; i++) {
        result[i] = bwt.charAt(next[x]);
        x = next[x];
    }
    return new String(result);
}
```

**private helper methods**

```java
int[] next(String s, char[] front) {
    Map<Character, Queue<Integer>> pos = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        pos.putIfAbsent(c, new LinkedList<>());
        pos.get(c).offer(i);
    }

    int[] nxt = new int[s.length()];
    for (int i = 0; i < front.length; i++) {
        char c = front[i];
        nxt[i] = pos.get(c).remove();
    }

    return nxt;
}
```

## Test

Compile with `javac InverseBWT.java` and run with `java InverseBWT`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**