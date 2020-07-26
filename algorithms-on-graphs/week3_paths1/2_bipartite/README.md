# 2. Checking whether a Graph is Bipartite

## Problem Description

**Task.** Given an undirected graph with _n_ vertices and _m_ edges and two vertices _u_ and _v_, check whether it is bipartite.

**Input Format.** An undirected graph with _n_ vertices and _m_ edges. The next line contains two vertices _u_ and _v_ of the graph.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>5</sup>; 0 ≤ _m_ ≤ 10<sup>3</sup>; 1 ≤ _u_, _v_ ≤ _n_; _u_ ̸= _v_.

**Output Format.** Output 1 if the graph is bipartite and 0 otherwise.

**Sample 1.**

```
    Input
        4 4
        1 2
        4 1
        2 3
        3 1
    Output
        0
```

**Sample 2.**

```
    Input
        5 4
        5 2
        4 2
        3 4
        1 4
    Output
        1
```

## Solution

```java
private static int bipartite(ArrayList<Integer>[] adj) {
    int V = adj.length;
    int[] color = new int[V];
    Arrays.fill(color, -1);

    Queue<Integer> q = new LinkedList<>();
    q.add(0);
    color[0] = RED;

    while (!q.isEmpty()) {
        int v = q.remove();
        for (int w : adj[v]) {
            if (color[w] == -1) {
                q.add(w);
                color[w] = (color[v] == RED) ? BLUE : RED;
            } else if (color[v] == color[w])
                return 0;
        }
    }

    return 1;
}
```

## Test

Compile with `javac Bipartite.java` and run with `java Bipartite`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**