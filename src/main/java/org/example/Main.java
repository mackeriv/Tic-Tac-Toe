package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random random = new Random();

        String[][] gridArray = {{"A1", "A2", "A3"}, {"B1", "B2", "B3"}, {"C1", "C2", "C3"}};

        System.out.println("Please choose a position from the grid: \n");

        String grid = ("""
                A1 A2 A3
                B1 B2 B3
                C1 C2 C3""");

        System.out.println(grid);

        String userChoice = in.nextLine();

        int dim1Pos = random.nextInt(gridArray.length);
        int dim2Pos = random.nextInt(gridArray[dim1Pos].length);
        String cpuChoice = gridArray[dim1Pos][dim2Pos];

        //System.out.println("Please make your next choice: ");

        while (true) {
            for (int i = 0; i < gridArray.length; i++){
                for (int j = 0; j < gridArray[i].length; j++){

                    if (userChoice.equals(gridArray[i][j])){

                        System.out.println(grid.replace(gridArray[i][j],
                                "\u001b[34;1m" + "\u001B[1m" + gridArray[i][j] + "\u001B[0m"));

                    }

                    else if (cpuChoice.equals(gridArray[i][j])){

                        System.out.println(grid.replace(gridArray[i][j],
                                "\u001b[31;1m" + "\u001B[1m" + gridArray[i][j] + "\u001B[0m"));

                    }

                }

            }

            break;

        }
    }
}