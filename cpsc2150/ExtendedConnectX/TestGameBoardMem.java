package cpsc2150.ExtendedConnectX;
import org.junit.*;

import static org.junit.Assert.*;
import java.util.Random;

public class TestGameBoardMem {
    // Constant Variables for the board size
    final private int inputNumRows = 5;
    final private int inputNumColumns = 5;
    final private int inputNumToWin = 4;

    // Private Constructor to initialize class
    private IGameBoard MakeGameBoard(int inputNumRows, int inputNumColumns, int inputNumToWin) {
        return new GameBoardMem(inputNumRows, inputNumColumns, inputNumToWin);
    }

    // Constructor Tests - 3
    @Test
    public void MakeGameBoard_Max_SizeBoard() {
        // Comparison 2D array initalized to inputNumRows and input Columns
        char[][] expectedBoard = new char[100][100];

        // Constructor for board
        IGameBoard board = MakeGameBoard(100, 100, 100);

        assertEquals(ToStringComparison(expectedBoard), board.toString());
    }
    @Test
    public void MakeGameBoard_Min_SizeBoard() {
        // Comparison 2D array initalized to inputNumRows and input Columns
        char[][] expectedBoard = new char[3][3];

        // Constructor for board
        IGameBoard board = MakeGameBoard(3, 3, 3);
        assertEquals(ToStringComparison(expectedBoard), board.toString());
    }

    @Test
    public void MakeGameBoard_Full_XY()
    {
        // Comparison 2D array initialized to inputNumRows and input Columns
        char[][] expectedBoard = new char[inputNumRows][inputNumColumns];

        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        char token = 'X';
        // Fill Whole Board With XY alternating
        for(int numRow = 0; numRow < inputNumRows; numRow++) {
            for(int numCol = 0; numCol < inputNumColumns; numCol++) {
                // changes token
                if(numRow % 2 == 0){
                    token = 'X';
                }
                else {
                    token = 'O';
                }
                // Places tokens on both boards
                board.placeToken(token,numCol);
                expectedBoard[numRow][numCol] = token;
            }
        }
        // check that boards are filled with X and O
        assertEquals(ToStringComparison(expectedBoard),board.toString());
    }


    // Check If Free Tests - 3

