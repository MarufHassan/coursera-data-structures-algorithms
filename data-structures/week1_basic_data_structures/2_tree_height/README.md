# 2. Compute tree height

## Problem Description

**Task.** You are given a description of a rooted tree. Your task is to compute and output its height.

**Input Format.** The first line contains the number of nodes _n_. The second line contains _n_ integer numbers from −1 to _n_ − 1 — parents of nodes. If the _i_-th one of them (0 ≤ _i_ ≤ _n_ − 1) is −1, node _i_ is the root, otherwise it’s 0-based index of the parent of _i_-th node. It is guaranteed that there is exactly one root. It is guaranteed that the input represents a tree.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>5</sup>.

**Output Format.** Output the height of the tree.

**Sample 1.**

```
    Input
        5
        4 -1 4 1 1
    Output
        3
```

**Sample 2.**

```
    Input
        5
        -1 0 4 0 3
    Output
        4
```


## Solution

```java
class Tree {
    int n;
    List<Integer>[] adj;
    int[] heights;
    int root;
    
    void read() throws IOException {
        FastScanner in = new FastScanner();
        n = in.nextInt();
        adj = new ArrayList[n];
        heights = new int[n];
        
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int child = 0; child < n; child++) {
            int parent = in.nextInt();
            if (parent == -1)
                root = child;
            else
                adj[parent].add(child);
        }
    }

    int computeHeight() {
        int maxHeight = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(root);
        while (!q.isEmpty()) {
            int pop = q.remove();
            for (int v : adj[pop]) {
                heights[v] = heights[pop] + 1;
                q.add(v);
            }
            maxHeight = heights[pop] + 1;
        }
        return maxHeight;
    }
}
```

```java
public class TreeHeight {
    static public void main(String[] args) throws IOException {
        Tree tree = new Tree();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
```

## Test

Compile with `javac TreeHeight.java` and run with `java TreeHeight`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**