class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;

        while (current != null) {
            // If both nodes are smaller than current, go left
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            }
            // If both nodes are greater than current, go right
            else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            }
            // Otherwise, current is the LCA
            else {
                return current;
            }
        }

        return null; // should not reach here if p and q are in the tree
    }
}

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}
