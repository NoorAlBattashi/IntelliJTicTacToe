package org.newtictactoe;

public class Board {
    private int BOARD_SIZE = 3;
    private String[][] board;


    public Board() {
        board = new String[][]{{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
    }

    public void printBoard() {
        System.out.println();
        System.out.println(board[0][0] + "  |  " + board[0][1] + "  |  " + board[0][2]);
        System.out.println("-------------");
        System.out.println(board[1][0] + "  |  " + board[1][1] + "  |  " + board[1][2]);
        System.out.println("-------------");
        System.out.println(board[2][0] + "  |  " + board[2][1] + "  |  " + board[2][2]);
        System.out.println();
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] ticTacToe) {
        board = ticTacToe;
    }

    public boolean checkMove(int playerMove, String[][] board, String p, boolean PlayerTurn) {
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
            PlayerTurn = true;
        }
        if (!board[rowIndex][colIndex].equals(p)) {
            this.board[rowIndex][colIndex] = p;
            PlayerTurn = false;
        } else {
            System.out.println("The position already choosen");
            PlayerTurn = true;
        }
        return PlayerTurn;
    }

}
