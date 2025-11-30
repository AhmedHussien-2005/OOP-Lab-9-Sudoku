package Backend.TwentySevenThreads;

import Backend.Pair;

import java.util.ArrayList;

public class OneRowChecker extends Thread{

    private int[][] board;
    private ArrayList<Pair> duplicates;
    private int rowIndex;

    public OneRowChecker(int[][] board, ArrayList<Pair> duplicates, int rowIndex){
        this.board = board;
        this.duplicates = duplicates;
        this.rowIndex = rowIndex;
    }

    @Override
    public void run() {
        for (int c1 = 0; c1 < 9; c1++) {
            for (int c2 = c1 + 1; c2 < 9; c2++) {

                if (board[rowIndex][c1] == board[rowIndex][c2] &&
                        board[rowIndex][c1] != 0) {
                    synchronized(duplicates) {
                        duplicates.add(new Pair(rowIndex, c1, rowIndex, c2));
                    }
                }
            }
        }
    }

}
