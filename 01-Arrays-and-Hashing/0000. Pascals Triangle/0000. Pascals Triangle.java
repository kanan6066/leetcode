1import java.util.*;
2
3class Solution {
4    public List<List<Integer>> generate(int numRows) {
5        List<List<Integer>> result = new ArrayList<>();
6
7        for (int i = 0; i < numRows; i++) {
8            List<Integer> row = new ArrayList<>();
9
10            row.add(1);
11
12            for (int j = 1; j < i; j++) {
13                row.add(result.get(i - 1).get(j - 1)
14                        + result.get(i - 1).get(j));
15            }
16
17            if (i > 0) {
18                row.add(1);
19            }
20
21            result.add(row);
22        }
23
24        return result;
25    }
26}