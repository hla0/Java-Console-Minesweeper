/**
 * 
 * @author brng8
 * @version 11.2.24
 * 
 */
public class Board {
	private Squares[][] Box;
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private boolean gameOver;
	
	/**
	 * Constructor method of the board.
	 * @param x the 2-D Array of Squares
	 */
	public Board(Squares[][] s) {
		gameOver = false;
		Box = s;
	}

	/**
	 * This method checks if the game has ended or not, and whether to update the board or not.
	 * @param a boolean that checks whether the game has ended or not.
	 */
	public void updateBoard(boolean a) {
		gameOver = a;
	}
	
	//used for testing
	public boolean getGameState() {
		return gameOver;
	}
	//public void printBoard(boolean gameOver) {
	/**
	 * Prints the current state of the board and will check if there is mine or a number in that square.
	 */
	public void printBoard() {
		System.out.print("    ");
		for (int i = 0; i < Box.length; i++) { 
			System.out.print(alphabet.charAt(i) + "   ");
		}
		System.out.print("\n  +");
		
		for (int i = 0; i < Box.length; i++) { 
			System.out.print("---+");
		}
		
		for (int i = 1; i <= Box.length; i++) {
			System.out.print("\n" + i);
			if (i < 10) {
				System.out.print(" ");
			}
			for (int y = 0; y < Box[i - 1].length; y++) {
				int mineCount = Box[y][i - 1].getCount();
				if (Box[y][i - 1].flipState()) {
					if (mineCount > 0) {
						System.out.print("| " + mineCount + " ");
					}
					else {
						System.out.print("| " + mineCount + " ");
					}
				}
				else if (Box[y][i - 1].returnFlagState()) {
					System.out.print("| P ");
					
				}
				//work on boolean that only prints mines when game is over.
				else if (!Box[y][i - 1].flipState() && gameOver) {
					if (Box[y][i - 1].returnMine()) {
						System.out.print("| * ");
					}
					else {
						System.out.print("|   ");
					}
				}
				else {
					System.out.print("|   ");
				}
				if (y == Box[i - 1].length - 1) {
					System.out.print("|"); 
				}
				
			}
			
			System.out.print("\n  +");
			
			for (int s = 0; s < Box.length; s++) { 
				System.out.print("---+");
			}
		}
		System.out.println();
	}
}
