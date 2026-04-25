import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        // Base Case: If the current combination reaches size k, add it to the result
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Optimization: i <= n - (k - current.size()) + 1
        // This ensures we don't start a loop if there aren't enough numbers left to reach size k
        for (int i = start; i <= n; i++) {
            // 1. Choose the number
            current.add(i);
            
            // 2. Recurse to the next number (i + 1)
            backtrack(i + 1, n, k, current, result);
            
            // 3. Backtrack (remove the number) to explore other options
            current.remove(current.size() - 1);
        }
    }
}