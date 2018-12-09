# 6. Closest Points

## Problem Description

**Task.** Given _n_ points on a plane, fnd the smallest distance between a pair of two (different) points.

**Input Format.** The first line contains the number _n_ of points. Each of the following _n_ lines defines a point (_x_<sub>_i_</sub>, _y_<sub>_i_</sub>).

**Constraints.** 2 ≤ _n_ ≤ 10<sup>5</sup>; −10<sup>9</sup> ≤ _x_<sub>_i_</sub>, _y_<sub>_i_</sub> ≤ 10<sub>9</sub> are integers.

**Output Format.** Output the minimum distance. The absolute value of the difference between the answer of your program and the optimal value should be at most 10<sup>−3</sup>. To ensure this, output your answer with at least four digits after the decimal point (otherwise your answer, while being computed correctly, can turn out to be wrong because of rounding issues).

**Sample 1.**

```
    Input
        2
        0 0
        3 4
    Output
        5.0
```

**Sample 2.**

```
    Input
        4
        7 7
        1 100
        4 8
        7 7
    Output
        0.0
```

**Sample 3.**

```
    Input
        11
        4 4
        -2 -2
        -3 -4
        -1 3
        2 3
        -4 0
        1 1
        -1 -1
        3 -1
        -4 2
        -2 4
    Output
        1.414213
```
## Solution

**Approach 1: Brute Force**

```java
static double minimalDistanceNaive(int[] x, int[] y) {
    double min = Double.POSITIVE_INFINITY;
    for (int i = 0; i < x.length; i++) {
        for (int j = i + 1; j < y.length; j++) {
            min = Math.min(min, distance(x[i], y[i], x[j], y[j]));
        }
    }
    return min;
}
```

**Approach 2: Divide and Conquer**


```java
static double minimalDistance(Point[] points, int lo, int hi) {
    if (hi <= lo)
        return Double.POSITIVE_INFINITY;

    if ((hi - lo) < 2)
        return distance(points[lo], points[hi]);

    int mid = lo + (hi - lo) / 2;

    double d1 = minimalDistance(points, lo, mid);
    double d2 = minimalDistance(points, mid + 1, hi);
    double min = Math.min(d1, d2);

    List<Point> strips = new ArrayList<Point>();
    for (int i = lo; i <= hi; i++) {
        if (Math.abs(points[mid].x - points[i].x) <= min) 
            strips.add(points[i]);
    }
    return Math.min(min, stripClosest(strips, min));
}

static double stripClosest(List<Point> strips, double min) {
    // sort points according to their y-coordinate
    Collections.sort(strips);
    for (int i = 0; i < strips.size(); i++) {
        for (int j = i + 1; j < strips.size() && (strips.get(j).y - strips.get(i).y) <= min; j++) {
            double dist = distance(strips.get(i), strips.get(j));
            if (dist < min)
                min = dist;
        }
    }
    return min;
}
```

**Helper Methods**

```java
static double distance(Point p1, Point p2) {
    long dx = p1.x - p2.x;
    long dy = p1.y - p2.y;
    return Math.sqrt(dx*dx + dy*dy);
}

static double distance(long x1, long y1, long x2, long y2) {
    long dx = x1 - x2;
    long dy = y1 - y2;
    return Math.sqrt(dx*dx + dy*dy);
}
```

**Stress Testing**

```java
static void stressTest() {
    Random r = new Random();
    int n = 10;
    int[] x = new int[n];
    int[] y = new int[n];
    while (true) {
        for (int i = 0; i < n; i++) {
            x[i] = r.nextInt(1000);
            y[i] = r.nextInt(1000);
        }
        double d1 = minimalDistanceNaive(x, y);
        double d2 = minimalDistance(x, y);
        if (d1 != d2) {
            System.out.println("Points: ");
            for (int i = 0; i < n; i++) {
                System.out.printf("(%d, %d) ", x[i], y[i]);
            }
            System.out.println();
            System.out.printf("Answer (Naive)    : %.4f\n", d1);
            System.out.printf("Answer (Efficient): %.4f", d2);
            break;
        }
        System.out.println("Correct");
    }
}
```

## Run

Compile with `javac Closest.java` and run with `java Closest`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**