# 3. Network packet processing simulation

## Problem Description

**Task.** In this problem you will implement a program to simulate the processing of network packets.

**Input Format.** The first line of the input contains the size _S_ of the buﬀer and the number _n_ of incoming network packets. Each of the next _n_ lines contains two numbers. _i_-th line contains the time of arrival _A_<sub>_i_</sub> and the processing time _P_<sub>_i_</sub> (both in milliseconds) of the _i_-th packet. It is guaranteed that the sequence of arrival times is non-decreasing (however, it can contain the exact same times of arrival in milliseconds — in this case the packet which is earlier in the input is considered to have arrived earlier).

**Constraints.** All the numbers in the input are integers. 1 ≤ _S_ ≤ 10<sup>5</sup>; 0 ≤ _n_ ≤ 10<sup>5</sup>; 0 ≤ _A_<sub>_i_</sub> ≤ 10<sup>6</sup>; 0 ≤ _P_<sub>_i_</sub> ≤ 10<sup>3</sup>; _A_<sub>_i_</sub> ≤ _A_<sub>_i+1_</sub> for 1 ≤ _i_ ≤ _n_ − 1.

**Output Format.** For each packet output either the moment of time (in milliseconds) when the processor began processing it or −1 if the packet was dropped (output the answers for the packets in the same order as the packets are given in the input).

**Sample 1.**

```
    Input
        1 0
    Output
```

**Sample 2.**

```
    Input
        1 2
        0 1
        0 1
    Output
        0
        -1
```

**Sample 3.**

```
    Input
        1 2
        0 1
        1 1
    Output
        0
        1
```

## Solution

```java
class Request {
    public Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }

    public int arrivalTime;
    public int processTime;
}
```

```java
class Response {
    public Response(boolean dropped, int startTime) {
        this.dropped = dropped;
        this.startTime = startTime;
    }

    public boolean dropped;
    public int startTime;
}
```

```java
class Buffer {
    public Buffer(int size) {
        this.size = size;
        this.queue = new LinkedList<Integer>();
        this.finishTime = 0;
    }

    public Response process(Request request) {
        while (queue.size() > 0 && queue.peekFirst() <= request.arrivalTime) {
            queue.removeFirst();
        }
        if (queue.size() >= size) { // buffer full
            return new Response(true, -1);
        }
        if (queue.size() <= 0) { // empty buffer
            finishTime += request.processTime;
            queue.add(finishTime);
            return new Response(false, request.arrivalTime);
        }
        finishTime = queue.peekLast();
        Response r = new Response(false, finishTime);
        finishTime += request.processTime;
        queue.addLast(finishTime);
        return r;
    }

    private int size;
    private Deque<Integer> queue;
    private int finishTime;
}
```

```java
class ProcessPackage {
    private static ArrayList<Request> readQueries(Scanner scanner) throws IOException {
        int requestsCount = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requestsCount; ++i) {
            int arrivalTime = scanner.nextInt();
            int processTime = scanner.nextInt();
            requests.add(new Request(arrivalTime, processTime));
        }
        return requests;
    }

    private static ArrayList<Response> processRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.process(requests.get(i)));
        }
        return responses;
    }

    private static void printResponses(ArrayList<Response> responses) throws IOException {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.startTime);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int bufferSize = scanner.nextInt();
        Buffer buffer = new Buffer(bufferSize);

        ArrayList<Request> requests = readQueries(scanner);
        ArrayList<Response> responses = processRequests(requests, buffer);
        printResponses(responses);
    }
}
```

## Test

Compile with `javac ProcessPackage.java` and run with `java ProcessPackage`.


**This is only for discussion and communication. Please don't use this for submission of assignments.**