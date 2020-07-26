# 1. Computing the Minimum Number of Flight Segments

## Problem Description

**Task.** Given an undirected graph with _n_ vertices and _m_ edges and two vertices _u_ and _v_, compute the length of a shortest path between _u_ and _v_ (that is, the minimum number of edges in a path from _u_ to _v_).

**Input Format.** An undirected graph with _n_ vertices and _m_ edges. The next line contains two vertices _u_ and _v_ of the graph.

**Constraints.** 2 ≤ _n_ ≤ 10<sup>5</sup>; 0 ≤ _m_ ≤ 10<sup>3</sup>; 1 ≤ _u_, _v_ ≤ _n_; _u_ ̸= _v_.

**Output Format.** Output the minimum number of edges in a path from _u_ to _v_, or −1 if there is no path.

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
        5 4
        5 2
        1 3
        3 4
        1 4
        3 5
    Output
        -1
```

## Solution

```java
private static int distance(ArrayList<Integer>[] adj, int s, int t) {
    int V = adj.length;
    int[] distTo = new int[V];
    Queue<Integer> q = new LinkedList<>();

    for (int v = 0; v < V; v++) {
        distTo[v] = INFINITY;
    }

    distTo[s] = 0;
    q.add(s);

    while (!q.isEmpty()) {
        int v = q.poll();
        for (int w : adj[v]) {
            if (distTo[w] == INFINITY) {
                q.add(w);
                distTo[w] = distTo[v] + 1;
            }
        }
    }

    return distTo[t] == INFINITY ? -1 : distTo[t];
}
```

## Test

Compile with `javac BFS.java` and run with `java BFS`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**