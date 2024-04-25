package test;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    Tile[][] board;
    String[][] colors;
    ArrayList<Word> words;
    private static int numofwordsonboard;

    private Board() {
        board = new Tile[15][15];
        colors = new String[15][15];
        this.numofwordsonboard = 0;
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                this.colors[i][j] = "";
            }
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
    public boolean boardLegal(Word word){
        if(board[7][7]==null) {//if board empty
            if(onBoard(word) && onStar(word))
                return true;
            return false;
        }
        else if(onBoard(word) && overLap(word) && (onSide(word)|| Cover(word)))
            return true;
        return false;
    }

    boolean onBoard(Word word){
        int len=word.tiles.length;
        if(!word.vertical)
            if (word.row>=0 && word.col>=0 && word.row<=14 && word.col+len-1<=14)
                return true;
        if(word.vertical)
            if (word.col >= 0 && word.col <= 14 && word.row >= 0 && word.row + len - 1 <= 14)
                return true;
        return false;
    }
    private boolean onStar(Word w) {
        int col = w.getCol();
        int row = w.getRow();
        for (int i = 0; i < w.getTiles().length; i++) {
            if (row == 7 && col == 7) {
                return true;
            }
            if (w.isVertical()) {
                row++;
            } else {
                col++;
            }
        }
        return false;
    }
    boolean onSide(Word word){
        if(!word.vertical) {
            int col=word.col;
            if (Right(word.row,word.col) || Left(word.row,(word.col)+(word.tiles.length-1)))
                return true;
            for (int i = 0; i < word.tiles.length; i++) {
                if (Up(word.row , col) || Down(word.row , col))
                    return true;
                col++;
            }
        }
        else {
            int row=word.row;
            if(Up(word.row,word.col) || Down((word.row)+(word.tiles.length-1),word.col))
                return true;
            for(int i=0; i<word.tiles.length; i++){
                if(Right(row,word.col) || Left(row,word.col))
                    return true;
                row++;
            }
        }return false;
    }
    boolean overLap(Word word) {
        int row=word.row;
        int col=word.col;
        if(!word.vertical){
            for (int i = 0; i < word.tiles.length; i++) {
                if (board[word.row][col] == null && word.tiles[i] == null)
                    return false;
                if(board[word.row][col]!=null && word.tiles[i]!=null)
                    return false;
                col++;
            }
        }
        else{
            for (int i = 0; i < word.tiles.length; i++){
                if (board[row][word.col] == null && word.tiles[i] == null)
                    return false;
                if (board[row][word.col]!=null && word.tiles[i]!=null)
                    return false;
                row++;
            }
        }return true;
    }
    boolean Left(int i, int j){ // checks if there is tile on left
        if(i>=0 && i<=14 && j-1>=0 && j-1<=14 && board[i][j-1]!=null)
            return true;
        return false;
    }
    boolean Right(int i, int j){ // checks if there is tile on right
        if(i>=0 && i<=14 && j+1>=0 && j+1<=14 && board[i][j+1]!=null)
            return true;
        return false;
    }
    boolean Up(int i, int j){ // checks if there is tile up
        if(i-1>=0 && i-1<=14 && j>=0 && j<=14 && board[i-1][j]!=null)
            return true;
        return false;
    }
    boolean Down(int i, int j){ // checks if there is tile down
        if(i+1>=0 && i+1<=14 && j>=0 && j<=14 && board[i+1][j]!=null)
            return true;
        return false;
    }
    boolean Cover(Word w) { // check if the word covering other words
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
    boolean dictionarylegal(Word w) {
        return true;
    }
    private ArrayList<Word> getAllWords(Tile[][] t) {
    ArrayList<Word> neWords = new ArrayList<>();
    // Horizontal
    for (int i = 0; i < t.length; i++) {
        for (int j = 0; j < t[i].length; j++) {
            if (t[i][j] != null) {
                int startCol = j; // word start column
                while (j < t[i].length && t[i][j] != null) {
                    j++;
                }
                int endCol = j - 1; // word end column
                if (endCol - startCol + 1 > 1) {
                    Tile[] tiles = Arrays.copyOfRange(t[i], startCol, endCol + 1);
                    neWords.add(new Word(tiles, i, startCol, false));
                }
            }
        }
    }
    // Vertical
    for (int j = 0; j < t[0].length; j++) {
        for (int i = 0; i < t.length; i++) {
            if (t[i][j] != null) {
                int startRow = i; // word start row
                while (i < t.length && t[i][j] != null) {
                    i++;
                }
                int endRow = i - 1; // word end row
                if (endRow - startRow + 1 > 1) {
                    Tile[] tiles = new Tile[endRow - startRow + 1];
                    for (int k = startRow; k <= endRow; k++) {
                        tiles[k - startRow] = t[k][j];
                    }
                    neWords.add(new Word(tiles, startRow, j, true));
                }
            }
        }
    }
        return neWords; 
    }
    public ArrayList<Word> getWords(Word word){
        Tile[][] tempboard=getTiles();
        implemantBoard(tempboard,word); // put the word on the board
        ArrayList<Word> arrwords=new ArrayList<Word>();
        Word w,ww; Tile[]tiles=new Tile[word.tiles.length];
        if(!word.vertical){
            int col1=word.col;
            for (int i = 0; i < word.tiles.length; i++) {
                tiles[i]=tempboard[word.row][col1]; col1++;
            } ww=new Word(tiles,word.row,word.col,false); arrwords.add(ww);
            if(Left(word.row, word.col)) {
                w = Getfromleft(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            else if(Right(word.row, word.col+word.tiles.length-1)) {
                w= Getright(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            int col=word.col;
            for(int k=0; k<word.tiles.length; k++){
                Word w1;
                if(Up(word.row,col) && word.tiles[k]!=null) {
                    w1 = Getfromup(tempboard,word.row, col);
                    arrwords.add(w1);
                }
                else if(Down(word.row, col) && word.tiles[k]!=null) {
                    w1 = Getdown(tempboard,word.row, col);
                    arrwords.add(w1);
                }
                col++;
            }
        }
        else{
            int row1=word.row;
            for (int i = 0; i < word.tiles.length; i++) {
                tiles[i]=tempboard[row1][word.col]; row1++;
            } ww=new Word(tiles,word.row,word.col,true); arrwords.add(ww);
            if(Up(word.row, word.col)) {
                w= Getfromup(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            else if(Down(word.row+word.tiles.length-1, word.col)) {
                w=Getdown(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            int row=word.row;
            for(int k=0; k<word.tiles.length; k++){
                Word w1;
                if(Left(row,word.col) && word.tiles[k]!=null) {
                   w1=Getfromleft(tempboard,row, word.col);
                   arrwords.add(w1);
                }
                else if(Right(row, word.col)&& word.tiles[k]!=null) {
                    w1=Getright(tempboard,row, word.col);
                    arrwords.add(w1);
                }
                row++;
            }
        }return arrwords;
   }
    Word Getfromup(Tile[][] tempboard,int i, int j){ // return new vertical word from up
        int row=i, countup=0,countdown=0, row1=i;
        while(Up(i,j)){
            countup++;
            i--;
        }
        while(Down(row,j)){
            countdown++;
            row++;
        }
        int first=row1-countup;
        int nrow= row1-countup;
        Tile[] tiles=new Tile[countdown+countup+1];
        for(int k=0; k<countdown+countup+1; k++) {
            tiles[k] = tempboard[nrow][j];
            nrow++;
        } Word word= new Word(tiles, first,j,true);
        return word;
    }
    Word Getdown(Tile[][] tempboard, int i, int j){ // return new vertical word from down
        int countdown=0;
        int first=i;
        while(Down(i,j)){
            countdown++;
            i++;
        }
        i=i-countdown;
        Tile[] tiles=new Tile[countdown+1];
        for(int k=0; k<countdown+1; k++){
            tiles[k]=tempboard[i][j];
            i++;
        }Word word=new Word(tiles,first,j,true);
        return word;
    }
    Word Getfromleft(Tile[][] tempbaord,int i, int j){
        int col=j;
        int countleft=0;
        int countright=0;
        while(Left(i,j)){
            countleft++;
            j--;
        }int first=j;
        int ncol=j;
        while(Right(i,col)){
            countright++;
            col++;
        }
        Tile[] tiles=new Tile[countleft+countright+1];
        for(int k=0; k<countleft+countright+1; k++){
            tiles[k]=tempbaord[i][ncol];
            ncol++;
        } Word word=new Word(tiles,i,first,false);
        return word;
    }
    Word Getright(Tile[][] tempboard ,int i, int j){
        int col=j;
        int first=j;
        int counterright=0;
        while (Right(i,j)){
            counterright++;
            j++;
        }Tile[] tiles=new Tile[counterright+1];
        for(int k=0; k<counterright+1; k++){
            tiles[k]= tempboard[i][col];
            col++;
        }
        Word word=new Word(tiles,i,first,false);
        return word;
    }
    int getScore(Word w) {
        int score = 0, yellow = 0, red = 0;
        if (!w.vertical) {
            int col = w.col;
            for (int i = 0; i < w.tiles.length; i++) {
                if (colors[w.row][col] == "Blue")
                    score += (w.tiles[i].score * 2);
                else if (colors[w.row][col] == "Navy")
                    score += (w.tiles[i].score * 3);
                else if (colors[w.row][col] == "Yellow" && numofwordsonboard!=1) {
                    yellow++; score+=w.tiles[i].score;
                }
                else if (colors[w.row][col] == "Red") {
                    red++; score+=w.tiles[i].score;;
                }
                else score += w.tiles[i].score;
                col++;
            }
        } else {
            int row = w.row;
            for (int i = 0; i < w.tiles.length; i++) {
                if (colors[row][w.col] == "Blue")
                    score += (w.tiles[i].score * 2);
                else if (colors[row][w.col] == "Navy")
                    score += (w.tiles[i].score * 3);
                else if (colors[row][w.col] == "Yellow"&& numofwordsonboard!=1) {
                    yellow++; score+=w.tiles[i].score;
                }
                else if (colors[row][w.col] == "Red") {
                    red++; score+=w.tiles[i].score;
                }
                else score+=w.tiles[i].score;
                row++;
            }
        }
        if(yellow>0) score= (yellow * (score * 2));
        if(red>0) score= (red * (score * 3));
        if(numofwordsonboard==1)
            score= score*2;
        return score;
    }
    public int tryPlaceWord(Word w) {
        ArrayList<Word> arrwords;
        int score = 0;
        if(numofwordsonboard==0 && boardLegal(w)){
            implemantBoard(board,w);
            return getScore(w);
        }
        if (boardLegal(w)) {
            arrwords = getWords(w);
            for (int i = 0; i < arrwords.size(); i++)
                if (!dictionarylegal(arrwords.get(i)))
                    return 0;
                    implemantBoard(board, w);
            for (int i = 0; i < arrwords.size(); i++)
                score += getScore(arrwords.get(i));
            return score;
        }
        return 0;
    }
    void implemantBoard(Tile[][] board, Word word) {
        if(!word.vertical) {
            int col=word.col;
            for (int i = 0; i < word.tiles.length; i++) {
                if(word.tiles[i]!=null)
                    board[word.row][col] = word.tiles[i];
                col++;
            }
        }
        else{
            int row = word.row;
            for (int i = 0; i < word.tiles.length; i++) {
                if(word.tiles[i]!=null)
                    board[row][word.col] = word.tiles[i];
                row++;
            }
        } numofwordsonboard++;
    }
}
