<h2><a href="https://leetcode.com/problems/plus-one">66. Plus One</a></h2>

<p>You are given a <strong>large integer</strong> represented as an integer array <code>digits</code>, where each <code>digits[i]</code> is the <code>i<sup>th</sup></code> digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading <code>0</code>'s.</p>

<p>Increment the large integer by one and return <em>the resulting array of digits</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> digits = [1,2,3]
<strong>Output:</strong> [1,2,4]
<strong>Explanation:</strong> The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> digits = [4,3,2,1]
<strong>Output:</strong> [4,3,2,2]
<strong>Explanation:</strong> The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre><strong>Input:</strong> digits = [9]
<strong>Output:</strong> [1,0]
<strong>Explanation:</strong> The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 100</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
	<li><code>digits</code> does not contain any leading <code>0</code>'s.</li>
</ul>


---

# 🛍️ Plus-One | Explained

## Approach 1: Right-to-Left Traversal with In-Place Carry Propagation
### Intuition
Think of an old mechanical odometer on a car or an analog tally counter. When you add $1$ to a number:
- If the rightmost wheel shows any digit from `0` to `8`, it simply rolls over to the next digit (`1` to `9`), and no other digits change.
- If the rightmost wheel shows `9`, it rolls over to `0` and triggers a carry that rolls the adjacent wheel to the left by $+1$.
- This ripple effect continues leftward until a wheel has a digit less than `9`.
- The only special case occurs when **all** digits are `9` (e.g., `999` $\to$ `1000`). In that case, all wheels reset to `0`, and we must prepend a new leading `1` by expanding the number by one digit.

Because the carry only propagates when encountering a `9`, we can iterate backwards from the least significant digit (end of the array) to the most significant digit (start of the array). The moment we encounter a digit smaller than `9`, we increment it by `1` and can immediately return the array, as no further carry will propagate.

### Algorithm Visualized
```mermaid
flowchart TD
    Start([Start: Loop i from length-1 down to 0]) --> CheckDigit{digits[i] < 9?}
    CheckDigit -- Yes --> Increment[digits[i] += 1]
    Increment --> ReturnExisting([Return digits])
    CheckDigit -- No --> SetZero[digits[i] = 0]
    SetZero --> NextIter{i >= 0?}
    NextIter -- Yes --> CheckDigit
    NextIter -- No --> AllocNew[Create new array of size length + 1]
    AllocNew --> SetLeadingOne[result[0] = 1]
    SetLeadingOne --> ReturnNew([Return result])
```

### Approach
1. **Traverse Backwards**: Start a loop from the last index (`digits.length - 1`) down to `0`.
2. **Check for Increment**:
   - If the current digit `digits[i]` is strictly less than `9`, increment it by `1` (`digits[i]++`). Since there is no carry to propagate further left, the addition is complete. Return the modified `digits` array immediately.
   - If the current digit is `9`, it becomes `0` (`digits[i] = 0`), and the carry of `1` naturally moves to the next iteration (the digit to the left).
3. **Handle Edge Case (All 9s)**:
   - If the loop finishes without returning, it means every single digit was a `9` (e.g., `[9, 9, 9]`), and all elements are now `[0, 0, 0]`.
   - Allocate a new integer array `result` of size `digits.length + 1`.
   - In Java, newly initialized `int[]` arrays are automatically zero-filled. Therefore, we only need to set the most significant digit `result[0] = 1` to produce `[1, 0, 0, 0]`.
   - Return `result`.

### Detailed Code Analysis

```java
for (int i = digits.length - 1; i >= 0; i--) {
```
- We initialize index `i` to `digits.length - 1`, pointing to the least significant digit (units place), and decrement `i` on each step to move toward the most significant digit (leftmost element).

