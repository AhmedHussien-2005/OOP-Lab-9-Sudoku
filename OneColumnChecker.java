package Backend.TwentySevenThreads;

import Backend.Pair;

import java.util.ArrayList;

public class OneColumnChecker extends Thread{

    private int[][] board;
    private ArrayList<Pair> duplicates;
    private int colIndex;

    public OneColumnChecker(int[][] board, ArrayList<Pair> duplicates, int colIndex){
        this.board = board;
        this.duplicates = duplicates;
        this.colIndex = colIndex;
    }

    @Override
    public void run() {
        for (int r1 = 0; r1 < 9; r1++) {
            for (int r2 = r1 + 1; r2 < 9; r2++) {

                if (board[r1][colIndex] == board[r2][colIndex] &&
                        board[r1][colIndex] != 0) {
                    synchronized(duplicates) {
                        duplicates.add(new Pair(r1, colIndex, r2, colIndex));
                    }
                }
            }
        }
    }

}
