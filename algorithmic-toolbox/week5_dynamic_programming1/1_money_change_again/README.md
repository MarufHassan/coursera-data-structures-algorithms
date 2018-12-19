# 1. Money Change Again

## Problem Description

**Task.** Your goal now is to apply dynamic programming for solving the Money Change Problem for denominations 1, 3, and 4.

**Input Format.** Integer _money_.

**Constraints.** 1 ≤ _money_ ≤ 10<sup>3</sup>.

**Output Format.** The minimum number of coins with denominations 1, 3, 4 that changes money.

**Sample 1.**

```
    Input
        2
    Output
        2
```

**Sample 2.**

```
    Input
        34
    Output
        9
```

## Solution

```java
private static int getChange(int money) {
    int[] coins = {1, 3, 4};
    int[] minCoins = new int[money + 1];

    for (int m = 1; m <= money; m++) {
        minCoins[m] = Integer.MAX_VALUE;
        for (int j = 0; j < coins.length; j++) {
            if (m >= coins[j]) {
                int numCoins = minCoins[m - coins[j]] + 1;
                minCoins[m] = Math.min(minCoins[m], numCoins);
            }
        }
    }

    return minCoins[money];
}
```

## Test

Compile with `javac ChangeDP.java` and run with `java ChangeDP`.

```java
import java.util.Scanner;

public class ChangeDP {

    private static int getChange(int money) {
        /* see previous code */
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}
```


**This is only for discussion and communication. Please don't use this for submission of assignments.**