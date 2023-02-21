package org.newtictactoe;

import java.util.Random;

public class Bot extends Player {
    /**
     * This method used to create a player
     *
     * @param name
     * @param symbol
     */
    public Bot(String name, String symbol) {
        super(name, symbol);
    }

    // make a random move on the board
    @Override
    public int makeMove(String[][] board, String playerSymbol) {
        Random rand = new Random();
        int randomNumber;
        boolean checkMove = false;
        int Number = 0;
        for (randomNumber = rand.nextInt(9) + 1; randomNumber <= 9 && checkMove == false; ) {
            String convert = Integer.toString(randomNumber);
            if (board[0][0].equals(convert) || board[0][1].equals(convert) || board[0][2].equals(convert)
                    || board[1][0].equals(convert) || board[1][1].equals(convert) || board[1][2].equals(convert)
                    || board[2][0].equals(convert) || board[2][1].equals(convert) || board[2][2].equals(convert)
            ) {

                Number = randomNumber;
                checkMove = true;


            } else {
                randomNumber = rand.nextInt(9) + 1;
                checkMove = false;
            }
        }
        if (board[0][0].equals(playerSymbol) && board[0][1].equals(playerSymbol) && board[0][2].equals("3") || board[1][2].equals(playerSymbol) && board[2][2].equals(playerSymbol) && board[0][2].equals("3") || board[2][0].equals(playerSymbol) && board[1][1].equals(playerSymbol) && board[0][2].equals("3")) {
            return Number = 3;
        }
        if (board[0][1].equals(playerSymbol) && board[0][2].equals(playerSymbol) && board[0][0].equals("1") || board[1][0].equals(playerSymbol) && board[2][0].equals(playerSymbol) && board[0][0].equals("1") || board[1][1].equals(playerSymbol) && board[2][2].equals(playerSymbol) && board[0][0].equals("1")) {
            return Number = 1;
        }
        if (board[1][0].equals(playerSymbol) && board[1][1].equals(playerSymbol) && board[1][2].equals("6")) {
            return Number = 6;
        }
        if (board[1][1].equals(playerSymbol) && board[1][2].equals(playerSymbol) && board[1][0].equals("4")) {
            return Number = 4;
        }
        if (board[2][0].equals(playerSymbol) && board[2][1].equals(playerSymbol) && board[2][2].equals("9") || board[0][2].equals(playerSymbol) && board[1][2].equals(playerSymbol) && board[2][2].equals("9") || board[0][0].equals(playerSymbol) && board[1][1].equals(playerSymbol) && board[2][2].equals("9")) {
            return Number = 9;
        }
        if (board[2][1].equals(playerSymbol) && board[2][2].equals(playerSymbol) && board[2][0].equals("7") || board[0][0].equals(playerSymbol) && board[1][0].equals(playerSymbol) && board[2][0].equals("7") || board[1][1].equals(playerSymbol) && board[0][2].equals(playerSymbol) && board[2][0].equals("7")) {
            return Number = 7;
        }
        if (board[0][1].equals(playerSymbol) && board[1][1].equals(playerSymbol) && board[2][1].equals("8")) {
            return Number = 8;
        }
        if (board[2][1].equals(playerSymbol) && board[1][1].equals(playerSymbol) && board[0][1].equals("2")) {
            return Number = 2;
        } else {
            return Number;
        }
    }

}
