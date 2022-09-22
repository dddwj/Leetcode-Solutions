class Solution:
    def minSteps(self, s, t):
        charArray = [0] * 26

        for char in s:
            charArray[ord(char) - ord('a')] += 1

        for char in t:
            charArray[ord(char) - ord('a')] -= 1

        count = 0
        for val in charArray:
            if val > 0:
                count += val

        return count