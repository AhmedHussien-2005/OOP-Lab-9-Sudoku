package Backend.ThreeThreads;

import Backend.Pair;

import java.util.ArrayList;

public class RowsChecker extends Thread{

    private int[][] board;
    private ArrayList<Pair> duplicates;

    public RowsChecker(int[][] board, ArrayList<Pair> duplicates){
        this.board = board;
        this.duplicates = duplicates;
    }


    @Override
    public void run(){
        for (int r = 0; r < 9; r++) {
            for (int c1 = 0; c1 < 9; c1++) {
                for (int c2 = c1 + 1; c2 < 9; c2++) {

                    if (board[r][c1] == board[r][c2] && board[r][c1] != 0) {
                        synchronized(duplicates) {
                            duplicates.add(new Pair(r, c1, r, c2));
                        }
                    }

                }
            }
        }
    }

}
