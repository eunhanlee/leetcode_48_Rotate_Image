public class Solution2 {
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