    @Test
    public void test_checkIfFree_LeftColumn() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // sets left most row full with tokens
        for (int i = 0; i < inputNumRows; i++) {
            board.placeToken('X', 0);
        }
        //should be false because nothing in column 0 should be free
        assertFalse(board.checkIfFree(0));
    }

    @Test // Return False
    public void test_checkIfFree_Entire_Board_Fill()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //fills the whole board with X token
        for(int i = 0; i < inputNumColumns; i++) {
            for(int j = 0; j < inputNumRows; j++) {
                board.placeToken('X', j);
            }
        }
        // checkIfFree should be false for every column
        for(int column = 0; column < inputNumColumns; column++){
            assertFalse(board.checkIfFree(column));
        }
    }

    @Test
    public void test_checkIfFree_Empty_Board()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // Checks that every column is empty
        for(int columns = 0; columns < inputNumRows; columns++) {
            assertTrue(board.checkIfFree(columns));
        }

    }

    @Test // Return True
    public void checkHorizWin_FullBoard(){
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumColumns);
        // fills board with tokens
        for(int col = 0; col < inputNumColumns; col++){
            for(int row = 0; row < inputNumRows; row++){
                board.placeToken('X', col);
            }
        }
        // Checking top right position for horizontal win
        //should return true since the board is full
        BoardPosition pos=new BoardPosition(inputNumColumns-1,inputNumRows-1);
        assertTrue(board.checkHorizWin(pos, 'X'));
    }
    //
    @Test // Return True
    public void checkHorizWin_OneShort_ToWin() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // Fills board Horizontally with one less than the number to win in a row
        for(int col = 0; col < inputNumToWin - 2; col++) {
            for(int row = 0; row < inputNumRows; row++) {
                board.placeToken('X', col);
            }
        }
        // every position should return false because there is only
        // one less than numtowin in a row horizontally
        for(int col = 0; col < inputNumColumns; col++) {
            for(int row = 0; row < inputNumRows; row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertFalse(board.checkHorizWin(pos, 'X'));
            }
        }
    }

    @Test // Return True
    public void checkHorizWin_BottomRow_Win()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // Sets Bottom row to be all Xs
        for(int col = 0; col < inputNumColumns; col++){
            board.placeToken('X', col);
        }
        // checks each position in bottom row
        //should all be true because the whole bottom row is Xs
        for(int col = 0; col < inputNumColumns; col++) {
            BoardPosition pos=new BoardPosition(0,col);
            assertTrue(board.checkHorizWin( pos, 'X'));
        }
    }

    @Test // Return False
    public void checkHorizWin_Empty() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //loop through every position in board
        //should be false since no tokens have been placed
        for(int row = 0; row < inputNumRows; row++) {
            for(int col = 0; col < inputNumColumns; col++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertFalse(board.checkHorizWin(pos, 'X'));
            }
        }
    }

    @Test // Returns true
    public void checkVertWin_FullBoard(){
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //fills board with Xs
        for(int row = 0; row < inputNumRows; row++){
            for(int col = 0; col < inputNumColumns; col++){
                board.placeToken('X', col);
            }
        }
        for(int row = 0; row < inputNumRows; row++){
            for(int col = 0; col < inputNumColumns; col++){
                BoardPosition pos=new BoardPosition(row,col );
                assertTrue(board.checkVertWin(pos,'X'));
            }
        }
    }


    @Test // Returns true
    public void checkVertWin_FirstColumn_Win() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // Fills first columns
        for(int i = 0; i < inputNumToWin; i++) {
            board.placeToken('X', 0);
        }
        // Checking first column for win
        //should be true since first column is full
        for(int row=0; row<board.getNumRows(); row++) {
            BoardPosition pos = new BoardPosition(0, 0);
            assertTrue(board.checkVertWin(pos, 'X'));
        }
    }

    @Test // Returns False
    public void checkVertWin_OneLess_NumToWin() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // place tokens in a row veritcally, only one less than numToWin in a row
        for(int row = 0; row < inputNumToWin - 2; row++) {
            for(int col = 0; col < inputNumColumns; col++) {
                board.placeToken('X', col);
            }
        }
        //should be false because there are only one less than num to win in a row
        for(int col = 0; col < inputNumColumns; col++) {
            BoardPosition pos=new BoardPosition(0,col);
            assertFalse(board.checkVertWin(pos, 'X'));
        }
    }
    //
    @Test // Returns true
    public void checkVertWin_Empty() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // Check every position for a win
        //should be false since there are no tokens placed
        for(int row = 0; row < inputNumRows; row++) {
            for (int col = 0; col < inputNumColumns; col++) {
                BoardPosition pos=new BoardPosition(row,col);
                assertFalse(board.checkVertWin(pos, 'X'));
            }
        }
    }


    @Test
    public void checkDiagWin_BottomLeft_UpRight() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //fills up board with tokens
        for(int row = 0; row < inputNumRows; row++){
            for(int col = 0; col < inputNumColumns; col++){
                board.placeToken('X', col);
            }
        }
        // Checks Bottom Left Up to right for Win
        BoardPosition pos=new BoardPosition(0,0);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void checkDiagWin_TopLeft_BottomRight() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //board is full with tokens
        for(int row = 0; row < inputNumRows; row++){
            for(int col = 0; col < inputNumColumns; col++){
                board.placeToken('X', col);
            }
        }
        // Checks top left to bottom right diagonally for win
        //tests the top left position in the board
        BoardPosition pos=new BoardPosition(board.getNumRows()-1,0);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void checkDiagWin_TopRight_BottomLeft() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //board is full with tokens
        for(int row = 0; row < inputNumRows; row++){
            for(int col = 0; col < inputNumColumns; col++){
                board.placeToken('X', col);
            }
        }
        // Checks top right to bottom left diagonally for win
        //tests the top right position in the board
        BoardPosition pos=new BoardPosition(board.getNumRows()-1,board.getNumColumns()-1);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void checkDiagWin_BottomRight_TopLeft()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //board is full with tokens
        for(int row = 0; row < inputNumRows; row++){
            for(int col = 0; col < inputNumColumns; col++){
                board.placeToken('X', col);
            }
        }
        // Checks bottom right to top left diagonally for win
        //tests the bottom right position in the board
        BoardPosition pos=new BoardPosition(0,board.getNumColumns()-1);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }
    //
    @Test
    public void checkDiagWin_FullBoard_TestMiddlePos()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //board is full with tokens
        for(int row = 0; row < inputNumRows; row++){
            for(int col = 0; col < inputNumColumns; col++){
                board.placeToken('X', col);
            }
        }
        //tests the middle position in the board
        BoardPosition pos=new BoardPosition((board.getNumRows()/2)+1,(board.getNumColumns()/2)+1);
        // Checks middle of the grid for a win
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void checkDiagWin_WinNUm_EqualBoardSize_FullWin()
    {
        // Constructor for board
        //number to win equals the board size
        IGameBoard board = MakeGameBoard(10,10,10);
        // fills board with same token
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('X', col);
            }
        }
        // Checks Bottom left position to top right direction for win
        BoardPosition pos=new BoardPosition(0,0);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void checkDiagWin_CheckPattern_NoWin() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        char token = 'X';
        // fills board like a checkerboard
        for(int col = 0; col < board.getNumColumns(); col++){
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken(token, col);
                // Alternating tokens
                if(token == 'X') {
                    token = 'O';
                }
                else if (token=='O'){
                    token = 'Y';
                }
                else if(token=='Y'){
                    token='Q';
                }
                else if(token=='Q'){
                    token='W';
                }
                else{
                    token='X';
                }
            }
        }
        //checks every position on the board for a digonal win
        //should be false because the board is filled like a checkerboard
        for(int col = 0; col < board.getNumColumns(); col++) {
            for (int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row,col);
                assertFalse(board.checkDiagWin(pos, 'X'));
                assertFalse(board.checkDiagWin(pos, 'O'));
            }
        }
    }

    @Test // Return True
    public void checkTie_Full_Board() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //fill board with Xs and Ys
        char token = 'X';
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken(token, col);
                if(token=='X'){
                    token='Y';
                }
                else{
                    token='X';
                }
            }
        }
        //should be true since the board is full
        assertTrue(board.checkTie());
    }

    @Test // Return False
    public void checkTie_EntireBoardFilled_ExceptOneTopElement()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //fills board full except for one position(the top right)
        char token='X';
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                if(col!=board.getNumColumns()-1&&row!=board.getNumRows()-1) {
                    board.placeToken(token, col);
                    if(token=='X'){
                        token='Y';
                    }
                    else{
                        token='X';
                    }
                }
            }
        }
        //should be false since there is still one open position
        assertFalse(board.checkTie());
    }

    @Test
    public void checkTie_Missing_RightColumn() {
        // Constructor for board
        char token='X';
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // fills the whole board except for the last column
        for(int col = 0; col < board.getNumColumns()-1; col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken(token, col);
                if(token=='X'){
                    token='Y';
                }
                else{
                    token='X';
                }
            }
        }
        //should be true since there is one column open
        assertFalse(board.checkTie());
    }

    @Test
    public void checkTie_Max_SizeBoard_Full() {
        // Constructor for board
        char token='X';
        IGameBoard board = MakeGameBoard(100, 100,25);
        // fills the whole board
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken(token, col);
                if(token=='X'){
                    token='Y';
                }
                else{
                    token='X';
                }
            }
        }
        //should return true since the maximum board size is filled
        assertTrue(board.checkTie());
    }

    // WhatsAtPos Tests
    @Test
    public void whatsAtPos_BottomLeftX()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // fills the whole board except for the last column
        for(int col = 0; col < board.getNumColumns()-1; col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('X', col);
            }
        }
        //tests the bottom left position on the board to be X
        BoardPosition pos=new BoardPosition(0,0);
        assertEquals( 'X',board.whatsAtPos(pos));
    }

    @Test
    public void whatsAtPos_Top_Right() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //fills board of Xs except top right position is O
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                if(col!=board.getNumColumns()-1&&row!=board.getNumRows()-1) {
                    board.placeToken('X', col);
                }
                else{
                    board.placeToken('O', col);
                }
            }
        }
        //pos is top right board position, should be O
        BoardPosition pos=new BoardPosition(board.getNumRows()-1,board.getNumColumns()-1);
        assertEquals('O', board.whatsAtPos(pos));
    }


    @Test
    public void whatsAtPos_EmptyBoard() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //board is empty
        //go through every position to make sure ' ' is at every pos
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertEquals(' ',board.whatsAtPos(pos));
            }
        }
    }

    @Test
    public void whatsAtPos_Max_Size_FullBoard() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(100, 100, 25);
        // fills board with X
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('X', col);
            }
        }
        //checks every position on the max board
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertEquals('X',board.whatsAtPos(pos));
            }
        }
    }

    @Test
    public void whatsAtPos_Min_Size_FullBoard() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(3, 3, 3);
        // fills board with X
        for(int col = 0; col < board.getNumColumns()-1; col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('X', col);
            }
        }
        //checks every position on the minimum sized board
        for(int col = 0; col < board.getNumColumns()-1; col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertEquals('X',board.whatsAtPos(pos));
            }
        }
    }

    @Test
    public void isPlayerAtPos_BottomLeftX()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // fills the whole board except for the last column
        for(int col = 0; col < board.getNumColumns()-1; col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('X', col);
            }
        }
        //tests the bottom left position on the board to be X
        BoardPosition pos=new BoardPosition(0,0);
        assertTrue(board.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void isPlayerAtPos_Top_Right() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //fills board of Xs except top right position is O
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                if(col!=board.getNumColumns()-1&&row!=board.getNumRows()-1) {
                    board.placeToken('X', col);
                }
                else{
                    board.placeToken('O', col);
                }
            }
        }
        //pos is top right board position, should be O
        BoardPosition pos=new BoardPosition(board.getNumRows()-1,board.getNumColumns()-1);
        assertTrue(board.isPlayerAtPos(pos, 'O'));
    }

    @Test
    public void isPlayerAtPos_EmptyBoard() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //board is empty
        //go through every position to make sure ' ' is at every pos
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertFalse(board.isPlayerAtPos(pos, 'X'));
            }
        }
    }

    @Test
    public void isPlayerAtPos_Max_Size_FullBoard() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(100, 100, 25);
        // fills board with X
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('X', col);
            }
        }
        //checks every position on the max board
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertTrue(board.isPlayerAtPos(pos,'X'));
            }
        }
    }

    @Test
    public void isPlayerAtPos_Min_Size_FullBoard() {
        // Constructor for board
        IGameBoard board = MakeGameBoard(3, 3, 3);
        // fills board with X
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('X', col);
            }
        }
        //checks every position on the max board
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertTrue(board.isPlayerAtPos(pos,'X'));
            }
        }
    }
    @Test
    public void placeToken_Full_Board()
    {
        // Constructor for board
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        // fills board with X
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('X', col);
            }
        }
        // Checks for Xs all over board
        //checks every position on the board
        //checks that every token was placed by calling isPlayerAtPos and whatsAtPos
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertTrue(board.isPlayerAtPos(pos,'X'));
                assertEquals(board.whatsAtPos(pos), 'X');
            }
        }
    }

    @Test
    public void placeToken_newBoard() {
        // Constructor
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //checks every position on the board
        //checks that no token was placed by calling isPlayerAtPos and whatsAtPos
        board.placeToken('X', 1);
        BoardPosition pos=new BoardPosition(0, 1);
        assertTrue(board.isPlayerAtPos(pos,'X'));
        assertEquals( 'X',board.whatsAtPos(pos));
    }

    @Test
    public void placeToken_Full_BoardXYZWQ()
    {
        // Constructor
        IGameBoard board = MakeGameBoard(inputNumRows, inputNumColumns, inputNumToWin);
        //array for all player tokens
        char[] players = new char[]{'X', 'Y', 'Z', 'W', 'Q'};
        // fills board with players (1 player each column)
        for(int row = 0; row < inputNumRows; row++) {
            for(int col = 0; col < inputNumColumns; col++) {
                board.placeToken(players[col], col);
            }
        }
        // checks for correct tokens
        for(int row = 0; row < inputNumRows ; row++) {
            for(int col = 0; col < inputNumColumns; col++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertEquals(board.whatsAtPos(pos), players[col]);
            }
        }
    }

    @Test
    public void placeToken_MaxBoard()
    {
        // Constructor
        IGameBoard board = MakeGameBoard(100, 100, 25);

        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('O', col);
            }
        }
        // Checks for Ys all over max board
        //checks every position on the max board
        //checks that every token was placed by calling isPlayerAtPos and whatsAtPos
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertTrue(board.isPlayerAtPos(pos,'O'));
                assertEquals(board.whatsAtPos(pos), 'O');
            }
        }
    }

    @Test
    public void placeToken_MinBoard()
    {
        // Constructor
        IGameBoard board = MakeGameBoard(3, 3, 3);

        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                board.placeToken('O', col);
            }
        }
        // Checks for Ys all over min board
        //checks every position on the min board
        //checks that every token was placed by calling isPlayerAtPos and whatsAtPos
        for(int col = 0; col < board.getNumColumns(); col++) {
            for(int row = 0; row < board.getNumRows(); row++) {
                BoardPosition pos=new BoardPosition(row, col);
                assertTrue(board.isPlayerAtPos(pos,'O'));
                assertEquals(board.whatsAtPos(pos), 'O');
            }
        }
    }

    private String ToStringComparison(char[][] expected) {
        IGameBoard board = MakeGameBoard(expected.length, expected[0].length, inputNumToWin);
        final int DOUBLEDIGITCOLUMNS=10;
        //initialize a stringBuilder
        StringBuilder boardString = new StringBuilder();
        //For loop to create the top column of gameboard with the columns numbered
        for(int i = 0; i < board.getNumColumns(); i++){
            boardString.append("| ");
            if(i<DOUBLEDIGITCOLUMNS){
                boardString.append(" ");
            }
            boardString.append(i);
        }
        //create new line
        boardString.append("|\n");
        //nested for loop to go through every element
        for(int rowNum = board.getNumRows()-1; rowNum >=0; rowNum--){
            boardString.append("| ");
            for(int colNum = 0; colNum < board.getNumColumns(); colNum++){
                //creates BoardPosition to see what value is there
                BoardPosition position=new BoardPosition(rowNum,colNum);
                if(expected[rowNum][colNum]=='\0'){
                    //position is empty
                    boardString.append("  ");
                }
                else{
                    //position is not empty
                    boardString.append(expected[rowNum][colNum]);
                    boardString.append(" ");
                }
                boardString.append("| ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

}