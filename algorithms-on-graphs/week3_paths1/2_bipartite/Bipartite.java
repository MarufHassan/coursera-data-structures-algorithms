import java.util.*;

public class Bipartite {
	private static final int RED = 1;
	private static final int BLUE = 2;

    private static int bipartite(ArrayList<Integer>[] adj) {
    	int V = adj.length;
        int[] color = new int[V];
        Arrays.fill(color, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        color[0] = RED;

        while (!q.isEmpty()) {
        	int v = q.remove();
        	for (int w : adj[v]) {
        		if (color[w] == -1) {
        			q.add(w);
        			color[w] = (color[v] == RED) ? BLUE : RED;
        		} else if (color[v] == color[w])
        			return 0;
        	}
        }

        return 1;
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
        System.out.println(bipartite(adj));
    }
}

