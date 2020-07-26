# 1. Finding an Exit from a Maze

## Problem Description

**Task.** Given an undirected graph and two distinct vertices _u_ and _v_, check if there is a path between _u_ and _v_.

**Input Format.** An undirected graph with _n_ vertices and _m_ edges. The next line contains two vertices _u_ and _v_ of the graph.

**Constraints.** 2 ≤ _n_ ≤ 10<sup>3</sup>; 1 ≤ _m_ ≤ 10<sup>3</sup>; 1 ≤ _u_, _v_ ≤ _n_; _u_ ̸= _v_.

**Output Format.** Output 1 if there is a path between _u_ and _v_ and 0 otherwise.

**Sample 1.**

```
    Input
        4 4
        1 2
        3 2
        4 3
        1 4
        1 4
    Output
        1
```

**Sample 2.**

```
    Input
        4 2
        1 2
        3 2
        1 4
    Output
        0
```

## Solution

```java
private static int reach(ArrayList<Integer>[] adj, int x, int y) {
    boolean[] visited = new boolean[adj.length];
    explore(adj, visited, x);
    return visited[y] ? 1 : 0;
}

private static void explore(ArrayList<Integer>[] adj, boolean[] visited, int s) {
    if (visited[s])  return;
    visited[s] = true;
    for (int v : adj[s]) {
        explore(adj, visited, v);
    }
}
```

## Test

Compile with `javac Reachability.java` and run with `java Reachability`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**