```java
    if (digits[i] < 9) {
        digits[i]++;
        return digits;
    }
```
- **Base Case for Early Exit**: If the current digit is anywhere in the range `[0, 8]`, adding `1` will not generate a carry. We increment `digits[i]` in-place and immediately return the reference to `digits`. This avoids unnecessary iterations over the rest of the array.

```java
    digits[i] = 0;
}
```
- If `digits[i] == 9`, adding `1` yields `10`. The digit at position `i` becomes `0`, and a carry of `1` is implicitly passed to the next iteration (`i - 1`).

```java
int[] result = new int[digits.length + 1];
result[0] = 1;
return result;
```
- If the loop terminates without returning, the original array contained only `9`s (e.g., `[9]`, `[9, 9]`, `[9, 9, 9]`).
- All positions in `digits` have already been reset to `0`.
- We allocate a new array `result` with length `digits.length + 1`. In Java, primitive `int` arrays are default-initialized to `0`.
- Setting `result[0] = 1` results in the final array representation (e.g., `[9, 9]` $\to$ `[1, 0, 0]`).

### Dry Run

#### Case 1: Standard Addition without Cascade (`digits = [1, 2, 3]`)
- **i = 2**: `digits[2]` is `3`. `3 < 9` is `true`.
  - `digits[2]` increments to `4`.
  - Returns `[1, 2, 4]`. (Total steps: 1)

#### Case 2: Partial Carry Cascade (`digits = [1, 2, 9]`)
- **i = 2**: `digits[2]` is `9`. `9 < 9` is `false`.
  - `digits[2]` becomes `0`. Array is now `[1, 2, 0]`.
- **i = 1**: `digits[1]` is `2`. `2 < 9` is `true`.
  - `digits[1]` increments to `3`.
  - Returns `[1, 3, 0]`. (Total steps: 2)

#### Case 3: Full Overflow / All Nines (`digits = [9, 9]`)
- **i = 1**: `digits[1]` is `9` $\to$ `digits[1] = 0`. Array: `[9, 0]`.
- **i = 0**: `digits[0]` is `9` $\to$ `digits[0] = 0`. Array: `[0, 0]`.
- Loop finishes.
- Allocate `result = new int[3]` $\to$ `[0, 0, 0]`.
- Set `result[0] = 1` $\to$ `[1, 0, 0]`.
- Returns `[1, 0, 0]`.

### Code
```java
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
```

### Complexity
- **Time:** $\mathcal{O}(N)$, where $N$ is the number of digits in the array.
  - **Best Case:** $\mathcal{O}(1)$ when the last digit is $< 9$ (e.g., `[1, 2, 3]`), requiring only a single operation.
  - **Worst Case:** $\mathcal{O}(N)$ when all digits are `9` (e.g., `[9, 9, 9]`), requiring a full traversal of the array plus an array allocation of size $N + 1$.
- **Space:** $\mathcal{O}(1)$ auxiliary space in the general case because the modifications are done directly in-place on the input array. $\mathcal{O}(N)$ only in the worst-case scenario where a new array of size $N + 1$ is allocated to accommodate the new leading digit.

---

## 🕵️‍♂️ Follow-up Questions

### 1. What if we need to add an arbitrary integer $K$ instead of just $1$? (LeetCode 989: Add to Array-Form of Integer)
Instead of checking for `< 9`, maintain $K$ as the active carry. In each iteration from right to left, add $K$ to `digits[i]`, update `digits[i] = (digits[i] + K) % 10`, and reduce $K = (digits[i] + K) / 10$. If $K > 0$ after processing all digits, prepend the remaining digits of $K$ to the output list.

### 2. Can we convert the array into an integer, add 1, and convert it back?
No. An array can contain up to $100$ digits. Standard primitive types (`int` up to $\approx 2 \times 10^9$, `long` up to $\approx 9 \times 10^{18}$) will overflow. While `BigInteger` in Java or arbitrary-precision integers in Python could handle it, doing so incurs unnecessary memory overhead and defeats the purpose of practicing digit-by-digit array manipulation.