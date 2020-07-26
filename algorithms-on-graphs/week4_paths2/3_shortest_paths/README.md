# 3. Exchanging Money Optimally

## Problem Description

**Task.** Given an directed graph with possibly negative edge weights and with 𝑛 vertices and 𝑚 edges as well as its vertex 𝑠, compute the length of shortest paths from 𝑠 to all other vertices of the graph.

**Input Format.** A graph is given in the standard format.

**Constraints.** 1 ≤ 𝑛 ≤ 10<sup>3</sup>, 0 ≤ 𝑚 ≤ 10<sup>4</sup>, 1 ≤ 𝑠 ≤ 𝑛, edge weights are integers of absolute value at most 10<sup>9</sup>.

**Output Format.** For all vertices 𝑖 from 1 to 𝑛 output the following on a separate line:

- “*”, if there is no path from 𝑠 to 𝑢;

- “-”, if there is a path from 𝑠 to 𝑢, but there is no shortest path from 𝑠 to 𝑢 (that is, the distance from 𝑠 to 𝑢 is −∞);

- the length of a shortest path otherwise.

**Sample 1.**

```
    Input
        6 7
        1 2 10
        2 3 5
        1 3 100
        3 5 7
        5 4 10
        4 3 -18
        6 1 -1
        1
    Output
        0
        10
        -
        -
        -
        *
```

**Sample 2.**

```
    Input
        5 4
        1 2 1
        4 1 2
        2 3 2
        3 1 -5
        4
    Output
        -
        -
        -
        0
        *
```

## Solution

```java
private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
    int V = adj.length;
    distance[s] = 0;
    reachable[s] = 1;

    for (int i = 1; i <= V - 1; i++) {
        for (int u = 0; u < V; u++) {
            for (int j = 0; j < adj[u].size(); j++) {
                int v = adj[u].get(j);
                int weight = cost[u].get(j);
                if (distance[u] != Long.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    reachable[v] = 1;
                }
            }
        }
    }

    Queue<Integer> q = new LinkedList<>();
    boolean[] marked = new boolean[V];

    for (int u = 0; u < V; u++) {
        for (int j = 0; j < adj[u].size(); j++) {
            int v = adj[u].get(j);
            int weight = cost[u].get(j);
            if (distance[u] != Long.MAX_VALUE && distance[u] + weight < distance[v]) {
                if (!marked[v]) {
                    marked[v] = true;
                    q.add(v);
                }
            }
        }
    }

    while (!q.isEmpty()) {
        int u = q.poll();
        shortest[u] = 0;
        marked[u] = true;
        for (int v : adj[u]) {
            if (!marked[v]) {
                q.add(v);
            }
        }
    }
}
```

## Test

Compile with `javac ShortestPaths.java` and run with `java ShortestPaths`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**