import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 1. Assign unique group IDs to -1 group items
        int groupId = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupId++;
            }
        }

        // 2. Initialize graphs and indegrees
        Map<Integer, List<Integer>> itemGraph = new HashMap<>();
        Map<Integer, List<Integer>> groupGraph = new HashMap<>();
        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[groupId];

        for (int i = 0; i < n; i++) itemGraph.put(i, new ArrayList<>());
        for (int i = 0; i < groupId; i++) groupGraph.put(i, new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                // Item dependency
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                // Group dependency
                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // 3. Topological Sort for items and groups
        List<Integer> itemOrder = topologicalSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topologicalSort(groupGraph, groupIndegree, groupId);

        if (itemOrder.isEmpty() || groupOrder.isEmpty()) return new int[0];

        // 4. Group items by their assigned groups
        Map<Integer, List<Integer>> orderedGroups = new HashMap<>();
        for (int item : itemOrder) {
            orderedGroups.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // 5. Build final result based on group order
        int[] result = new int[n];
        int index = 0;
        for (int grp : groupOrder) {
            List<Integer> items = orderedGroups.getOrDefault(grp, new ArrayList<>());
            for (int item : items) {
                result[index++] = item;
            }
        }

        return result;
    }

    private List<Integer> topologicalSort(Map<Integer, List<Integer>> graph, int[] indegree, int count) {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);
            for (int neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        return order.size() == count ? order : new ArrayList<>();
    }
}