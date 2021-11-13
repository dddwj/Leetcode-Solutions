class Solution:
    def isValid(self, s: str) -> bool:
        if len(s) < 1:
            return True

        stack = list()
        for char in s:
            if char == "(" or char == "[" or char == "{":
                stack.append(char)
            else:
                if len(stack) == 0:
                    return False
                left = stack.pop()
                if left == "(" and char != ")":
                    return False
                if left == "[" and char != "]":
                    return False
                if left == "{" and char != "}":
                    return False

        return len(stack) == 0
