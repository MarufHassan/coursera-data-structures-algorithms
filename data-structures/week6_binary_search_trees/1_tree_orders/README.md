# 1. Binary tree traversals

## Problem Description

**Task.** You are given a rooted binary tree. Build and output its in-order, pre-order and post-order traversals.

**Input Format.** The first line contains the number of vertices _n_. The vertices of the tree are numbered from 0 to _n_ − 1. Vertex 0 is the root.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>5</sup>; 0 ≤ _key_<sub>_i_</sub> ≤ 10<sup>9</sup>; −1 ≤ _left_<sub>_i_</sub>, _right_<sub>_i_</sub> ≤ _n_ − 1.

**Output Format.** Print three lines. The first line should contain the keys of the vertices in the in-order traversal of the tree. The second line should contain the keys of the vertices in the pre-order traversal of the tree. The third line should contain the keys of the vertices in the post-order traversal of the tree.

**Sample 1.**

```
    Input
        5
        4 1 2
        2 3 4
        5 -1 -1
        1 -1 -1
        3 -1 -1
    Output
        1 2 3 4 5
        4 2 1 3 5
        1 3 2 5 4
```

**Sample 2.**

```
    Input
        10
        0 7 2
        10 -1 -1
        20 -1 6
        30 8 9
        40 3 -1
        50 -1 -1
        60 1 -1
        70 5 4
        80 -1 -1
        90 -1 -1
    Output
        50 70 80 30 90 40 0 20 10 60
        0 70 50 40 30 80 90 20 60 10
        50 80 90 30 40 70 10 60 20 0
```

## Test

Compile with `javac TreeOrdersFinder.java` and run with `java TreeOrdersFinder`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**