import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, boolean[] used) {
        // Base case: If the current list size matches the input length, we found a permutation
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if the element is already in the current permutation
            if (used[i]) continue;

            // 1. Choose: Mark element as used and add to path
            used[i] = true;
            tempList.add(nums[i]);

            // 2. Explore: Recurse to build the rest of the permutation
            backtrack(result, tempList, nums, used);

            // 3. Un-choose (Backtrack): Remove last element and mark as unused
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }
}