package cpsc2150.ExtendedConnectX;

/**
 *
 *
 * @InitializationEnsures:
 *      [IGameBoard consists of only null characters and
 *      is numberRows x numberColumns]
 *		
 * @Defines:    numberRows: Z
 * 				numberColumns: Z
 * 				numberToWin: Z
 *
 * @Constraints:MIN_NUM_ROWS <= numberRows <= MAX_NUM_ROWS
 *              MIN_NUM_COLUMNS <= numberColumns <= MAX_NUM_COLUMNS
 *              MIN_NUMTOWIN <= numberToWin <= MAX_NUMTOWIN
 *
* @Invariant: MIN_NUM_ROWS<=numRows<=MAX_NUM_ROWS
* @Invariant: MIN_NUM_COLUMNS<=numColumns<=MAX_NUM_COLUMNS
* @Invariant: MIN_NUM_TOWIN<=numToWin<=MAX_NUM_TOWIN
*/

public interface IGameBoard
{
	// sizes for the dimensions of the board and the number to win variables
	public static final int MAX_NUM_ROWS = 100;
	public static final int MAX_NUM_COLUMNS = 100;
	public static final int MAX_NUM_TOWIN = 25;
	public static final int MAX_PLAYERS=10;

	public static final int MIN_NUM_ROWS = 3;
	public static final int MIN_NUM_COLUMNS = 3;
	public static final int MIN_NUM_TOWIN = 3;
	public static final int MIN_PLAYERS=2;
	

   /** 
	* @description determines if column is able to accept another token.
   * @param c [the column that the user wishes to place the token into]
   * @return boolean [true if there is an available space in the desired column,
   *     false if no available space in desired column]
   * @pre: 0<=c<=numberColumns
   * @post: checkIfFree()=true iff [column can hold another]
   * 		board=#board;
   *
   */
	default boolean checkIfFree(int c)
	{
		int rowNum;
		for(rowNum=0; rowNum<getNumRows(); rowNum++){
			if(whatsAtPos(new BoardPosition(rowNum,c)) == ' ')
			{
				//empty found
				return true;
			}
		}
		//no open spaces
		return false;
	}
	
  /** 
   * @description determines if the last token placed results in a win
   * @param c [the column that will be checked for the last token resulting in a
   *     win]
   * @return boolean (true if the last token placed results in a player winning
   *     the game, and false otherwise)
   * @pre:  0<=c<=numberColumns
   * 		checkIfFree(c)=true
   * @post: checkForWin()=true iff [last token placed resulted in a win in any orientation]
   * 		board=#board
   */		
	 default boolean checkForWin(int c){
		for(int i = 0; i < getNumRows(); i++)
		{
			BoardPosition pos = new BoardPosition(i, c);
			if(whatsAtPos(pos)!=' '){
				char currentToken=whatsAtPos(pos);
				// Checks if X or O are true for the horizontal check
				if(checkHorizWin(pos, currentToken))
				{
					return true;
				}
			}
		}
		for(int i = 0; i < getNumRows(); i++)
		{
			BoardPosition pos = new BoardPosition(i, c);
			if(whatsAtPos(pos)!=' '){
				char currentToken=whatsAtPos(pos);
				// Checks if X or O are true for the horizontal check
				if(checkDiagWin(pos, currentToken))
				{
					return true;
				}
				if(checkVertWin(pos, currentToken)){
					return true;
				}
			}
		}
		return false;
	}

  /**
   * @description determines if the last token placed results in a tie meaning the board is full 
   * @param: none
   * @return [true if the game board results in a tie game, otherwise
   *     false]
   * @pre: checkHorizWin=false
   * 		checkVertWin=false
   * 		checkDiagWin = false
   * @post: checkTie()==true iff [board is full]
   * 		board=#board
   */
	default boolean checkTie()
	{
		int numberColumns=getNumColumns();
		//loop through every column to see if there is an open spot
		for(int columnCounter = 0; columnCounter < numberColumns; columnCounter++)
		{
			if(checkIfFree(columnCounter)==true)
			{
				return false;
			}
		}
		// every space is full, so it's a tie
		return true;
	}

   /** 
	* @description places token p in column c on the game board
   * @param p [the character whose turn it is either X or O]
   * @param c [the column that the user desires to place the token into]
   * @return void
   * @pre: checkIfFree(int c)=true
   *       0<=c<=numberColumns
   * @post: [token of type p will be placed at the lowest available position in column c]
   * 
   */
	void placeToken(char p, int c);

	/**
	 *@description determines if the last token placed results in a horizontal win
	 * @param pos [the game board position of the last token placed that will be searched for a win]
	 * @param p [the character for user X or O, whose turn it is.]
	 * @return [returns true if the last token place resulted in the player winning
	 *     by getting 5 in a row horizontally, false otherwise]
	 * @pre: 0<=pos.getRow()<=numberRows
 	* 		0<=pos.getColumn()<=numberColumns
	 * @post: checkHorizWin()=true iff [pos results in there being a win horizontally]
	 * 		board=#board
	 */
	default boolean checkHorizWin(BoardPosition pos, char p)
	{
		//variables
		int tokens=-1;
		int r=pos.getRow();
		for(int i=pos.getColumn(); i<getNumColumns(); i++){
			//create BoardPosition
			BoardPosition tester=new BoardPosition(r,i);
			if(whatsAtPos(tester)==p){
				tokens++;
			}
			else{
				break;
			}
		}
		for(int i=pos.getColumn();i>=0; i--){
			//create BoardPosition
			BoardPosition tester=new BoardPosition(r,i);
			if(whatsAtPos(tester)==p){
				tokens++;
			}
			else{
				break;
			}
		}
		//enough in a row to win?
		if(tokens>=getNumToWin()){
			return true;
		}
		else{
			return false;
		}
	}


