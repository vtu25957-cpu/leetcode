class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        // If root is p or q, root is the LCA
        if (root == p || root == q) return root;

        // Recurse left and right
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If p and q found in different subtrees, root is LCA
        if (left != null && right != null) return root;

        // Otherwise, return the non-null child
        return left != null ? left : right;
    }
}

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}
