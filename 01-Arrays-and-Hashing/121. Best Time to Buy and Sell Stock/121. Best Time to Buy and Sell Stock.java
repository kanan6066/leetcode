1class Solution {
2    public int maxProfit(int[] prices) {
3        int minPrice = prices[0];
4        int maxProfit = 0;
5
6        for (int i = 1; i < prices.length; i++) {
7            // Best price to buy so far
8            minPrice = Math.min(minPrice, prices[i]);
9
10            // Profit if we sell today
11            int profit = prices[i] - minPrice;
12
13            // Maximum profit
14            maxProfit = Math.max(maxProfit, profit);
15        }
16
17        return maxProfit;
18    }
19}
20