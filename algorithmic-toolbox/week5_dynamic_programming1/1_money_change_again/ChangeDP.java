import java.util.Scanner;

public class ChangeDP {

    private static int getChange(int money) {
    	int[] coins = {1, 3, 4};
    	int[] minCoins = new int[money + 1];

    	for (int m = 1; m <= money; m++) {
    		minCoins[m] = Integer.MAX_VALUE;
    		for (int j = 0; j < coins.length; j++) {
    			if (m >= coins[j]) {
    				int numCoins = minCoins[m - coins[j]] + 1;
    				minCoins[m] = Math.min(minCoins[m], numCoins);
    			}
    		}
    	}

        return minCoins[money];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

