package Backend.TwentySevenThreads;

import Backend.AbstractGame;
import Backend.Pair;
import Backend.ThreeThreads.BoxesChecker;
import Backend.ThreeThreads.ColumnsChecker;
import Backend.ThreeThreads.RowsChecker;

import java.util.ArrayList;

public class Game27Threads extends AbstractGame {

    public Game27Threads(){
        super();
    }

    @Override
    public boolean checkValidity(){
        duplicates.clear();

        //mmkn a3melha be arrayList wa7da of type (Thread) because these 3 types extends (Thread)
        ArrayList<OneRowChecker> checkRow = new ArrayList<>();
        ArrayList<OneColumnChecker> checkCol = new ArrayList<>();
        ArrayList<OneBoxChecker> checkBox = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            checkRow.add(new OneRowChecker(board, duplicates, i));
            checkCol.add(new OneColumnChecker(board, duplicates, i));
            checkBox.add(new OneBoxChecker(board, duplicates, i));

            checkRow.get(i).start();
            checkCol.get(i).start();
            checkBox.get(i).start();
        }
        for (int i = 0; i < 9; i++) {
            try{
                checkRow.get(i).join();
                checkCol.get(i).join();
                checkBox.get(i).join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        return duplicates.isEmpty();
    }
}
