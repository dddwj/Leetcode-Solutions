class Solution:
    def findMaxK(self, nums: List[int]) -> int:
        if not nums:
            return -1
        largest = 0
        seen = set()
        for num in nums:
            if num not in seen:
                seen.add(num)
            if -num in seen:
                largest = max(abs(num), largest)
        return largest if largest else -1
