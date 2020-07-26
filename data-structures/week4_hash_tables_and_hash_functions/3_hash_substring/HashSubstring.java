import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    private static int R = 256; // radix
    private static long Q = 1597018849L; // a large prime, small enough to avoid long overflow

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    // Compute hash for key[0..m-1]. 
    private static long hash(String key, int m) { 
        long h = 0;
        for (int j = 0; j < m; j++) 
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    private static boolean check(String txt, String pat, int i) {
    	int m = pat.length();
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) 
                return false; 
        }
        return true;
    }

    private static List<Integer> getOccurrences(Data input) {
        String pat = input.pattern, txt = input.text;
        int m = pat.length(), n = txt.length();

        List<Integer> occurrences = new ArrayList<Integer>();
        long patHash = hash(pat, m); // pattern hash
        long txtHash = hash(txt, m); // text hash

        if ((patHash == txtHash) && check(txt, pat, 0))
            occurrences.add(0);
        
        // precompute R^(m-1) % Q for use in removing leading char
        long RM = 1;
        for (int i = 1; i <= m-1; i++)
            RM = (R * RM) % Q;

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < n; i++) {
            // Remove leading char, add trailing char, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i-m) % Q) % Q; 
            txtHash = (txtHash*R + txt.charAt(i)) % Q; 

            // match
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(txt, pat, offset))
                occurrences.add(offset);
        }

        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

