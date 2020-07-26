import java.util.*;

class EditDistance {

	public static int editDistance(String s, String t) {
		int m = s.length();
		int n = t.length();

		int[][] D = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			D[i][0] = i;
		}

		for (int j = 0; j <= n; j++) {
			D[0][j] = j;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				int insertion = D[i - 1][j] + 1;
				int deletion = D[i][j - 1] + 1;
				int match = D[i - 1][j - 1];
				int mismatch = D[i - 1][j - 1] + 1;

				if (s.charAt(i - 1) == t.charAt(j - 1))
					D[i][j] = Math.min(insertion, Math.min(deletion, match));
				else
					D[i][j] = Math.min(insertion, Math.min(deletion, mismatch));
			}
		}

		return D[m][n];
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		String s = scan.next();
		String t = scan.next();

		System.out.println(editDistance(s, t));
	}

}

