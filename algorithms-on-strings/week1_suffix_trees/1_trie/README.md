# 1. Construct a Trie from a Collection of Patterns

## Problem Description

**Task.** Construct a trie from a collection of patterns.

**Input Format.** An integer _n_ and a collection of strings Patterns = {_p_<sub>_1_</sub>, . . . , _p_<sub>_n_</sub>} (each string is given on a separate line).

**Constraints.** 1 ≤ _n_ ≤ 100; 1 ≤ |_p_<sub>_i_</sub>| ≤ 100 for all 1 ≤ _i_ ≤ _n_; _p_<sub>_i_</sub>’s contain only symbols A, C, G, T; no _p_<sub>_i_</sub> is a prefix of _p_<sub>_j_</sub> for all 1 ≤ _i_ ̸= _j_ ≤ _n_.

**Output Format.** The adjacency list corresponding to Trie(Patterns), in the following format. If `Trie(Patterns)` has _n_ nodes, first label the root with 0 and then label the remaining nodes with the integers 1 through _n_−1 in any order you like. Each edge of the adjacency list of `Trie(Patterns)` will be encoded by a triple: the first two members of the triple must be the integers _i_, _j_ labeling the initial and terminal nodes of the edge, respectively; the third member of the triple must be the symbol _c_ labeling the edge; output each such triple in the format u->v:c (with no spaces) on a separate line.

**Sample 1.**

```
    Input
        1
        ATA
    Output
        0->1:A
        2->3:A
        1->2:T
```

**Sample 2.**

```
    Input
        3
        AT
        AG
        AC
    Output
        0->1:A
        1->4:C
        1->3:G
        1->2:T
```

**Sample 3.**

```
    Input
        3
        ATAGA
        ATC
        GAT
    Output
        0->1:A
        1->2:T
        2->3:A
        3->4:G
        4->5:A
        2->6:C
        0->7:G
        7->8:A
        8->9:T
```

## Solution

```java
List<Map<Character, Integer>> buildTrie(String[] patterns) {
    List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
    trie.add(new HashMap<Character, Integer>());

    for (String pattern : patterns) {
        int curr = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            Map<Character, Integer> node = trie.get(curr);
            if (node.containsKey(c)) {
                curr = node.get(c);
            } else {
                trie.add(new HashMap<>());
                node.put(c, trie.size() - 1);
                curr = trie.size() - 1;
            }
        }
    }        
    return trie;
}
```

## Test

Compile with `javac Trie.java` and run with `java Trie`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**