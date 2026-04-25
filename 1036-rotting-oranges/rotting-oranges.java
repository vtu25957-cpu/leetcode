import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        
        // 1. Initial scan to find all rotten oranges and count fresh ones
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }
        
        // If there are no fresh oranges, 0 minutes have elapsed
        if (freshCount == 0) return 0;
        
        int minutes = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        // 2. BFS: Spread the rot
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                
                for (int[] dir : directions) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    
                    // If neighbor is within bounds and is a fresh orange
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; // Infect the orange
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                        rottedThisMinute = true;
                    }
                }
            }
            
            if (rottedThisMinute) minutes++;
        }
        
        // 3. Check if any fresh oranges are left unreachable
        return freshCount == 0 ? minutes : -1;
    }
}