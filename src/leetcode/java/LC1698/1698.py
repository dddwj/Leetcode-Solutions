class Solution_brute_force:
    def countDistinct(self, s: str) -> int:
        combinations = set()

        n = len(s)
        for i in range(n):
            for j in range(i, n):
                sub = s[i:j+1]
                combinations.add(sub)

        return len(combinations)

# https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/discuss/984839/9-lines-Trie-with-python
# https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/discuss/984393/Java-Why-is-this-labeled-hard-Is-just-a-Trie...
class Solution_Tries:
    def countDistinct(self, s: str) -> int:
        n = len(s)
        trie = dict()
        res = 0

        for i in range(n):
            curr = trie
            for j in range(i, n):
                if s[j] not in curr:
                    curr[s[j]] = dict()
                    res += 1
                curr = curr[s[j]]

        return res


# Best solution: O(N) Time: To be understood...

# https://www.youtube.com/watch?v=m2lZRmMjebw&t=205s
# https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/discuss/1010936/Python-Suffix-Array-%2B-LCP-O(N-logN)

