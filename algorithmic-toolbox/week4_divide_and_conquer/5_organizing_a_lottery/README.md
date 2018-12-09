# 5. Organizing a Lottery

## Problem Description

**Task.** You are given a set of points on a line and a set of segments on a line. The goal is to compute, for each point, the number of segments that contain this point.

**Input Format.** The frst line contains two non-negative integers _s_ and _p_ defning the number of segments and the number of points on a line, respectively. The next _s_ lines contain two integers _a_<sub>_i_</sub>, _b_<sub>_i_</sub> defining the _i_-th segment [_a_<sub>_i_</sub>, _b_<sub>_i_</sub>]. The next line contains _p_ integers defning points _x_<sub>_1_</sub>, _x_<sub>_2_</sub>, . . . , _x_<sub>_p_</sub>.

**Constraints.** 1 ≤ _s_, _p_ ≤ 50000; −10<sup>8</sup> ≤ _a_<sub>_i_</sub> ≤ _b_<sub>_i_</sub> ≤ 10<sup>8</sup> for all 0 ≤ _i_ < _s_; −10<sup>8</sup> ≤ _x_<sub>_j_</sub> ≤ 10<sup>8</sup> for all 0 ≤ _j_ < _p_.

**Output Format.** Output _p_ non-negative integers _k_<sub>_0_</sub>, _k_<sub>_1_</sub>, . . . , _k_<sub>_p-1_</sub> where _k_<sub>_i_</sub> is the number of segments which contain _x_<sub>_i_</sub>. 

**Sample 1.**

```
    Input
        2 3
        0 5
        7 10
        1 6 11
    Output
        1 0 0
```

**Sample 2.**

```
    Input
        1 3
        -10 10
        -100 100 0
    Output
        0 0 1
```

**Sample 3.**

```
    Input
        3 2
        0 5
        -3 2
        7 10
        1 6
    Output
        2 0
```
## Solution

**Approach 1: Brute Force**

```java
private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
    int[] cnt = new int[points.length];
    for (int i = 0; i < points.length; i++) {
        for (int j = 0; j < starts.length; j++) {
            if (starts[j] <= points[i] && points[i] <= ends[j]) {
                cnt[i]++;
            }
        }
    }
    return cnt;
}
```

**Approach 2: Fast**

**Idea**

 `l`, `r`, `p` stand for left, right, and point. The idea is that when you have a sorted list of all the points you are going to scan it; when scanning the list you need to know whether the current point is a left endpoint of a segment, a right end point, or just an initial point.

Let me give you are toy example. Assume that we are given three points _x_<sub>_1_</sub>=5,_x_<sub>_2_</sub>=8,_x_<sub>_3_</sub>=3 and two segments [_a_<sub>_1_</sub>,_b_<sub>_1_</sub>]=[4,10], [_a_<sub>_2_</sub>,_b_<sub>_2_</sub>]=[2,6]. We then create the following list:

```
(5,p), (8,p), (3,p), (4,l), (10,r), (2,l), (6,r)(5,p),(8,p),(3,p),(4,l),(10,r),(2,l),(6,r)
```

Let's now sort it:

```
(2,l), (3,p), (4,l), (5,p), (6,r), (8,p), (10,r)(2,l),(3,p),(4,l),(5,p),(6,r),(8,p),(10,r)
```

Now, let's scan it from left to right. The first item indicates the beginning of a segment (as it has l). The next is a point and we know that it is covered by one segment. The next item (4,l) indicates that we have the second segment. The next item (5,_p_) is a point and it is covered by two segments. The next item indicates the end of some segment. And so on.

```java
private static class Pair {
    int x;
    char pos;
    public Pair(int x, char pos) {
        this.x = x;
        this.pos = pos;
    }
}

private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
    int[] cnt = new int[points.length];
    int total = starts.length + ends.length + points.length;
    Pair[] pairs = new Pair[total];
    Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();

    int i, k;
    for (i = 0, k = 0; i < starts.length; i++) {
        pairs[k++] = new Pair(starts[i], 'l');
        pairs[k++] = new Pair(ends[i], 'r');
    }

    for (i = 0; i < points.length; i++) {
        pairs[k++] = new Pair(points[i], 'p');
    }
    Arrays.sort(pairs, new Comparator<Pair>() {
        public int compare(Pair p1, Pair p2) {
            if  (p1.x < p2.x)   return -1;
            if  (p1.x > p2.x)   return +1;
            else {
                if (p1.pos < p2.pos)    return -1;
                if (p1.pos > p2.pos)    return +1;
                else                    return  0;
            }
        }
    });

    int count = 0;
    for (Pair pair : pairs) {
        if      (pair.pos == 'l')   count++;
        else if (pair.pos == 'r')   count--; 
        else                        frequency.put(pair.x, count);
    }

    for (i = 0; i < points.length; i++) {
        cnt[i] += frequency.get(points[i]);
    }        
    return cnt;
}
```


**Stress Testing**

```java
private static void stressTesting() {
    Random r = new Random();
    int n = 5;
    int m = 5;

    int[] starts = new int[n];
    int[] ends = new int[n];
    int[] points = new int[m];

    int max = 20, min;

    while (true) {
        for (int i = 0; i < n; i++) {
            starts[i] = r.nextInt(max);
            min = starts[i];
            ends[i] = r.nextInt((max - min) + 1) + min;
        }
        for (int i = 0; i < m; i++) {
            points[i] = r.nextInt(max);
        }

        int[] cnt1 = naiveCountSegments(starts, ends, points);
        int[] cnt2 = fastCountSegments(starts, ends, points);

        for (int i = 0; i < cnt1.length; i++) {
            if (cnt1[i] != cnt2[i]) {
                System.out.println("Lines: ");
                for (int j = 0; j < starts.length; j++) {
                    System.out.printf("(%d, %d) ", starts[j], ends[j]);
                }
                System.out.println("\nPoints: ");
                for (int j = 0; j < points.length; j++) {
                    System.out.printf("%d ", points[j]);
                }
                System.out.println("\nBrute Force Solution: ");
                for (int x : cnt1) {
                    System.out.print(x + " ");
                }
                System.out.println("\nOptimized Solution: ");
                for (int x : cnt2) {
                    System.out.print(x + " ");
                }
                return;
            }
        }
        System.out.println("Correct");
    }
}
```

## Run

Compile with `javac PointsAndSegments.java` and run with `java PointsAndSegments`.

```java
import java.util.*;

public class PointsAndSegments {
    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        /* see previous code */
    }

    private static void stressTesting() {
        /* see previous code */
    }

    public static void main(String[] args) {
        //stressTesting();
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}
```


**This is only for discussion and communication. Please don't use this for submission of assignments.**