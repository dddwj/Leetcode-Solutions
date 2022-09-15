# Reference: https://leetcode.com/problems/last-stone-weight/solution/
class Solution_brute_force:
    # Time: O(N^2)
    # Space: O(1) (in-place)
    def lastStoneWeight(self, stones: List[int]) -> int:
        if not stones or len(stones) <= 1:
            return stones[0]

        def remove_largest(stones):
            index_of_largest = stones.index(max(stones))
            stones[index_of_largest], stones[-1] = stones[-1], stones[index_of_largest]
            return stones.pop()


        while len(stones) > 1:
            stone1 = remove_largest(stones)
            stone2 = remove_largest(stones)
            if stone1 == stone2:
                continue
            else:
                stones.append(stone1 - stone2)

        return stones[0] if stones else 0

class Solution_heap:
    # Time: O(NlgN)
    # Space: O(1) for heap in python
    def lastStoneWeight(self, stones: List[int]) -> int:
        if not stones or len(stones) <= 1:
            return stones[0]

        stones = [stone*(-1) for stone in stones]
        heapq.heapify(stones)  # heapify: in-place, linear time

        while len(stones) > 1:
            stone_1 = heapq.heappop(stones)
            stone_2 = heapq.heappop(stones)
            if stone_1 != stone_2:
                heapq.heappush(stones, stone_1 - stone_2)

        return -heapq.heappop(stones) if stones else 0
