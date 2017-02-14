
public class Row {
	//Data field to store the NumberBoxes associated with the row
	private NumberBox[] boxes;
	private boolean solved = false;
	
	//Constructors
	public Row() {
		setBoxes(new NumberBox[9]);
	}

	//Getters and setters for data fields
	public NumberBox[] getBoxes() {
		return boxes;
	}
	public void setBoxes(NumberBox[] boxes) {
		this.boxes = boxes;
	}
	public boolean isSolved() {
		return solved;
	}
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
}
