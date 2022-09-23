# Reference: https://leetcode.com/problems/mirror-reflection/discuss/146336/Java-solution-with-an-easy-to-understand-explanation

class Solution:
    def mirrorReflection(self, p: int, q: int) -> int:
        m = q
        n = p
        while m % 2 == 0 and n % 2 == 0:
            m /= 2
            n /= 2

        if m % 2 == 0 and n % 2 == 1:
            return 0
        if m % 2 == 1 and n % 2 == 1:
            return 1
        if m % 2 == 1 and n % 2 == 0:
            return 2

        return -1