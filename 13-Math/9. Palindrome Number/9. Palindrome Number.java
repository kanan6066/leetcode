1import java.util.*;
2
3class Solution {
4    public static boolean isPalindrome(int x) {
5        if (x < 0) return false;
6
7        int original = x;
8        long reversed = 0;
9
10        while (x != 0) {
11            reversed = reversed * 10 + x % 10;
12            x /= 10;
13        }
14
15        return original == reversed;
16    }
17
18    public static void main(String[] args) {
19        Scanner sc = new Scanner(System.in);
20        int x = sc.nextInt();
21
22        System.out.println(isPalindrome(x));
23    }
24}