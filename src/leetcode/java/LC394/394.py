class Solution:
    def decodeString(self, s: str) -> str:
        if not s or len(s) == 0:
            return ""
        stack = []
        for char in s:
            if char == ']':
                temp_str = stack.pop()
                stack.pop()
                k = int(stack.pop())
                decoded_string = ""
                for i in range(k):
                    decoded_string += temp_str
                if stack and stack[-1].isalpha():
                    temp_str = stack.pop()
                    temp_str += decoded_string
                    stack.append(temp_str)
                else:
                    stack.append(decoded_string)
            else:
                if stack and char.isnumeric() and stack[-1].isnumeric():
                    k = int(stack.pop())
                    k = k * 10 + int(char)
                    stack.append(str(k))
                    continue
                if stack and char.isalpha() and stack[-1].isalpha():
                    temp_str = stack.pop()
                    temp_str += str(char)
                    stack.append(temp_str)
                    continue
                stack.append(char)
        return stack[0]


class Solution_concise:
    # Reference: https://leetcode.com/problems/decode-string/discuss/87662/Python-solution-using-stack
    def decodeString(self, s: str) -> str:
        if not s or len(s) == 0:
            return ""
        stack = []
        curNum = 0
        curString = ''
        for char in s:
            if char == '[':
                stack.append(curString)
                stack.append(curNum)
                curString = ''
                curNum = 0
            elif char == ']':
                k = stack.pop()
                prevString = stack.pop()
                curString = prevString + k * curString
            elif char.isdigit():
                curNum = curNum * 10 + int(char)
            else:
                curString += char

        return curString



# Reference: https://leetcode.com/problems/decode-string/solution/
class Solution_recursion:
    def decodeString(self, s: str) -> str:
        self.index = 0
        res = self.helper(s)
        return res

    def helper(self, s: str):
        res = ''
        while self.index < len(s):
            char = s[self.index]
            if char == ']':
                break
            if char.isalpha():
                res += str(char)
                self.index += 1
            if char.isdigit():
                # Get number
                k = 0
                while self.index < len(s) and s[self.index].isdigit():
                    k = k * 10 + int(s[self.index])
                    self.index += 1

                # ignore '['
                self.index += 1

                # Recursion
                next_str = self.helper(s)

                # ignore ']'
                self.index += 1

                # Generate string
                res += (next_str * k)

        return res
