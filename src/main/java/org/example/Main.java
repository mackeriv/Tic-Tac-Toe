package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random random = new Random();
        boolean found = false;
        boolean second = false;

        String[][] gridArray = {{"A1", "A2", "A3"}, {"B1", "B2", "B3"}, {"C1", "C2", "C3"}};

        String grid = ("""
                A1 A2 A3
                B1 B2 B3
                C1 C2 C3""");

        System.out.println("Please choose a position from the grid: \n");

        while (true) {

            if (second && found) {
                System.out.println("Please make a new choice: ");
            }

            System.out.println(grid);
            String userChoice = in.nextLine();

            int dim1Pos = random.nextInt(gridArray.length);
            int dim2Pos = random.nextInt(gridArray[dim1Pos].length);
            String cpuChoice = gridArray[dim1Pos][dim2Pos];

            found = false;

            for (int i = 0; i < gridArray.length; i++) {
                for (int j = 0; j < gridArray[i].length; j++) {

                    if (cpuChoice.equals(gridArray[i][j])) {

                        grid = grid.replace(gridArray[i][j],
                                "\u001b[31;1m" + "\u001B[1m" + gridArray[i][j] + "\u001B[0m");
                    }
                }
            }

            for (int i = 0; i < gridArray.length; i++) {
                for (int k = 0; k < gridArray[i].length; k++) {

                    if (userChoice.equalsIgnoreCase(gridArray[i][k])) {

                        grid = grid.replace(gridArray[i][k],
                                "\u001b[34;1m" + "\u001B[1m" + gridArray[i][k] + "\u001B[0m");
                        found = true;
                    }
                }
            }

            if (!found) {
                    System.out.println("Invalid option. Please pick a choice from the grid. ");
            }

            second = true;

        }
    }
}