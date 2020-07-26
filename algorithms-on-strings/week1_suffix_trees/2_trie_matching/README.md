# 2. Implement TrieMatching

## Problem Description

**Task.** Implement TrieMatching algorithm.

**Input Format.** The first line of the input contains a string _Text_, the second line contains an integer _n_, each of the following _n_ lines contains a pattern from Patterns = {_p_<sub>_1_</sub>, . . . , _p_<sub>_n_</sub>}

**Constraints.** 1 ≤ _n_ ≤ 100; 1 ≤ |_p_<sub>_i_</sub>| ≤ 100 for all 1 ≤ _i_ ≤ _n_; _p_<sub>_i_</sub>’s contain only symbols A, C, G, T; no _p_<sub>_i_</sub> is a prefix of _p_<sub>_j_</sub> for all 1 ≤ _i_ ̸= _j_ ≤ _n_.

**Output Format.** All starting positions in _Text_ where a string from _Patterns_ appears as a substring in increasing order (assuming that _Text_ is a 0-based array of symbols).

**Sample 1.**

```
    Input
        AAA
        1
        AA
    Output
        0 1
```

**Sample 2.**

```
    Input
        AA
        1T
    Output
        
```

**Sample 3.**

```
    Input
        AATCGGGTTCAATCGGGGT
        2
        ATCG
        GGGT
    Output
        1 4 11 15
```

## Solution

```java
List<Node> buildTrie(List<String> patterns) {
    List<Node> trie = new ArrayList<>();
    trie.add(new Node());

    for (String pattern : patterns) {
        int curr = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char symbol = pattern.charAt(i);
            Node n = trie.get(curr);
            int idx = letterToIndex(symbol);

            if (n.next[idx] != Node.NA) {
                curr = n.next[idx];
            } else {
                trie.add(new Node());
                n.next[idx] = trie.size() - 1;
                curr = trie.size() - 1;
            }
        }
    }
    return trie;
}
```

```java
int prefixTrieMatching(String text, int start, List<Node> trie) {
    for (int i = start, v = 0; i < text.length(); i++) {
        char symbol = text.charAt(i);
        int idx = letterToIndex(symbol);
        v = trie.get(v).next[idx];

        if (v != Node.NA && trie.get(v).isLeaf())
            return start;
        else if (v == Node.NA)
            return -1;
    }

    return -1;
}
```

```java
List <Integer> solve (String text, int n, List <String> patterns) {
    List <Integer> result = new ArrayList <Integer> ();
    List<Node> trie = buildTrie(patterns);
    
    for (int i = 0; i < text.length(); i++) {
        int pos = prefixTrieMatching(text, i, trie);

        if (pos >= 0)
            result.add(pos);
    }

    return result;
}
```

## Test

Compile with `javac TrieMatching.java` and run with `java TrieMatching`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**