import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Acyclicity {
	private static boolean hasCycle = false;

    private static int acyclic(ArrayList<Integer>[] adj) {
        boolean[] marked = new boolean[adj.length];
        boolean[] onstack = new boolean[adj.length];

        for (int v = 0; v < adj.length; v++) {
        	if (hasCycle) break;
        	if (!marked[v])
        		dfs(adj, marked, onstack, v);
        }
        return hasCycle ? 1 : 0;
    }

    private static void dfs(List<Integer>[] adj, boolean[] marked, boolean[] onstack, int v) {
    	marked[v] = true;
    	onstack[v] = true;
    	for (int w : adj[v]) {
    		if (!marked[w])
    			dfs(adj, marked, onstack, w);
    		else if (onstack[w]) {
    			hasCycle = true;
    			return;
    		}
    	}
    	onstack[v] = false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}

