class Solution {
    public int uniquePaths(int m, int n) {
        // Use an array to store the number of ways to reach each column
        int[] dp = new int[n];
        
        // Initialize the first "row" with 1s
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        
        // Fill the DP table row by row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // The new dp[j] = dp[j] (top) + dp[j-1] (left)
                dp[j] += dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
}