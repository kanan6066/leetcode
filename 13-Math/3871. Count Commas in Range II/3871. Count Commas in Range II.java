1class Solution {
2    public long countCommas(long n) {
3        long ans = 0;
4        long start = 1000;
5        long commas = 1;
6
7        while (start <= n) {
8            long end = start * 1000 - 1;
9
10            if (end > n) {
11                end = n;
12            }
13
14            ans += (end - start + 1) * commas;
15
16            start *= 1000;
17            commas++;
18        }
19
20        return ans;
21    }
22}