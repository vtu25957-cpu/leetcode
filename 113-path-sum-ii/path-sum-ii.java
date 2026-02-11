import java.util.*;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        List<Integer> path = new ArrayList<>();
        dfs(root, targetSum, path, result);
        return result;
    }

    private void dfs(TreeNode node, int remainingSum, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        // Add current node to path
        path.add(node.val);

        // Check if it's a leaf and the path sums to target
        if (node.left == null && node.right == null && node.val == remainingSum) {
            result.add(new ArrayList<>(path)); // add a copy of the path
        } else {
            // Recurse left and right
            dfs(node.left, remainingSum - node.val, path, result);
            dfs(node.right, remainingSum - node.val, path, result);
        }

        // Backtrack: remove current node before returning
        path.remove(path.size() - 1);
    }
}

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}
