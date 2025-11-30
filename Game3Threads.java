package Backend.ThreeThreads;

import Backend.AbstractGame;
import Backend.Pair;

import java.util.ArrayList;

public class Game3Threads extends AbstractGame {

    public Game3Threads(){
        super();
    }

    @Override
    public boolean checkValidity(){
        duplicates.clear();

        RowsChecker checkRows = new RowsChecker(board, duplicates);
        ColumnsChecker checkColumns = new ColumnsChecker(board, duplicates);
        BoxesChecker checkBoxes = new BoxesChecker(board, duplicates);

        checkRows.start();
        checkColumns.start();
        checkBoxes.start();

        try{
            checkRows.join();
            checkColumns.join();
            checkBoxes.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return duplicates.isEmpty();
    }
}
