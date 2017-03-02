
public class Cell {
	//Zone, row, column give the number of the zone, row and column that the cell resides in
	//Value stores the number that fits in the cell
	//The possible array tells whether it is possible for a number to fit in the cell; each index i refers to i+1 (e.g. possible[0] stores the possibility of 1)
	//Solved tells whether or not the cell has been solved completely
	//All cells start with a default of unsolved; setSolved() can change this
	private int zone, row, column;
	private int value = 0;
	private boolean[] possible = new boolean[9];
	private boolean solved = false;
	
	//Row, column, and zone can only be set by the constructor at the moment of instantiation
	public Cell(int row, int column, int zone) {
		this.row = row;
		this.column = column;
		this.zone = zone;
	}
	
	//Getters for row, column, and zone; getters and setters for the the rest of the variables
	public int getZone() {
		return this.zone;
	}
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public int getValue() {
		return this.value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	public boolean isPossible(int i) {
		return possible[i-1];
	}
	
	//Helper method to add a value to the cell's possibilities
	public void setPossible(int i) {
		possible[i-1] = true;
	}
	
	//Reset the possibilities for the cell
	public void resetPossible() {
		for (int i=0; i<9; i++) {
			possible[i] = false;
		}
	}
	
	//If the cell is unsolved and only one possibility variable is true, solve cell using that value
	public void candidateCheck() {
		if (!isSolved()) { 
			if (possible[0] && !possible[1] && !possible[2] && !possible[3] && !possible[4] && !possible[5] && !possible[6] && !possible[7] && !possible[8]) {
				setValue(1);
				setSolved(true);
			}
			if (!possible[0] && possible[1] && !possible[2] && !possible[3] && !possible[4] && !possible[5] && !possible[6] && !possible[7] && !possible[8]) {
				setValue(2);
				setSolved(true);
			}
			if (!possible[0] && !possible[1] && possible[2] && !possible[3] && !possible[4] && !possible[5] && !possible[6] && !possible[7] && !possible[8]) {
				setValue(3);
				setSolved(true);
			}
			if (!possible[0] && !possible[1] && !possible[2] && possible[3] && !possible[4] && !possible[5] && !possible[6] && !possible[7] && !possible[8]) {
				setValue(4);
				setSolved(true);
			}
			if (!possible[0] && !possible[1] && !possible[2] && !possible[3] && possible[4] && !possible[5] && !possible[6] && !possible[7] && !possible[8]) {
				setValue(5);
				setSolved(true);
			}
			if (!possible[0] && !possible[1] && !possible[2] && !possible[3] && !possible[4] && possible[5] && !possible[6] && !possible[7] && !possible[8]) {
				setValue(6);
				setSolved(true);
			}
			if (!possible[0] && !possible[1] && !possible[2] && !possible[3] && !possible[4] && !possible[5] && possible[6] && !possible[7] && !possible[8]) {
				setValue(7);
				setSolved(true);
			}
			if (!possible[0] && !possible[1] && !possible[2] && !possible[3] && !possible[4] && !possible[5] && !possible[6] && possible[7] && !possible[8]) {
				setValue(8);
				setSolved(true);
			}
			if (!possible[0] && !possible[1] && !possible[2] && !possible[3] && !possible[4] && !possible[5] && !possible[6] && !possible[7] && possible[8]) {
				setValue(9);
				setSolved(true);
			}
		}
	}
}
