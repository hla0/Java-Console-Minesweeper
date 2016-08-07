/**
 * 
 * @author yallim
 * @version 11.2.24
 * 
 */
public class Grid {
	private Squares[][] Box;
	private int row;
	private int col;
	private int numOfMine;
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private static final double mineConstant  = 0.16;
	private Scoreboard score;
	private Board board;
	private boolean error = false;
	private int numOfSquares;
	
	/**
	 * It constructs the Grid
	 * @param row1 is the row of the board
	 * @param col1 is the column of the board
	 */
	
	//for testing purposes
	public Grid () {
		row = 8;
		col = 8;
		numOfMine = getNumOfMine();
		Box = new Squares[row][col];
		numOfSquares = row * col;
		score = new Scoreboard( getNumOfMine(), numOfSquares);
		board = new Board(Box);
		for (int x = 0;x < row; x++) {
			for (int y = 0; y < col; y++) {
				String columnLetter = "" + alphabet.charAt(x);
				int rownumber = y + 1;
				String location = columnLetter + rownumber;
				Box[x][y] = new Squares(location);
			}
		}
	}
	
	/**
	 * sets the row and col fields to be row1 and col1 and gets the number of mines to 
	 * be placed on the grid
	 * Box is referenced to a new 2d array of squares
	 * score is referenced to a new Scoreboard and the grid is assembled
	 * @param row1 the amount of rows the grid will have
	 * @param col1 the amount of cols the grid will have
	 */
	public Grid(int row1, int col1) {
		row = row1;
		col = col1;
		numOfMine = getNumOfMine();
		Box = new Squares[row][col];
		//constructor was bugged for a bit setting row as column and column as row.
		numOfSquares = row * col;
		score = new Scoreboard( getNumOfMine(), numOfSquares);
		assembleGrid();
		board = new Board(Box);
		//uncomment the bottom after testing.
		processPrintBoard();
	}
	
