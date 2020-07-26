# 2. Is it a binary search tree?

## Problem Description

**Task.** You are given a binary tree with integers as its keys. You need to test whether it is a correct binary search tree. The definition of the binary search tree is the following: for any node of the tree, if its key is _x_, then for any node in its left subtree its key must be strictly less than _x_, and for any node in its right subtree its key must be strictly greater than _x_. In other words, smaller elements are to the left, and bigger elements are to the right. You need to check whether the given binary tree structure satisfes this condition. You are guaranteed that the input contains a valid binary tree. That is, it is a tree, and each node has at most two children.

**Input Format.** The first line contains the number of vertices _n_. The vertices of the tree are numbered from 0 to _n_ − 1. Vertex 0 is the root.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>5</sup>; −2<sup>31</sup> < _key_<sub>_i_</sub> < 2<sup>31</sup> − 1;; −1 ≤ _left_<sub>_i_</sub>, _right_<sub>_i_</sub> ≤ _n_ − 1.

**Output Format.** If the given binary tree is a correct binary search tree (see the definition in the problem description), output one word “CORRECT” (without quotes). Otherwise, output one word “INCORRECT” (without quotes).

**Sample 1.**

```
    Input
        3
        2 1 2
        1 -1 -1
        3 -1 -1
    Output
        CORRECT
```

**Sample 2.**

```
    Input
        3
        1 1 2
        2 -1 -1
        3 -1 -1
    Output
        INCORRECT
```

## Test

Compile with `javac ValidateBST.java` and run with `java ValidateBST`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**