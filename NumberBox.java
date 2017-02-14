
public class NumberBox {
	//Data fields to store the value entered in the box, the parent row, column, and zone
	//The position of the box on the grid is encoded using the coordinate system:
	//	zone, column, row
	private int value, zone, column, row;
	private boolean solved = false;
	
	//Constructors
	public NumberBox(int zone, int column, int row) {
		this.zone = zone;
		this.column = column;
		this.row = row;
	}
	
	public NumberBox(int zone, int column, int row, int value) {
		this.zone = zone;
		this.column = column;
		this.row = row;
		this.value = value;
		solved = true;
	}
	
	//Getters and setters for data fields
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getZone() {
		return zone;
	}
	public void setZone(int zone) {
		this.zone = zone;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
}
