# Time: O(N*k)
# Space: O(N)
class Solution_brute_force_simulation:
    def findTheWinner(self, n: int, k: int) -> int:
        if k == 0:
            return -1

        arr = list(i+1 for i in range(n))
        curr = -1
        for remain in range(n, 1, -1):
            th_person = self.get_th_person(len(arr), k)
            person, curr = self.remove_person(arr, curr, th_person)
            print(person)
        return arr[0]

    def get_th_person(self, n: int, k: int) -> int:
        th = k % n
        if th == 0:
            th += n
        return th

    def remove_person(self, arr: list(), curr: int, th: int) -> (int, int):
        curr += th
        if curr >= len(arr):
            curr %= len(arr)
        person = arr[curr]
        arr.remove(person)

        curr = curr - 1
        if curr == -1:
            curr += len(arr)
        return person, curr


# Reference: https://leetcode.com/problems/find-the-winner-of-the-circular-game/discuss/1152594/C%2B%2B-oror-QUEUE-oror-SELF-EXPALANATORY
# Time: O(N*k)
# Space: O(N)
class Solution_simulation:
    def findTheWinner(self, n: int, k: int) -> int:
        if k == 0:
            return -1

        q = deque(i+1 for i in range(n))
        while len(q) > 1:
            count = k
            while count > 1:
                temp = q.popleft()
                q.append(temp)
                count -= 1
            q.popleft()

        return q[0]

# Reference: https://leetcode.com/problems/find-the-winner-of-the-circular-game/discuss/1601186/C%2B%2B-oror-3-Approach-oror-Easy-Understanding
# Time: O(N)
# Space: O(N)
class Solution_recursion:
    def findTheWinner(self, n: int, k: int) -> int:
        return self.helper(n, k) + 1

    def helper(self, n: int, k: int) -> int:
        if n == 1:
            return 0
        res = (self.helper(n-1, k) + k) % n
        print(res)
        return res


# Reference: https://cs.stackexchange.com/questions/7048/a-recursive-formula-for-generalized-josephus-problem (see pdf)
# Time: O(N)
# Space: O(1)
class Solution_recursion_optimized:
    def findTheWinner(self, n: int, k: int) -> int:
        return self.helper(n, k) + 1  # +1 is for converting 0-based indexing to 1-based indexing

    def helper(self, n, k):
        ans = 0
        for i in range(1, n+1):
            ans = (ans + k) % i
        return ans