# Reference: https://leetcode.wang/leetCode-93-Restore-IP-Addresses.html
#            https://leetcode.com/problems/restore-ip-addresses/discuss/31140/Python-easy-to-understand-solution-(backtracking)

class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        res = []
        self.dfs(s=s, idx=0, path="", res=res)
        return res

    def dfs(self, s, idx, path, res):
        # print(path)
        if idx > 4:
            return
        if (4 - idx) * 3 < len(s):
            return
        if idx == 4 and not s:
            res.append(path[:-1])
            return
        for i in range(1, min(4, len(s) + 1)):
            if s[:i] == '0' or (s[0] != '0' and 0 < int(s[:i]) < 256):
                self.dfs(s=s[i:], idx=idx+1, path=path + s[:i] + ".", res=res)

