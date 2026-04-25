class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            // When count is 0, we assume the current number is the majority candidate
            if (count == 0) {
                candidate = num;
            }

            // If the current number matches the candidate, increment count
            // Otherwise, decrement it
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}