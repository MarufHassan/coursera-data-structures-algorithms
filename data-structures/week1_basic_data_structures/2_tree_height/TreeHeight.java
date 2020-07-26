import java.util.*;
import java.io.*;

class Tree {
	int n;
	List<Integer>[] adj;
	int[] heights;
	int root;
	
	void read() throws IOException {
		FastScanner in = new FastScanner();
		n = in.nextInt();
		adj = new ArrayList[n];
		heights = new int[n];
		
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		for (int child = 0; child < n; child++) {
			int parent = in.nextInt();
			if (parent == -1)
				root = child;
			else
				adj[parent].add(child);
		}
	}

	int computeHeight() {
		int maxHeight = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(root);
		while (!q.isEmpty()) {
			int pop = q.remove();
			for (int v : adj[pop]) {
				heights[v] = heights[pop] + 1;
				q.add(v);
			}
			maxHeight = heights[pop] + 1;
		}
        return maxHeight;
	}
}

public class TreeHeight {
	static public void main(String[] args) throws IOException {
        Tree tree = new Tree();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
class FastScanner {
	StringTokenizer tok = new StringTokenizer("");
	BufferedReader in;

	FastScanner() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	String next() throws IOException {
		while (!tok.hasMoreElements())
			tok = new StringTokenizer(in.readLine());
		return tok.nextToken();
	}
	int nextInt() throws IOException {
		return Integer.parseInt(next());
	}
}