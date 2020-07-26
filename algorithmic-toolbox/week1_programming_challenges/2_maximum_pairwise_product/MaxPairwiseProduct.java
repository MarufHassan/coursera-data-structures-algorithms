import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    private static long getMaxPairwiseProduct(int[] numbers) {
        long maxProduct = 0;
        long product;
        int n = numbers.length;
        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                product = (long) numbers[first] * numbers[second];
                maxProduct = Math.max(maxProduct, product);
            }
        }

        return maxProduct;
    }

    private static long getMaxPairwiseProductFast(int[] numbers) {
        int n = numbers.length;

        int index = 0;
        for (int i = 1; i < n; i++) {
            if (numbers[index] < numbers[i])
                index = i;
        }
        swap(numbers, index, n - 1);

        index = 0;
        for (int i = 1; i < n - 1; i++) {
            if (numbers[index] < numbers[i])
                index = i;
        }
        swap(numbers, index, n - 2);
        
        return (long) numbers[n - 1] * numbers[n - 2];
    }

    private static void swap(int[] numbers, int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductFast(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
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