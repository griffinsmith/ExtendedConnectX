package cpsc2150.ExtendedConnectX;
/**
 * @Invariant:
 *             BoardPosition (row, column)
 *             0 <= row <= numberRows
 *             0 <= column <= numberColumns
 */
public class BoardPosition {
    private int row;
    private int column;

    /**
     *
     * @param row
     * @param column
     * @pre:
     * @post: [board position is constructed, and is now a part of the game board with an X or O value]
     */
    public BoardPosition(int row, int column){
        this.row=row;
        this.column=column;
    }

    /**
     * @param: none
     * @return int (returns the row of the board position)
     * @pre:
     * @post: getRow()=row;
     */
    public int getRow(){
        return row;
    }

    /**
     * @param: none
     * @return int (returns the column of the board position)
     * @pre:
     * @post: getColumn()=column
     */
    public int getColumn(){
        return column;
    }

    /**
     *
     * @param first (the first board position we will be comparing)
     * @param second (the second board position we will be comparing)
     * @return boolean (true if the row anc column of these two positions are the same, false otherwise)
     * @pre: none
     * @post: equals()=true iff first.row=second.row && first.column=second.column
     */
    public boolean equals(BoardPosition first, BoardPosition second){
        if(first.row==second.row && first.column==second.column){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @param: row (the row of the position we are going to make a string)
     * @param: column (the column of the position we are going to make into a string)
     * @return: String (the formatted string of the position on the gameboard)
     * @pre: none
     * @post: toString()="<row>,<column>"
     */
    public String toString(int row, int column){
        String rowString=String.valueOf(row);
        String colString=String.valueOf(column);
        String finalS=rowString+","+colString;
        return finalS;
    }

}
