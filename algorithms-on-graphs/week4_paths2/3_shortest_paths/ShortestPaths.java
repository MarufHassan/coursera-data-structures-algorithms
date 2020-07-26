import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
    	int V = adj.length;
    	distance[s] = 0;
    	reachable[s] = 1;

    	for (int i = 1; i <= V - 1; i++) {
    		for (int u = 0; u < V; u++) {
    			for (int j = 0; j < adj[u].size(); j++) {
    				int v = adj[u].get(j);
    				int weight = cost[u].get(j);
    				if (distance[u] != Long.MAX_VALUE && distance[u] + weight < distance[v]) {
    					distance[v] = distance[u] + weight;
    					reachable[v] = 1;
    				}
    			}
    		}
    	}

    	Queue<Integer> q = new LinkedList<>();
    	boolean[] marked = new boolean[V];

    	for (int u = 0; u < V; u++) {
			for (int j = 0; j < adj[u].size(); j++) {
				int v = adj[u].get(j);
				int weight = cost[u].get(j);
				if (distance[u] != Long.MAX_VALUE && distance[u] + weight < distance[v]) {
					if (!marked[v]) {
						marked[v] = true;
						q.add(v);
					}
				}
			}
		}

    	while (!q.isEmpty()) {
    		int u = q.poll();
    		shortest[u] = 0;
    		marked[u] = true;
    		for (int v : adj[u]) {
    			if (!marked[v]) {
    				q.add(v);
    			}
    		}
    	}
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

