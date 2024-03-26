package test;

import java.util.ArrayList;

public class Board {
    Tile[][] board;
    String[][] colors;
    

    private Board() {
        board = new Tile[15][15];
        colors = new String[15][15];
        this.colors[7][7] = "Yellow";
        this.colors[0][0] = "Red";
        this.colors[7][0] = "Red";
        this.colors[14][0] = "Red";
        this.colors[7][0] = "Red";
        this.colors[14][7] = "Red";
        this.colors[14][0] = "Red";
        this.colors[14][14] = "Red";
        this.colors[1][1] = "Yellow";
        this.colors[2][2] = "Yellow";
        this.colors[3][3] = "Yellow";
        this.colors[4][4] = "Yellow";
        this.colors[13][1] = "Yellow";
        this.colors[12][2] = "Yellow";
        this.colors[11][3] = "Yellow";
        this.colors[10][4] = "Yellow";
        this.colors[10][10] = "Yellow";
        this.colors[11][11] = "Yellow";
        this.colors[12][12] = "Yellow";
        this.colors[13][13] = "Yellow";
        this.colors[4][10] = "Yellow";
        this.colors[1][1] = "Yellow";
        this.colors[3][11] = "Yellow";
        this.colors[2][12] = "Yellow";
        this.colors[1][13] = "Yellow";
        this.colors[3][0] = "Blue";
        this.colors[11][0] = "Blue";
        this.colors[6][2] = "Blue";
        this.colors[8][2] = "Blue";
        this.colors[0][3] = "Blue";
        this.colors[14][3] = "Blue";
        this.colors[6][6] = "Blue";
        this.colors[8][6] = "Blue";
        this.colors[12][6] = "Blue";
        this.colors[3][7] = "Blue";
        this.colors[11][7] = "Blue";
        this.colors[2][8] = "Blue";
        this.colors[6][8] = "Blue";
        this.colors[8][8] = "Blue";
        this.colors[12][8] = "Blue";
        this.colors[0][11] = "Blue";
        this.colors[14][11] = "Blue";
        this.colors[6][12] = "Blue";
        this.colors[3][14] = "Blue";
        this.colors[11][14] = "Blue";
        this.colors[7][7] = "Star";
        this.colors[5][1] = "Navy";
        this.colors[9][1] = "Navy";
        this.colors[5][1] = "Navy";
        this.colors[1][5] = "Navy";
        this.colors[5][5] = "Navy";
        this.colors[9][5] = "Navy";
        this.colors[13][5] = "Navy";
        this.colors[1][9] = "Navy";
        this.colors[5][9] = "Navy";
        this.colors[9][9] = "Navy";
        this.colors[13][9] = "Navy";
        this.colors[5][13] = "Navy";
        this.colors[9][13] = "Navy";
    }

    // return copy of the board
    private Tile[][] getTiles() {
        return this.board.clone();
    }
    // Private static board to hold the single instance of the class
    private static Board b;
    // singleton
    public static Board getBoard() {
        if (b == null) {
            b = new Board();
        }
        return b;
    }

    public boolean boardLeagl(Word w) {
        int wordSize = w.getTiles().length;
        if (wordSize < 15 && board[7][7] != null) { //[7][7] center of the board
            if(cover(w) && linked(w)) 
                return true;
        }
        return false;        
    }
    
    boolean cover(Word w) { // check if the word covering other words
        int wordSize = w.getTiles().length;
        int col = w.getCol();
        int row = w.getRow();
        if (w.isVertical()) { 
            for (int i = 0; i < wordSize; i++) { 
                if (board[row + i][col] != null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < wordSize; i++) {
                if (board[row][col + i] != null) {
                    return true;
                }
            }
        }
        return false;  
    }

    boolean linked(Word w) { // check if the word is linked to other words
        int wordSize = w.getTiles().length;
        int col = w.getCol();
        int row = w.getRow();
        if (w.isVertical()) {
            if (board[row - 1][col] != null || board[row + wordSize][col] != null) {
                return true;
            }
        } else {
            if (board[row][col - 1] != null || board[row][col + wordSize + 1] != null) {
                return true;
            }
        }        
        return false;
    }   
}
