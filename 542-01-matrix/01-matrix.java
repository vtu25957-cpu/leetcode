class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        
        // Step 1: Initialize the distance matrix and queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    mat[i][j] = Integer.MAX_VALUE; // Mark 1s as unvisited
                }
            }
        }
        
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        // Step 2: Multi-source BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d : directions) {
                int ni = curr[0] + d[0];
                int nj = curr[1] + d[1];
                
                // If the neighbor is within bounds and we found a shorter path
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    if (mat[ni][nj] > mat[curr[0]][curr[1]] + 1) {
                        mat[ni][nj] = mat[curr[0]][curr[1]] + 1;
                        queue.offer(new int[]{ni, nj});
                    }
                }
            }
        }
        
        return mat;
    }
}