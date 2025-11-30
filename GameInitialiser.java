package Backend;

import Backend.OneThread.Game;
import Backend.ThreeThreads.Game3Threads;
import Backend.TwentySevenThreads.Game27Threads;

public class GameInitialiser {
    public static AbstractGame startGame(int choice){
        AbstractGame game;
        if(choice == 1){
            game = new Game();
        }
        else if(choice == 2){
            game = new Game3Threads();
        }
        else if(choice == 3){
            game = new Game27Threads();
        }
        else{
            System.out.println("Invalid Choice, Starting Default: 1 Threaded Game");
            game = new Game();
        }
        return game;
    }
}
