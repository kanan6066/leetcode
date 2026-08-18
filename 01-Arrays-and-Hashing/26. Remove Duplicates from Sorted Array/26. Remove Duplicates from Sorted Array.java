1class Solution {
2    public int removeDuplicates(int[] nums) {
3         int i = 0;
4
5        for (int j = 1; j < nums.length; j++) {
6
7            if (nums[i] != nums[j]) {
8                i++;
9                nums[i] = nums[j];
10            }
11        }
12
13        return i + 1;
14    }
15}