import Backend.OneThread.Game;
import Backend.ThreeThreads.Game3Threads;
import Backend.TwentySevenThreads.Game27Threads;
import Backend.AbstractGame;
import Backend.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSudoku Solution Verifier\n");

        System.out.print("Enter CSV file path: ");
        String csvFilePath = scanner.nextLine().trim();

        if (csvFilePath.isEmpty()) {
            System.err.println("Error in file path");
            scanner.close();
            return;
        }

        int[][] board = readSudokuCSV(csvFilePath);
        if (board == null) {
            System.err.println("Error in reading CSV file");
            scanner.close();
            return;
        }

        System.out.println("\nChoose Verification Mode:\n");
        System.out.println("1. Sequential Mode");
        System.out.println("2. Three Threads Mode");
        System.out.println("3. Twenty-Seven Threads Mode");
        System.out.println("========================================\n");

        System.out.print("Enter your choice (1, 2, 3): ");
        String choice = scanner.nextLine().trim();

        int mode;
        switch (choice) {
            case "1":
                mode = 0;
                System.out.println("\nSequential Mode selected\n");
                break;
            case "2":
                mode = 3;
                System.out.println("\nThree Threads Mode selected\n");
                break;
            case "3":
                mode = 27;
                System.out.println("\nTwenty-Seven Threads Mode selected\n");
                break;
            default:
                System.err.println("Error Invalid choice. Please enter 1, 2, 3");
                scanner.close();
                return;
        }

        AbstractGame game = selectGame(mode);
        if (game == null) {
            System.err.println("Error Invalid mode");
            scanner.close();
            return;
        }

        game.setBoard(board);
        boolean isValid = game.checkValidity();

        System.out.println("========================================");
        if (isValid) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");
            System.out.println("\nDuplicate values found:");
            System.out.println("----------------------------------------");
            printDuplicates(game.getDuplicates());
        }
        System.out.println("========================================");
        scanner.close();
    }

    public static int[][] readSudokuCSV(String filePath) {
        int[][] board = new int[9][9];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null && row < 9) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] values = line.split(",");

                if (values.length != 9) {
                    System.err.println("Error Each row must have exactly 9 values");
                    return null;
                }

                for (int col = 0 ; col < 9 ; col++) {
                    try {
                        int value = Integer.parseInt(values[col].trim());
                        if (value < 1 || value > 9) {
                            System.err.println("Error Values must be between 1 and 9");
                            return null;
                        }
                        board[row][col] = value;
                    } catch (NumberFormatException e) {
                        System.err.println("Error Invalid number in CSV");
                        return null;
                    }
                }
                row++;
            }

            if (row != 9) {
                System.err.println("Error Board must have exactly 9 rows");
                return null;
            }

            return board;

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static AbstractGame selectGame(int mode) {
        switch (mode) {
            case 0:
                return new Game();
            case 3:
                return new Game3Threads();
            case 27:
                return new Game27Threads();
            default:
                return null;
        }
    }

    public static void printDuplicates(ArrayList<Pair> duplicates) {
        if (duplicates.isEmpty()) return;

        ArrayList<String> rows = new ArrayList<>();
        ArrayList<String> cols = new ArrayList<>();
        ArrayList<String> boxes = new ArrayList<>();

        for (int i = 0; i < duplicates.size(); i++) {
            Pair p = duplicates.get(i);
            int r1 = (Integer) p.firstR;
            int c1 = (Integer) p.firstC;
            int r2 = (Integer) p.secondR;
            int c2 = (Integer) p.secondC;

            if (r1 == r2) {
                String key = "ROW " + (r1 + 1);
                rows.add(key + ", #" + p.firstR + ", [" + r1 + "," + c1 + "] [" + r2 + "," + c2 + "]");
            } else if (c1 == c2) {
                String key = "COL " + (c1 + 1);
                cols.add(key + ", #" + p.firstC + ", [" + r1 + "," + c1 + "] [" + r2 + "," + c2 + "]");
            } else {
                int box1 = (r1 / 3) * 3 + (c1 / 3) + 1;
                boxes.add("BOX " + box1 + ", #[" + r1 + "," + c1 + "], [" + r1 + "," + c1 + "] [" + r2 + "," + c2 + "]");
            }
        }

        if (!rows.isEmpty()) {
            System.out.println("\nROWS:");
            for (int i = 0; i < rows.size(); i++) {
                System.out.println(rows.get(i));
            }
        }

        if (!cols.isEmpty()) {
            System.out.println("\nCOLUMNS:");
            for (int i = 0; i < cols.size(); i++) {
                System.out.println(cols.get(i));
            }
        }

        if (!boxes.isEmpty()) {
            System.out.println("\nBOXES:");
            for (int i = 0; i < boxes.size(); i++) {
                System.out.println(boxes.get(i));
            }
        }
    }
}