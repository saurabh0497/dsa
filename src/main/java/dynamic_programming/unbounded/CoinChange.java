package dynamic_programming.unbounded;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(CoinChange.Memoized.coinChange(new int[]{1,2,5},5));
        System.out.println(CoinChange.Memoized.coinChange(new int[]{2},3));
        System.out.println(CoinChange.Tabulation.coinChange(new int[]{1},1));
        System.out.println(CoinChange.Tabulation.coinChange(new int[]{1,2,5},5));
    }
    static class Memoized {
        static int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] dp = new int[n + 1][amount + 1];
            int ans = solve(coins, amount, n, dp);
            return ans == Integer.MAX_VALUE - 1 ? -1 : ans;
        }

        static int solve(int[] coins, int amount, int n, int[][] dp) {
            if (amount == 0) {
                return 0;
            }
            if (n == 0 || amount < 0) {
                return Integer.MAX_VALUE - 1;
            } else if (n == 1 && amount % coins[n - 1] == 0) {
                return amount / coins[n - 1];
            } else if (n == 1 && amount % coins[n - 1] != 0) {
                return Integer.MAX_VALUE - 1;
            } else {
                if (coins[n - 1] <= amount) {
                    return dp[n][amount] = Math.min(1 + solve(coins, amount - coins[n - 1], n, dp), solve(coins, amount, n - 1, dp));
                } else
                    return dp[n][amount] = solve(coins, amount, n - 1, dp);
            }
        }
    }
    static class Tabulation {
        static int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] dp = new int[n+1][amount+1];

            for (int i = 0; i < amount + 1; i++) {
                dp[0][i] = Integer.MAX_VALUE - 1;
            }

            for (int i = 1; i < coins.length + 1; i++) {
                for (int j = 1; j < amount +1; j++) {
                    if (i == 1){
                        if(j % coins[0] == 0) {
                            dp[i][j] = j / coins[0];
                        } else if (j % coins[0] != 0) {
                            dp[i][j] = Integer.MAX_VALUE - 1;
                        }
                    }
                    else {
                        if (coins[i-1] <= j) {
                            dp[i][j] = Math.min(1+ dp[i][j-coins[i-1]], dp[i-1][j]);
                        } else {
                            dp[i][j] = dp[i-1][j];
                        }
                    }
                }
            }
            return dp[n][amount] == Integer.MAX_VALUE -1 ? -1 : dp[n][amount];
        }
    }
}