	/**
	 * counts the number of flipped squares on the grid
	 * @return the number of flipped squares on the grid
	 */
	public int numOfFlipped() {
		int count = 0;
		for (int x = 0;x < row; x++) {
			for (int y = 0; y < col; y++) {
				if (Box[x][y].flipState()) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * constructs the squares in the grid as well as places the mines on the field and
	 * assigns the count variable to each square
	 */
	public void assembleGrid() {
		for (int x = 0;x < row; x++) {
			for (int y = 0; y < col; y++) {
				String columnLetter = "" + alphabet.charAt(x);
				int rownumber = y + 1;
				String location = columnLetter + rownumber;
				Box[x][y] = new Squares(location);
			}
		}
		placeMines();
		for (int x = 0;x < row; x++) {
			for (int y = 0; y < col; y++) {
				assignCount(Box[x][y]);
			}
		}
	}
	
	/**
	 * processes the game state and prints to the console the current state of the game
	 */
	public void processPrintBoard() {
		score.checkWin(numOfFlipped());
		boolean gameOver = getGameState();
		board.updateBoard(gameOver);
		score.printScore();
		board.printBoard();
		//don't use
		//board.printBoard(score.gameState());
	}

	/**
	 * calls the flipGameState method which turns off the gameOver boolean
	 */
	public void changeGameState() {
		score.flipGameState();
	}
	
	/**
	 * 
	 * @return the current state of the game
	 */
	public boolean getGameState() {
		return score.gameState();
	}
	
	/**
	 * places the mine in random squares around the grid until the countMine variable 
	 * reaches numOfMine which is the total number of mines to be placed on the grid
	 */
	public void placeMines() {
		int row1;
		int col1;
		int countMine = 0;
		while (countMine < numOfMine) {
			row1 = (int)(Math.random() * row); 
			col1 = (int)(Math.random() * col); 
			if (!Box[row1][col1].returnMine()) {
				Box[row1][col1].setMine();
				countMine++;
			}
		}
	}
	
	//need to swap count diagonally
	//fixed
	/**
	 * assigns a square a number based on how many squares adjacent to their position have mines
	 */
	public void assignCount(Squares a) {
		int count = 0;
		for (String s: getAdjacentLocations(a.getLocation())) {
			int row1 = convertRow(s);
			int col1 = convertCol(s);
			if (Box[col1][row1].returnMine()) {
				count++;
			}
		}
		String loc = a.getLocation();
		
		int row1 = convertRow(loc);
		int col1 = convertCol(loc);
		Box[col1][row1].setCount(count);
		//System.out.println(loc + " " + count);
	}
	
	
	/* old start of the method to get the squares around.
	public String[] getAdjacentLocations(String location) {
		String[] tmp = new String[8];
		String col1 = ("" + location.trim().charAt(0)).toLowerCase();
		String row1 = location.substring(1).trim();
		int x = Integer.parseInt(row1);
		int y = alphabet.indexOf(col1);
		
		
		
		System.out.println(tmp.length);
		return tmp;
	}
	*/

	//method is not used anymore since the row does not need to be changed mid way and the
	//constructor can handle it
	/**
	 * It sets the row.
	 * @param a is the row number
	 */
	public void  setRow( int a ) {
		row = a;
	}

	//method is not used anymore since the column does not need to be changed mid way and the
	//constructor can handle it
	/**
	 * It sets the column.
	 * @param b is the column number
	 */
	public void setCol( int b ) {
		col = b;
	}
	
	/**
	 * gets the number of rows in the grid
	 * @return the number of rows
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * gets the number of columns in the grid
	 * @return the number of columns
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * calculates the number of mines to be placed on the field depending on the mineConstant
	 * and returns that number
	 * 
	 * @return the number of mines to set in the grid
	 */
	public int getNumOfMine() {
		return (int) (row * col * mineConstant);
	}
	
	
	//needs more development with getting adjacent locations
	//finished with the method
	/**
	 * Inspects the location if its flagged or not, then if the spot is flipped or not,
	 * and then if if its a mine records loss and starts again, If not a mine checks the 
	 * number of mines around it, and if none calls getAdjacentLocations()
	 * 
	 * 
	 * @param location
	 *            the raw point where the intended inspect is wanted. (example:
	 *            d5)
	 */
	public void inspect(String location)
	{
		location = location.trim().toLowerCase();
		int col = convertCol(location);
		int row = convertRow(location);
		boolean inSquare = Box[row][col].returnMine();
		//System.out.println(row + " " + col);
		if (Box[row][col].returnFlagState() == false) {
			//System.out.println(reverseRow(row));
			//System.out.println(Box[reverseRow(row)][reverseCol(col)].getCount());
			//System.out.println(Box[row][col].getLocation());
			if(inSquare)
			{
				score.recordLoss();
				//System.out.println("mine");
			}
			else
			{
				//System.out.println("not mine");
				if (!Box[row][col].flipState()) {
					//if (Box[reverseRow(row)][reverseCol(col)].getCount() == 0) {
					if (Box[row][col].getCount() == 0) {
					//if (Box[col][row].getCount() == 0) {
						Box[row][col].setFlip();
						//Box[col][row].setFlip();
						//check point
						//few bugs got rid of bugs
						for (String s : getAdjacentLocations(location)) {
							inspect(s);
						}
					}
					else {
						Box[row][col].setFlip();
					}
				}
				else {
					//System.out.println("flipped");
				}
			}
		}
		else {
			//System.out.println("flagged");
		}
	}
	
	/*
	 * methods were used when due to row column mix up was tried as a fix
	public int reverseRow(int row1) {
		return row - row1 - 1;
	}
	public int reverseCol(int col1) {
		return col - col1 - 1;
	}
	*/
	
	// should rename tmp and temp
	/**
	 * When Inspect flips a mine that has 0 around it, this is called, checks
	 * all adjacent squares and then inspect is called on them.
	 * Gets all adjacent locations of the square location that is sent including
	 * the current square.
	 * 
	 * @param location
	 *            of the point that has 0 mines around it.
	 * @return the adjacent locations of the squares around it in a 2-d array of
	 *         Strings.
	 */
	public String[] getAdjacentLocations(String location) {
		String[] loc = new String[10];
		int locNum = 0;
		int col1 = convertCol(location);
		int row1 = convertRow(location);
		for (int x = row1 - 1; x <= row1 + 1; x++) {
			for (int y = col1 - 1; y <= col1 + 1; y++) {
				if (locNum < loc.length && y >= 0 && y < col && x >= 0 && x < row) {
					//System.out.println(locNum + " " + loc[locNum]);
					loc[locNum] = Box[x][y].getLocation();
					locNum++;
				}
			}
		}
		
		String[] loc1 = new String[locNum];
		for (int a = 0; a < loc1.length; a++) {
			loc1[a] = loc[a];
		}
		
		//System.out.println(tmp.length);
		return loc1;
	}
	
	/**
	 * flags the current location
	 * @param location the location to be flagged
	 */
	public void flag(String location) {
		location = location.trim().toLowerCase();
		int col = convertCol(location);
		int row = convertRow(location);
		
		//System.out.println(row + " " + column);
		//System.out.println(Box[row][col].returnFlagState());
		Box[row][col].changeFlagOn();
		//System.out.println(Box[row][col].returnFlagState());
	}
	/**
	 * unflags the current location
	 * @param location the location to be unflagged
	 */
	public void unflag(String location) {
		location = location.trim().toLowerCase();
		int col = convertCol(location);
		int row = convertRow(location);
		//System.out.println(row + " " + column);
		//System.out.println(Box[row][col].returnFlagState());
		Box[row][col].changeFlagOff();
		//System.out.println(Box[row][col].returnFlagState());
	}
	
	//need to make more robust to prevent the higher numbers from breaking it
	//need to create an error and restart.
	//used the and section and the extra if to get rid of the extra spaces in between numbers larger than 10
	/**
	 *  Converts the String containing the location and turns into a column number(int form)
	 * @param location is the String containing the given command
	 * @return returns the number ( int form) of the column, if the number has an error, return -1
	 */
	public int convertCol(String location) {
		location = location.trim();
		String add = "";
		int column;
		
		//starts it after the letter row is set so error is not triggered.
		for (int a = 1; a < location.trim().length(); a++) {
			if(!Character.isDigit(location.charAt(a)) && location.charAt(a) != ' ') {
				error = true;
			}
			else {
				if (Character.isDigit(location.charAt(a))) {
					add += location.charAt(a);
				}
			}
		}
		//System.out.println(error + " " + add);
		if (add.length() > 0 && !error) {
			column = Integer.parseInt("" + add.trim()) - 1;
		}
		else {
			column = -1;
		}
		error = false;
		return column;
	}
	
	//need to make more robust to prevent the locations from breaking it
	//need to create an error and restart.
	/**
	 * Converts the String containing the location and turns into a row number(int form)
	 * @param location is the String containing the given command
	 * @return returns the number ( int form ) of the row, if the number has an error, return -1 
	 */
	public int convertRow(String location) {
		location = location.trim();
		int row;
		if (location.length() > 0) {
			row = alphabet.indexOf(location.charAt(0));
		}
		else {
			row = -1;
		}
		//System.out.println(row);
		return row;
	}
	
	
	//mainly used for testing at this point
	/**
	 * 
	 * @return the 2-d array of Squares used
	 */
	public Squares[][] returnArray() {
		return Box;
	}

}
