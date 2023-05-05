# 48. Rotate Image Problem Solved: Uncover the Most Efficient Java Algorithm

[Homepage link]()

# Problem

[Problem_Link](https://leetcode.com/problems/rotate-image/description/)

## **Problem Solving Approach**

1. "Rotating a position" means that each position has a designated position to be moved to.
2. For example, when n is 3:
    
    ```java
    (0,0)->(0,2)
    (0,2)->(2,2)
    (2,2)->(2,0)
    (2,0)->(0,0)
    
    (0,1)->(1,2)
    (1,2)->(2,1)
    (2,1)->(2,1)
    (1,0)->(0,1)
    ```
    
3. The above rule can be calculated by how far it is from n.
    
    ```java
    First loop
    n=3
    Distance from the y-axis = i = 0
    Distance from the x-axis = j = 0
    
    Starting point = (i,(i+distance from the x axis)) = (0,0)
    ((n-1-distance from the x axis-distance from the y axis),i)=((3-1-0-0),0)=(2,0)
    ((n-1-distance from the y axis),(n-1-distance from the x axis-distance from the y axis))=((3-1-0),(3-0-1-0))=(2,2)
    (((i+distance from the x axis)),(n-1-distance from the y axis))=((0+0),(3-1-0))=(0,2)
    
    Second loop
    n=3
    Distance from the y-axis = i = 0
    Distance from the x-axis = j = 1
    
    Starting point = (i,(i+distance from the x axis)) = (0,1)
    ((n-1-distance from the x axis-distance from the y axis),i)=((3-1-1-0),0)=(1,0)
    ((n-1-distance from the y axis),(n-1-distance from the x axis-distance from the y axis))=((3-1-0),(3-1-1-0))=(2,1)
    (((i+distance from the x axis)),(n-1-distance from the y axis))=((0+1),(3-1-0))=(1,2)
    ```
    
4. first loop only half of all and second loop inside the first loop should skip as first lopped
5. For example, when n is 3:
    
    ```
    (0,0) (0,1) (0,2)
    (1,0) (1,1) (1,2)
    (2,0) (2,1) (2,2)
    ```
    
    Only (0,0) and (0,1) should be included in the loop.
    
6. For example, when n is 4:
    
    ```
    (0,0) (0,1) (0,2) (0,3)
    (1,0) (1,1) (1,2) (1,3)
    (2,0) (2,1) (2,2) (2,3)
    (3,0) (3,1) (3,2) (3,3)
    ```
    
    Only (0,0), (0,1), (0,2), (1,1), and (1,2) should be included in the loop.
    
7. In order to determine which elements to swap in each rotation, we can calculate the distance that each element has from the outermost layer of the matrix.
8. For example, if n is 3, the first rotation involves swapping elements at position (0,0), (0,2), (2,2), and (2,0). The second rotation involves swapping elements at position (0,1), (1,2), (2,1), and (1,0). We can see that the distance that each element has from the outermost layer of the matrix corresponds to the number of rotations it needs to undergo.
9. To implement this, we can use two nested loops. The outer loop iterates from 0 to n/2 - 1, since we only need to perform rotations for the first n/2 layers of the matrix. The inner loop iterates from the current layer (i) to n - i - 2, since we only need to perform rotations within the current layer.
10. Using these loops, we can determine the four elements that need to be rotated in each step, and then swap them accordingly.
11. Therefore, the time complexity of this solution is O(n^2), since we need to visit every element in the matrix. The space complexity is also O(1), since we are performing the rotations in-place.

# Time O(n^2), Space O(1)

```java
class Solution {
    /**
     * Rotates a 2D array clockwise by 90 degrees.
     * @param matrix a 2D array
     */
    public void rotate(int[][] matrix) {
        // Get the last position in the array (subtract 1 since the array is zero-indexed)
        int lastPos = matrix.length - 1;
        // Only loop through half of the array's length for i since each iteration swaps two values
        int i_loop = matrix.length / 2;
        for (int i = 0; i < i_loop; i++) {
            // j_loop determines how many values to swap based on the current i index
            int j_loop = lastPos - 2 * i;
            for (int j = 0; j < j_loop; j++) {
                setNewPos(matrix, i, j, lastPos);
            }
        }
    }

    /**
     * Swaps the value at the current position with the value 90 degrees clockwise from it.
     * @param matrix a 2D array
     * @param i the row index of the current position
     * @param j the column index of the current position
     * @param lastPos the last index of the array
     */
    public static void setNewPos(int[][] matrix, int i, int j, int lastPos) {
        // Swaps the value at the current position with the value 90 degrees clockwise from it.
        // (i,j) -> (lastPos-i-j,i) -> (lastPos-i,lastPos-i-j) -> (j,lastPos-i) -> (i,j)

        int temp = matrix[i][i + j];
        matrix[i][i + j] = matrix[lastPos - i - j][i];
        matrix[lastPos - i - j][i] = matrix[(lastPos - i)][lastPos - i - j];
        matrix[(lastPos - i)][lastPos - i - j] = matrix[i + j][(lastPos - i)];
        matrix[i + j][(lastPos - i)] = temp;
    }
}
```

## **Problem Solving Approach2**

1. Rotating a matrix by 90 degrees can be difficult, but flipping it is easy.
2. If we flip a matrix along its diagonal, and then flip it horizontally, this is equivalent to rotating it by 90 degrees.

# Time O(n^2), Space O(1)

```java
public class Solution {
    /**
     * Rotates a 2D array clockwise by 90 degrees.
     *
     * @param matrix a 2D array
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Flip the array diagonally.
        for (int i = 0; i < n; i++) {
            // j should start from i, since elements below the diagonal have already been swapped.
            for (int j = i; j < n; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Flip each row horizontally.
        for (int i = 0; i < n; i++) {
            // j should be less than n/2, since flipping beyond the middle would cause duplicates.
            for (int j = 0; j < n / 2; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}
```