public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // 1. Shift result left to make room
            result <<= 1;
            // 2. If the last bit of n is 1, add 1 to result
            if ((n & 1) == 1) {
                result++;
            }
            // 3. Unsigned right shift n to process the next bit
            n >>>= 1;
        }
        return result;
    }
}