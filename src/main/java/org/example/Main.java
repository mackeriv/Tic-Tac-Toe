package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random random = new Random();

    //creates and array of choices and a grid String to be printed for every turn
    static String[][] gridArray = {{"A1", "A2", "A3"}, {"B1", "B2", "B3"}, {"C1", "C2", "C3"}};
    static String grid = ("""
                A1 A2 A3
                B1 B2 B3
                C1 C2 C3""");

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean found = false;

        System.out.println("Please choose a position from the grid. Enter \"Q\" to quit: \n");

        while (true) {

            //when everything goes right, prints a new statement after the first choices are made
            if (found) {
                System.out.println("Please make a new choice: \n");
            }

            System.out.println(grid);
            String userChoice = in.nextLine();

            //quits the application
            if (userChoice.equalsIgnoreCase("Q")) {
                break;
            }

            //resets the "found" flag the helps the program prompt the user for a valid choice
            found = false;

            //loop that takes user input, finds it on the grid and amends the String with color formatting
            for (int i = 0; i < gridArray.length; i++) {
                for (int j = 0; j < gridArray[i].length; j++) {

                    if (userChoice.equalsIgnoreCase(gridArray[i][j])) {

                        grid = grid.replace(gridArray[i][j],
                                "\u001b[34;1m" + "\u001B[1m" + gridArray[i][j] + "\u001B[0m");
                        //changes the chosen position on the grid String for "taken" and the "found" flag to true
                        gridArray[i][j] = "X";
                        found = true;
                    }
                }
            }

            String cpuChoice = rollCpuChoice();

            //loop that takes the randomized CPU choice, finds it on the grid and amends the String with color formatting
            outerLoop:
            for (int i = 0; i < gridArray.length; i++) {
                for (int j = 0; j < gridArray[i].length; j++) {

                    //if "found" flag is still set to "false", breaks loop and displays an error message
                    if (!found) {
                        break outerLoop;
                    }

                    //finds the CPU choice and colors it, marks it as "taken" in the array
                    if (cpuChoice.equals(gridArray[i][j])) {

                        grid = grid.replace(gridArray[i][j],
                                "\u001b[31;1m" + "\u001B[1m" + gridArray[i][j] + "\u001B[0m");
                        gridArray[i][j] = "O";

                    }
                }
            }

            //error message to be displayed in case of invalid choices
            if (!found) {
                    System.out.println("Invalid option. Please pick a choice from the grid: \n");
            }

        }

        System.out.println("Thank you for playing!");

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