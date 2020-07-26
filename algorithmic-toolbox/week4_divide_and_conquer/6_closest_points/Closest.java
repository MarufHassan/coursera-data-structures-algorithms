import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
        // compare according to y-coordinates
        public int compareTo(Point o) {
            return o.y == y ?
            	Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    static double minimalDistance(Point[] points, int lo, int hi) {
    	if (hi <= lo)
    		return Double.POSITIVE_INFINITY;

    	if ((hi - lo) < 2)
    		return distance(points[lo], points[hi]);

    	int mid = lo + (hi - lo) / 2;

    	double d1 = minimalDistance(points, lo, mid);
    	double d2 = minimalDistance(points, mid + 1, hi);
    	double min = Math.min(d1, d2);

    	List<Point> strips = new ArrayList<Point>();
    	for (int i = lo; i <= hi; i++) {
    		if (Math.abs(points[mid].x - points[i].x) <= min) 
    			strips.add(points[i]);
    	}
    	return Math.min(min, stripClosest(strips, min));
    }

    static double stripClosest(List<Point> strips, double min) {
    	// sort points according to their y-coordinate
    	Collections.sort(strips);
    	for (int i = 0; i < strips.size(); i++) {
    		for (int j = i + 1; j < strips.size() && (strips.get(j).y - strips.get(i).y) <= min; j++) {
    			double dist = distance(strips.get(i), strips.get(j));
    			if (dist < min)
    				min = dist;
    		}
    	}
    	return min;
    }

    static double minimalDistance(int[] x, int y[]) {
        double ans = Double.POSITIVE_INFINITY;
        int n = x.length;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
        	points[i] = new Point(x[i], y[i]);
        }

        // sort points according to their x-coordinate
        Arrays.sort(points, new Comparator<Point>() {
        	public int compare(Point it, Point that) {
        		return it.x == that.x ?
            		Long.signum(it.y - that.y) : Long.signum(it.x - that.x);
        	}
        });

        ans = minimalDistance(points, 0, n - 1);
        return ans;
    }

    static double distance(Point p1, Point p2) {
        long dx = p1.x - p2.x;
        long dy = p1.y - p2.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    static double distance(long x1, long y1, long x2, long y2) {
        long dx = x1 - x2;
        long dy = y1 - y2;
        return Math.sqrt(dx*dx + dy*dy);
    }

    static double minimalDistanceNaive(int[] x, int[] y) {
    	double min = Double.POSITIVE_INFINITY;
    	for (int i = 0; i < x.length; i++) {
    		for (int j = i + 1; j < y.length; j++) {
    			min = Math.min(min, distance(x[i], y[i], x[j], y[j]));
    		}
    	}
    	return min;
    }

    static void stressTest() {
    	Random r = new Random();
    	int n = 10;
        int[] x = new int[n];
        int[] y = new int[n];
        while (true) {
        	for (int i = 0; i < n; i++) {
	            x[i] = r.nextInt(1000);
	            y[i] = r.nextInt(1000);
	        }
	        double d1 = minimalDistanceNaive(x, y);
	        double d2 = minimalDistance(x, y);
	        if (d1 != d2) {
	        	System.out.println("Points: ");
	        	for (int i = 0; i < n; i++) {
	        		System.out.printf("(%d, %d) ", x[i], y[i]);
	        	}
	        	System.out.println();
	        	System.out.printf("Answer (Naive)    : %.4f\n", d1);
	        	System.out.printf("Answer (Efficient): %.4f", d2);
	        	break;
	        }
	        System.out.println("Correct");
        }
    }

    public static void main(String[] args) throws Exception {
    	//stressTest();
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
