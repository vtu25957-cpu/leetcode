import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. Initialize adjacency list and indegree array
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // 2. Build the graph
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];
            adj.get(prerequisite).add(course);
            indegree[course]++;
        }

        // 3. Add all courses with 0 indegree to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 4. Process the queue
        int[] result = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[index++] = current;

            // Reduce indegree for neighbors
            for (int neighbor : adj.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // 5. If index matches numCourses, we found a valid path; else, there is a cycle
        return (index == numCourses) ? result : new int[0];
    }
}