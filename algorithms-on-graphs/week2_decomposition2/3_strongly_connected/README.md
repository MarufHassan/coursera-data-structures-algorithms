# 3. Checking Whether Any Intersection in a City is Reachable from Any Other

## Problem Description

**Task.** Compute the number of strongly connected components of a given directed graph with _n_ vertices and _m_ edges.

**Input Format.** An undirected graph with _n_ vertices and _m_ edges. The next line contains two vertices _u_ and _v_ of the graph.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>4</sup>; 0 ≤ _m_ ≤ 10<sup>4</sup>; 1 ≤ _u_, _v_ ≤ _n_; _u_ ̸= _v_.

**Output Format.** Output the number of strongly connected components.

**Sample 1.**

```
    Input
        4 4
        1 2
        4 1
        2 3
        3 1
    Output
        2
```

**Sample 2.**

```
    Input
        5 7
        2 1
        3 2
        3 1
        4 3
        4 1
        5 2
        5 3
    Output
        5
```

## Solution

```java
private static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] adj) {
    ArrayList<Integer>[] rev = (ArrayList<Integer>[]) new ArrayList[adj.length];

    for (int v = 0; v < rev.length; v++) {
        rev[v] = new ArrayList<>();
    }

    for (int v = 0; v < adj.length; v++) {
        for (int w : adj[v]) {
            rev[w].add(v);
        }
    }

    return rev;
}

private static List<Integer> reversePostOrder(ArrayList<Integer>[] adj) {
    List<Integer> order = new ArrayList<>();

    boolean[] marked = new boolean[adj.length];
    for (int v = 0; v < adj.length; v++) {
        dfs(adj, order, marked, v);
    }
    Collections.reverse(order);
    return order;
}
```

## Test

Compile with `javac StronglyConnected.java` and run with `java StronglyConnected`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**