	/**
	 * @description determines if the last token placed results in a vertical win
	 * @param pos [the game board position of the last token placed]
	 * @param p [the character for user X or O, whose turn it is]
	 * @return boolean[true if the last token place resulted in the player p winning
	 *     by getting 5 in a row vertically, false otherwise]
	 * @pre: 0<=pos.getRow()<=numberRows
 	* 		0<=pos.getColumn()<=numberColumns
	 * @post: checkVertWin()==true iff [p has a vertical win, false otherwise]
	 *		board=#board
	 */
	default boolean checkVertWin(BoardPosition pos, char p){
		// Variable for highest token
		int highest = 0;
		// find highest token connected to pos
		for (int rows = highest; rows < getNumRows(); rows++) {
			BoardPosition tempPos=new BoardPosition(rows, pos.getColumn());
			if (whatsAtPos(tempPos) == p)
			{
				highest = rows;
				break;
			}
		}
		// Boolean to end loop if win is found
		boolean winner = true;
		// if highest token is enough to win
		if (highest - 1 + getNumToWin() < getNumRows()) {
			// for loop to search for win
			for (int i = highest; i < highest + getNumToWin(); i++) {
				// false if wrong character
				BoardPosition tempPos=new BoardPosition(i, pos.getColumn());
				if (whatsAtPos(tempPos) != p) {
					winner = false;
					break;
				}
			}
			// return true if nothing changes
			return winner;
		}
		//return false if a vert win is not found
		return false;
	}

	/**
   * @description determines if the last token placed results in a diagonal win
   * @param pos [the game board position of the last token placed]
   * @param p [the character for user X or O, whose turn it is.]
   * @return boolean[true if the last token place resulted in the player winning
   *     by getting 5 in a row diagonally, false otherwise]
   * @pre: 0<=pos.getRow()<=numberRows
 	* 		0<=pos.getColumn()<=numberColumns
   * @post: checkDiagWin()=true iff [pos results in a diagonal win]
   *		board=#board
   */
	default boolean checkDiagWin(BoardPosition pos, char p)
	{
		int tokens=0;
		int posRow=pos.getRow();
		int posColumn=pos.getColumn();
		int least=0;
		//checks down left
		while(posColumn>=least && posRow>=least){
			if(whatsAtPos(new BoardPosition(posRow,posColumn))==p){
				tokens++;
				posRow--;
				posColumn--;
			}
			else{
				break;
			}
		}
		//checks up right
		posRow=pos.getRow()+1;
		posColumn=pos.getColumn()+1;
		while(posColumn<getNumColumns() && posRow<getNumRows()){
			if(whatsAtPos(new BoardPosition(posRow,posColumn))==p){
				tokens++;
				posRow++;
				posColumn++;
			}
			else{
				break;
			}
		}
		//enough tokens to win?
		if(tokens>=getNumToWin()){
			return true;
		}
		//reset variables
		tokens=0;
		posRow=pos.getRow();
		posColumn=pos.getColumn();
		//checks up left
		while(posColumn>=least && posRow<getNumRows()){
			if(whatsAtPos(new BoardPosition(posRow,posColumn))==p){
				tokens++;
				posRow++;
				posColumn--;
			}
			else{
				break;
			}
		}
		//checks down right
		posRow=pos.getRow()-1;
		posColumn=pos.getColumn()+1;
		while(posColumn<getNumColumns() && posRow>=least){
			if(whatsAtPos(new BoardPosition(posRow,posColumn))==p){
				tokens++;
				posRow--;
				posColumn++;
			}
			else{
				break;
			}
		}
		//enough tokens to win?
		if(tokens>=getNumToWin()){
			return true;
		}
		//no diag win
		return false;
	}


  /**
   *@description determines what char is at position p and returns it 
   * @param pos [the game board position that will be checked to see what is
   *     there]
   * @return char [the char that is in the position pos of the gameboard]
   * @pre: 0<=pos.getRow()<=numberRows
 	* 		0<=pos.getColumn()<=numberColumns
   * @post: whatsAtPos()=[char at pos]
   * 		board=#board
   */
	char whatsAtPos(BoardPosition pos);

   /**
   *@description determines if player p is at position pos
   * @param pos [the game board position that will be checked to see if p is
   *     there]
   * @param p [the player we will be checking for]
   * @return boolean [true if p is at position pos, false otherwise]
   * @pre: 0<=pos.getRow()<=numberRows
   * 	   0<=pos.getColumn()<=numberColumns
   * @post: isPlayerAtPos()==true iff whatsAtPos(pos)=p
   * 		board=#board
   */
 default boolean isPlayerAtPos(BoardPosition pos, char p){
	if(whatsAtPos(pos)==p){
		return true;
	}
	return false;
}

	/**
	 * @description gets the number of rows in the gameboard
	 * @pre 
	 * @post getNumRows()=numberRows;
	 * @return [numberRows]
	 * 		board=#board
	 */
	int getNumRows();

	/**
	 * @description gets the number of column in the gameboard
	 * @pre 
	 * @post getNumColumns()=numberColumns;
	 * 		board=#board
	 * @return [numberColumns]
	 * 			
	 */
	int getNumColumns();

	/**
	 * @description gets the number of tokens in a row needed to win
	 * @pre 
	 * @post getNumToWin()=numberToWin;
	 * 		board=#board
	 * @return [numberToWin]
	 * 		
	 */
	int getNumToWin();
}
