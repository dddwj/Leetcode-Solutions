# See also: LC381
# Reference: https://leetcode.com/problems/insert-delete-getrandom-o1/solution/
class RandomizedSet:

    def __init__(self):
        self.map = {}
        self.list = []

    def insert(self, val: int) -> bool:
        if val not in self.map:
            self.list.append(val)
            self.map[val] = len(self.list) - 1
            return True
        else:
            return False

    def remove(self, val: int) -> bool:
        if val not in self.map:
            return False
        last_element, idx = self.list[-1], self.map[val]
        self.list[idx], self.map[last_element] = last_element, idx
        self.list.pop()
        del self.map[val]
        return True

    def getRandom(self) -> int:
        return random.choice(self.list)

# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
