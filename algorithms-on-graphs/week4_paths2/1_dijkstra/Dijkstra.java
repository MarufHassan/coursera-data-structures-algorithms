import java.util.*;

public class Dijkstra {
	private static final int INFINITY = Integer.MAX_VALUE;

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    	int V = adj.length;

    	int[] distTo = new int[V];
    	Arrays.fill(distTo, INFINITY);
    	distTo[s] = 0;

    	IndexMinPQ pq = new IndexMinPQ(V);
    	pq.insert(s, 0);

    	while (!pq.isEmpty()) {
    		int v = pq.delMin();
    		for (int w = 0; w < adj[v].size(); w++) {
    			relax(pq, distTo, v, adj[v].get(w), cost[v].get(w));
    		}
    	}

        return (distTo[t] == INFINITY) ? -1 : distTo[t];
    }

    private static void relax(IndexMinPQ pq, int[] distTo, int v, int w, int weight) {
		if (distTo[w] > distTo[v] + weight) {
			distTo[w] = distTo[v] + weight;

			if (pq.contains(w))
				pq.decreaseKey(w, distTo[w]);
			else
				pq.insert (w, distTo[w]);
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

class IndexMinPQ {
	private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private int[] keys;      // keys[i] = priority of i

	public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        n = 0;
        keys = new int[maxN + 1];
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];
        Arrays.fill(keys, -1);
        Arrays.fill(qp, -1);
	}

	public boolean contains(int i) {
        return qp[i] != -1;
    }

	public boolean isEmpty() {
        return n == 0;
    }

    public void insert(int i, int key) {
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int delMin() {
    	int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1; // delete
        keys[min] = -1;
        pq[n + 1] = -1; // not needed
        return min;
    }

    public void decreaseKey(int i, int key) {
    	keys[i] = key;
        swim(qp[i]);
    }

   /***************************************************************************
    * Heap helper functions.
    ***************************************************************************/

   private boolean greater(int i, int j) {
        return Integer.compare(keys[pq[i]], keys[pq[j]]) > 0;
    }

   private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}

