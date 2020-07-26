import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        boolean[] visited = new boolean[adj.length];
        for (int v = 0; v < adj.length; v++) {
        	if (!visited[v]) {
        		explore(adj, visited, v);
        		result += 1;
        	}
        }
        return result;
    }

    private static void explore(ArrayList<Integer>[] adj, boolean[] visited, int s) {
        if (visited[s])  return;
        visited[s] = true;
        for (int v : adj[s]) {
            explore(adj, visited, v);
        }
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
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

