import java.util.*;

public class GCD {
  private static int gcdNaive(int a, int b) {
    int currentGcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
      if (a % d == 0 && b % d == 0) {
        if (d > currentGcd) {
          currentGcd = d;
        }
      }
    }

    return currentGcd;
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
    System.out.println(gcdFast(a, b));
  }
}
