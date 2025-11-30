package Backend;
import Backend.OneThread.*;
import Backend.ThreeThreads.*;
import Backend.TwentySevenThreads.*;

import java.util.ArrayList;

public abstract class AbstractGame {
    protected int[][] board;
    protected ArrayList<Pair> duplicates;

    public AbstractGame(){
        duplicates = new ArrayList<>();
        board = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = 0;
            }
        }
    }
    public abstract boolean checkValidity();

    public boolean play(int row, int col, int value){
        if(board[row][col] != 0)
            return false;
        else
            board[row][col] = value;
        return true;
    }
    public int[][] getBoard(){
        return board;
    }
    public void setBoard(int[][] Board){
        this.board = Board;
    }
    public ArrayList<Pair> getDuplicates(){
        return this.duplicates;
    }
}
