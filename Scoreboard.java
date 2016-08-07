/**
 *
 *@author Yalli
 *Version 11.2.23
 */
public class Scoreboard {

	private static int win;
	private static int loss;
	private boolean gameOver = false;
	private int numOfSquares;
	private int numOfMines;

	/**
	 *  The constructor will evidently make the grid
	 * @param numMines the number of mines the game is going to have.
	 * @param numSquares the number of squares that the game is going to have.
	 */
	public Scoreboard(int numMines, int numSquares) {
		numOfMines = numMines;
		numOfSquares = numSquares;
	}
	//might place in a different class
	public void checkWin(int a) {
		if (a + numOfMines == numOfSquares) {
			recordWin();
		}
	}
	
	/**
	 * @return the number of wins you have
	 */
	public int getWin() {
		return win;
	}
	
	/**
	 * @return returns the number of losses that you have
	 */
	public int getLoss() {
		return loss;
	}
	
	/**
	 * records the win, adds 1 to win.
	 */
	public void recordWin() {
		win++;
		gameOver = !gameOver;
	}
	
	/**
	 *  records a loss, adds 1 to loss.
	 */
	public void recordLoss() {
		loss++;
		gameOver = !gameOver;
	}
	
	//might edit
	/**
	 *  prints out the score, "Wins : x + \t Loss : y
	 */
	public void printScore() {
		System.out.println("Wins : " + win + "\t Loss : " + loss );
	}
	
	/**
	 * ends the gameOver boolean by turning it false,
	 * stops the triggering of the gameOver condition only if it was changed to true
	 */
	public void flipGameState() {
		if (gameOver) {
			gameOver = !gameOver;
		}
	}
	
	/**
	 * @return the current state of the game.
	 */
	public boolean gameState() {
		return gameOver;
	}
}
