import java.util.*;

public class LCM {
  private static long lcmNaive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }

  private static long lcmFast(int a, int b) {
    int gcd = gcdFast(a, b);
    return ((long) a * b ) / gcd;
  }

  private static int gcdFast(int a, int b) {
    if (b <= 0)
      return a;
    return gcdFast(b, a % b);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcmFast(a, b));
  }
}
