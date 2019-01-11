# 1. Check brackets in the code

## Problem Description

**Task.** In this problem you will implement a feature for a text editor to fnd errors in the usage of brackets in the code.

**Input Format.** Input contains one string `S` which consists of big and small latin letters, digits, punctuation marks and brackets from the set `[]{}()`.

**Constraints.** The length of `S` is at least 1 and at most 10<sup>5</sup>.

**Output Format.** If the code in `S` uses brackets correctly, output “Success" (without the quotes). Otherwise, output the 1-based index of the first unmatched closing bracket, and if there are no unmatched closing brackets, output the 1-based index of the first unmatched opening bracket.

**Sample 1.**

```
    Input
        []
    Output
        Success
```

**Sample 2.**

```
    Input
        {}[]
    Output
        Success
```

**Sample 3.**

```
    Input
        []
    Output
        Success
```

**Sample 4.**

```
    Input
        [()]
    Output
        Success
```

**Sample 5.**

```
    Input
        (())
    Output
        Success
```

**Sample 6.**

```
    Input
        {
    Output
        1
```

**Sample 7.**

```
    Input
        {[}
    Output
        3
```

**Sample 8.**

```
    Input
        foo(bar[i);
    Output
        10
```


## Solution

```java
public static void main(String[] args) throws IOException {
    InputStreamReader in = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(in);
    String text = reader.readLine();
    
    Stack<Bracket> s = new Stack<Bracket>();
    for (int position = 0; position < text.length(); ++position) {
        char next = text.charAt(position);

        if (next == '(' || next == '[' || next == '{') {
            s.push(new Bracket(next, position));
        }

        if (next == ')' || next == ']' || next == '}') {
            if (s.empty()) {
                System.out.println(position + 1);
                return;
            }
            Bracket b = s.pop();
            if (!b.match(next)) {
                System.out.println(position + 1);
                return;
            }
        }
    }

    if (s.empty())
        System.out.println("Success");
    else
        System.out.println(s.pop().position + 1);
}
```

```java
class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}
```

## Test

Compile with `javac CheckBrackets.java` and run with `java CheckBrackets`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**