# 3. Merging tables

## Problem Description

**Task.** There are _n_ tables stored in some database. The tables are numbered from 1 to _n_. All tables share the same set of columns. Each table contains either several rows with real data or a symbolic link to another table. Initially, all tables contain data, and _i_-th table has _r_<sub>_i_</sub> rows.

**Input Format.** The first line of the input contains two integers _n_ and _m_ — the number of tables in the database and the number of merge queries to perform, respectively. The second line of the input contains _n_ integers _r_<sub>_i_</sub> — the number of rows in the _i_-th table. Then follow _m_ lines describing merge queries. Each of them contains two integers _destination_<sub>_i_</sub> and _source_<sub>_i_</sub> — the numbers of the tables to merge.

**Constraints.**  1 ≤ _n_, _m_ ≤ 100 000; 0 ≤ _r_<sub>_i_</sub> ≤ 10 000; 1 ≤ _destination_<sub>_i_</sub>, _source_<sub>_i_</sub> ≤ _n_.

**Output Format.** For each query print a line containing a single integer — the maximum of the sizes of all tables (in terms of the number of rows) after the corresponding operation

**Sample 1.**

```
    Input
        5 5
        1 1 1 1 1
        3 5
        2 4
        1 4
        5 4
        5 3
    Output
        2
        2
        3
        5
        5
```

**Sample 2.**

```
    Input
        6 4
        10 0 5 0 3 3
        6 6
        6 5
        5 4
        4 3
    Output
        10
        10
        10
        10
```

## Solution

```java
class Table {
    Table parent;
    int rank;
    int numberOfRows;

    Table(int numberOfRows) {
        this.numberOfRows = numberOfRows;
        rank = 0;
        parent = this;
    }
    Table getParent() {
        if(this != parent)
            parent =  parent.getParent();
        return parent;
    }
}
```

```java
void merge(Table destination, Table source) {
    Table realDestination = destination.getParent();
    Table realSource = source.getParent();
    if (realDestination == realSource) {
        return;
    }
    // merge two components here
    if (realDestination.rank < realSource.rank) {
        realDestination.parent = realSource;
        realSource.numberOfRows += realDestination.numberOfRows;
        realDestination.numberOfRows = 0;
    }
    else {
        realSource.parent = realDestination;
        realDestination.numberOfRows +=  realSource.numberOfRows;
        realSource.numberOfRows = 0;

        if (realSource.rank == realDestination.rank)
            realDestination.rank += 1;
    }
    maximumNumberOfRows = Math.max(Math.max(maximumNumberOfRows, realDestination.numberOfRows), 
        realSource.numberOfRows);
}
```

## Test

Compile with `javac MergingTables.java` and run with `java MergingTables`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**