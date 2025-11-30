package Backend.ThreeThreads;

import Backend.Pair;

import java.util.ArrayList;

public class ColumnsChecker extends Thread{

    private int[][] board;
    private ArrayList<Pair> duplicates;

    public ColumnsChecker(int[][] board, ArrayList<Pair> duplicates){
        this.board = board;
        this.duplicates = duplicates;
    }

    @Override
    public void run(){
        for (int col = 0; col < board[0].length; col++) {

            for (int r1 = 0; r1 < board.length; r1++) {
                for (int r2 = r1 + 1; r2 < board.length; r2++) {

                    if (board[r1][col] == board[r2][col]  && board[r1][col] != 0) {
                        synchronized(duplicates) {
                            duplicates.add(new Pair(r1, col, r2, col));
                        }
                    }

                }
            }

        }
    }
}
