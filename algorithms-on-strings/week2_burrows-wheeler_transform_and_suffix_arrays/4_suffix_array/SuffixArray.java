import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

public class SuffixArray {
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

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public Integer[] computeSuffixArray(String text) {
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
    	return indices;
    }


    static public void main(String[] args) throws IOException {
        new SuffixArray().run();
    }

    public void print(Integer[] x) {
    	PrintWriter out = new PrintWriter(System.out);
        for (int a : x) {
            out.print(a + " ");
        }
        out.close();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        Integer[] SuffixArray = computeSuffixArray(text);
        print(SuffixArray);
    }
}
