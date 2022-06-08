package cpsc2150.ExtendedConnectX;

import java.util.Scanner;
import java.util.ArrayList;

public class GameScreen{
	
/**
 * main will deal with all interaction with the user. X will always go first. 
 * Player X will enter a column and, if valid, it the token will be placed.
 * Once this turn has completed, player O will go through the same process.
 * This will continue until a player has won by placing 5 tokens in a row,
 * or the game has resulted in a tie. Then the user will be able to play again
 * if they so desire.
 * 
 */
	/**
	 * @description deals with all of the user interaction. Makes sure the game is played correctly and 
	 * error checks for flaws in the user input
	 * @param args [not used]
	 * @pre whoseTurn='X'[player X goes first]
	 * 		[realBoard is a 6x9 char array]
	 * @post none
	 */
	public static void main(String[] args){
		//takes the user input
		Scanner input=new Scanner(System.in);

		IGameBoard realBoard;
		//stays true until user does not want to play again
		boolean keepPlaying;

		//variables for all of the input by the user
		int columnPicked;
		int numPlayers;
		int userNumRows;
		int userNumColumns;
		int userNumToWin;

		//character that is read to recieve input from user specifying if they would like to play again
		char playAgain;
		char gameType;

		//List for all of the players tokens
		ArrayList<Character> whichTokens;

		//big loop that continues until the user specifies they do not want to play again
		do{
			//to determine the number of players that the user wants
			do {
				System.out.println("How many players?");
				numPlayers = input.nextInt();
				//error checking
				if(numPlayers > IGameBoard.MAX_PLAYERS)
				{
					System.out.println("Must be 10 players or fewer");
				}

				if(numPlayers < IGameBoard.MIN_PLAYERS)
				{
					System.out.println("Must be at least 2 players");
				}
			}while(numPlayers > IGameBoard.MAX_PLAYERS || numPlayers < IGameBoard.MIN_PLAYERS );

			//Initializes whichTokens
			whichTokens=new ArrayList<>();

			//tempToken variable
			char tempToken;

			//for loop for each player
			for(int count = 0; count < numPlayers; count++)
			{
				do{
					System.out.println("Enter the character to represent player " + (count+1));
					// makes token input upperCase
					tempToken = Character.toUpperCase(input.next().charAt(0));
					// if token is already being used by another player 
					if(whichTokens.contains(tempToken))
					{
						System.out.println(tempToken + " is already taken as a player token!");
					}
				}while(whichTokens.contains(tempToken));
				// Adds token to the list
				whichTokens.add(tempToken);
			}		
			
			// loop for User input specifying the desired number of rows
			do{
				System.out.println("How many rows should be on the board?");
				userNumRows = input.nextInt();
				// error check: rows must be less than maximum number of rows
				if(userNumRows > IGameBoard.MAX_NUM_ROWS){
					System.out.println("Can have at most " + IGameBoard.MAX_NUM_ROWS + " rows");
				}
				// error check: rows must be greater than minimum number of rows
				if(userNumRows < IGameBoard.MIN_NUM_ROWS ){
					System.out.println("Must have at least " + IGameBoard.MIN_NUM_ROWS + " rows.");
				}
			}while(userNumRows > IGameBoard.MAX_NUM_ROWS || userNumRows < IGameBoard.MIN_NUM_ROWS);	
			
			// loop for User iput specifying the desired number of columns
			do{
				System.out.println("How many columns should be on the board?");
				userNumColumns = input.nextInt();
				// error check: rows must be less than maximum number of rows
				if (userNumColumns > IGameBoard.MAX_NUM_COLUMNS){
					System.out.println("Can have at most " + IGameBoard.MAX_NUM_COLUMNS + " columns");
				}
				// error check: rows must be greater than minimum number of rows
				if (userNumColumns < IGameBoard.MIN_NUM_COLUMNS){
					System.out.println("Must have at least " + IGameBoard.MIN_NUM_COLUMNS + " columns.");
				}
			}while(userNumColumns > IGameBoard.MAX_NUM_COLUMNS || userNumColumns < IGameBoard.MIN_NUM_COLUMNS );

			//loop for user input specifying the desired number in a row to win
			do
			{
				System.out.println("How many in a row to win?");
				userNumToWin = input.nextInt();
				// error check: input must be less than maximum number to win 
				if (userNumToWin > IGameBoard.MAX_NUM_TOWIN){
					System.out.println("Can have at most " + IGameBoard.MAX_NUM_TOWIN + " in a row to win");
				}
				// error check: input must be more than minimum number to win 
				if (userNumToWin < IGameBoard.MIN_NUM_TOWIN){
					System.out.println("Must have at least " + IGameBoard.MIN_NUM_TOWIN + " in a row to win.");
				}
				// error check: input must be less than number of Columns and number of Rows
				else if(userNumToWin > userNumColumns && userNumToWin > userNumRows){
					System.out.println("Cannot have a win condition greater than the number of Rows and Columns");
				}
			}while(userNumToWin < IGameBoard.MIN_NUM_TOWIN || userNumToWin > IGameBoard.MAX_NUM_TOWIN || userNumToWin > userNumColumns && userNumToWin > userNumRows);


			// loops for valid user input specifying the disred gametype
			do
			{
				System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
				gameType = Character.toUpperCase(input.next().charAt(0));
				// error check: input must be M or F
				if(gameType != 'F' && gameType != 'M')
				{
					System.out.println("Please enter F or M");
				}
			}while(gameType != 'F' && gameType != 'M');

			// constructs correct game type
			if(gameType == 'M')
			{
				realBoard=new GameBoardMem(userNumRows, userNumColumns, userNumToWin);

			}
			else
			{
				realBoard=new GameBoard(userNumRows, userNumColumns, userNumToWin);

			}

			//initialize the number of turns to be 0
			int numberTurns=0;
			
			//Initializes keepPlaying
			keepPlaying=true;

			//prints empty, initialized board
			System.out.println(realBoard.toString());

			while(keepPlaying){
				//resets to player 1 token if needed
				if(numberTurns==whichTokens.size()){
					numberTurns=0;
				}
				//loop for user input specifying the desired column they would like to place their token
				do{
				System.out.println("Player " + whichTokens.get(numberTurns) +" , what column do you want to place your marker in?");
				columnPicked = input.nextInt();
				//while loop for input to be validated
				while(columnPicked>=realBoard.getNumColumns() || !realBoard.checkIfFree(columnPicked)|| columnPicked<0){
					if (columnPicked >= realBoard.getNumColumns()){
						System.out.println("Column cannot be greater than " + (realBoard.getNumColumns() - 1));
					}
					else if (columnPicked < 0){
						System.out.println("Column cannot be less than 0");
					}
					else{
						System.out.println("Column is full");
					}
					System.out.println("Player " + whichTokens.get(numberTurns) +" , what column do you want to place your marker in?");
					columnPicked = input.nextInt();
				}
				}while(columnPicked >= realBoard.getNumColumns() || columnPicked < 0 || !realBoard.checkIfFree(columnPicked));

				//places token because player entered valid column
				realBoard.placeToken(whichTokens.get(numberTurns), columnPicked);

				//prints updated gameboard
				System.out.println(realBoard.toString());

				//checks for win and ends the game if there is a winner
				if(realBoard.checkForWin(columnPicked)){
						System.out.println("Player " + whichTokens.get(numberTurns) + " Won!");
						keepPlaying = false;
					}
					//checks for tie and ends game if there is a tie
					if(realBoard.checkTie() && keepPlaying)
					{
						System.out.println("The game has ended in a tie!");
						keepPlaying= false;
					}
					//increases numberTurns to determine whose turn it is
					numberTurns++;
				}
			//determines if user would like to play again
			System.out.println("Would you like to play again? Y/N");	
			playAgain = input.next().charAt(0);
			//make sure input from player is valid
			while(playAgain!='n' && playAgain!='N' && playAgain!='y' && playAgain!='Y'){
				System.out.println("Answer must be Y or N");
				System.out.println("Would you like to play again? Y/N");
				playAgain = input.next().charAt(0);
			}
			}while(playAgain=='Y' || playAgain=='y');
		}
}
