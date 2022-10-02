# Reference: https://leetcode.com/problems/ip-to-cidr/solutions/110219/easy-to-understand-python/
class Solution:
    def ip2number(self, ip):
        fields = ip.split(".")
        numbers = list(int(field) for field in fields)
        n = (numbers[0] << 24) + (numbers[1] << 16) + (numbers[2] << 8) + numbers[3]
        return n

    def number2ip(self, n):
        fields = [str(n>>24&255), str(n>>16&255), str(n>>8&255), str(n&255)]
        return ".".join(fields)

    def ilowbit(self, x):
        for i in range(32):
            if (x & (1 << i)):
                return i
        return 32

    def lowbit(self, x):
        return 1 << self.ilowbit(x)

    def ipToCIDR(self, ip: str, n: int) -> List[str]:
        number = self.ip2number(ip)
        result = []
        while n > 0:
            lb = self.lowbit(number)
            while lb > n:
                lb = (lb >> 1)

            n = n - lb
            result.append(self.number2ip(number) + "/" + str(32 - self.ilowbit(lb)))
            number += lb
        return result