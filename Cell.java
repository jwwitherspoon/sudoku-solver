
public class Cell {
	//Zone, row, column give the number of the zone, row and column that the cell resides in
	//Value stores the number that fits in the cell
	//The maybe variables tell whether it is possible for a number to fit in that cell previously solved cells
	//Solved tells whether or not the cell has been solved completely
	//All cells start with a default of unsolved; constructor or setter method can change this
	private int zone, row, column;
	private int value;
	private boolean maybe1, maybe2, maybe3, maybe4, maybe5, maybe6, maybe7, maybe8, maybe9;
	private boolean solved = false;
	
	//Row and column can only be set by the constructor at the moment of instantiation; zone is set afterward
	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	//Getters for row and column; getters and setters for the the rest of the variables
	public int getZone() {
		return this.zone;
	}
	public void setZone(int zone) {
		this.zone = zone;
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
}
