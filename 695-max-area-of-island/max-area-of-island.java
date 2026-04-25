class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If we find land, start a DFS to find the full area
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        // Base cases: check bounds and if the cell is water (0)
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        // "Sink" the island: mark current cell as visited by setting it to 0
        grid[r][c] = 0;

        // Recursively visit all 4 neighbors and sum the area
        return 1 + dfs(grid, r + 1, c) + 
                   dfs(grid, r - 1, c) + 
                   dfs(grid, r, c + 1) + 
                   dfs(grid, r, c - 1);
    }
}