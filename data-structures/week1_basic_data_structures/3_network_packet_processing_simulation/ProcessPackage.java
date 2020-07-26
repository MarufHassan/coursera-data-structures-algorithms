import java.io.*;
import java.util.*;

class Request {
    public Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }

    public int arrivalTime;
    public int processTime;
}

class Response {
    public Response(boolean dropped, int startTime) {
        this.dropped = dropped;
        this.startTime = startTime;
    }

    public boolean dropped;
    public int startTime;
}

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
