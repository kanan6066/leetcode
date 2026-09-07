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
17    public List<List<Integer>> levelOrder(TreeNode root) {
18        
19        List<List<Integer>> ans = new ArrayList<>();
20        
21        if (root == null) {
22            return ans;
23        }
24
25        Queue<TreeNode> queue = new LinkedList<>();
26        queue.add(root);
27
28        while (!queue.isEmpty()) {
29            
30            int n = queue.size();
31            List<Integer> level = new ArrayList<>();
32
33            for (int i = 0; i < n; i++) {
34                
35                TreeNode node = queue.poll();
36                level.add(node.val);
37
38                if (node.left != null) {
39                    queue.add(node.left);
40                }
41
42                if (node.right != null) {
43                    queue.add(node.right);
44                }
45            }
46
47            ans.add(level);
48        }
49
50        return ans;
51    
52    }
53}