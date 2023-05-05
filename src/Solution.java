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