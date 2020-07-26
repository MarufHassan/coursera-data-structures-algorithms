import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BWMatching {
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

    // Preprocess the Burrows-Wheeler Transform bwt of some text
    // and compute as a result:
    //   * starts - for each character C in bwt, starts[C] is the first position
    //       of this character in the sorted array of
    //       all characters of the text.
    //   * occ_count_before - for each character C in bwt and each position P in bwt,
    //       occ_count_before[C][P] is the number of occurrences of character C in bwt
    //       from position 0 to position P inclusive.
    private void PreprocessBWT(String bwt, Map<Character, Integer> starts, Map<Character, int[]> occ_counts_before) {
        char[] firstColumn = bwt.toCharArray();
        Arrays.sort(firstColumn);

        for (int i = 0; i < firstColumn.length; i++) {
        	starts.putIfAbsent(firstColumn[i], i);
        }

        for (int i = 0; i < bwt.length(); i++) {
        	char c = bwt.charAt(i);
        	if (occ_counts_before.get(c) == null)
        		occ_counts_before.put(c, new int[bwt.length() + 1]);
        	int[] count = occ_counts_before.get(c);
        	count[i + 1]++;
        }
        for (char c : occ_counts_before.keySet()) {
        	int[] count = occ_counts_before.get(c);
        	for (int i = 1; i < count.length; i++) {
        		count[i] += count[i - 1];
        	}
        }
    }

    // Compute the number of occurrences of string pattern in the text
    // given only Burrows-Wheeler Transform bwt of the text and additional
    // information we get from the preprocessing stage - starts and occ_counts_before.
    int CountOccurrences(String pattern, String bwt, Map<Character, Integer> starts, Map<Character, int[]> occ_counts_before) {
    	int top = 0, bottom = bwt.length() -1;
    	int i = pattern.length() - 1;
        while (top <= bottom) {
        	if (i >= 0) {
        		char symbol = pattern.charAt(i);
        		int[] count = occ_counts_before.get(symbol);
        		if (count == null)
        			return 0;
        		int firstOccur = starts.get(symbol);
        		top = firstOccur + count[top];
        		bottom = firstOccur + count[bottom + 1] - 1;
        		i--;
        	} else {
        		return bottom - top + 1;
        	}
        }
        return 0;
    }

    static public void main(String[] args) throws IOException {
        new BWMatching().run();
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        // Start of each character in the sorted list of characters of bwt,
        // see the description in the comment about function PreprocessBWT
        Map<Character, Integer> starts = new HashMap<Character, Integer>();
        // Occurrence counts for each character and each position in bwt,
        // see the description in the comment about function PreprocessBWT
        Map<Character, int[]> occ_counts_before = new HashMap<Character, int[]>();
        // Preprocess the BWT once to get starts and occ_count_before.
        // For each pattern, we will then use these precomputed values and
        // spend only O(|pattern|) to find all occurrences of the pattern
        // in the text instead of O(|pattern| + |text|).
        PreprocessBWT(bwt, starts, occ_counts_before);
        int patternCount = scanner.nextInt();
        String[] patterns = new String[patternCount];
        int[] result = new int[patternCount];
        for (int i = 0; i < patternCount; ++i) {
            patterns[i] = scanner.next();
            result[i] = CountOccurrences(patterns[i], bwt, starts, occ_counts_before);
        }
        print(result);
    }
}
