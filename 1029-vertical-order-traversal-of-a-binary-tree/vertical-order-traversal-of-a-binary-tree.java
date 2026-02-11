import java.util.*;

class Solution {
    // Nested class to store a node with its row and column
    class NodeInfo {
        TreeNode node;
        int row, col;
        NodeInfo(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<NodeInfo> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);

        // Sort by column, then row, then node value
        nodes.sort((a, b) -> {
            if (a.col != b.col) return a.col - b.col;
            if (a.row != b.row) return a.row - b.row;
            return a.node.val - b.node.val;
        });

        List<List<Integer>> result = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;

        for (NodeInfo ni : nodes) {
            if (ni.col != prevCol) {
                result.add(new ArrayList<>());
                prevCol = ni.col;
            }
            result.get(result.size() - 1).add(ni.node.val);
        }

        return result;
    }

    private void dfs(TreeNode node, int row, int col, List<NodeInfo> nodes) {
        if (node == null) return;
        nodes.add(new NodeInfo(node, row, col));
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }
}

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}
