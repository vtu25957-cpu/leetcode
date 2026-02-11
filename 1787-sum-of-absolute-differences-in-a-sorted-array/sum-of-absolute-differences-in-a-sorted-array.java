class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] result = new int[n];
        
        // Build prefix sum
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        
        for (int i = 0; i < n; i++) {
            int leftSum = i > 0 ? prefixSum[i - 1] : 0;
            int rightSum = prefixSum[n - 1] - prefixSum[i];
            
            int leftCount = i;
            int rightCount = n - i - 1;
            
            result[i] = (nums[i] * leftCount - leftSum) + (rightSum - nums[i] * rightCount);
        }
        
        return result;
    }
}
