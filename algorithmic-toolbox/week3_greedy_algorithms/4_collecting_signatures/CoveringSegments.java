import java.util.*;

public class CoveringSegments {

    private static List<Integer> optimalPoints(Segment[] segments) {
        Arrays.sort(segments, new Comparator<Segment> () {
	        public int compare(Segment s1, Segment s2) {
	        	if (s1.end < s2.end)
	        		return -1;
	        	else if (s1.end > s2.end) 
	        		return +1;
	        	else
	        		return 0;
	        }
        });

        int n = segments.length;
        List<Integer> points = new ArrayList<Integer>();

        int s = 0;
        while (s < n) {
        	Segment segment = segments[s];
        	int p = s;
        	while (p < n && segments[p].start <= segment.end) 
        		p++;
        	points.add(segment.end);
        	s = p;
        }

        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        List<Integer> points = optimalPoints(segments);
        System.out.println(points.size());
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
