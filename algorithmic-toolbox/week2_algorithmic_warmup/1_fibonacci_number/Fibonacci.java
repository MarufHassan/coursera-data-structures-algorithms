import java.util.Scanner;

public class Fibonacci {
  private static int calcFib(int n) {
    if (n <= 1)
        return n;

    int previous = 0;
    int current  = 1;

    for (int i = 0; i < n - 1; ++i) {
        int tmpPrevious = previous;
        previous = current;
        current = tmpPrevious + current;
    }

    return current;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calcFib(n));
  }
}
