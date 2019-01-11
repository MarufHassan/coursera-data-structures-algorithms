# 4. Extending stack interface

## Problem Description

**Task.** Implement a stack supporting the operations `Push()`, `Pop()`, and `Max()`.

**Input Format.** The first line of the input contains the number _q_ of queries. Each of the following _q_ lines specifes a query of one of the following formats: push v, pop, or max.

**Constraints.** 1 ≤ _q_ ≤ 400 000, 0 ≤ _v_ ≤ 10 000.

**Output Format.** For each max query, output (on a separate line) the maximum value of the stack.

**Sample 1.**

```
    Input
        5
        push 2
        push 1
        max
        pop
        max
    Output
        2
        2
```

**Sample 2.**

```
    Input
        5
        push 1
        push 2
        max
        pop
        max
    Output
        2
        1
```

**Sample 3.**

```
    Input
        10
        push 2
        push 3
        push 9
        push 7
        push 2
        max
        max
        max
        pop
        max
    Output
        9
        9
        9
        9
```

**Sample 4.**

```
    Input
        3
        push 1
        push 7
        pop
    Output
```

## Solution

```java
public class StackWithMax {
    private Stack<Integer> data;
    private Stack<Integer> max;

    public StackWithMax() {
        data = new Stack<Integer>();
        max = new Stack<Integer>();
    }

    public void push(int x) {
        data.push(x);
        if (max.empty() || x >= max())
            max.push(x);
    }

    public int pop() {
        if (data.peek() == max())
            max.pop();
        return data.pop();
    }

    public int peek() {
        return data.peek();
    }

    public int max() {
        return max.peek();
    }
}
```

```java
static public void main(String[] args) throws IOException {
    FastScanner scanner = new FastScanner();
    int queries = scanner.nextInt();
    StackWithMax stack = new StackWithMax();
    for (int qi = 0; qi < queries; ++qi) {
        String operation = scanner.next();
        if ("push".equals(operation)) {
            int value = scanner.nextInt();
            stack.push(value);
        } else if ("pop".equals(operation)) {
            stack.pop();
        } else if ("max".equals(operation)) {
            System.out.println(stack.max());
        }
    }
}
```

## Test

Compile with `javac StackWithMax.java` and run with `java StackWithMax`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**