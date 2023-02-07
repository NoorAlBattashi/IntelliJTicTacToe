package org.newtictactoe;


import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Board boardOfGame = new Board();
    public static StateManager manager = new StateManager();

    public static void main(String[] args) {

        System.out.println("Start the Game!");
        boardOfGame.printBoard();

        // Play the Game

        // define the player turn
        boolean PlayerOneTurn;
        boolean PlayerTwoTurn;
        int validTurnsCounter = 0;

        // player turn
        PlayerOneTurn = true;
        PlayerTwoTurn = false;
        boolean isThereAWinner = false;
        boolean isDraw = false;

        System.out.println("Do you want to: ");
        System.out.println("1-Continue the previous game");
        System.out.println("2-Start new game");
        System.out.print("Here: ");
        int choose = intScanner();
        System.out.println();

        if (choose == 1) {
            // create hashmaps to store the values
            HashMap<String, String[][]> storeValues = new HashMap<String, String[][]>();
            storeValues = manager.getPreviousGame();
            // Add the values inside the variables
            Symbol player1Symbol = new Symbol(storeValues.get("playersSymbol")[0][1]);
            Symbol player2Symbol = new Symbol(storeValues.get("playersSymbol")[1][1]);
            PlayerOneTurn = Boolean.parseBoolean(storeValues.get("playersturn")[0][1]);
            PlayerTwoTurn = Boolean.parseBoolean(storeValues.get("playersturn")[1][1]);
            validTurnsCounter = Integer.valueOf(storeValues.get("validTurnsCounter")[0][1]);
            boardOfGame.setBoard(storeValues.get("ticTacToeArr"));

            // continue the game
            startGame(validTurnsCounter, isThereAWinner, isDraw, player1Symbol.getSymbol(), player2Symbol.getSymbol(), PlayerOneTurn,
                    PlayerTwoTurn, boardOfGame.getBoard());
            // 2 means start new game
        } else if (choose == 2) {
            //clear the file
            manager.clearFile();
            // display the board
            boardOfGame.printBoard();
            // players select there symbol
            System.out.print("Player 1 write your Symbol: ");
            String player1Symbol = stringScanner();
            Symbol player1 = new Symbol(player1Symbol);
            System.out.print("Player 2 write your Symbol: ");
            String player2Symbol = stringScanner();
            Symbol player2 = new Symbol(player2Symbol);
            System.out.println();

            // new game
            startGame(validTurnsCounter, isThereAWinner, isDraw, player1.getSymbol(), player2.getSymbol(), PlayerOneTurn,
                    PlayerTwoTurn, boardOfGame.getBoard());
        } else {
            System.out.println("Wrong Selection!!");
        }
    }

    public static String stringScanner() {
        try {
            Scanner inputScanner = new Scanner(System.in);
            String outputValue = inputScanner.nextLine();
            return outputValue;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. It must be a string.");
            System.out.print("Enter again here: ");
            return stringScanner();
        }
    }

    public static int intScanner() {
        try {
            Scanner inputScanner = new Scanner(System.in);
            int outputValue = inputScanner.nextInt();
            return outputValue;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. It must be an integer.");
            System.out.print("Enter again here: ");
            return intScanner();
        }
    }

    public static void startGame(int validTurnsCounter, boolean isThereAWinner, boolean isDraw, String player1Symbol,
                                 String player2Symbol, boolean PlayerOneTurn, boolean PlayerTwoTurn, String[][] ticTacToeArr) {
        // LOOP TO CONTINUE THE GAME
        while (validTurnsCounter < 9 && isThereAWinner == false && isDraw == false) {
            // display the board
            boardOfGame.printBoard();
            System.out.print("Press 0 if you want to exit and 1 to continue: ");
            int playerchoice = intScanner();
            // exit the game
            if (playerchoice == 0) {
                // add Symbols
                String[][] playersSymbolStrings = {{"player1Symbol", player1Symbol},
                        {"player2Symbol", player2Symbol}};
                manager.storeTheGame("playersSymbol", playersSymbolStrings);
                // add playersturn
                String[][] playersturnStrings = {{"PlayerOneTurn", String.valueOf(PlayerOneTurn)},
                        {"PlayerTwoTurn", String.valueOf(PlayerTwoTurn)}};
                manager.storeTheGame("playersturn", playersturnStrings);
                // add validTurnsCounter
                String[][] counterStrings = {{"validTurnsCounter", Integer.toString(validTurnsCounter)}};
                manager.storeTheGame("validTurnsCounter", counterStrings);
                // add the ticTacToeArr
                manager.storeTheGame("ticTacToeArr", ticTacToeArr);

                System.out.println("Exit");
                break;
            } else if (playerchoice == 1) {
                // player 1 turn
                if (PlayerOneTurn == true && PlayerTwoTurn == false && isThereAWinner == false && isDraw == false) {
                    // player 1 select move

                    System.out.println();
                    System.out.print("Player 1, Make a move, Enter position: ");
                    int player1Move = intScanner();

                    // check the selection position of player1 and add value
                    if (PlayerOneTurn == true && PlayerTwoTurn == false) {
                        PlayerOneTurn = boardOfGame.checkMove(player1Move, boardOfGame.getBoard(), player1Symbol, PlayerOneTurn);
                        if (PlayerOneTurn == false) {
                            PlayerTwoTurn = true;
                            validTurnsCounter++;
                        } else {
                            PlayerOneTurn = true;
                            PlayerTwoTurn = false;
                        }
                    }

                    // check winner
                    if (isThereAWinner == false) {
                        isThereAWinner = checkWinner(ticTacToeArr, player1Symbol);
                    }

                    // check Draw
                    if (isThereAWinner == false) {
                        isDraw = checkDraw(ticTacToeArr);
                    }

                    // player 2 turn
                    if (PlayerOneTurn == false && PlayerTwoTurn == true && isThereAWinner == false && isDraw == false) {
                        System.out.println();
                        System.out.print("Player 2, Make a move, Enter position: ");
                        Scanner player2Movesc = new Scanner(System.in);
                        int player2Move = Integer.parseInt(player2Movesc.nextLine());

                        // check the selection position of player2 and add value
                        if (PlayerTwoTurn == true && PlayerOneTurn == false) {
                            PlayerTwoTurn = boardOfGame.checkMove(player2Move, boardOfGame.getBoard(), player2Symbol, PlayerTwoTurn);
                            if (PlayerTwoTurn == false) {
                                PlayerOneTurn = true;
                                validTurnsCounter++;
                            } else {
                                PlayerTwoTurn = true;
                                PlayerOneTurn = false;
                            }
                        }

                        // check winner
                        if (!isThereAWinner) {
                            isThereAWinner = checkWinner(ticTacToeArr, player2Symbol);
                            checkWinner(ticTacToeArr, player2Symbol);
                        }

                    }
                }

            } else {
                System.out.println("Wrong selection!");
            }

        } // loop
    }

    private static boolean checkWinner(String[][] board, String symbol) {
        boolean playerWon = false;
        if (board[0][0].equals(symbol) && board[0][1].equals(symbol) && board[0][2].equals(symbol)
                || board[1][0].equals(symbol) && board[1][1].equals(symbol) && board[1][2].equals(symbol)
                || board[2][0].equals(symbol) && board[2][1].equals(symbol) && board[2][2].equals(symbol)
                || board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol)
                || board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol)
                || board[0][0].equals(symbol) && board[1][0].equals(symbol) && board[2][0].equals(symbol)
                || board[0][1].equals(symbol) && board[1][1].equals(symbol) && board[2][1].equals(symbol)
                || board[0][2].equals(symbol) && board[1][2].equals(symbol) && board[2][2].equals(symbol)) {

            // display the board
            boardOfGame.printBoard();

            // display win message
            System.out.println(symbol + ", You win !");
            playerWon = true;
            manager.clearFile();
        }
        return playerWon;
    }

    private static boolean checkDraw(String[][] board) {
        boolean Draw = false;
        if (!board[0][0].equals("1") && !board[0][1].equals("2") && !board[0][2].equals("3") && !board[1][0].equals("4")
                && !board[1][1].equals("5") && !board[1][2].equals("6") && !board[2][0].equals("7")
                && !board[2][1].equals("8") && !board[2][2].equals("9")) {

            // display the board
            boardOfGame.printBoard();

            // display draw message
            System.out.println("Draw, No winner !");
            Draw = true;

            // clear the file
            manager.clearFile();
        }
        return Draw;

    }
}