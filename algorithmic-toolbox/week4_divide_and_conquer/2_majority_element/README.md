# 2. Majority Element

## Problem Description

**Task.** The goal in this code problem is to check whether an input sequence contains a majority element.

**Input Format.** The frst line contains an integer _n_, the next one contains a sequence of _n_ non-negative integers _a_<sub>_0_</sub>, _a_<sub>_1_</sub>, . . . , _a_<sub>_n-1_</sub>.

**Constraints.** 1 ≤ _n_ ≤ 10<sup>5</sup>; 0 ≤ _a_<sub>_i_</sub> ≤ 10<sup>9</sup> for all 0 ≤ _i_ < _n_.

**Output Format.** Output 1 if the sequence contains an element that appears strictly more than _n_/2 times, and 0 otherwise.

**Sample 1.**

```
    Input
        5
        2 3 9 2 2
    Output
        1
```

**Sample 2.**

```
    Input
        4
        1 2 3 4
    Output
        0
```

**Sample 3.**

```
    Input
        4
        1 2 3 1
    Output
        1
```

## Solution

**Approach 1: Counting Elements (Non-recursive)**


```java
private static int getMajorityElementNonRecursive(int[] a) {
    Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
    int ans = -1;

    for (int i = 0; i < a.length; i++) {
        frequency.put(a[i], frequency.getOrDefault(a[i], 0) + 1);
    }

    for (int key : frequency.keySet()) {
        int value = frequency.get(key);
        if (value > a.length / 2) {
            ans = value;
            break;
        }
    }
    return ans;
}
```

**Approach 2: Divide and Conquer**

```java
private static int getMajorityElement(int[] a, int lo, int hi) {
    if (hi <= lo)
            return a[lo];
    int mid = lo + (hi - lo) / 2;
    int lElement = getMajorityElement(a, lo, mid);
    int rElement = getMajorityElement(a, mid + 1, hi);

    if (lElement == rElement)
        return lElement;

    int lcount = frequency(a, lElement, lo, hi);
    int rcount = frequency(a, rElement, lo, hi);

    if (lcount > Math.ceil((hi - lo + 1) / 2))
        return lElement;
    if (rcount > Math.ceil((hi - lo + 1) / 2))
        return rElement;

    return -1;  // no majority element
}

private static int getMajorityElement(int[] a) {
    int ans = -1;
    ans = getMajorityElement(a, 0, a.length - 1);
    return ans;
}
```
**Helper Method**

```java
private static int frequency(int[] a, int element, int lo, int hi) {
    int cnt = 0;
    for (int i = lo; i <= hi; i++) {
        if (a[i] == element)
            cnt++;
    }
    return cnt;
}
```


**This is only for discussion and communication. Please don't use this for submission of assignments.**