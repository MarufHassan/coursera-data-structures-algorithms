# 2. Determining an Order of Courses

## Problem Description

**Task.** Compute a topological ordering of a given directed acyclic graph (DAG) with _n_ vertices and _m_ edges.

**Input Format.** An undirected graph with _n_ vertices and _m_ edges. The next line contains two vertices _u_ and _v_ of the graph.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>3</sup>; 0 ≤ _m_ ≤ 10<sup>3</sup>; 1 ≤ _u_, _v_ ≤ _n_; _u_ ̸= _v_.

**Output Format.** Output _any_ topological ordering of its vertices. (Many DAGs have more than just one topological ordering. You may output any of them.)

**Sample 1.**

```
    Input
        4 3
        1 2
        4 1
        3 1
    Output
        4 3 1 2
```

**Sample 2.**

```
    Input
        4 1
        3 1
    Output
        2 3 1 4
```

## Solution

```java
private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
    boolean used[] = new boolean[adj.length];
    ArrayList<Integer> order = new ArrayList<Integer>();

    for (int v = 0; v < adj.length; v++) {
        dfs(adj, used, order, v);
    }
    Collections.reverse(order);
    return order;
}

private static void dfs(ArrayList<Integer>[] adj, boolean[] used, ArrayList<Integer> order, int s) {
    if (used[s])    return;
    used[s] = true;
    for (int w : adj[s]) {
        dfs(adj, used, order, w);
    }
    order.add(s);
}
```

## Test

Compile with `javac Toposort.java` and run with `java Toposort`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**