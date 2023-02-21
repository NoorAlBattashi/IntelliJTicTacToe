package org.newtictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String playerName;
    private Board board = new Board();

    /**
     * This method used to create a player
     *
     * @param name
     * @param symbol
     */
    public Player(String name, String symbol) {
        this.symbol = new Symbol(symbol);
        playerName = name;

    }

    /**
     * This method used to get the player name
     *
     * @return this.playerName
     */
    public String getplayerName() {
        return this.playerName;
    }

    /**
     * This method used to get the player symbol
     *
     * @return symbol.getSymbol()
     */
    public String getSymbol() {
        return symbol.getSymbol();
    }

    public int makeMove(String[][] ticTacToeArr, String playerSymbol) {
        try {
            Scanner inputScanner = new Scanner(System.in);
            int outputValue = inputScanner.nextInt();
            return outputValue;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. It must be an integer.");
            System.out.print("Enter again here: ");
            return makeMove(ticTacToeArr, playerSymbol);
        }
    }
}
