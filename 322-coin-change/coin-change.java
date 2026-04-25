import java.util.Arrays;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        // Initialize the DP array with a value larger than any possible result
        // amount + 1 is a safe "infinity" because the max coins needed is 'amount' (all 1s)
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        
        // Base case: 0 coins are needed to make the amount 0
        dp[0] = 0;
        
        // Iterate through every amount from 1 to the target amount
        for (int i = 1; i <= amount; i++) {
            // Check each coin denomination
            for (int coin : coins) {
                if (i - coin >= 0) {
                    // Update dp[i] if using this coin results in fewer coins
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // If dp[amount] is still the initial 'max', the amount is unreachable
        return dp[amount] > amount ? -1 : dp[amount];
    }
}