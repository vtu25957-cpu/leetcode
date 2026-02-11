class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // Update max profit if selling today is better
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            
            // Update minimum price seen so far
            minPrice = Math.min(minPrice, prices[i]);
        }
        
        return maxProfit;
    }
}
