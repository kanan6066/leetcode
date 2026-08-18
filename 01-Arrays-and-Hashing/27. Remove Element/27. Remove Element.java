1class Solution {
2    public int removeElement(int[] nums, int val) {
3         int i = 0;
4
5        for (int j = 0; j < nums.length; j++) {
6
7            if (nums[j] != val) {
8                nums[i] = nums[j];
9                i++;
10            }
11
12        }
13
14        return i;
15    }
16}