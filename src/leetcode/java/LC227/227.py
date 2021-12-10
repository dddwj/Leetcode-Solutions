class Solution_complex:
    def calculate(self, s: str) -> int:
        if not s or len(s) == 0:
            return 0

        s = s.strip()
        stack = []
        currentNumber = 0
        operation = '+'
        for i, char in enumerate(s):
            if char not in {'+', '-', '*', '/'}:   # char is a number
                currentNumber = currentNumber * 10 + int(char)
            if char in {'+', '-', '*', '/'} or i == len(s)-1:          # char is a sign
                if operation == '+':
                    stack.append('+' + str(currentNumber))
                elif operation == '-':
                    stack.append('-' + str(currentNumber))
                elif operation == '*':
                    prevNumber = stack.pop()
                    sign, prevNumber = prevNumber[0], int(prevNumber[1:])
                    currentNumber = currentNumber * prevNumber
                    stack.append(str(sign) + str(currentNumber))
                elif operation == '/':
                    prevNumber = stack.pop()
                    sign, prevNumber = prevNumber[0], int(prevNumber[1:])
                    currentNumber = int(prevNumber / currentNumber)
                    stack.append(str(sign) + str(currentNumber))
                else:
                    pass
                currentNumber = 0
                operation = char

        result = 0
        for element in stack:
            currentNumber = int(element[1:])
            if element[0] == '-':
                result = result - int(currentNumber)
            elif element[0] == '+':
                result = result + int(currentNumber)
            else:
                pass
        return result

class Solution:
    def calculate(self, s: str) -> int:
        if not s or len(s) == 0:
            return 0

        operators = {'+', '-', '*', '/'}
        nums = {str(x) for x in range(0, 10)}

        s = s.strip()
        lastNumber = 0
        result = 0
        currentNumber = 0
        operation = '+'
        for i, char in enumerate(s):
            if char == ' ':
                continue

            if char in nums:
                currentNumber = currentNumber * 10 + int(char)

            if char in operators or i == len(s) - 1:
                if operation == '+':
                    result += lastNumber
                    lastNumber = currentNumber
                elif operation == '-':
                    result += lastNumber
                    lastNumber = (-currentNumber)
                elif operation == '*':
                    lastNumber = lastNumber * currentNumber
                elif operation == '/':
                    lastNumber = int(lastNumber / currentNumber)
                currentNumber = 0
                operation = char
        result += lastNumber
        return result


s = Solution()
print(s.calculate("3+2*14"))
print(s.calculate(" 3/ 2 "))