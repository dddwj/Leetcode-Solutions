class Solution:
    def minRemoveToMakeValid(self, s: str) -> str:
        s = list(s)
        stack = list()

        for i, char in enumerate(s):
            if char == '(':
                stack.append(i)
            elif char == ')':
                if stack:
                    stack.pop()
                else:
                    s[i] = ''

        while stack:
            i = stack.pop()
            s[i] = ''

        return ''.join(s)