import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
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

    String BWTNaive(String text) {
        StringBuilder result = new StringBuilder();
        int n = text.length();
        String[] suffixes = new String[n];

        for (int i = n - 1; i >= 0; i--) {
        	suffixes[n - i - 1] = text.substring(i, n) + text.substring(0, i);
        }
        Arrays.sort(suffixes);
        for (int i = 0; i < n; i++) {
        	result.append(suffixes[i].charAt(n - 1));
        }

        return result.toString();
    }

    String BWT(String text) {
    	StringBuilder result = new StringBuilder();
    	int n = text.length();

    	Integer[] indices = new Integer[n];
    	for (int i = 0; i < n; i++) {
    		indices[i] = i;
    	}

    	Arrays.sort(indices, new Comparator<Integer>() {
    		public int compare(Integer idx1, Integer idx2) {
    			for (int i = 0; i < n; i++) {
    				int n1 = (idx1 + i) % n;
    				int n2 = (idx2 + i) % n;

    				if (text.charAt(n1) < text.charAt(n2))
    					return -1;
    				else if (text.charAt(n1) > text.charAt(n2))
    					return +1;
    			}
    			return 0;
    		}
    	});

    	for (int i = 0; i < n; i++) {
    		int idx = (indices[i] - 1);
    		if (idx < 0)
    			idx += n;
    		result.append(text.charAt(idx));
    	}

    	return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWTNaive(text));
    }
}