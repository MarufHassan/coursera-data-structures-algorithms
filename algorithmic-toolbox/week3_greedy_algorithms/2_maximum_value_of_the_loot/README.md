# 2. Maximum Value of the Loot

## Problem Description

**Task.** The goal of this code problem is to implement an algorithm for the fractional knapsack problem.

**Input Format.** The frst line of the input contains the number _n_ of items and the capacity _W_ of a knapsack. The next _n_ lines defne the values and weights of the items. The _i_-th line contains integers _v_<sub>i</sub> and _w_<sub>i</sub>—the value and the weight of _i_-th item, respectively

**Constraints.** 1 ≤ _n_ ≤ 10<sup>3</sup>, 0 ≤ _W_ ≤ 2 · 10<sup>6</sup>; 0 ≤ _v_<sub>i</sub> ≤ 2 · 10<sup>6</sup>, 0 < _w_<sub>i</sub> ≤ 2 · 10<sup>6</sup> for all 1 ≤ _i_ ≤ _n_. All the numbers are integers.

**Output Format.** Output the maximal value of fractions of items that fit into the knapsack. The absolute value of the difference between the answer of your program and the optimal value should be at most 10<sup>−3</sup>. To ensure this, output your answer with at least four digits after the decimal point (otherwise your answer, while being computed correctly, can turn out to be wrong because of rounding issues).

**Sample 1.**

```
    Input
        3 50
        60 20
        100 50
        120 30
    Output
        180.0000
```

**Sample 2.**

```
    Input
        1 10
        500 30
    Output
        166.6667
```

## Solution

**Approach 1: Without Sorting**


```java
private static double getOptimalValue(int capacity, int[] values, int[] weights) {
    double value = 0;
    int n = values.length;

    for (int i = 0; i < n; i++) {
        if (capacity <= 0)
            return value;

        int max = 0;
        double maxPerUnit = (values[0] * 1.0) / weights[0];
        for (int j = 1; j < n; j++) {
            double perunit = (values[j] * 1.0) / weights[j];
            if (weights[j] > 0 && perunit > maxPerUnit) {
                maxPerUnit = perunit;
                max = j;
            }
        }
        int taken = Math.min(capacity, weights[max]);
        value += (taken * (values[max] * 1.0) / weights[max]);
        weights[max] -= taken;
        capacity -= taken;
    }

    return value;
}
```

**Approach 2: Using decreasing order sorting**


```java
static class Item {
    int value;
    int weight;
    Double perunit; // wrapper type for convinient of comparison

    public Item(int v, int w) {
        value = v;
        weight = w;
        perunit = (v * 1.0) / w;
    }
}

private static double getOptimalValue(int capacity, int[] values, int[] weights) {
    double value = 0.0;
    int n = values.length;

    Item[] items = new Item[n];
    for (int i = 0; i < n; i++) {
        items[i] = new Item(values[i], weights[i]);
    }

    Arrays.sort(items, new Comparator<Item>() { // custom compaator for decreasing order
        public int compare(Item o1, Item o2) {
            return o2.perunit.compareTo(o1.perunit);
        }
    });

    for (int i = 0; i < n; i++) {
        if (capacity <= 0)
            return value;
        int taken = Math.min(capacity, items[i].weight);
        value += (taken * (items[i].value * 1.0) / items[i].weight);
        capacity -= taken;
    }

    return value;
}
```
## Test

Compile with `javac FractionalKnapsack.java` and run with `java FractionalKnapsack`.

```java
import java.util.*;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        /* see previous code */
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.printf("%.4f\n", getOptimalValue(capacity, values, weights));
    }
}
```

**This is only for discussion and communication. Please don't use this for submission of assignments.**