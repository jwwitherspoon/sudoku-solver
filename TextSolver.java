import java.util.Scanner;

//TODO Handle exception where puzzle cannot be solved
//TODO Handle exception where puzzle requires some guesswork

public class TextSolver {
	//Create game board as a 2D array
	//First dimension is row, second dimension is column
	private static Cell[][] field = new Cell[9][9];
	
	public static void main(String[] args) {
		//Populate field with cells; make row and column values in cells correspond to row and column number
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				field[i][j] = new Cell(i,j);
			}
		}				
		
		//Create and populate zones by connecting each cell from field to its corresponding zone cell
		//Zone layout:
		//	0	1	2
		//	3	4	5
		//	6	7	8
		Cell[][] zone = {{field[0][0], field[0][1], field[0][2],
							field[1][0], field[1][1], field[1][2],
							field[2][0], field[2][1], field[2][2]},
						{field[0][3], field[0][4], field[0][5],
							field[1][3], field[1][4], field[1][5],
							field[2][3], field[2][4], field[2][5]},
						{field[0][6], field[0][7], field[0][8],
							field[1][6], field[1][7], field[1][8],
							field[2][6], field[2][7], field[2][8]},
						{field[3][0], field[3][1], field[3][2],
							field[4][0], field[4][1], field[4][2],
							field[5][0], field[5][1], field[5][2]},
						{field[3][3], field[3][4], field[3][5],
							field[4][3], field[4][4], field[4][5],
							field[5][3], field[5][4], field[5][5]},
						{field[3][6], field[3][7], field[3][8],
							field[4][6], field[4][7], field[4][8],
							field[5][6], field[5][7], field[5][8]},
						{field[6][0], field[6][1], field[6][2],
							field[7][0], field[7][1], field[7][2],
							field[8][0], field[8][1], field[8][2]},
						{field[6][3], field[6][4], field[6][5],
							field[7][3], field[7][4], field[7][5],
							field[8][3], field[8][4], field[8][5]},
						{field[6][6], field[6][7], field[6][8],
							field[7][6], field[7][7], field[7][8],
							field[8][6], field[8][7], field[8][8]}};
		
		//Assign zone numbers to cells
		for (int i=0; i<9; i++) {
			zone[0][i].setZone(0);	zone[1][i].setZone(1);	zone[2][i].setZone(2);
			zone[3][i].setZone(3);	zone[4][i].setZone(4);	zone[5][i].setZone(5);
			zone[6][i].setZone(6);	zone[7][i].setZone(7);	zone[8][i].setZone(8);
		}
		
		//Input Sudoku grid and fill the cells with their respective values
		System.out.println("Enter your Sudoku grid. Use the number 0 to denote an empty cell.");
		System.out.println("Separate lines with Enter and numbers with Space.");
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				int temp = scan.nextInt();
				if (temp!=0) {
					field[i][j].setValue(temp);
					field[i][j].setSolved(true);
				}
			}
		}
		scan.close();
		
		//Create a variable to check if the puzzle is still in progress
		//While the puzzle is still unsolved, run through the solution process
		boolean puzzleSolved = false;
		while (!puzzleSolved) {
			//Compute all possibilities for each cell
			//For each zone
			for (int i=0; i<9; i++) {
				//For each cell in zone[i]
				for (int j=0; j<9; j++) {
					//If the cell is not solved
					if (!zone[i][j].isSolved()) {
						//Test the cell for each possible value 1-9
						for (int k=1; k<=9; k++) {
							//As long as the value is not already in a solved cell in the zone
							if (((zone[i][0].isSolved() && zone[i][0].getValue()!=k) || !(zone[i][0].isSolved())) &&
									((zone[i][1].isSolved() && zone[i][1].getValue()!=k) || !(zone[i][1].isSolved())) &&
									((zone[i][2].isSolved() && zone[i][2].getValue()!=k) || !(zone[i][2].isSolved())) &&
									((zone[i][3].isSolved() && zone[i][3].getValue()!=k) || !(zone[i][3].isSolved())) &&
									((zone[i][4].isSolved() && zone[i][4].getValue()!=k) || !(zone[i][4].isSolved())) &&
									((zone[i][5].isSolved() && zone[i][5].getValue()!=k) || !(zone[i][5].isSolved())) &&
									((zone[i][6].isSolved() && zone[i][6].getValue()!=k) || !(zone[i][6].isSolved())) &&
									((zone[i][7].isSolved() && zone[i][7].getValue()!=k) || !(zone[i][7].isSolved())) &&
									((zone[i][8].isSolved() && zone[i][8].getValue()!=k) || !(zone[i][8].isSolved()))) {
								zone[i][j].setValue(k);
								if (checkCell(zone[i][j])) {
									zone[i][j].addPossible();
								}
							}
						}
					}
				}
			}

			//Check for cells that only have one possibility and mark them solved
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					//If only one possibility variable is true, solve cell using that value
					if (field[i][j].isMaybe1() ^ field[i][j].isMaybe2() ^ field[i][j].isMaybe3() ^
							field[i][j].isMaybe4() ^ field[i][j].isMaybe5() ^ field[i][j].isMaybe6() ^
							field[i][j].isMaybe7() ^ field[i][j].isMaybe8() ^ field[i][j].isMaybe9()) { 
						if (field[i][j].isMaybe1()) {
							field[i][j].setValue(1);
							field[i][j].setSolved(true);
						}
						if (field[i][j].isMaybe2()) {
							field[i][j].setValue(2);
							field[i][j].setSolved(true);
						}
						if (field[i][j].isMaybe3()) {
							field[i][j].setValue(3);
							field[i][j].setSolved(true);
						}
						if (field[i][j].isMaybe4()) {
							field[i][j].setValue(4);
							field[i][j].setSolved(true);
						}
						if (field[i][j].isMaybe5()) {
							field[i][j].setValue(5);
							field[i][j].setSolved(true);
						}
						if (field[i][j].isMaybe6()) {
							field[i][j].setValue(6);
							field[i][j].setSolved(true);
						}
						if (field[i][j].isMaybe7()) {
							field[i][j].setValue(7);
							field[i][j].setSolved(true);
						}
						if (field[i][j].isMaybe8()) {
							field[i][j].setValue(8);
							field[i][j].setSolved(true);
						}
						if (field[i][j].isMaybe9()) {
							field[i][j].setValue(9);
							field[i][j].setSolved(true);
						}
					}
				}
			}
			
			//TODO Finish section
			//Check for cells that are the only possibility for a number in a zone
			for (int i=0; i<9; i++) {
				if (zone[i][0].isMaybe1() ^ zone[i][1].isMaybe1() ^ zone[i][2].isMaybe1() ^
						zone[i][3].isMaybe1() ^ zone[i][4].isMaybe1() ^ zone[i][5].isMaybe1() ^
						zone[i][6].isMaybe1() ^ zone[i][7].isMaybe1() ^ zone[i][8].isMaybe1()) {
					if (zone[i][0].isMaybe1()) {
						zone[i][0].setValue(1);
						zone[i][0].setSolved(true);
					}
				}
			}

			//Check to see if the entire puzzle is solved
			//If yes, break while loop; if no, reset possibilities for all unsolved cells
			if (checkPuzzle()) {
				puzzleSolved = true;
			} else {
				for (int i=0; i<9; i++) {
					for (int j=0; j<9; j++) {
						if (!field[i][j].isSolved()) {
							field[i][j].setMaybe1(false);
							field[i][j].setMaybe2(false);
							field[i][j].setMaybe3(false);
							field[i][j].setMaybe4(false);
							field[i][j].setMaybe5(false);
							field[i][j].setMaybe6(false);
							field[i][j].setMaybe7(false);
							field[i][j].setMaybe8(false);
							field[i][j].setMaybe9(false);
						}
					}
				}
			}
		}

		//Print the solved puzzle
		print(field);
	}
	
	//Helper method to print the puzzle
	public static void print(Cell[][] field) {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++)
				System.out.print(field[i][j].getValue() + " ");
			System.out.println("");
		}
	}
	
	//Helper method to check to see if the value of a cell can work for that cell
	public static boolean checkCell(Cell cell) {
		//Create variables to represent the row and column the cell will be checked against
		int row = cell.getRow();
		int column = cell.getColumn();
		
		//Check cell against values of the other row members
		for (int i=0; i<9; i++) {
			if (field[row][i].isSolved() && field[row][i].getValue()==cell.getValue())
				return false;
		}
		
		//Check cell against values of the other column members
		for (int i=0; i<9; i++) {
			if (field[i][column].isSolved() && field[i][column].getValue()==cell.getValue())
				return false;
		}
		
		//If the cell's value is not equal to any of the values of the solved cells in the same row and column, return true
		return true;
	}
	
	//Helper method to check if the entire puzzle has been solved
	//If one cell is unsolved, return false; otherwise return true
	public static boolean checkPuzzle() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (!field[i][j].isSolved())
					return false;
			}
		}
		return true;
	}
	
}

//Test puzzle
// 0 8 5 3 2 4 9 6 7 0 4 6 1 8 7 5 2 3 0 7 3 9 5 6 1 8 4 0 2 4 6 1 3 7 5 9 0 1 9 5 7 8 3 4 2 0 5 7 4 9 2 8 1 6 0 3 2 7 4 1 6 9 8 0 9 8 2 6 5 4 3 1 0 6 1 8 3 9 2 7 5
