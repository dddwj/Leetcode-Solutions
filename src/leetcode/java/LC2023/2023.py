# Solution: Brute Force
# Time: O(N^2) where N = len(nums) M = len(target)
# Space: O(N)
class Solution_BruteForce:
    def numOfPairs(self, nums: List[str], target: str) -> int:
        s = set()
        n = len(nums)
        for i in range(n):
            for j in range(n):
                if i == j:
                    continue
                if target == nums[i] + nums[j]:
                    s.add((i, j))

        return len(s)

# Solution: HashMap
# Reference: https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/discuss/1499007/Python3-freq-table/1578780
# Time: O(N + M) where N = len(nums) M = len(target)
# Space:O O(N)
class Solution_Hashmap:
    def numOfPairs(self, nums: List[str], target: str) -> int:
        c = defaultdict(int)
        for num in nums:
            c[num] += 1

        res = 0
        for i in range(len(target)):
            first = target[:i]
            second = target[i:]
            if first == second:
                res += c[first] * (c[first] - 1)
            else:
                res += c[first] * c[second]

        return res