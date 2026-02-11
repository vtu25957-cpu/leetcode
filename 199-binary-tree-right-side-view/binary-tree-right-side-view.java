import java.util.*;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) return;

        // If this is the first node we encounter at this depth, add it
        if (depth == result.size()) {
            result.add(node.val);
        }

        // Visit right child first
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }
}
