1class Solution {
2    public int searchInsert(int[] nums, int target) {
3         int left = 0;
4        int right = nums.length - 1;
5
6        while (left <= right) {
7
8            int mid = left + (right - left) / 2;
9
10            if (nums[mid] == target) {
11                return mid;
12            }
13
14            if (nums[mid] < target) {
15                left = mid + 1;
16            } else {
17                right = mid - 1;
18            }
19        }
20
21        return left;
22    }
23}