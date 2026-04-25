import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU(10001); // Max total emails based on constraints
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToId = new HashMap<>();
        int id = 0;

        // 1. Map each email to a unique ID and its owner's name
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, id++);
                }
                emailToName.put(email, name);
                // 2. Union all emails within the same account
                dsu.union(emailToId.get(account.get(1)), emailToId.get(email));
            }
        }

        // 3. Group emails by their root parent ID
        Map<Integer, List<String>> components = new HashMap<>();
        for (String email : emailToId.keySet()) {
            int root = dsu.find(emailToId.get(email));
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        // 4. Format the result
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (List<String> componentEmails : components.values()) {
            Collections.sort(componentEmails);
            List<String> merged = new ArrayList<>();
            merged.add(emailToName.get(componentEmails.get(0))); // Add the name
            merged.addAll(componentEmails); // Add sorted emails
            mergedAccounts.add(merged);
        }

        return mergedAccounts;
    }
}

class DSU {
    int[] parent;
    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}