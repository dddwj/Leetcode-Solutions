# """
# This is Sea's API interface.
# You should not implement it, or speculate about its implementation
# """
class Sea:
   def hasShips(self, topRight: 'Point', bottomLeft: 'Point') -> bool:

class Point:
	def __init__(self, x: int, y: int):
		self.x = x
		self.y = y

# Reference: https://leetcode.com/problems/number-of-ships-in-a-rectangle/solutions/1691285/number-of-ships-in-a-rectangle/
# Time: O( S ⋅ ((lg(2)(max(M,N) − lg(4)S)) ) where M, N = height, width of the sea.
# Space: O(lg(2)(max(M,N))
class Solution:
    def countShips(self, sea: 'Sea', topRight: 'Point', bottomLeft: 'Point') -> int:
        # If the current rectangle does not contain any ships, return 0.
        if bottomLeft.x > topRight.x or bottomLeft.y > topRight.y:
            return 0
        if not sea.hasShips(topRight, bottomLeft):
            return 0

        # If the rectangle represents a single point, a ship is located.
        if bottomLeft.x == topRight.x and bottomLeft.y == topRight.y:
            return 1

        # Recursively check each of the 4 sub-rectangles for ships.
        midX = (bottomLeft.x + topRight.x) // 2
        midY = (bottomLeft.y + topRight.y) // 2
        return self.countShips(sea, Point(midX, midY), bottomLeft) + \
               self.countShips(sea, topRight, Point(midX+1, midY+1)) + \
               self.countShips(sea, Point(midX, topRight.y), Point(bottomLeft.x, midY+1)) + \
               self.countShips(sea, Point(topRight.x, midY), Point(midX+1, bottomLeft.y))
