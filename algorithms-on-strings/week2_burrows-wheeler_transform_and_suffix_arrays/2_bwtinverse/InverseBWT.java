import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
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

    int[] next(String s, char[] front) {
        Map<Character, Queue<Integer>> pos = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            pos.putIfAbsent(c, new LinkedList<>());
            pos.get(c).offer(i);
        }

        int[] nxt = new int[s.length()];
        for (int i = 0; i < front.length; i++) {
            char c = front[i];
            nxt[i] = pos.get(c).remove();
        }

        return nxt;
    }

    String inverseBWT(String bwt) {
        char[] front = bwt.toCharArray();
        Arrays.sort(front);
        int first = bwt.indexOf("$");

        int[] next = next(bwt, front);
        char[] result = new char[next.length];
        for (int i = 0, x = first; i < next.length; i++) {
            result[i] = bwt.charAt(next[x]);
            x = next[x];
        }
        return new String(result);
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
