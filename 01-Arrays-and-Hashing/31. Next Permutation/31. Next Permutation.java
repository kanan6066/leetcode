1class Solution {
2    public void nextPermutation(int[] nums) {
3
4        int i = nums.length - 2;
5
6        while (i >= 0 && nums[i] >= nums[i + 1]) {
7            i--;
8        }
9        if (i >= 0) {
10            int j = nums.length - 1;
11
12            while (nums[j] <= nums[i]) {
13                j--;
14            }
15            swap(nums, i, j);
16        }
17        reverse(nums, i + 1, nums.length - 1);
18    }
19    private void swap(int[] nums, int i, int j) {
20        int temp = nums[i];
21        nums[i] = nums[j];
22        nums[j] = temp;
23    }
24    private void reverse(int[] nums, int left, int right) {
25        while (left < right) {
26            swap(nums, left, right);
27            left++;
28            right--;
29        }
30    }
31}