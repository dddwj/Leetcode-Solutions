class Solution:

    """
    The key observation to make is that any sequence of the same letter is a palindrome. For example a, aa, aaa, aaaaaaaa, etc. Because there are only 2 unique letters that can appear in the string, we know we can always solve the problem with at most 2 steps. i.e.

    1. Remove all the a's as a single palindromic subsequence.
    2. Remove all the b's as a single palindromic subsequence.

    This leaves us with only 3 possible answers for any given string: 0, 1, or 2. We will need to classify each string we're given into one of these 3 categories. If you haven't yet solved the problem, have another think about how you could do this before you read on.

    """
    def removePalindromeSub(self, s: str) -> int:
        def is_palindrome(s):
            lo = 0
            hi = len(s) - 1
            while lo < hi:
                if s[lo] != s[hi]:
                    return False
                lo += 1
                hi -= 1
            return True

        if not s:
            return 0
        if is_palindrome(s):
            return 1
        return 2