# 1. Computing the Minimum Cost of a Flight

## Problem Description

**Task.** Given an directed graph with positive edge weights and with 𝑛 vertices and 𝑚 edges as well as two vertices 𝑢 and 𝑣, compute the weight of a shortest path between 𝑢 and 𝑣 (that is, the minimum total weight of a path from 𝑢 to 𝑣).

**Input Format.** A graph is given in the standard format. The next line contains two vertices 𝑢 and 𝑣.

**Constraints.** 1 ≤ 𝑛 ≤ 10<sup>4</sup>, 0 ≤ 𝑚 ≤ 10<sup>5</sup>, 𝑢 ̸= 𝑣, 1 ≤ 𝑢, 𝑣 ≤ 𝑛, edge weights are non-negative integers not exceeding 10<sup>3</sup>.

**Output Format.** Output the minimum weight of a path from 𝑢 to 𝑣, or −1 if there is no path.

**Sample 1.**

```
    Input
        4 4
        1 2 1
        4 1 2
        2 3 2
        1 3 5
        1 3
    Output
        3
```

**Sample 2.**

```
    Input
        5 9
        1 2 4
        1 3 2
        2 3 2
        3 2 1
        2 4 2
        3 5 4
        5 4 1
        2 5 3
        3 4 4
        1 5
    Output
        6
```

## Solution

```java
private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    int V = adj.length;

    int[] distTo = new int[V];
    Arrays.fill(distTo, INFINITY);
    distTo[s] = 0;

    IndexMinPQ pq = new IndexMinPQ(V);
    pq.insert(s, 0);

    while (!pq.isEmpty()) {
        int v = pq.delMin();
        for (int w = 0; w < adj[v].size(); w++) {
            relax(pq, distTo, v, adj[v].get(w), cost[v].get(w));
        }
    }

    return (distTo[t] == INFINITY) ? -1 : distTo[t];
}
```

**private helper methods**

```java
private static void relax(IndexMinPQ pq, int[] distTo, int v, int w, int weight) {
    if (distTo[w] > distTo[v] + weight) {
        distTo[w] = distTo[v] + weight;

        if (pq.contains(w))
            pq.decreaseKey(w, distTo[w]);
        else
            pq.insert (w, distTo[w]);
    }
}
```

**Index Minimum Priority Queue Class**

```java
class IndexMinPQ {
    private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private int[] keys;      // keys[i] = priority of i

    public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        n = 0;
        keys = new int[maxN + 1];
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];
        Arrays.fill(keys, -1);
        Arrays.fill(qp, -1);
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void insert(int i, int key) {
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int delMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1; // delete
        keys[min] = -1;
        pq[n + 1] = -1; // not needed
        return min;
    }

    public void decreaseKey(int i, int key) {
        keys[i] = key;
        swim(qp[i]);
    }

   /***************************************************************************
    * Heap helper functions.
    ***************************************************************************/

   private boolean greater(int i, int j) {
        return Integer.compare(keys[pq[i]], keys[pq[j]]) > 0;
    }

   private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}
```

## Test

Compile with `javac Dijkstra.java` and run with `java Dijkstra`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**