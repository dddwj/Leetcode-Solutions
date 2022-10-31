# See also: LC347

# Solution: Min Heap
# Time: O(NlgK) + O(N) + O(KlgK) ===> O(NlgK)
#   O(N) to build frequency dictionary. O(NlgK) to build heap. O(KlgK) to sort/make output
# Space: O(N) + O(K) ===> O(N)
# Reference: https://leetcode.com/problems/top-k-frequent-words/solution/
class Solution_MIN_HEAP:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        freqs = {}
        for word in words:
            if word not in freqs:
                freqs[word] = 0
            freqs[word] += 1

        h = []
        for word, freq in freqs.items():
            heapq.heappush(h, Pair(word, freq))
            if len(h) > k:
                heapq.heappop(h)

        res = []
        while h:
            res.insert(0, heapq.heappop(h).word)
        return res

        # OR:
        # return [p.word for p in sorted(h, reverse=True)]

class Pair:
    def __init__(self, word, freq):
        self.word = word
        self.freq = freq

    def __lt__(self, p):
        return self.freq < p.freq or (self.freq == p.freq and self.word > p.word)

