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
19    public List<Double> averageOfLevels(TreeNode root) {
20        List<Double> result = new ArrayList<>();
21
22        Queue<TreeNode> queue = new LinkedList<>();
23        queue.offer(root);
24
25        while (!queue.isEmpty()) {
26            int size = queue.size();
27            long sum = 0;
28
29            for (int i = 0; i < size; i++) {
30                TreeNode node = queue.poll();
31
32                sum += node.val;
33
34                if (node.left != null) {
35                    queue.offer(node.left);
36                }
37
38                if (node.right != null) {
39                    queue.offer(node.right);
40                }
41            }
42
43            result.add((double) sum / size);
44        }
45
46        return result;
47    }
48}