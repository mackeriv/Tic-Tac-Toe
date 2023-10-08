package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random random = new Random();
    static boolean found = false;

    //creates and array of choices and a grid String to be printed for every turn
    static String[][] gridArray = {{"A1", "A2", "A3"}, {"B1", "B2", "B3"}, {"C1", "C2", "C3"}};
    static String grid = ("""
                A1 A2 A3
                B1 B2 B3
                C1 C2 C3""");

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int count = 0;

        System.out.println("Please choose a position from the grid. Enter \"Q\" to quit: \n");

        while (true) {

            //prints a new statement after the first choices are made
            if (found) {
                System.out.println("\nPlease make a new choice: \n");
            }

            System.out.println(grid);
            String userChoice = in.nextLine();

            //quits the application
            if (userChoice.equalsIgnoreCase("Q")) {
                System.out.println("\nThank you for playing!");
                break;
            }

            //resets the "found" flag that enables the program to prompt the user for a valid choice
            found = false;

            if (!processPlay(userChoice, true)) continue;
            else count++;

            //error message to be displayed in case of invalid choices
            if (!found) {
                System.out.println("Invalid option. Please pick a choice from the grid: \n");
                continue;
            }

            if (checkWin()) {
                //checks if there's a winning combination after the user's turn
                System.out.println("\n" + grid);
                System.out.println("\n*** YOU WIN! ***\n");
                count = 0;
                reset();
                continue;
            }

            //when the count reaches 5, it means the user has played 5 turns without a winner and the game is a draw
            if (count == 5) {
                System.out.println("\n*** DRAW! ***\n");
                count = 0;
                reset();
                continue;
            }

            //rolls a random choice for the CPU and checks if it's a winning combination
            String cpuChoice = rollCpuChoice();
            processPlay(cpuChoice, false);
            if (checkWin()) {
                System.out.println("\n" + grid);
                System.out.println("\n*** YOU LOSE! ***\n");
                count = 0;
                reset();
            }
        }
    }

    private static void reset(){
        //provides a clean array and a clean grid String for new games
        gridArray = new String[][] {{"A1", "A2", "A3"}, {"B1", "B2", "B3"}, {"C1", "C2", "C3"}};
        grid = ("""
                A1 A2 A3
                B1 B2 B3
                C1 C2 C3""");
    }

    private static boolean processPlay(String choice, Boolean isPlayer) {

        try {
            //Converts both characters into integers
            int i = choice.toUpperCase().charAt(0)-65;
            int j = Integer.parseInt(choice.substring(1,2)) - 1;

            //takes the choices, finds them on the grid and amends the String with color formatting
            if (choice.equalsIgnoreCase(gridArray[i][j])) {
                grid = grid.replace(gridArray[i][j], (isPlayer ? "\u001b[34;1m" : "\u001b[31;1m") + gridArray[i][j] + "\u001B[0m");
                //changes the chosen position on the grid String for "X" or "O" and the "found" flag to true
                gridArray[i][j] = isPlayer ? "X" : "O";
                found = true;
            }

        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid option. Please pick a choice from the grid: \n");
            return false;
        }
        return true;
    }

    private static boolean checkWin() {
        //checks for horizontal combinations
        for (int i = 0; i < gridArray.length; i++) {
            if (Arrays.stream(gridArray[i]).distinct().count() == 1) return true; }

        //checks for diagonal combinations (diagonal 1)
        String[] diag1 = new String[gridArray.length];
        for (int i = 0; i < gridArray.length; i++) {
            diag1[i] = gridArray[i][i];
        }
        if (Arrays.stream(diag1).distinct().count() == 1) return true;

        //checks for diagonal combinations (diagonal 2)
        String[] diag2 = new String[gridArray.length];
        for (int i = 0; i < gridArray.length; i++) {
            diag2[i] = gridArray[gridArray.length-1-i][i];
        }
        if (Arrays.stream(diag2).distinct().count() == 1) return true;

        //checks for vertical combinations
        String[] col = new String[gridArray.length];
        for (int i = 0; i < gridArray.length; i++) {
            for (int j = 0; j < gridArray.length; j++) {
                col[j] = gridArray[j][i];
            }
            if (Arrays.stream(col).distinct().count() == 1) return true;
        }
        return false;
    }

    private static String rollCpuChoice() {
        String cpuChoice;
        do {
            //generates a random choice from the grid for the CPU
            int dim1Pos = random.nextInt(gridArray.length);
            int dim2Pos = random.nextInt(gridArray[dim1Pos].length);
            cpuChoice = gridArray[dim1Pos][dim2Pos];

            //if the CPU choice is taken, rolls a new random choice until the position is available
        } while (cpuChoice.equals("X") || cpuChoice.equals("O"));
        return cpuChoice;
    }

}