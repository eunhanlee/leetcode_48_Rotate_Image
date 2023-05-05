public class Main {
    public static void main(String[] args) {
        int[][] temp = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution tt = new Solution();
        print2Darray(temp);
        System.out.println();
        tt.rotate(temp);
        print2Darray(temp);

        System.out.println("============");

        int[][] temp2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution2 tt2 = new Solution2();
        print2Darray(temp2);
        System.out.println();
        tt2.rotate(temp2);
        print2Darray(temp2);

    }


    public static void print2Darray(int[][] temp){
        for (int[] m : temp) {
            for (int val : m) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}