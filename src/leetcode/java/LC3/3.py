class Solution_sliding_window_unoptimized:
    def lengthOfLongestSubstring(self, s: str) -> int:
        longest_length = 0
        occr = [0] * 256
        slow = fast = 0

        while fast < len(s):
            r_index = ord(s[fast])
            while slow < fast and occr[r_index]:
                l_index = ord(s[slow])
                occr[l_index] = 0
                slow += 1
            occr[r_index] = 1
            longest_length = max(longest_length, fast - slow + 1)
            fast += 1

        return longest_length


# Reference: https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
class Solution_sliding_window_optimized:
    def lengthOfLongestSubstring(self, s: str) -> int:
        longest_length = 0
        occr = [None] * 256
        slow = fast = 0

        while fast < len(s):
            r_index = ord(s[fast])
            if occr[r_index] is not None:
                slow = max(slow, occr[r_index] + 1)   # Need to ensure 'slow' never jumps back
            longest_length = max(longest_length, fast - slow + 1)
            occr[r_index] = fast
            fast += 1

        return longest_length