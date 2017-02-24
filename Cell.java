
public class Cell {
	//Zone, row, column give the number of the zone, row and column that the cell resides in
	//Value stores the number that fits in the cell
	//The maybe variables tell whether it is possible for a number to fit in that cell previously solved cells
	//Solved tells whether or not the cell has been solved completely
	//All cells start with a default of unsolved; setSolved() can change this
	private int zone, row, column;
	private int value;
	private boolean maybe1, maybe2, maybe3, maybe4, maybe5, maybe6, maybe7, maybe8, maybe9;
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
	public boolean isMaybe1() {
		return maybe1;
	}
	public void setMaybe1(boolean maybe1) {
		this.maybe1 = maybe1;
	}
	public boolean isMaybe2() {
		return maybe2;
	}
	public void setMaybe2(boolean maybe2) {
		this.maybe2 = maybe2;
	}
	public boolean isMaybe3() {
		return maybe3;
	}
	public void setMaybe3(boolean maybe3) {
		this.maybe3 = maybe3;
	}
	public boolean isMaybe4() {
		return maybe4;
	}
	public void setMaybe4(boolean maybe4) {
		this.maybe4 = maybe4;
	}
	public boolean isMaybe5() {
		return maybe5;
	}
	public void setMaybe5(boolean maybe5) {
		this.maybe5 = maybe5;
	}
	public boolean isMaybe6() {
		return maybe6;
	}
	public void setMaybe6(boolean maybe6) {
		this.maybe6 = maybe6;
	}
	public boolean isMaybe7() {
		return maybe7;
	}
	public void setMaybe7(boolean maybe7) {
		this.maybe7 = maybe7;
	}
	public boolean isMaybe8() {
		return maybe8;
	}
	public void setMaybe8(boolean maybe8) {
		this.maybe8 = maybe8;
	}
	public boolean isMaybe9() {
		return maybe9;
	}
	public void setMaybe9(boolean maybe9) {
		this.maybe9 = maybe9;
	}
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
	//Helper method to add the value of the cell to its possibilities
	public void addPossible() {
		switch (this.value) {
			case 1:
				this.setMaybe1(true);
				break;
			case 2:
				this.setMaybe2(true);
				break;
			case 3:
				this.setMaybe3(true);
				break;
			case 4:
				this.setMaybe4(true);
				break;
			case 5:
				this.setMaybe5(true);
				break;
			case 6:
				this.setMaybe6(true);
				break;
			case 7:
				this.setMaybe7(true);
				break;
			case 8:
				this.setMaybe8(true);
				break;
			case 9:
				this.setMaybe9(true);
				break;
			default:
				break;
		}
	}
	
	//Reset the possibilities for the cell
	public void resetPossible() {
		setMaybe1(false);
		setMaybe2(false);
		setMaybe3(false);
		setMaybe4(false);
		setMaybe5(false);
		setMaybe6(false);
		setMaybe7(false);
		setMaybe8(false);
		setMaybe9(false);
	}
	
	//If the cell is unsolved and only one possibility variable is true, solve cell using that value
	public void candidateCheck() {
		if (!isSolved()) { 
			if (isMaybe1() && !isMaybe2() && !isMaybe3() && !isMaybe4() && !isMaybe5() && !isMaybe6() && !isMaybe7() && !isMaybe8() && !isMaybe9()) {
				setValue(1);
				setSolved(true);
			}
			if (!isMaybe1() && isMaybe2() && !isMaybe3() && !isMaybe4() && !isMaybe5() && !isMaybe6() && !isMaybe7() && !isMaybe8() && !isMaybe9()) {
				setValue(2);
				setSolved(true);
			}
			if (!isMaybe1() && !isMaybe2() && isMaybe3() && !isMaybe4() && !isMaybe5() && !isMaybe6() && !isMaybe7() && !isMaybe8() && !isMaybe9()) {
				setValue(3);
				setSolved(true);
			}
			if (!isMaybe1() && !isMaybe2() && !isMaybe3() && isMaybe4() && !isMaybe5() && !isMaybe6() && !isMaybe7() && !isMaybe8() && !isMaybe9()) {
				setValue(4);
				setSolved(true);
			}
			if (!isMaybe1() && !isMaybe2() && !isMaybe3() && !isMaybe4() && isMaybe5() && !isMaybe6() && !isMaybe7() && !isMaybe8() && !isMaybe9()) {
				setValue(5);
				setSolved(true);
			}
			if (!isMaybe1() && !isMaybe2() && !isMaybe3() && !isMaybe4() && !isMaybe5() && isMaybe6() && !isMaybe7() && !isMaybe8() && !isMaybe9()) {
				setValue(6);
				setSolved(true);
			}
			if (!isMaybe1() && !isMaybe2() && !isMaybe3() && !isMaybe4() && !isMaybe5() && !isMaybe6() && isMaybe7() && !isMaybe8() && !isMaybe9()) {
				setValue(7);
				setSolved(true);
			}
			if (!isMaybe1() && !isMaybe2() && !isMaybe3() && !isMaybe4() && !isMaybe5() && !isMaybe6() && !isMaybe7() && isMaybe8() && !isMaybe9()) {
				setValue(8);
				setSolved(true);
			}
			if (!isMaybe1() && !isMaybe2() && !isMaybe3() && !isMaybe4() && !isMaybe5() && !isMaybe6() && !isMaybe7() && !isMaybe8() && isMaybe9()) {
				setValue(9);
				setSolved(true);
			}
		}
	}
}
