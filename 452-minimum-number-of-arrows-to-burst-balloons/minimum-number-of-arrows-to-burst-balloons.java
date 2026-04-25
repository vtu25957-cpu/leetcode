import java.util.Arrays;

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        // 1. Sort balloons by their end coordinate (x_end)
        // Use Integer.compare to prevent overflow from subtraction
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        // 2. Start with one arrow and point it at the end of the first balloon
        int arrows = 1;
        int currentEnd = points[0][1];

        // 3. Iterate through the remaining balloons
        for (int i = 1; i < points.length; i++) {
            // If the current balloon starts AFTER the previous arrow's position
            if (points[i][0] > currentEnd) {
                // We need a new arrow
                arrows++;
                // Update the arrow position to the end of this new balloon
                currentEnd = points[i][1];
            }
        }

        return arrows;
    }
}