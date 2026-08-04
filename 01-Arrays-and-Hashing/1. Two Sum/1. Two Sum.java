1class Solution {
2    public int[] twoSum(int[] nums, int target) {
3          HashMap<Integer, Integer> map = new HashMap<>();
4
5        for (int i = 0; i < nums.length; i++) {
6            int complement = target - nums[i];
7
8            if (map.containsKey(complement)) {
9                return new int[] { map.get(complement), i };
10            }
11
12            map.put(nums[i], i);
13        }
14
15        return new int[] {};
16    }
17 }
18