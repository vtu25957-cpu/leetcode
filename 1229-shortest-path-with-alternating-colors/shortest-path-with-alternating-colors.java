import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // 1. Build Adjacency Lists
        // We use two lists: one for red (0) and one for blue (1)
        List<Integer>[] redAdj = new ArrayList[n];
        List<Integer>[] blueAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            redAdj[i] = new ArrayList<>();
            blueAdj[i] = new ArrayList<>();
        }
        for (int[] edge : redEdges) redAdj[edge[0]].add(edge[1]);
        for (int[] edge : blueEdges) blueAdj[edge[0]].add(edge[1]);

        // 2. Initialize results and visited tracking
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
        // visited[node][color] -> 0 for red, 1 for blue
        boolean[][] visited = new boolean[n][2];

        // 3. BFS Queue: [node, distance, lastColor]
        // lastColor: 0 for red, 1 for blue, -1 for start
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, -1});
        
        // Mark both "colors" as visited for node 0 to prevent loops back to start
        visited[0][0] = true;
        visited[0][1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0];
            int dist = curr[1];
            int lastCol = curr[2];

            // Update answer if it's the first time reaching this node
            if (answer[u] == -1) {
                answer[u] = dist;
            }

            // If last edge was blue (or start), try moving via RED edges
            if (lastCol == 1 || lastCol == -1) {
                for (int v : redAdj[u]) {
                    if (!visited[v][0]) {
                        visited[v][0] = true;
                        queue.offer(new int[]{v, dist + 1, 0});
                    }
                }
            }

            // If last edge was red (or start), try moving via BLUE edges
            if (lastCol == 0 || lastCol == -1) {
                for (int v : blueAdj[u]) {
                    if (!visited[v][1]) {
                        visited[v][1] = true;
                        queue.offer(new int[]{v, dist + 1, 1});
                    }
                }
            }
        }

        return answer;
    }
}