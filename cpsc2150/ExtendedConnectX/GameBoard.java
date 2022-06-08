package cpsc2150.ExtendedConnectX;

/**
 * @Invariant: MIN_NUM_ROWS<=numRows<=MAX_NUM_ROWS
 * @Invariant: MIN_NUM_COLUMNS<=numColumns<=MAX_NUM_COLUMNS
 * @Invariant: MIN_NUM_TOWIN<=numToWin<=MAX_NUM_TOWIN
 *
 * Correspondence: numRows=numberRows
 * Correspondence: numColumns=numberColumns
 * Correspondence: numToWin=numberToWin
 */

public class GameBoard extends AbsGameBoard{

	// 2D array to hold the gameboard
	private char [][] board;

	// dimensions that will be used to construct gameboard
	private int numRows;
	private int numColumns;
	private int numToWin;


	/**
	 * @description [constructs a 2D array of character initialized to a space character]
       * @pre: none
       * 
       * @post: [a gameboard of the correct dimensions (numRowsxnumColumns) will be made and
       * every element initialized with ' ']
       */
	public GameBoard(int userNumRows, int userNumColumns, int userNumToWin)
	{
		numRows=userNumRows;
		numColumns=userNumColumns;
		numToWin=userNumToWin;
	// board is a 2D array of numRowsxnumColumns
		board = new char[numRows][numColumns];
		for(int x=0; x<numRows; x++){
			for(int y=0; y<numColumns; y++){
				board[x][y]=' ';
			}
		}
	}


	public void placeToken(char p, int c){

		for(int i=0; i<getNumRows(); i++){
			if(whatsAtPos(new BoardPosition(i,c) ) == ' ')
			{
				board[i][c] = p;
				break;
			}
		}
	}

	// Returns the value at pos
	public char whatsAtPos(BoardPosition pos)
	{
		char empty=' ';
		int r=pos.getRow();
		int c=pos.getColumn();
		// Return empty if pos is empty
		if(board[r][c] == '\0')
		{
			return empty;
		}
		// Return char in board

		return board[r][c];
	}


	// Returns numRows
	public int getNumRows()
	{
		return numRows;
	}

	// Returns numColumns
	public int getNumColumns()
	{
		return numColumns;
	}

	// Returns numToWin
	public int getNumToWin()
	{
		return numToWin;
	}
}