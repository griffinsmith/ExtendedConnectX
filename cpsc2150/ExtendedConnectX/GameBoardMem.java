package cpsc2150.ExtendedConnectX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Invariant: MIN_NUM_ROWS<=numRows<=MAX_NUM_ROWS
 * @Invariant: MIN_NUM_COLUMNS<=numColumns<=MAX_NUM_COLUMNS
 * @Invariant: MIN_NUM_TOWIN<=numToWin<=MAX_NUM_TOWIN
 *
 * Correspondence: numRows=numberRows
 * Correspondence: numColumns=numberColumns
 * Correspondence: numToWin=numberToWin
 */

public class GameBoardMem extends AbsGameBoard{

    //makes a map
    private Map<Character, List<BoardPosition>> mapBoard;

    //Game Board Dimensions and number needed to win
    private int numRows;
    private int numColumns;
    private int numToWin;

    //Constructor to make the map

	/**
     * @description [constructs a hashmap of <Character, List<BoardPosition>> to store the tokens and positions]
	 * @pre MIN_NUM_ROWS <= userNumRows <= MAX_NUM_ROWS [input is between max and min values]
	 * @pre MIN_NUM_COLUMNS <= userNumColumns <= MAX_NUM_COLUMNS [input is between max and min values]
	 * @pre MIN_NUM_TOWIN <= userNumToWin <= MAX_NUM_TOWIN [input is between max and min values]
	 * @post [uses the user input to set the gameboard specifications and rules
     *        and creates new gameboard and hashMap]
	 * @param userNumRows: [The number of rows inputted by user]
	 * @param userNumColumns: [The number of columns inputted by user]
	 * @param userNumToWin: [The number of tokens needed in a row inputted by user for a win]
	 */
    public GameBoardMem(int userNumRows, int userNumColumns, int userNumToWin)
	{
		numRows=userNumRows;
		numColumns=userNumColumns;
		numToWin=userNumToWin;
	// board is a 2D array of NUMROWSxNUMCOLUMNS
		mapBoard = new HashMap<>();
	}

    public void placeToken(char p, int c){
        //places the token in the c column 
        int rowNum;
        for(rowNum=0; rowNum<getNumRows(); rowNum++){
			if(whatsAtPos(new BoardPosition(rowNum,c)) == ' '){
                List<BoardPosition> positions=mapBoard.get(p);
                if(positions==null){
                        positions=new ArrayList<BoardPosition>();
                }
                positions.add(new BoardPosition(rowNum,c));
				mapBoard.put(p, positions);
                return;
			}
		}
    }

    public char whatsAtPos(BoardPosition pos){
        for(char key :  mapBoard.keySet()){
            List<BoardPosition> positions=mapBoard.get(key);
            for(int i=0; i<positions.size(); i++){
                BoardPosition position=positions.get(i);
                if(pos.getColumn()==position.getColumn() && pos.getRow()==position.getRow()){
                    return key;
                }
            }
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char p){
        List<BoardPosition> positions=mapBoard.get(p);
        if(positions!=null) {
            for (int i = 0; i < positions.size(); i++) {
                BoardPosition temp=positions.get(i);
                if (pos.equals(pos,temp)){
                    return true;
                }
            }
        }
        return false;
    }

	// Returns numRows private class variable
	public int getNumRows()
	{
		return numRows;
	}

	// Returns numColumns private class variable
	public int getNumColumns()
	{
		return numColumns;
	}

	// Returns numToWin private class variable
	public int getNumToWin()
	{
		return numToWin;
	}


}
