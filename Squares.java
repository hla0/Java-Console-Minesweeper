/**
 *
 * @author Bryant
 * @version 11.2.24
 */

public class Squares {

	private boolean mine;
	private boolean flip;
	private int count;
	private boolean flag;
	private String location;
	
	/*
	public Squares(String x) {
		location = x;
		flip = false;
		flag = false;
	}
	*/
	
	/**
	 * Constructor method that has preset values in it.
	 */
	public Squares() {
		flip = false;
		mine = false;
		flag = false;
	}
	
	/**
	 * Constructor method that has preset values in it and takes the location of 
	 * the square as a parameter
	 */
	public Squares(String x) {
		location = x;
		flip = false;
		mine = false;
		flag = false;
	}
	/**
	 * 	@return the count of mines around the square
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * sets the count variable to the number given
	 * @param a the number the count field is to be set to
	 */
	public void setCount(int a) {
		count = a;
	}
	
	/**
	 * Sets the mine.
	 */
	public void setMine() {
		mine = true;
	}
	
	/**
	 * See if the mine is set or not.
	 * @return the state of the mine boolean to check whether or not it is set
	 */
	public boolean returnMine() {
		return mine;
	}
	
	/**
	 * Flips the current square.
	 */
	public void setFlip() {
		flip = true;
	}
	
	/**
	 * Checks if the square is flipped or not.
	 * @return the state of the flip boolean to check whether or not it is flipped
	 */
	public boolean flipState() {
		return flip;
	}
	
	//for turning blank into flag
	/**
	 * Checks if the square is flipped, then checks if the square is flagged.
	 * If it is flagged, do nothing.
	 * If it isn't flagged, flag it.
	 */
	public void changeFlagOn() {
		if (!flip) {
			if (!flag) {
				flag = !flag;
			}
		}
	}
	
	//for turning flag into blank
	/**
	 * Checks if the square is flipped, then checks if the square is flagged.
	 * If it is flagged, unflag it.
	 * If it isn't flagged, do nothing.
	 */
	public void changeFlagOff() {
		if (!flip) {
			if (flag) {
				flag = !flag;
			}
		}
	}
	
	/**
	 * returns the state of the flagged/unflagged square
	 * @return the state of the flag boolean to check whether or not it is flagged
	 */
	public boolean returnFlagState() {
		return flag;
	}

	/**
	 * Gives the location of the square.
	 * @return the location of the square
	 */
	public String getLocation() {
		return location;
	}
	
}