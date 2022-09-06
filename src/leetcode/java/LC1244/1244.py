# Reference: https://leetcode.com/problems/design-a-leaderboard/solution/

class Leaderboard_brute_force:

    def __init__(self):
        self.scores = defaultdict()

    def addScore(self, playerId: int, score: int) -> None:
        if playerId not in self.scores:
            self.scores[playerId] = 0
        self.scores[playerId] += score

    def top(self, K: int) -> int:
        values = [v for _, v in sorted(self.scores.items(), key=lambda item: item[1])]
        values.sort(reverse=True)
        return sum(values[:K])

    def reset(self, playerId: int) -> None:
        self.scores[playerId] = 0


class Leaderboard_brute_force_optimized:

    def __init__(self):
        self.scores = {}

    def addScore(self, playerId: int, score: int) -> None:
        if playerId not in self.scores:
            self.scores[playerId] = 0
        self.scores[playerId] += score

    def top(self, K: int) -> int:
        heap = []
        for x in self.scores.values():
            heapq.heappush(heap, x)
            if len(heap) > K:
                heapq.heappop(heap)
        res = 0
        while heap:
            res += heapq.heappop(heap)
        return res

    def reset(self, playerId: int) -> None:
        self.scores[playerId] = 0

