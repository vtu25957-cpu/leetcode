class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // both null → symmetric
        if (left == null && right == null) return true;

        // one null or values differ → not symmetric
        if (left == null || right == null || left.val != right.val) return false;

        // check mirrored children
        return isMirror(left.left, right.right) &&
               isMirror(left.right, right.left);
    }
}
