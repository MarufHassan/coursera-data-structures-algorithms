# 1. Convert array into heap

## Problem Description

**Task.** The first step of the HeapSort algorithm is to create a heap from the array you want to sort. By the way, did you know that algorithms based on Heaps are widely used for external sort, when you need to sort huge files that don’t fit into memory of a computer? Your task is to implement this first step and convert a given array of integers into a heap. You will do that by applying a certain number of swaps to the array. Swap is an operation which exchanges elements _a_<sub>_i_</sub> and _a_<sub>_j_</sub> of the array _a_ for some _i_ and _j_. You will need to convert the array into a heap using only _O_(_n_) swaps, as was described in the lectures. Note that you will need to use a min-heap instead of a max-heap in this problem.

**Input Format.** The first line of the input contains single integer _n_. The next line contains _n_ space-separated integers _a_<sub>_i_</sub>.

**Constraints.** 1 ≤ _n_ ≤ 100 000; 0 ≤ _i_, _j_ ≤ _n_ − 1; 0 ≤ _a_<sub>_0_</sub>, _a_<sub>_1_</sub>, . . . , _a_<sub>_n-1_</sub> ≤ 10<sup>9</sup>. All _a_<sub>_i_</sub> are distinct.

**Output Format.** The first line of the output should contain single integer _m_ — the total number of swaps. _m_ must satisfy conditions 0 ≤ _m_ ≤ 4_n_. The next _m_ lines should contain the swap operations used to convert the array _a_ into a heap. Each swap is described by a pair of integers _i_, _j_ — the 0-based indices of the elements to be swapped. 

**Sample 1.**

```
    Input
        5
        5 4 3 2 1
    Output
        3
        1 4
        0 1
        1 3
```

**Sample 2.**

```
    Input
        5
        1 2 3 4 5
    Output
        0
```

## Solution

```java
private void generateSwaps() {
  swaps = new ArrayList<Swap>();
  for (int k = N / 2; k >= 0; k--) {
    shiftDown(k);
  }
}
```

**private helper methods**

```java
private void shiftDown(int k) {
    while (2 * k  + 1 < N) {
        int j = 2 * k + 1;
        if (j < N - 1 && data[j] > data[j + 1])
            j++;
        if (data[k] <= data[j])
            break;
        exch(k, j);
        k = j;
    }
}

private void exch(int k, int j) {
    swaps.add(new Swap(k, j));
    int temp = data[k];
    data[k] = data[j];
    data[j] = temp;
}
```

## Test

Compile with `javac BuildHeap.java` and run with `java BuildHeap`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**