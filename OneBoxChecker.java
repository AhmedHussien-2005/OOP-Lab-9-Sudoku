package Backend.TwentySevenThreads;

import Backend.Pair;

import java.util.ArrayList;

public class OneBoxChecker extends Thread{

    private int[][] board;
    private ArrayList<Pair> duplicates;
    private int boxIndex;

    public OneBoxChecker(int[][] board, ArrayList<Pair> duplicates, int boxIndex){
        this.board = board;
        this.duplicates = duplicates;
        this.boxIndex = boxIndex;
    }

    @Override
    public void run() {

        int boxRow = boxIndex / 3;  // 0,1,2
        int boxCol = boxIndex % 3;  // 0,1,2

        // Top-left cell of this box
        int startRow = boxRow * 3;
        int startCol = boxCol * 3;

        for (int r1 = 0; r1 < 3; r1++) {
            for (int c1 = 0; c1 < 3; c1++) {

                int globalR1 = startRow + r1;
                int globalC1 = startCol + c1;

                for (int r2 = r1; r2 < 3; r2++) {
                    for (int c2 = (r2 == r1 ? c1 + 1 : 0); c2 < 3; c2++) {

                        int globalR2 = startRow + r2;
                        int globalC2 = startCol + c2;

                        if (board[globalR1][globalC1] == board[globalR2][globalC2] &&
                                board[globalR1][globalC1] != 0) {
                            synchronized(duplicates) {
                                duplicates.add(new Pair(globalR1, globalC1, globalR2, globalC2));
                            }
                        }
                    }
                }
            }
        }
    }

}
