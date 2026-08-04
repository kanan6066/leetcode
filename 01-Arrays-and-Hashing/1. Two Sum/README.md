<h2><a href="https://leetcode.com/problems/two-sum">1. Two Sum</a></h2>

<p>You are given an array of integers <code>nums</code>&nbsp;and an integer <code>target</code>, return <em>indices of the two numbers such that they add up to <code>target</code></em>.</p>

<p>You may assume that each input would have <strong><em>exactly</em> one solution</strong>, and you may not use the <em>same</em> element twice.</p>

<p>You can return the answer in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> nums = [2,7,11,15], target = 9
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> Because nums[0] + nums[1] == 9, we return [0, 1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> nums = [3,2,4], target = 6
<strong>Output:</strong> [1,2]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre><strong>Input:</strong> nums = [3,3], target = 6
<strong>Output:</strong> [0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><strong>Only one valid answer exists.</strong></li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:&nbsp;</strong>Can you come up with an algorithm that is less than <code>O(n<sup>2</sup>)</code><font face="monospace">&nbsp;</font>time complexity?

---

# 🛍️ Two-Sum | Explained

## Approach 1: One-Pass Hash Table
### Intuition
Imagine you are a coat check manager at an event. Guests hand you ticket numbers ($X$), and you need to find two guests whose ticket numbers sum up to a target value ($Target$). 

Instead of asking every guest in line to compare tickets with every other guest (which would take $O(n^2)$ time), you calculate the exact ticket number you need to match the current guest: $Y = Target - X$. You then look up $Y$ in your quick-reference registry book (a Hash Map). 
- If $Y$ is already checked in, you immediately pair them up.
- If $Y$ is not yet checked in, you write down the current guest's ticket number $X$ along with their position in line, so a future guest can match with them.

#### Simple Language Dry Run
Let's trace the algorithm step-by-step using an example:
- **Input:** `nums = [2, 7, 11, 15]`, `target = 9`

1. **Initialization:**
   - Create empty `map = {}`
2. **Iteration $i = 0$ (`nums[0] = 2`):**
   - Calculate `complement = 9 - 2 = 7`
   - Is `7` in `map`? No.
   - Insert current element into map: `map = {2: 0}`
3. **Iteration $i = 1$ (`nums[1] = 7`):**
   - Calculate `complement = 9 - 7 = 2`
   - Is `2` in `map`? **Yes!** Its stored index is `map.get(2) = 0`.
   - Match found! Return array containing `[0, 1]`.

---

### Algorithm Visualized

```mermaid
flowchart TD
    Start([Start Loop: i = 0]) --> CheckLoop{i < nums.length?}
    CheckLoop -- Yes --> Calc[Calculate complement = target - nums[i]]
    Calc --> CheckMap{map.containsKey'complement'?}
    CheckMap -- Yes --> Found[Return new int[] { map.get'complement', i }]
    CheckMap -- No --> Put[map.put'nums[i], i']
    Put --> Inc[i++]
    Inc --> CheckLoop
    Found --> End([End])
    CheckLoop -- No --> ReturnEmpty[Return new int[] {}]
    ReturnEmpty --> End
```

---

### Approach
1. **Initialize Data Structure:** Create a `HashMap<Integer, Integer>` where keys represent array element values and values represent their zero-based indices.
2. **Iterate Through Array:** Traverse `nums` from left to right using index `i`.
3. **Compute Complement:** For each element `nums[i]`, calculate `complement = target - nums[i]`.
4. **Lookup:** Search the map for `complement`:
   - **Hit:** If present, the pair is found. Return an array with the complement's index (`map.get(complement)`) and the current index (`i`).
   - **Miss:** If absent, record the current element and its index (`map.put(nums[i], i)`) into the map.
5. **Fallback:** If the loop terminates without finding a pair, return an empty array `new int[] {}` (or throw an exception depending on problem constraints).

---

### Detailed Code Analysis

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Step 1: Initialize the hash map.
        // HashMap provides O(1) average time complexity for insertions and key lookups.
        HashMap<Integer, Integer> map = new HashMap<>();

        // Step 2: Traverse the array sequentially.
        for (int i = 0; i < nums.length; i++) {
            // Step 3: Compute the required complementary value.
            int complement = target - nums[i];

            // Step 4: Check if the complement has already been indexed in prior iterations.
            if (map.containsKey(complement)) {
                // Return the index of the previously seen complement and the current index.
                return new int[] { map.get(complement), i };
            }

            // Step 5: Store the current element value and its index for future reference.
            map.put(nums[i], i);
        }

        // Step 6: Return an empty array if no solution is found (defensive coding).
        return new int[] {};
    }
}
```

---

### Code

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        return new int[] {};
    }
}
```

---

### Complexity
- **Time Complexity:** $\mathcal{O}(n)$
  - We traverse the array of $n$ elements at most once.
  - In each iteration, `HashMap.containsKey()` and `HashMap.put()` operation runs in $\mathcal{O}(1)$ average time complexity.
  - Overall time complexity is $\mathcal{O}(n)$.

- **Space Complexity:** $\mathcal{O}(n)$
  - In the worst-case scenario (e.g., matching pair elements are at the end of the array or no pair exists), the `HashMap` stores up to $n - 1$ or $n$ key-value entries.
  - Additional memory required scales linearly with the input array size.

---

## 🕵️‍♂️ Follow-up Questions

### 1. What if the input array is already sorted?
If `nums` is guaranteed to be sorted, you can solve the problem in $\mathcal{O}(n)$ time and **$\mathcal{O}(1)$ extra space** using the **Two-Pointer Pattern**:
- Place a `left` pointer at index `0` and a `right` pointer at index `n - 1`.
- While `left < right`:
  - If `nums[left] + nums[right] == target`, return `[left, right]`.
  - If `nums[left] + nums[right] < target`, increment `left++` (to increase the sum).
  - If `nums[left] + nums[right] > target`, decrement `right--` (to decrease the sum).

### 2. How does this code handle duplicate values in the `nums` array?
Because the complement lookup (`map.containsKey`) happens **before** inserting the current element into the map (`map.put`), duplicate values are handled cleanly.
- For example, if `nums = [3, 3]` and `target = 6`:
  1. At $i = 0$: `nums[0] = 3`. `complement = 3`. Map is empty. Insert `{3: 0}`.
  2. At $i = 1$: `nums[1] = 3`. `complement = 3`. `map.containsKey(3)` evaluates to `true` (index 0). Returns `[0, 1]` immediately before `nums[1]` overwrites key `3` in the map.