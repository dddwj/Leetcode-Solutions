class Solution:
    def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
        # mat1: m x k
        # mat2: k x n
        # res:  m x n

        ans = [[0] * len(mat2[0]) for _ in range(len(mat1))]

        for row_index, row in enumerate(mat1):
            for element_index, row_element in enumerate(row):
                if row_element != 0:
                    for col_index, col_element in enumerate(mat2[element_index]):
                        if col_element != 0:
                            # print(row_index, col_index)
                            ans[row_index][col_index] += row_element * col_element

        return ans