import java.util.*;
import java.io.*;

public class TreeOrdersFinder {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
            inOrderIterative(0, result);
			return result;
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			preOrderIterative(0, result);
			return result;
		}

		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			postOrderIterative(0, result);
			return result;
		}

		void inOrder(int root, List<Integer> result) {
			if (root < 0)
				return;
			inOrder(left[root], result);
			result.add(key[root]);
			inOrder(right[root], result);
		}

		void inOrderIterative(int root, List<Integer> result) {
			if (root < 0)
				return;
			Stack<Integer> stack = new Stack<>();
			int curr = root;
			while (curr >= 0 || !stack.isEmpty()) {
				while (curr >= 0) {
					stack.push(curr);
					curr = left[curr];
				}
				curr = stack.pop();
				result.add(key[curr]);
				curr = right[curr];
			}
		}

		void preOrder(int root, List<Integer> result) {
			if (root < 0)
				return;
			result.add(key[root]);
			preOrder(left[root], result);
			preOrder(right[root], result);
		}

		void preOrderIterative(int root, List<Integer> result) {
			if (root < 0)
				return;
			Stack<Integer> stack = new Stack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				int pop = stack.pop();
				result.add(key[pop]);
				if (right[pop] >= 0)
					stack.push(right[pop]);
				if (left[pop] >= 0)
					stack.push(left[pop]);
			}
		}

		void postOrder(int root, List<Integer> result) {
			if (root < 0)
				return;
			postOrder(left[root], result);
			postOrder(right[root], result);
			result.add(key[root]);
		}

		void postOrderIterative(int root, List<Integer> result) {
			if (root < 0)
				return;
			Stack<Integer> stack = new Stack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				int pop = stack.pop();
				result.add(key[pop]);
				if (left[pop] >= 0)
					stack.push(left[pop]);
				if (right[pop] >= 0)
					stack.push(right[pop]);
			}
			Collections.reverse(result);
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new TreeOrdersFinder().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
