class Solution:
    def reverse(self, x: int) -> int:
        if x == -2**31:   # 1 11111...111
            return 0

        if x < 0:
            x = 0 - x
            flag = -1
        else:
            flag = 1

        abs_val = int(''.join(reversed([c for c in str(x)])))

        return flag * abs_val if abs_val < 2**31 else 0