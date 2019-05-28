public class Main {
    public static void main(String[] args) {

        int [][] A  = new int [4][4];
        int [][] B = {{10, 1, 10, 1}, {1, 1, 1, 10}, {10, 1, 10, 1}, {1, 10, 0, 1}};
        Solution solution = new Solution();
        System.out.println(solution.solution(B));
    }
}
