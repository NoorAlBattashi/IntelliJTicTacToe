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
            String player1Name = storeValues.get("playersName")[0][1];
            String player2Name = storeValues.get("playersName")[1][1];
            String player1Symbol = storeValues.get("playersSymbol")[0][1];
            String player2Symbol = storeValues.get("playersSymbol")[1][1];
            PlayerOneTurn = Boolean.parseBoolean(storeValues.get("playersturn")[0][1]);
            PlayerTwoTurn = Boolean.parseBoolean(storeValues.get("playersturn")[1][1]);
            validTurnsCounter = Integer.valueOf(storeValues.get("validTurnsCounter")[0][1]);
            boardOfGame.setBoard(storeValues.get("ticTacToeArr"));

            //add players
            Player player1 = new Player(player1Name, player1Symbol);
            Player player2 = new Player(player2Name, player2Symbol);
            // continue the game
            startGame(validTurnsCounter, isThereAWinner, isDraw, player1, player2, PlayerOneTurn,
                    PlayerTwoTurn, boardOfGame.getBoard());
            // 2 means start new game
        } else if (choose == 2) {
            //clear the file
            manager.clearFile();
            // display the board
            boardOfGame.printBoard();

            // player1 details
            System.out.print("Player 1 write your name: ");
            String player1Name = stringScanner();
            System.out.print(player1Name + ", write your Symbol: ");
            String player1Symbol = stringScanner();

            //player2 details
            System.out.print("Player 2 write your name: ");
            String player2Name = stringScanner();
            System.out.print(player2Name + ", write your Symbol: ");
            String player2Symbol = stringScanner();
            System.out.println();

            //add players
            Player player1 = new Player(player1Name, player1Symbol);
            Player player2 = new Player(player2Name, player2Symbol);

            // start new game
            startGame(validTurnsCounter, isThereAWinner, isDraw, player1, player2, PlayerOneTurn,
                    PlayerTwoTurn, boardOfGame.getBoard());
        } else {
            System.out.println("Wrong Selection!!");
        }
    }

    /**
     * This method used to get String input from the user
     *
     * @return outputValue
     * @return stringScanner()
     */
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

    /**
     * This method used to get int input from the user
     *
     * @return outputValue
     * @return intScanner()
     */
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

    /**
     * This method used to start playing the game
     *
     * @param validTurnsCounter
     * @param isThereAWinner
     * @param isDraw
     * @param player1
     * @param player2
     * @param playerOneTurn
     * @param playerTwoTurn
     * @param ticTacToeArr
     */
    public static void startGame(int validTurnsCounter, boolean isThereAWinner, boolean isDraw, Player player1,
                                 Player player2, boolean playerOneTurn, boolean playerTwoTurn, String[][] ticTacToeArr) {

        // LOOP TO CONTINUE THE GAME
        while (validTurnsCounter < 9 && isThereAWinner == false && isDraw == false) {
            // display the board
            boardOfGame.printBoard();
            System.out.print("Press 0 if you want to exit and 1 to continue: ");
            int playerchoice = intScanner();
            // exit the game
            if (playerchoice == 0) {
                //clear the file to make sure it is empty from any previous game
                manager.clearFile();
                //add player names
                String[][] playersNameStrings = {{"player1Name", player1.getplayerName()},
                        {"player2Name", player2.getplayerName()}};
                manager.storeTheGame("playersName", playersNameStrings);
                // add Symbols
                String[][] playersSymbolStrings = {{"player1Symbol", player1.getSymbol()},
                        {"player2Symbol", player2.getSymbol()}};
                manager.storeTheGame("playersSymbol", playersSymbolStrings);
                // add playersturn
                String[][] playersturnStrings = {{"PlayerOneTurn", String.valueOf(playerOneTurn)},
                        {"PlayerTwoTurn", String.valueOf(playerTwoTurn)}};
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
                if (playerOneTurn == true && playerTwoTurn == false && isThereAWinner == false && isDraw == false) {

                    // player 1 select move
                    System.out.println();
                    System.out.print(player1.getplayerName() + ", Make a move, Enter position: ");
                    int player1Move = intScanner();

                    // check the selection position of player1 and add value
                    if (playerOneTurn == true && playerTwoTurn == false) {
                        playerOneTurn = boardOfGame.checkMove(player1Move, boardOfGame.getBoard(), player1.getSymbol(), playerOneTurn);
                        if (!playerOneTurn) {
                            playerTwoTurn = true;
                            validTurnsCounter++;
                        } else {
                            playerOneTurn = true;
                            playerTwoTurn = false;
                        }
                    }

                    // check winner
                    if (!isThereAWinner) {
                        isThereAWinner = checkWinner(ticTacToeArr, player1);
                    }

                    // check Draw
                    if (!isThereAWinner) {
                        isDraw = checkDraw(ticTacToeArr);
                    }

                    // player 2 turn
                    if (playerOneTurn == false && playerTwoTurn == true && isThereAWinner == false && isDraw == false) {
                        System.out.println();
                        System.out.print(player2.getplayerName() + ", Make a move, Enter position: ");
                        Scanner player2Movesc = new Scanner(System.in);
                        int player2Move = Integer.parseInt(player2Movesc.nextLine());

                        // check the selection position of player2 and add value
                        if (playerTwoTurn == true && playerOneTurn == false) {
                            playerTwoTurn = boardOfGame.checkMove(player2Move, boardOfGame.getBoard(), player2.getSymbol(), playerTwoTurn);
                            if (!playerTwoTurn) {
                                playerOneTurn = true;
                                validTurnsCounter++;
                            } else {
                                playerTwoTurn = true;
                                playerOneTurn = false;
                            }
                        }

                        // check winner
                        if (!isThereAWinner) {
                            isThereAWinner = checkWinner(ticTacToeArr, player2);
                        }

                    }
                }

            } else {
                System.out.println("Wrong selection!");
            }

        } // loop
    }

    /**
     * This method used to check if there is a winner
     *
     * @param board
     * @param p
     * @return playerWon
     */
    private static boolean checkWinner(String[][] board, Player p) {
        String symbol = p.getSymbol();
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
            System.out.println(p.getplayerName() + ", You win !");
            playerWon = true;
            manager.clearFile();
        }
        return playerWon;
    }

    /**
     * This method used to check if the game draw
     *
     * @param board
     * @return Draw
     */
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