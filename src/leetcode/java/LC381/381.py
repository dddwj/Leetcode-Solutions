# See also: LC380
# Reference: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/solution/
class RandomizedCollection:

    def __init__(self):
        self.lst = []
        self.map = defaultdict(set)

    def insert(self, val: int) -> bool:
        self.map[val].add(len(self.lst))
        self.lst.append(val)
        return len(self.map[val]) == 1


    def remove(self, val: int) -> bool:
        if not self.map[val]:
            return False
        remove_idx, last_val = self.map[val].pop(), self.lst[-1]
        self.lst[remove_idx] = last_val
        self.map[last_val].add(remove_idx)
        self.map[last_val].remove(len(self.lst) - 1)
        self.lst.pop()
        return True


    def getRandom(self) -> int:
        return random.choice(self.lst)


# Your RandomizedCollection object will be instantiated and called as such:
# obj = RandomizedCollection()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()