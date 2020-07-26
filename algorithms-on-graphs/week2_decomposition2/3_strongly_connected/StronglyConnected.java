import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
	private static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] adj) {
		ArrayList<Integer>[] rev = (ArrayList<Integer>[]) new ArrayList[adj.length];

		for (int v = 0; v < rev.length; v++) {
			rev[v] = new ArrayList<>();
		}

		for (int v = 0; v < adj.length; v++) {
			for (int w : adj[v]) {
				rev[w].add(v);
			}
		}

		return rev;
	}

	private static List<Integer> reversePostOrder(ArrayList<Integer>[] adj) {
		List<Integer> order = new ArrayList<>();

		boolean[] marked = new boolean[adj.length];
		for (int v = 0; v < adj.length; v++) {
			dfs(adj, order, marked, v);
		}
		Collections.reverse(order);
		return order;
	}

	private static void dfs(ArrayList<Integer>[] adj, List<Integer> order, boolean[] marked, int s) {
		if (marked[s])	return;
		marked[s] = true;
		for (int w : adj[s]) {
			dfs(adj, order, marked, w);
		}
		order.add(s);
	}

    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        List<Integer> order = reversePostOrder(reverseGraph(adj));
        boolean[] marked = new boolean[adj.length];
        int count = 0;
        for (int v : order) {
        	if (!marked[v]) {
        		dfs(adj, marked, v);
        		count++;
        	}
        }
        return count;
    }

    private static void dfs(ArrayList<Integer>[] adj, boolean[] marked, int s) {
    	if (marked[s])	return;
		marked[s] = true;
		for (int w : adj[s]) {
			dfs(adj, marked, w);
		}
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

