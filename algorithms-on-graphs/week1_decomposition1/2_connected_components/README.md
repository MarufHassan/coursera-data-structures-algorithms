# 2. Adding Exits to a Maze

## Problem Description

**Task.** Given an undirected graph with _n_ vertices and _m_ edges, compute the number of connected components in it.

**Input Format.** A graph is given in the standard format.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>3</sup>; 0 ≤ _m_ ≤ 10<sup>3</sup>;

**Output Format.** Output the number of connected components.

**Sample 1.**

```
    Input
        4 2
        1 2
        3 2
    Output
        2
```

## Solution

```java
private static int numberOfComponents(ArrayList<Integer>[] adj) {
    int result = 0;
    boolean[] visited = new boolean[adj.length];
    for (int v = 0; v < adj.length; v++) {
        if (!visited[v]) {
            explore(adj, visited, v);
            result += 1;
        }
    }
    return result;
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

Compile with `javac ConnectedComponents.java` and run with `java ConnectedComponents`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**