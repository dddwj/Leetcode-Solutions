# Reference: https://leetcode.com/problems/numbers-with-same-consecutive-differences/solution/
class Solution_DFS:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        self.ans = []

        for num in range(1, 10):
            self.dfs(k, n - 1, num)

        return list(self.ans)

    def dfs(self, k: int, n_remain: int, curr: int):
        if n_remain == 0:
            self.ans.append(curr)
            return

        tail_digit = curr % 10
        next_digits = set([tail_digit + k, tail_digit - k])

        for next_digit in next_digits:
            if 0 <= next_digit < 10:
                new_num = curr * 10 + next_digit
                self.dfs(k, n_remain - 1, new_num)


class Solution_BFS:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        self.ans = []

        queue = [digit for digit in range(1, 10)]

        for level in range(n - 1):
            next_queue = []
            for num in queue:
                tail_digit = num % 10
                next_digits = set([tail_digit + k, tail_digit - k])

                for next_digit in next_digits:
                    if 0 <= next_digit < 10:
                        new_num = num * 10 + next_digit
                        next_queue.append(new_num)
            queue = next_queue

        return queue