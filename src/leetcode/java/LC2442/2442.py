class Solution:
    def countDistinctIntegers(self, nums: List[int]) -> int:
        if not nums:
            return 0
        s = set()
        for num in nums:
            if num not in s:
                s.add(num)
            rnum = self.reverse(num)
            if rnum not in s:
                s.add(rnum)
        return len(s)


    def reverse(self, num: int) -> int:
        res = 0
        while num > 0:
            res = ((res * 10) + (num % 10))
            num //= 10
        return res