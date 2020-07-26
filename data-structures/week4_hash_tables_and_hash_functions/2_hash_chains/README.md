# 2. Hashing with chains

## Problem Description

In this problem you will implement a hash table using the chaining scheme. Chaining is one of the most popular ways of implementing hash tables in practice. The hash table you will implement can be used to implement a phone book on your phone or to store the password table of your computer or web service (but don’t forget to store hashes of passwords instead of the passwords themselves, or you will get hacked!).

**Task.** In this task your goal is to implement a hash table with lists chaining. You are already given the number of buckets _m_ and the hash function. Your program should support the following kinds of queries:

- `add` string — insert string into the table. If there is already such string in the hash table, then just ignore the query.

- `del` string — remove string from the table. If there is no such string in the hash table, then just ignore the query.

- `find` string — output “yes" or “no" (without quotes) depending on whether the table contains string or not.

- `check` _i_ — output the content of the _i_-th list in the table. Use spaces to separate the elements of the list. If _i_-th list is empty, output a blank line. 

When inserting a new string into a hash chain, you must insert it in the beginning of the chain.

**Input Format.** There is a single integer _m_ in the frst line — the number of buckets you should have. The next line contains the number of queries _N_. It’s followed by _N_ lines, each of them contains one query in the format described above.

**Constraints.**  1 ≤ _N_ ≤ 10<sup>5</sup>; _N_ / _5_ ≤ _m_ ≤ _N_. All the strings consist of latin letters. Each of them is non-empty and has length at most 15.

**Output Format.** Print the result of each of the `find` and `check` queries, one result per line, in the same order as these queries are given in the input.

**Sample 1.**

```
    Input
        5
        12
        add world
        add HellO
        check 4
        find World
        find world
        del world
        check 4
        del HellO
        add luck
        add GooD
        check 2
        del good
    Output
        HellO world
        no
        yes
        HellO
        GooD luck
```

**Sample 2.**

```
    Input
        48
        add test
        add test
        find test
        del test
        find test
        find Test
        add Test
        find Test
    Output
        yes
        no
        no
        yes
```

**Sample 3.**

```
    Input
        3
        12
        check 0
        find help
        add help
        add del
        add add
        find add
        find del
        del del
        find del
        check 0
        check 1
        check 2
    Output
        no
        yes
        yes
        no

        add help
```

## Solution

```java
// store all strings in one list
private LinkedList<String>[] elems;
// for hash function
private int bucketCount;
private int prime = 1000000007;
private int multiplier = 263;

private void processQuery(Query query) {
    int h = query.type.equals("check") ? query.ind : hashFunc(query.s);
    if (elems[h] == null)
        elems[h] = new LinkedList<>();
    switch (query.type) {
        case "add":
            if (!elems[h].contains(query.s))
                elems[h].addFirst(query.s);
            break;
        case "del":
            if (elems[h].contains(query.s))
                elems[h].remove(query.s);
            break;
        case "find":
            if (elems[h].contains(query.s))
                writeSearchResult(true);
            else
                writeSearchResult(false);

            break;
        case "check":
            int idx = query.ind;
            for (String cur : elems[idx])
                    out.print(cur + " ");
            out.println();
            // Uncomment the following if you want to play with the program interactively.
            // out.flush();
            break;
        default:
            throw new RuntimeException("Unknown query: " + query.type);
    }
}
```

## Test

Compile with `javac HashChains.java` and run with `java HashChains`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**