class Solution {
    public int climbStairs(int n) {
        // Base cases: 1 step = 1 way, 2 steps = 2 ways
        if (n <= 2) {
            return n;
        }

        int first = 1;  // Ways to reach the 1st step
        int second = 2; // Ways to reach the 2nd step

        for (int i = 3; i <= n; i++) {
            int current = first + second;
            first = second; // Move 'first' up to the next step
            second = current; // Move 'second' up to the current step
        }

        return second;
    }
}