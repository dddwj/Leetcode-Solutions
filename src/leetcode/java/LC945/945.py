# Sort
# Time: O(NlgN)
# Space: O(1)  for in-place sorting
class Solution_2:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        if not nums:
            return 0

        nums.sort()
        slow = nums[0]
        move = 0
        for num in nums:
            if slow > num:
                move += (slow - num)
            slow = max(slow + 1, num + 1)

        return move



# Count
# Time: O(N + M) N = len(nums)   M = max(nums)
class Solution1:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        max_val = max(nums)
        count = collections.Counter(nums)
        # print(count)
        taken = []

        moves = 0
        for x in range(max_val + len(nums)):
            if count[x] >= 2:
                taken.extend([x] * (count[x] - 1))
            elif taken and count[x] == 0:
                moves += x - taken.pop()
        return moves