package dynamic_programming.unbounded;

import java.util.HashMap;
import java.util.Map;

public class CoinChange2 {

    // 1. Optimized Bottom-Up DP (1D Array)
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }

    // 2. Recursive (No memoization)
    public int changeRecursive(int amount, int[] coins) {
        return helper(coins, amount, coins.length - 1);
    }

    private int helper(int[] coins, int amount, int i) {
        if (amount == 0) return 1;
        if (amount < 0 || i < 0) return 0;
        return helper(coins, amount - coins[i], i) + helper(coins, amount, i - 1);
    }

    // 3. Memoized Recursive (Top-down DP)
    public int changeMemo(int amount, int[] coins) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(coins, amount, coins.length - 1, memo);
    }

    private int dfs(int[] coins, int amount, int i, Map<String, Integer> memo) {
        if (amount == 0) return 1;
        if (amount < 0 || i < 0) return 0;

        String key = amount + "," + i;
        if (memo.containsKey(key)) return memo.get(key);

        int include = dfs(coins, amount - coins[i], i, memo);
        int exclude = dfs(coins, amount, i - 1, memo);

        memo.put(key, include + exclude);
        return include + exclude;
    }

    // ------------------ TEST CASES ------------------

    public void runTests() {
        CoinChange2 solver = new CoinChange2();

        // Optimized DP Tests
        assert solver.change(5, new int[]{1, 2, 5}) == 4 : "Test 1 failed";
        assert solver.change(3, new int[]{2}) == 0 : "Test 2 failed";
        assert solver.change(10, new int[]{10}) == 1 : "Test 3 failed";
        assert solver.change(0, new int[]{1, 2}) == 1 : "Test 4 failed";
        assert solver.change(5, new int[]{}) == 0 : "Test 5 failed";
        assert solver.change(100, new int[]{1, 2, 5}) == 541 : "Test 6 failed";

        // Recursive Tests
        assert solver.changeRecursive(5, new int[]{1, 2, 5}) == 4 : "Recursive Test 1 failed";
        assert solver.changeRecursive(3, new int[]{2}) == 0 : "Recursive Test 2 failed";
        assert solver.changeRecursive(0, new int[]{1, 2}) == 1 : "Recursive Test 3 failed";

        // Memoized Tests
        assert solver.changeMemo(5, new int[]{1, 2, 5}) == 4 : "Memo Test 1 failed";
        assert solver.changeMemo(3, new int[]{2}) == 0 : "Memo Test 2 failed";
        assert solver.changeMemo(100, new int[]{1, 2, 5}) == 541 : "Memo Test 3 failed";

        System.out.println("All tests passed!");
    }

    public static void main(String[] args) {
        new CoinChange2().runTests();
    }
}
