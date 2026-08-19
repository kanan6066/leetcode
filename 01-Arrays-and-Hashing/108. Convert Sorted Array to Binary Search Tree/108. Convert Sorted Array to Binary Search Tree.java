1/**
2 * Definition for a binary tree node.
3 * public class TreeNode {
4 *     int val;
5 *     TreeNode left;
6 *     TreeNode right;
7 *     TreeNode() {}
8 *     TreeNode(int val) { this.val = val; }
9 *     TreeNode(int val, TreeNode left, TreeNode right) {
10 *         this.val = val;
11 *         this.left = left;
12 *         this.right = right;
13 *     }
14 * }
15 */
16class Solution {
17    public TreeNode sortedArrayToBST(int[] nums) {
18        return buildBST(nums, 0, nums.length - 1);
19    }
20      private TreeNode buildBST(int[] nums, int left, int right) {
21        if (left > right) {
22            return null;
23        }
24
25        int mid = left + (right - left) / 2;
26
27        TreeNode root = new TreeNode(nums[mid]);
28
29        root.left = buildBST(nums, left, mid - 1);
30        root.right = buildBST(nums, mid + 1, right);
31
32        return root;
33    }
34}