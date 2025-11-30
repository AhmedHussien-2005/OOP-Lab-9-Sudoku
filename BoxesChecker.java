package Backend.ThreeThreads;

import Backend.Pair;

import java.util.ArrayList;

public class BoxesChecker extends Thread{

    private int[][] board;
    private ArrayList<Pair> duplicates;

    public BoxesChecker(int[][] board, ArrayList<Pair> duplicates){
        this.board = board;
        this.duplicates = duplicates;
    }

    @Override
    public void run(){
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {

                for (int r1 = 0; r1 < 3; r1++) {
                    for (int c1 = 0; c1 < 3; c1++) {

                        int globalR1 = boxRow * 3 + r1;
                        int globalC1 = boxCol * 3 + c1;

                        for (int r2 = r1; r2 < 3; r2++) {
                            for (int c2 = (r2 == r1 ? c1 + 1 : 0); c2 < 3; c2++) {

                                int globalR2 = boxRow * 3 + r2;
                                int globalC2 = boxCol * 3 + c2;

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
    }
}
