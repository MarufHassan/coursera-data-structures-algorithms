import java.util.*;
import java.io.*;

public class PointsAndSegments {
	private static class Pair {
		int x;
		char pos;
		public Pair(int x, char pos) {
			this.x = x;
			this.pos = pos;
		}
	}

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        int total = starts.length + ends.length + points.length;
        Pair[] pairs = new Pair[total];
		Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();

        int i, k;
        for (i = 0, k = 0; i < starts.length; i++) {
        	pairs[k++] = new Pair(starts[i], 'l');
        	pairs[k++] = new Pair(ends[i], 'r');
        }

        for (i = 0; i < points.length; i++) {
        	pairs[k++] = new Pair(points[i], 'p');
        }
        Arrays.sort(pairs, new Comparator<Pair>() {
        	public int compare(Pair p1, Pair p2) {
        		if 	(p1.x < p2.x)	return -1;
        		if 	(p1.x > p2.x)	return +1;
        		else {
        			if (p1.pos < p2.pos)	return -1;
        			if (p1.pos > p2.pos)	return +1;
        			else 					return  0;
        		}
        	}
        });

        int count = 0;
        for (Pair pair : pairs) {
        	if 		(pair.pos == 'l')	count++;
        	else if (pair.pos == 'r') 	count--; 
        	else 						frequency.put(pair.x, count);
        }

        for (i = 0; i < points.length; i++) {
        	cnt[i] += frequency.get(points[i]);
        }        
        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    private static void stressTesting() {
    	Random r = new Random();
    	int n = 5;
        int m = 5;

        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];

        int max = 20, min;

        while (true) {
        	for (int i = 0; i < n; i++) {
	            starts[i] = r.nextInt(max);
	            min = starts[i];
	            ends[i] = r.nextInt((max - min) + 1) + min;
	        }
	        for (int i = 0; i < m; i++) {
	            points[i] = r.nextInt(max);
	        }

	        int[] cnt1 = naiveCountSegments(starts, ends, points);
	        int[] cnt2 = fastCountSegments(starts, ends, points);

	        for (int i = 0; i < cnt1.length; i++) {
	        	if (cnt1[i] != cnt2[i]) {
	        		System.out.println("Lines: ");
	        		for (int j = 0; j < starts.length; j++) {
	        			System.out.printf("(%d, %d) ", starts[j], ends[j]);
	        		}
	        		System.out.println("\nPoints: ");
	        		for (int j = 0; j < points.length; j++) {
	        			System.out.printf("%d ", points[j]);
	        		}
	        		System.out.println("\nBrute Force Solution: ");
	        		for (int x : cnt1) {
			            System.out.print(x + " ");
			        }
			        System.out.println("\nOptimized Solution: ");
	        		for (int x : cnt2) {
			            System.out.print(x + " ");
			        }
	        		return;
	        	}
	        }
	        System.out.println("Correct");
        }
    }

    public static void main(String[] args) {
    	//stressTesting();
        FastScanner scanner = new FastScanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

