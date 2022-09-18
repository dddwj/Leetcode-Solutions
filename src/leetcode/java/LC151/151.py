# Reference: https://leetcode.com/problems/reverse-words-in-a-string/solution/
class Solution_two_pointers:
# Same as LC186
# Time: O(N)
# Space: O(N)  Python/Java string is immutable! C++ string is mutable.
    def trim_spaces(self, s: str) -> list:
            left, right = 0, len(s)-1
            while left <= right and s[left] == ' ':
                left += 1
            while left <= right and s[right] == ' ':
                right -= 1

            output = []
            while left <= right:
                if s[left] != ' ':
                    output.append(s[left])
                elif output[-1] != ' ':
                    output.append(s[left])
                left += 1

            return output

    def reverse(self, l: list, left: int, right: int) -> None:
        while left < right:
            l[left], l[right] = l[right], l[left]
            left += 1
            right -= 1

    def reverse_each_word(self, l: list) -> None:
        n = len(l)
        start = end = 0
        while end < n-1:
            while end < n-1 and l[end+1] != ' ':
                end += 1
            self.reverse(l, start, end)
            start = end + 2
            end = end + 2


    def reverseWords(self, s: str) -> str:
        l = self.trim_spaces(s)
        self.reverse(l, 0 , len(l)-1)
        self.reverse_each_word(l)
        return ''.join(l)


class Solution_built_in:
# Time: O(N)
# Space: O(N)
    def reverseWords(self, s: str) -> str:
        return ' '.join(reversed(s.split()))