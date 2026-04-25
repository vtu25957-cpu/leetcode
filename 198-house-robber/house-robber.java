class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int prev2 = 0; // Max money robbed up to 2 houses ago
        int prev1 = 0; // Max money robbed up to 1 house ago

        for (int amount : nums) {
            // At each house, we decide: 
            // Is it better to rob this house (amount + prev2) or skip it (prev1)?
            int current = Math.max(prev1, amount + prev2);
            
            // Update pointers for the next iteration
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}