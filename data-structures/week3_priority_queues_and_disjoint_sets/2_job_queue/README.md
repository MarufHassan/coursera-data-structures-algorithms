# 2. Parallel processing

## Problem Description

**Task.** You have a program which is parallelized and uses _m_ independent threads to process the given list of _m_ jobs. Threads take jobs in the order they are given in the input. If there is a free thread, it immediately takes the next job from the list. If a thread has started processing a job, it doesn’t interrupt or stop until it finishes processing the job. If several threads try to take jobs from the list simultaneously, the thread with smaller index takes the job. For each job you know exactly how long will it take any thread to process this job, and this time is the same for all the threads. You need to determine for each job which thread will process it and when will it start processing.

**Input Format.** The first line of the input contains integers _n_ and _m_.
The second line contains _m_ integers _t_</sub>_i_</sub> — the times in seconds it takes any thread to process _i_-th job. The times are given in the same order as they are in the list from which threads take jobs. Threads are indexed starting from 0.

**Constraints.**  1 ≤ _n_ ≤ 10<sup>5</sup>; 1 ≤ _m_ ≤ 10<sup>5</sup>; 0 ≤ _t_<sub>_i_</sub> ≤ 10<sup>9</sup>.

**Output Format.** Output exactly _m_ lines. _i_-th line (0-based index is used) should contain two space separated integers — the 0-based index of the thread which will process the _i_-th job and the time in seconds when it will start processing that job.

**Sample 1.**

```
    Input
        2 5
        1 2 3 4 5
    Output
        0 0
        1 0
        0 1
        1 2
        0 4
```

**Sample 2.**

```
    Input
        4 20
        1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
    Output
        0 0
        1 0
        2 0
        3 0
        0 1
        1 1
        2 1
        3 1
        0 2
        1 2
        2 2
        3 2
        0 3
        1 3
        2 3
        3 3
        0 4
        1 4
        2 4
        3 4
```

## Solution

```java
private void assignJobs() {
    assignedWorker = new int[jobs.length];
    startTime = new long[jobs.length];
    PriorityQueue<Worker> pq = new PriorityQueue<Worker>();
    for (int i = 0; i < numWorkers; i++) {
        pq.offer(new Worker(i, 0));
    }
    for (int i = 0; i < jobs.length; i++) {
        int duration = jobs[i];
        Worker bestWorker = pq.poll();
        assignedWorker[i] = bestWorker.index;
        startTime[i] = bestWorker.processing;
        pq.offer(new Worker(bestWorker.index, bestWorker.processing + duration));
    }
}
```

**private helper class**

```java
private class Worker implements Comparable<Worker> {
    int index;
    long processing;

    public Worker(int index, long processing) {
        this.index = index;
        this.processing = processing;
    }

    public int compareTo(Worker that) {
        if (this.processing < that.processing)  return -1;
        if (this.processing > that.processing)  return +1;
        
        return this.index - that.index;
    }
}
```

## Test

Compile with `javac JobQueue.java` and run with `java JobQueue`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**