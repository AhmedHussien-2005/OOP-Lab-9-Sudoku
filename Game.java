package Backend.OneThread;


import Backend.AbstractGame;
import Backend.Pair;

import java.util.ArrayList;

public class Game extends AbstractGame {

    public Game(){
        super();
    }

    public boolean play(int row, int col, int value){
        if(board[row][col] != 0)
            return false;
        else
            board[row][col] = value;
        return true;
    }

    @Override
    public boolean checkValidity(){
        duplicates.clear();

        checkRows();
        checkColumns();
        checkBoxes();
        return duplicates.isEmpty();
    }

    public void checkRows(){
        for (int r = 0; r < 9; r++) {
            for (int c1 = 0; c1 < 9; c1++) {
                for (int c2 = c1 + 1; c2 < 9; c2++) {

                    if (board[r][c1] == board[r][c2] && board[r][c1] != 0) {
                        duplicates.add(new Pair(r, c1, r, c2));
                    }

                }
            }
        }
    }
    public void checkColumns(){
        for (int col = 0; col < board[0].length; col++) {

            for (int r1 = 0; r1 < board.length; r1++) {
                for (int r2 = r1 + 1; r2 < board.length; r2++) {

                    if (board[r1][col] == board[r2][col]  && board[r1][col] != 0) {
                        duplicates.add(new Pair(r1, col, r2, col));
                    }

                }
            }

        }
    }
    public void checkBoxes(){
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
