1class Solution {
2    public String longestCommonPrefix(String[] strs) {
3         String prefix = strs[0];
4
5        for (int i = 1; i < strs.length; i++) {
6
7            while (!strs[i].startsWith(prefix)) {
8                prefix = prefix.substring(0, prefix.length() - 1);
9
10                if (prefix.isEmpty()) {
11                    return ;
12                }
13            }
14        }
15
16        return prefix;
17    }
18}