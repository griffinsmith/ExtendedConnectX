default: cpsc2150/ExtendedConnectX/GameScreen.java cpsc2150/ExtendedConnectX/GameBoard.java clean
	javac cpsc2150/ExtendedConnectX/GameScreen.java cpsc2150/ExtendedConnectX/GameBoard.java
run:
	java cpsc2150/ExtendedConnectX/GameScreen cpsc2150/ExtendedConnectX/GameBoard
clean:
	rm -f cpsc2150/ExtendedConnectX/*.class
test:
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/ExtendedConnectX/TestGameBoard.java cpsc2150/ExtendedConnectX/TestGameBoardMem.java cpsc2150/ExtendedConnectX/GameScreen.java cpsc2150/ExtendedConnectX/IGameBoard.java cpsc2150/ExtendedConnectX/GameBoard.java cpsc2150/ExtendedConnectX/GameBoardMem.java cpsc2150/ExtendedConnectX/AbsGameBoard.java
testGB:
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.ExtendedConnectX.TestGameBoard
testGBMem:
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.ExtendedConnectX.TestGameBoardMem