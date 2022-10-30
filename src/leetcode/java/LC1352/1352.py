# Reference: https://leetcode.com/problems/product-of-the-last-k-numbers/discuss/510260/JavaC%2B%2BPython-Prefix-Product
class ProductOfNumbers:
    def __init__(self):
        self.q = [1]

    def add(self, num: int) -> None:
        if num == 0:
            self.q = [1]
        else:
            self.q.append(self.q[-1] * num)

    def getProduct(self, k: int) -> int:
        if k >= len(self.q):
            return 0
        return self.q[-1] // self.q[-k-1]




# Your ProductOfNumbers object will be instantiated and called as such:
# obj = ProductOfNumbers()
# obj.add(num)
# param_2 = obj.getProduct(k)