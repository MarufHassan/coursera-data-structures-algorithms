import java.util.*;
import java.io.*;

public class ValidateBST {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean solve() {
        	return isBinarySearchTree();
        }

        boolean isBinarySearchTree() {
        	if (nodes == 0) return true;
			return checkBST(0, null, null);
        }

        boolean checkBST(int root, Integer lower, Integer upper) {
        	if (root < 0)	return true;

        	if (upper != null && tree[root].key >= tree[upper].key) // check validity of left subtree
        		return false;
        	if (lower != null && tree[root].key < tree[lower].key) // check validity of right subtree
        		return false;

        	return checkBST(tree[root].left, lower, root)
        		&& checkBST(tree[root].right, root, upper);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ValidateBST().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.solve()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
