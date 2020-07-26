# 1. Checking Consistency of CS Curriculum

## Problem Description

**Task.** Check whether a given directed graph with _n_ vertices and _m_ edges contains a cycle.

**Input Format.** An undirected graph with _n_ vertices and _m_ edges. The next line contains two vertices _u_ and _v_ of the graph.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>3</sup>; 0 ≤ _m_ ≤ 10<sup>3</sup>; 1 ≤ _u_, _v_ ≤ _n_; _u_ ̸= _v_.

**Output Format.** Output 1 if the graph contains a cycle and 0 otherwise.

**Sample 1.**

```
    Input
        4 4
        1 2
        4 1
        2 3
        3 1
    Output
        1
```

**Sample 2.**

```
    Input
        5 7
        1 2
        2 3
        1 3
        3 4
        1 4
        2 5
        3 5
    Output
        0
```

## Solution

```java
private static int acyclic(ArrayList<Integer>[] adj) {
    boolean[] marked = new boolean[adj.length];
    boolean[] onstack = new boolean[adj.length];

    for (int v = 0; v < adj.length; v++) {
        if (hasCycle) break;
        if (!marked[v])
            dfs(adj, marked, onstack, v);
    }
    return hasCycle ? 1 : 0;
}

private static void dfs(List<Integer>[] adj, boolean[] marked, boolean[] onstack, int v) {
    marked[v] = true;
    onstack[v] = true;
    for (int w : adj[v]) {
        if (!marked[w])
            dfs(adj, marked, onstack, w);
        else if (onstack[w]) {
            hasCycle = true;
            return;
        }
    }
    onstack[v] = false;
}
```

## Test

Compile with `javac Acyclicity.java` and run with `java Acyclicity`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**