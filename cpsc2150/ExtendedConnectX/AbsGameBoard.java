package cpsc2150.ExtendedConnectX;

public abstract class AbsGameBoard implements IGameBoard{
	public final int DOUBLEDIGITCOLUMNS=10;

    @Override
    public String toString()
    {
        //initialize a stringBuilder
		StringBuilder boardString = new StringBuilder();
        //For loop to create the top column of gameboard with the columns numbered
		for(int i = 0; i < getNumColumns(); i++){
			boardString.append("| ");
			if(i<DOUBLEDIGITCOLUMNS){
				boardString.append(" ");
			}
				boardString.append(i);
		}
        //create new line
		boardString.append("|\n");
        //nested for loop to go through every element 
		for(int rowNum = getNumRows()-1; rowNum >=0; rowNum--){
            boardString.append("| ");
			for(int colNum = 0; colNum < getNumColumns(); colNum++){
                //creates BoardPosition to see what value is there
				BoardPosition position=new BoardPosition(rowNum,colNum);
				if(whatsAtPos(position)==' '){
                    //position is empty
					boardString.append("  ");
				}
				else{
                    //position is not empty
					boardString.append(whatsAtPos(position));
					boardString.append(" ");
				}
				boardString.append("| ");
			}
			boardString.append("\n");
		}
		return boardString.toString();
}
}
