import java.util.*;

public class NegativeCycle {
	private static final int INF = Integer.MAX_VALUE;

	private static int V;

    private static boolean hasNegativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist, int src) {

        dist[src] = 0;

        for (int i = 1; i <= V - 1; i++) {
        	for (int u = 0; u < V; u++) {
        		for (int j = 0; j < adj[u].size(); j++) {
        			int v = adj[u].get(j);
        			int weight = cost[u].get(j);
        			if (dist[u] != INF && dist[u] + weight < dist[v]) {
        				dist[v] = dist[u] + weight;
        			}
        		}
        	}
        }

        for (int u = 0; u < V; u++) {
    		for (int j = 0; j < adj[u].size(); j++) {
    			int v = adj[u].get(j);
    			int weight = cost[u].get(j);
    			if (dist[u] != INF && dist[u] + weight < dist[v]) {
    				return true;
    			}
    		}
    	}

        return false;
    }

    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
    	boolean[] marked = new boolean[V];
    	int[] dist = new int[V];
    	Arrays.fill(dist, INF);

    	for (int i = 0; i < V; i++) {
    		if (marked[i])	continue;
    		if (hasNegativeCycle(adj, cost, dist, i))
    			return 1;
    		for (int v = 0; v < V; v++) {
    			if (dist[v] != INF) {
    				marked[v] = true;
    			}
    		}
    	}

    	return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];

        V = n;
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
        System.out.println(negativeCycle(adj, cost));
    }
}


