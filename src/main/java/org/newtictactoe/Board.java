package org.newtictactoe;

public class Board {
    private int BOARD_SIZE = 3;
    private String[][] board;

    /**
     * This method used to create a board
     */
    public Board() {
        board = new String[][]{{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
    }

    /**
     * This method used to display the board in the game
     */
    public void printBoard() {
        System.out.println();
        System.out.println(board[0][0] + "  |  " + board[0][1] + "  |  " + board[0][2]);
        System.out.println("-------------");
        System.out.println(board[1][0] + "  |  " + board[1][1] + "  |  " + board[1][2]);
        System.out.println("-------------");
        System.out.println(board[2][0] + "  |  " + board[2][1] + "  |  " + board[2][2]);
        System.out.println();
    }

    /**
     * This method used to get the board array
     *
     * @return board
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * This method used to set the board game
     *
     * @param ticTacToe
     */
    public void setBoard(String[][] ticTacToe) {
        board = ticTacToe;
    }

    /**
     * This method used to check the available locations in the board and set the player symbols
     *
     * @param playerMove
     * @param board
     * @param p
     * @param playerTurn
     * @return playerTurn
     */
    public boolean checkMove(int playerMove, String[][] board, String p, boolean playerTurn) {
        int rowIndex = 0;
        int colIndex = 0;

        if (playerMove == 1) {
            rowIndex = 0;
            colIndex = 0;
        } else if (playerMove == 2) {
            rowIndex = 0;
            colIndex = 1;
        } else if (playerMove == 3) {
            rowIndex = 0;
            colIndex = 2;
        } else if (playerMove == 4) {
            rowIndex = 1;
            colIndex = 0;
        } else if (playerMove == 5) {
            rowIndex = 1;
            colIndex = 1;
        } else if (playerMove == 6) {
            rowIndex = 1;
            colIndex = 2;
        } else if (playerMove == 7) {
            rowIndex = 2;
            colIndex = 0;

        } else if (playerMove == 8) {
            rowIndex = 2;
            colIndex = 1;
        } else if (playerMove == 9) {
            rowIndex = 2;
            colIndex = 2;
        } else {
            System.out.println("Wrong selection");
            playerTurn = true;
        }
        if (!board[rowIndex][colIndex].equals(p)) {
            this.board[rowIndex][colIndex] = p;
            playerTurn = false;
        } else {
            System.out.println("The position already choosen");
            playerTurn = true;
        }
        return playerTurn;
    }

}
