# 1. Phone book

## Problem Description

In this problem you will implement a simple phone book manager.

**Task.** In this task your goal is to implement a simple phone book manager. It should be able to process the following types of user’s queries:
- `add` number name. It means that the user adds a person with name name and phone number number to the phone book. If there exists a user with such number already, then your manager has to overwrite the corresponding name.

- `del` number. It means that the manager should erase a person with number number from the phone book. If there is no such person, then it should just ignore the query.

- `find` number. It means that the user looks for a person with phone number number. The manager should reply with the appropriate name, or with string “not found" (without quotes) if there is no such person in the book.

**Input Format.** There is a single integer _N_ in the first line — the number of queries. It’s followed by _N_ lines, each of them contains one query in the format described above.

**Constraints.**  1 ≤ _N_ ≤ 10<sup>5</sup>. All phone numbers consist of decimal digits, they don’t have leading zeros, and each of them has no more than 7 digits. All names are non-empty strings of latin letters, and each of them has length at most 15. It’s guaranteed that there is no person with name “not found".

**Output Format.** Print the result of each `find` query — the name corresponding to the phone number or “not found" (without quotes) if there is no person in the phone book with such phone number. Output one result per line in the same order as the `find` queries are given in the input.

**Sample 1.**

```
    Input
        12
        add 911 police
        add 76213 Mom
        add 17239 Bob
        find 76213
        find 910
        find 911
        del 910
        del 911
        find 911
        find 76213
        add 76213 daddy
        find 76213
    Output
        Mom
        not found
        police
        not found
        Mom
        daddy
```

**Sample 2.**

```
    Input
        8
        find 3839442
        add 123456 me
        add 0 granny
        find 0
        find 123456
        del 0
        del 0
        find 0
    Output
        not found
        granny
        me
        not found
```

## Solution

```java
// Keep list of all existing (i.e. not deleted yet) contacts.
private String[] contacts = new String[10000000];

private void processQuery(Query query) {
    if (query.type.equals("add")) {
        // if we already have contact with such number,
        // we should rewrite contact's name
        contacts[query.number] = query.name;
    } else if (query.type.equals("del")) {
        contacts[query.number] = null;
    } else {
        String response = contacts[query.number];
        if (response == null)
            response = "not found";
        writeResponse(response);
    }
}
```

## Test

Compile with `javac PhoneBook.java` and run with `java PhoneBook`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**