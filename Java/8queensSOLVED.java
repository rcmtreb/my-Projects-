public class EQ {
    static final int N = 8;
    static int[] queens = new int[N];

    public static void main(String[] args) {
        solve(0);
    }

    static void solve(int col) {
        if (col == N) {
            printBoard();
            System.exit(0);
        }
        for (int row = 0; row < N; row++) {
            if (safe(row, col)) {
                queens[col] = row;
                solve(col + 1);
            }
        }
    }

    static boolean safe(int row, int col) {
        for (int i = 0; i < col; i++)
            if (queens[i] == row || Math.abs(queens[i] - row) == col - i)
                return false;
        return true;
    }

    static void printBoard() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++)
                System.out.print(queens[c] == r ? " Q " : " . ");
            System.out.println();
        }
    }
}
