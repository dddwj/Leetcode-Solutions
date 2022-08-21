class Solution:
    mapping = [".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
               ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."]

    def uniqueMorseRepresentations(self, words: List[str]) -> int:
        seen = set()
        for word in words:
            chars = [self.getMorse(char) for char in word]
            code = ''.join(chars)
            seen.add(code)
        return len(seen)

    def getMorse(self, char: str) -> str:
        if len(char) > 1:
            return ''
        index = ord(char) - ord('a')
        return self.mapping[index]
