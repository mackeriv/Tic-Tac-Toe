package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random random = new Random();
    static String[][] gridArray = {{"A1", "A2", "A3"}, {"B1", "B2", "B3"}, {"C1", "C2", "C3"}};
    static String grid = ("""
                A1 A2 A3
                B1 B2 B3
                C1 C2 C3""");
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random random = new Random();
        boolean found = false;
        boolean second = false;

        System.out.println("Please choose a position from the grid. Enter \"Q\" to quit: \n");

        while (true) {
            if (second && found) {
                System.out.println("Please make a new choice: \n");
            }
            System.out.println(grid);
            String userChoice = in.nextLine();

            if (userChoice.equalsIgnoreCase("Q")) {
                break;
            }

            if (!processPlay(userChoice, true)) continue;
            if(checkWin()){
                System.out.println("you won!");
                reset();
                continue;
            }

            String cpuChoice = rollCpuChoice();
            processPlay(cpuChoice, false);
            if(checkWin()){
                System.out.println("you lost :-(");
                reset();
                continue;
            }

            second = true;
        }
        System.out.println("Thank you for playing!");
    }

    private static void reset() {
        System.out.println(grid);

        gridArray = new String[][] {{"A1", "A2", "A3"}, {"B1", "B2", "B3"}, {"C1", "C2", "C3"}};
        grid = ("""
                A1 A2 A3
                B1 B2 B3
                C1 C2 C3""");
    }

    private static boolean processPlay(String choice, Boolean isPlayer){
        int i = choice.toUpperCase().charAt(0)-65  ;        //quando input for B2 i será 1
        int j = Integer.valueOf(choice.substring(1,2))-1;   //quando input for B2 j será 1
        try {
            grid = grid.replace(gridArray[i][j],
                    (isPlayer?"\u001b[34;1m":"\u001b[31;1m") + "\u001B[1m" + gridArray[i][j] + "\u001B[0m"); //pinta opcao do usuario de azul
            gridArray[i][j] = isPlayer?"X":"O";
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid option. Please pick a choice from the grid: \n");
            return false;
        }
        return true;
    }

    private static boolean checkWin() {
        for(int i = 0; i<gridArray.length;i++){ //linhas
            if (Arrays.stream(gridArray[i]).distinct().count() == 1) return true;
        }
        String[] diag = new String[gridArray.length]; //diagonal principal
        for(int c=0;c<gridArray.length;c++){
            diag[c]=gridArray[c][c];
        }
        if (Arrays.stream(diag).distinct().count() == 1) return true;

        String[] diag2 = new String[gridArray.length]; //diagonal secundaria
        for(int c=0;c<gridArray.length;c++){
            diag2[c]=gridArray[gridArray.length-1-c][c];
        }
        if (Arrays.stream(diag2).distinct().count() == 1) return true;

        for(int j = 0; j<gridArray.length;j++){ //colunas
            String[] column = new String[gridArray.length];
            for(int c=0;c<gridArray.length;c++){
                column[c]=gridArray[c][j];
            }
            if(Arrays.stream(column).distinct().count() == 1) return true;
        }
        return false;
    }

    private static String rollCpuChoice() {
        String cpuChoice;
        do {
            int dim1Pos = random.nextInt(gridArray.length);
            int dim2Pos = random.nextInt(gridArray[dim1Pos].length);
            cpuChoice = gridArray[dim1Pos][dim2Pos];
        } while (cpuChoice.equals("X") || cpuChoice.equals("O"));
        return cpuChoice;
    }
}