# Heap + Lazy update
class MaxStack_solution11:
    import heapq

    def __init__(self):
        self.heap = []
        self.next_idx = 0
        self.stack = []
        self.removed = set()

    def push(self, x: int) -> None:
        self.stack.append((x, self.next_idx))
        heapq.heappush(self.heap, (-x, -self.next_idx))
        self.next_idx += 1

    def pop(self) -> int:
        while self.stack and self.stack[-1][1] in self.removed:
            self.stack.pop()
        num, idx = self.stack.pop()
        self.removed.add(idx)
        return num

    def top(self) -> int:
        while self.stack and self.stack[-1][1] in self.removed:
            self.stack.pop()
        return self.stack[-1][0]

    def peekMax(self) -> int:
        while self.heap and -self.heap[0][1] in self.removed:
            heapq.heappop(self.heap)
        return -self.heap[0][0]

    def popMax(self) -> int:
        while self.heap and -self.heap[0][1] in self.removed:
            heapq.heappop(self.heap)
        num, idx = heapq.heappop(self.heap)
        self.removed.add(-idx)
        return -num


# Heap + Lazy update (Easier to understand)
# Reference: https://leetcode.com/problems/max-stack/discuss/309621/Python-using-stack-%2B-heap-%2B-set-with-explanation-and-discussion-of-performance
#   https://stackoverflow.com/questions/3954530/how-to-make-heapq-evaluate-the-heap-off-of-a-specific-attribute
class MaxStack_solution12:
    def __init__(self):
        self.soft_deleted = set()
        self.max_heap = []
        self.recency_stack = []
        self.next_id = 0

    def push(self, x: int) -> None:
        heapq.heappush(self.max_heap, (-x, self.next_id))
        self.recency_stack.append((x, self.next_id))
        self.next_id -= 1
        # How to make heapq evaluate the heap off of a specific attribute?: https://stackoverflow.com/questions/3954530/how-to-make-heapq-evaluate-the-heap-off-of-a-specific-attribute

    def _clean_up(self) -> None:
        while self.recency_stack and self.recency_stack[-1][1] in self.soft_deleted:
            self.soft_deleted.remove(self.recency_stack.pop()[1])
        while self.max_heap and self.max_heap[0][1] in self.soft_deleted:
            self.soft_deleted.remove(heapq.heappop(self.max_heap)[1])

    def pop(self) -> int:
        value, idx = self.recency_stack.pop()
        self.soft_deleted.add(idx)
        self._clean_up()
        return value

    def top(self) -> int:
        return self.recency_stack[-1][0]

    def peekMax(self) -> int:
        return -self.max_heap[0][0]

    def popMax(self) -> int:
        value, idx = heapq.heappop(self.max_heap)
        self.soft_deleted.add(idx)
        self._clean_up()
        return -value


# Your MaxStack object will be instantiated and called as such:
# obj = MaxStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.peekMax()
# param_5 = obj.popMax()