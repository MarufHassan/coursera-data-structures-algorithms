# 2. Detecting Anomalies in Currency Exchange Rates

## Problem Description

**Task.** Given an directed graph with possibly negative edge weights and with 𝑛 vertices and 𝑚 edges, check whether it contains a cycle of negative weight.

**Input Format.** A graph is given in the standard format.

**Constraints.** 1 ≤ 𝑛 ≤ 10<sup>3</sup>, 0 ≤ 𝑚 ≤ 10<sup>4</sup>, edge weights are integers of absolute value at most 10<sup>3</sup>.

**Output Format.** Output 1 if the graph contains a cycle of negative weight and 0 otherwise.

**Sample 1.**

```
    Input
        4 4
        1 2 -5
        4 1 2
        2 3 2
        3 1 1
    Output
        1
```

## Solution

```java
private static boolean hasNegativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist, int src) {

    dist[src] = 0;

    for (int i = 1; i <= V - 1; i++) {
        for (int u = 0; u < V; u++) {
            for (int j = 0; j < adj[u].size(); j++) {
                int v = adj[u].get(j);
                int weight = cost[u].get(j);
                if (dist[u] != INF && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
    }

    for (int u = 0; u < V; u++) {
        for (int j = 0; j < adj[u].size(); j++) {
            int v = adj[u].get(j);
            int weight = cost[u].get(j);
            if (dist[u] != INF && dist[u] + weight < dist[v]) {
                return true;
            }
        }
    }

    return false;
}

private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
    boolean[] marked = new boolean[V];
    int[] dist = new int[V];
    Arrays.fill(dist, INF);

    for (int i = 0; i < V; i++) {
        if (marked[i])  continue;
        if (hasNegativeCycle(adj, cost, dist, i))
            return 1;
        for (int v = 0; v < V; v++) {
            if (dist[v] != INF) {
                marked[v] = true;
            }
        }
    }

    return 0;
}
```

## Test

Compile with `javac NegativeCycle.java` and run with `java NegativeCycle`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**