import Backend.OneThread.*;
import Backend.ThreeThreads.*;
import Backend.TwentySevenThreads.*;
import Backend.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose:");
        System.out.println("1) 0");
        System.out.println("2) 3");
        System.out.println("3) 27");

        String SChoice = scan.nextLine();
        int choice = -1;
        try {
            choice = Integer.parseInt(SChoice);
        }
        catch(NumberFormatException e){
        }

        AbstractGame game = GameInitialiser.startGame(choice);

        int[][] testBoard = {
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},

                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},

                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1}
        };

        String csvFile = "Board.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {

            for (int row = 0; row < 9; row++) {
                StringBuilder line = new StringBuilder();

                for (int col = 0; col < 9; col++) {
                    line.append(testBoard[row][col]);
                    if (col < 8)
                        line.append(",");
                }

                writer.write(line.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String line;
        int[][] board1 = new int[9][9];

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            int j = 0;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                for(int i = 0; i < 9; i++){
                    board1[j][i] = Integer.parseInt(fields[i]);
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        game.setBoard(board1);
        boolean check = game.checkValidity();
        if(check)
            System.out.println("VALID!");
        else {
            System.out.println("INVALID!");
            ArrayList<Pair> duplicates = game.getDuplicates();
            for(int i = 0; i < duplicates.size(); i++){
                if(duplicates.get(i).firstR == duplicates.get(i).secondR) {
                    System.out.println("Row " + duplicates.get(i).firstR + ": " +duplicates.get(i).firstR + "," + duplicates.get(i).firstC
                            + " " + duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
                else if(duplicates.get(i).firstC == duplicates.get(i).secondC) {
                    System.out.println("Col " + duplicates.get(i).firstC + ": " +duplicates.get(i).firstR + "," + duplicates.get(i).firstC
                            + " " + duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
                else if (duplicates.get(i).firstR / 3 == duplicates.get(i).secondR / 3 &&
                        duplicates.get(i).firstC / 3 == duplicates.get(i).secondC / 3) {

                    int boxIndex = (duplicates.get(i).firstR / 3) * 3 + (duplicates.get(i).firstC / 3);
                    System.out.println("Box " + boxIndex + ": " +
                            duplicates.get(i).firstR + "," + duplicates.get(i).firstC + " " +
                            duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
            }
        }
        /*
        int[][] board2 = {
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},

                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},

                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9}
        };
        game.setBoard(board2);
        boolean checkAgain = game.checkValidity();
        if(checkAgain)
            System.out.println("VALID!");
        else {
            System.out.println("INVALID!");
            ArrayList<Pair> duplicates = game.getDuplicates();
            for(int i = 0; i < duplicates.size(); i++){
                if(duplicates.get(i).firstR == duplicates.get(i).secondR) {
                    System.out.println("Row " + duplicates.get(i).firstR + ": " +duplicates.get(i).firstR + "," + duplicates.get(i).firstC
                            + " " + duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
                else if(duplicates.get(i).firstC == duplicates.get(i).secondC) {
                    System.out.println("Col " + duplicates.get(i).firstC + ": " +duplicates.get(i).firstR + "," + duplicates.get(i).firstC
                            + " " + duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
                else if (duplicates.get(i).firstR / 3 == duplicates.get(i).secondR / 3 &&
                        duplicates.get(i).firstC / 3 == duplicates.get(i).secondC / 3) {

                    int boxIndex = (duplicates.get(i).firstR / 3) * 3 + (duplicates.get(i).firstC / 3);
                    System.out.println("Box " + boxIndex + ": " +
                            duplicates.get(i).firstR + "," + duplicates.get(i).firstC + " " +
                            duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
            }
        }

        int[][] board3 = {
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},

                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},

                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
        game.setBoard(board3);
        boolean checkAgain2 = game.checkValidity();
        if(checkAgain2)
            System.out.println("VALID!");
        else {
            System.out.println("INVALID!");
            ArrayList<Pair> duplicates = game.getDuplicates();
            for(int i = 0; i < duplicates.size(); i++){
                if(duplicates.get(i).firstR == duplicates.get(i).secondR) {
                    System.out.println("Row " + duplicates.get(i).firstR + ": " +duplicates.get(i).firstR + "," + duplicates.get(i).firstC
                            + " " + duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
                else if(duplicates.get(i).firstC == duplicates.get(i).secondC) {
                    System.out.println("Col " + duplicates.get(i).firstC + ": " +duplicates.get(i).firstR + "," + duplicates.get(i).firstC
                            + " " + duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
                else if (duplicates.get(i).firstR / 3 == duplicates.get(i).secondR / 3 &&
                        duplicates.get(i).firstC / 3 == duplicates.get(i).secondC / 3) {

                    int boxIndex = (duplicates.get(i).firstR / 3) * 3 + (duplicates.get(i).firstC / 3);
                    System.out.println("Box " + boxIndex + ": " +
                            duplicates.get(i).firstR + "," + duplicates.get(i).firstC + " " +
                            duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
            }
        }

        int[][] board4 = {
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},

                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},

                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1}
        };
        game.setBoard(board4);
        boolean checkAgain3 = game.checkValidity();
        if(checkAgain3)
            System.out.println("VALID!");
        else {
            System.out.println("INVALID!");
            ArrayList<Pair> duplicates = game.getDuplicates();
            for(int i = 0; i < duplicates.size(); i++){
                if(duplicates.get(i).firstR == duplicates.get(i).secondR) {
                    System.out.println("Row " + duplicates.get(i).firstR + ": " +duplicates.get(i).firstR + "," + duplicates.get(i).firstC
                            + " " + duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
                else if(duplicates.get(i).firstC == duplicates.get(i).secondC) {
                    System.out.println("Col " + duplicates.get(i).firstC + ": " +duplicates.get(i).firstR + "," + duplicates.get(i).firstC
                            + " " + duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
                else if (duplicates.get(i).firstR / 3 == duplicates.get(i).secondR / 3 &&
                        duplicates.get(i).firstC / 3 == duplicates.get(i).secondC / 3) {

                    int boxIndex = (duplicates.get(i).firstR / 3) * 3 + (duplicates.get(i).firstC / 3);
                    System.out.println("Box " + boxIndex + ": " +
                            duplicates.get(i).firstR + "," + duplicates.get(i).firstC + " " +
                            duplicates.get(i).secondR + "," + duplicates.get(i).secondC);
                }
            }
        }

         */
    }

}