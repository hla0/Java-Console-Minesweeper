import java.util.*;
public class Control  {
	private Grid grid = null; 
	private Scanner keybd = null;
	private String com;
	private String com1;
	private boolean on = true;
	private boolean start = true;
	private boolean restart = false;	
	private String start1;
	private String location;
	
	/**
	 * constructor for the class
	 */
	public Control() {
		startGame();
	}
	
	//printing of letters in comments were to find what direction the errors went through.
	//turned out a boolean was left on and wasn't turned off, making errors continuously sent.
	/**
	 * starts the game up and keeps it running until the on boolean becomes false
	 */
	public void startGame() {
		
		if (start) {
			keybd = new Scanner(System.in);
			System.out.println("Starting game of minesweeper, Choose Difficulty: b for beginner, " +
					"i for intermediate, or e for expert");
			getDifficulty();
			setDifficulty(start1);
			
			start = false;
		}
		while (on) {
			String command = getCommand();
			processCommand(command);
			if (restart) {
				System.out.println("\nGame Over! Restart and Choose Difficulty: b for beginner, " +
									"i for intermediate, or e for expert");
				getDifficulty();
				setDifficulty(start1);
				//System.out.println(control.restartState());
				flipRestart();
				//System.out.println(control.restartState());
				//control = new Control(a.grid);
			}
		}
	}
	
	//bottom method was just added in
	/**
	 * gets the difficulty from the command sent
	 */
	public void getDifficulty() {
		try {
			start1 = keybd.nextLine();
		}
		catch (Exception e) {
			
		}
		if (start1.trim().length() > 0) {
			start1 = "" + start1.trim().toLowerCase().charAt(0);
		}
		if (!start1.trim().equals("e") && !start1.trim().equals("i") && !start1.trim().equals("b")){
			start1 = "";
		}
	}
	
	/**
	 * gets the command that is entered as a line
	 * @return the command sent
	 */
	public String getCommand() {
		keybd = new Scanner(System.in);
		System.out.println("Input command: ");
		String command = "";
		try {
			command = keybd.nextLine().trim();
		}
		catch (Exception e) {
			
		}
		//System.out.println("c " + command);
		return command;
	}

	//bug happened when there was a space between the location.
	/**
	 * processes the command and decides what action to do based on it and prints the board's
	 * new state if an error was not triggered and the game is not quit
	 * @param command the command sent to be processed
	 */
	public void processCommand (String command){
		verifyCommand(command);
		//System.out.println("d " + command + " w " + a + " x " + location);
		boolean count = false; 
		
		if (com.equals("q")) {
			quit();
		}
		else if (location.length() < 2 || command.trim().length() <= 0) {
			//testing of getCommand();
			System.out.println("Error, try again!");
			//System.out.println("a");
			com1 = getCommand();
			//System.out.println(com1);
			processCommand(com1);
		}
		else if(com.equals("i")) {
			//System.out.println("e " + count);
			count = true;
			grid.inspect(location);
		}
		else if(com.equals("f")) {
			count = true;
			grid.flag(location);
		}
		else if (com.equals("u")) {
			count = true;
			grid.unflag(location);	
		}
		else {
			System.out.println("Error, try again!");
			//System.out.println("b");
			com1 = getCommand();
			processCommand(com1);
		}
		if (count) {
			//commented out for testing
			//commented out to avoid having commands print out the grid
			grid.processPrintBoard();

			if (grid.getGameState()) {
				startRestart();
			}
			count = false;
		}
	}

	/**
	 * starts the restart of the game and tells the startGame method to ask for difficulty
	 * again
	 */
	public void startRestart() {
		restart = true;
		grid.changeGameState();
	}
	
	/**
	 * verifies if the command is valid by testing whether its out of bounds and whether or
	 * not it has the right characters to start a command and sets those characters into
	 * a field
	 * @param command the command that is being typed into the console to be verified
	 */
	public void verifyCommand(String command) {
		String add = "";
		if (command.length() > 0) {
			com = ("" + command.trim().charAt(0)).toLowerCase();
			if (command.length() > 1) {
				//System.out.println("s");
				location = command.trim().substring(1).toLowerCase().trim();
				for (int a = 1; a < location.trim().length(); a++) {
					if(!Character.isDigit(location.charAt(a)) && location.charAt(a) != ' ') {
					}
					else {
						if (Character.isDigit(location.charAt(a)) && location.charAt(a) != ' ') {
							add += location.charAt(a);
						}
					}
				}
				location = location.charAt(0) + add;
			}
			else {
				location = "";
				//System.out.println("f");
			}
		}
		else {
			com = command;
			location = "";
			//System.out.println("g");
		}
		
		int col1 = grid.convertCol(location);
		//System.out.println(col1);
		int row1 = grid.convertRow(location);
		//System.out.println(row1);
		
		if (col1 >= grid.getCol() || col1 < 0 || row1 >= grid.getRow() || row1 < 0) {
			//System.out.println("l");
			location = "";
		}
		//System.out.println(location);
		
	}
	
	//bottom two methods are used for testing
	/**
	 * gets the location that is currently being used
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @return the command that is being used in the com field
	 */
	public String getCommand1() {
		return com;
	}
	
	//is being used for testing
	/**
 	 * @return the current state of the restart boolean
	 */
	public boolean restartState() {
		return restart;
	}
	
	/**
	 * changes the restart boolean to opposite of what it is currently
	 */
	public void flipRestart() {
		restart = !restart;
	}

	/**
	 * 
	 * @param x the string which decides which difficulty level to set
	 */
	public void setDifficulty(String x) {
		String s = "";
		if (x.length() > 0) {
			s = "" + x.charAt(0); 
		}
		if(s.equalsIgnoreCase("b")) {
			//Box.setRow(8);
			//Box.setColumn(8);
			//commented out for testing
			System.out.println("Starting game with beginner level");
			grid = new Grid(8, 8);
		}
		
		else if(s.equalsIgnoreCase("i")) {
			//Box.setRow(12);
			//Box.setColumn(12);
			//commented out for testing
			System.out.println("Starting game with intermediate level");
			grid = new Grid(12, 12);
		}
		else if(s.equalsIgnoreCase("e")) {
			//Box.setRow(15);
			//Box.setColumn(15);
			//commented out for testing
			System.out.println("Starting game with expert level");
			grid = new Grid(15, 15);
		}
		else {
			//commented out for testing
			System.out.println("Starting game with default beginner level");
			grid = new Grid(8, 8);
		}
	}
	//used for testing
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * turns the on boolean false which turns off the game
	 */
	public void quit() {
		on = false;
		//commented out for testing
		System.out.println("Game ended.");
	}
	
	// was previously used to send the boolean to the driver
	// now used for testing
	/**
	 * @return the boolean that keeps the game on if true
	 */
	public boolean returnState() {
		return on;
	}
	//	
}	