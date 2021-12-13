# Reference: https://leetcode.com/problems/maximum-swap/discuss/107068/Java-simple-solution-O(n)-time
# https://leetcode.com/problems/maximum-swap/discuss/185982/Straightforward-O(n)-python
# https://leetcode.com/problems/maximum-swap/discuss/107066/Python-Straightforward-with-Explanation

class Solution_Brute_Force:
    def maximumSwap(self, num: int) -> int:
        A = list(str(num))
        maximum = num

        for i in range(len(A)):
            for j in range(i+1, len(A)):
                A[j], A[i] = A[i], A[j]
                number = int("".join(A))
                if maximum < number:
                    maximum = number
                A[i], A[j] = A[j], A[i]

        return maximum

class Solution:
    def maximumSwap(self, num: int) -> int:
        A = list(str(num))
        A = [int(_) for _ in A]
        last = {x: i for i, x in enumerate(A)}
        n = len(A)

        for i in range(n):
            for digit in range(9, -1, -1):
                if digit not in last:
                    continue
                if A[i] < digit and last.get(digit) > i:
                    A[i], A[last.get(digit)] = digit, A[i]
                    A = [str(_) for _ in A]
                    return int("".join(A))

        return num