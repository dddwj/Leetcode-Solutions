class Solution:
    def sumOfNumberAndReverse(self, num: int) -> bool:
        if num == 0:
            return True

        for i in range(num):
            ri = self.reverse(i)
            if i + ri == num:
                return True
        return False

    def reverse(self, num: int) -> int:
        res = 0
        while num > 0:
            res = res * 10 + num % 10
            num = num // 10
        return res