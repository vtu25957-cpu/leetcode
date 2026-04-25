class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);

        // Continue until fast reaches 1 or slow and fast meet in a cycle
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);           // Move one step
            fast = getNext(getNext(fast));  // Move two steps
        }

        // If fast hit 1, it's a happy number
        return fast == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            totalSum += d * d;
            n = n / 10;
        }
        return totalSum;
    }
}