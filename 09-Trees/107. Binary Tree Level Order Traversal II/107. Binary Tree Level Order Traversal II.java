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
16import java.util.*;
17
18class Solution {
19    public List<List<Integer>> levelOrderBottom(TreeNode root) {
20        List<List<Integer>> result = new LinkedList<>();
21
22        if (root == null) {
23            return result;
24        }
25
26        Queue<TreeNode> queue = new LinkedList<>();
27        queue.offer(root);
28
29        while (!queue.isEmpty()) {
30            int size = queue.size();
31            List<Integer> level = new ArrayList<>();
32
33            for (int i = 0; i < size; i++) {
34                TreeNode node = queue.poll();
35                level.add(node.val);
36
37                if (node.left != null) {
38                    queue.offer(node.left);
39                }
40
41                if (node.right != null) {
42                    queue.offer(node.right);
43                }
44            }
45
46            // Add current level at the beginning
47            result.add(0, level);
48        }
49
50        return result;
51    }
52}