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
		
		//Create and populate zones
		//Zone layout:
		//	0	1	2
		//	3	4	5
		//	6	7	8
		Cell[] zone0 = {field[0][0], field[0][1], field[0][2],
						field[1][0], field[1][1], field[1][2],
						field[2][0], field[2][1], field[2][2]};
		Cell[] zone1 = {field[0][3], field[0][4], field[0][5],
						field[1][3], field[1][4], field[1][5],
						field[2][3], field[2][4], field[2][5]};
		Cell[] zone2 = {field[0][6], field[0][7], field[0][8],
						field[1][6], field[1][7], field[1][8],
						field[2][6], field[2][7], field[2][8]};
		Cell[] zone3 = {field[3][0], field[3][1], field[3][2],
						field[4][0], field[4][1], field[4][2],
						field[5][0], field[5][1], field[5][2]};
		Cell[] zone4 = {field[3][3], field[3][4], field[3][5],
						field[4][3], field[4][4], field[4][5],
						field[5][3], field[5][4], field[5][5]};
		Cell[] zone5 = {field[3][6], field[3][7], field[3][8],
						field[4][6], field[4][7], field[4][8],
						field[5][6], field[5][7], field[5][8]};
		Cell[] zone6 = {field[6][0], field[6][1], field[6][2],
						field[7][0], field[7][1], field[7][2],
						field[8][0], field[8][1], field[8][2]};
		Cell[] zone7 = {field[6][3], field[6][4], field[6][5],
						field[7][3], field[7][4], field[7][5],
						field[8][3], field[8][4], field[8][5]};
		Cell[] zone8 = {field[6][6], field[6][7], field[6][8],
						field[7][6], field[7][7], field[7][8],
						field[8][6], field[8][7], field[8][8]};
		
		//Assign zone numbers to cells
		for (int i=0; i<9; i++) {
			zone0[i].setZone(0);	zone1[i].setZone(1);	zone2[i].setZone(2);
			zone3[i].setZone(3);	zone4[i].setZone(4);	zone5[i].setZone(5);
			zone6[i].setZone(6);	zone7[i].setZone(7);	zone8[i].setZone(8);
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
			//Compute all possibilities for zone0
			//For each cell in zone0
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone0[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone0[0].isSolved() && zone0[0].getValue()!=j) || !(zone0[0].isSolved())) &&
								((zone0[1].isSolved() && zone0[1].getValue()!=j) || !(zone0[1].isSolved())) &&
								((zone0[2].isSolved() && zone0[2].getValue()!=j) || !(zone0[2].isSolved())) &&
								((zone0[3].isSolved() && zone0[3].getValue()!=j) || !(zone0[3].isSolved())) &&
								((zone0[4].isSolved() && zone0[4].getValue()!=j) || !(zone0[4].isSolved())) &&
								((zone0[5].isSolved() && zone0[5].getValue()!=j) || !(zone0[5].isSolved())) &&
								((zone0[6].isSolved() && zone0[6].getValue()!=j) || !(zone0[6].isSolved())) &&
								((zone0[7].isSolved() && zone0[7].getValue()!=j) || !(zone0[7].isSolved())) &&
								((zone0[8].isSolved() && zone0[8].getValue()!=j) || !(zone0[8].isSolved()))) {
							zone0[i].setValue(j);
							if (checkCell(zone0[i])) {
								zone0[i].addPossible();
							}
						}
					}
				}
			}
			//Compute all possibilities for zone1
			//For each cell in zone1
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone1[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone1[0].isSolved() && zone1[0].getValue()!=j) || !(zone1[0].isSolved())) &&
								((zone1[1].isSolved() && zone1[1].getValue()!=j) || !(zone1[1].isSolved())) &&
								((zone1[2].isSolved() && zone1[2].getValue()!=j) || !(zone1[2].isSolved())) &&
								((zone1[3].isSolved() && zone1[3].getValue()!=j) || !(zone1[3].isSolved())) &&
								((zone1[4].isSolved() && zone1[4].getValue()!=j) || !(zone1[4].isSolved())) &&
								((zone1[5].isSolved() && zone1[5].getValue()!=j) || !(zone1[5].isSolved())) &&
								((zone1[6].isSolved() && zone1[6].getValue()!=j) || !(zone1[6].isSolved())) &&
								((zone1[7].isSolved() && zone1[7].getValue()!=j) || !(zone1[7].isSolved())) &&
								((zone1[8].isSolved() && zone1[8].getValue()!=j) || !(zone1[8].isSolved()))) {
							zone1[i].setValue(j);
							if (checkCell(zone1[i])) {
								zone1[i].addPossible();
							}
						}
					}
				}
			}
			//Compute all possibilities for zone2
			//For each cell in zone2
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone2[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone2[0].isSolved() && zone2[0].getValue()!=j) || !(zone2[0].isSolved())) &&
								((zone2[1].isSolved() && zone2[1].getValue()!=j) || !(zone2[1].isSolved())) &&
								((zone2[2].isSolved() && zone2[2].getValue()!=j) || !(zone2[2].isSolved())) &&
								((zone2[3].isSolved() && zone2[3].getValue()!=j) || !(zone2[3].isSolved())) &&
								((zone2[4].isSolved() && zone2[4].getValue()!=j) || !(zone2[4].isSolved())) &&
								((zone2[5].isSolved() && zone2[5].getValue()!=j) || !(zone2[5].isSolved())) &&
								((zone2[6].isSolved() && zone2[6].getValue()!=j) || !(zone2[6].isSolved())) &&
								((zone2[7].isSolved() && zone2[7].getValue()!=j) || !(zone2[7].isSolved())) &&
								((zone2[8].isSolved() && zone2[8].getValue()!=j) || !(zone2[8].isSolved()))) {
							zone2[i].setValue(j);
							if (checkCell(zone2[i])) {
								zone2[i].addPossible();
							}
						}
					}
				}
			}
			//Compute all possibilities for zone3
			//For each cell in zone3
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone3[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone3[0].isSolved() && zone3[0].getValue()!=j) || !(zone3[0].isSolved())) &&
								((zone3[1].isSolved() && zone3[1].getValue()!=j) || !(zone3[1].isSolved())) &&
								((zone3[2].isSolved() && zone3[2].getValue()!=j) || !(zone3[2].isSolved())) &&
								((zone3[3].isSolved() && zone3[3].getValue()!=j) || !(zone3[3].isSolved())) &&
								((zone3[4].isSolved() && zone3[4].getValue()!=j) || !(zone3[4].isSolved())) &&
								((zone3[5].isSolved() && zone3[5].getValue()!=j) || !(zone3[5].isSolved())) &&
								((zone3[6].isSolved() && zone3[6].getValue()!=j) || !(zone3[6].isSolved())) &&
								((zone3[7].isSolved() && zone3[7].getValue()!=j) || !(zone3[7].isSolved())) &&
								((zone3[8].isSolved() && zone3[8].getValue()!=j) || !(zone3[8].isSolved()))) {
							zone3[i].setValue(j);
							if (checkCell(zone3[i])) {
								zone3[i].addPossible();
							}
						}
					}
				}
			}
			//Compute all possibilities for zone4
			//For each cell in zone4
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone4[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone4[0].isSolved() && zone4[0].getValue()!=j) || !(zone4[0].isSolved())) &&
								((zone4[1].isSolved() && zone4[1].getValue()!=j) || !(zone4[1].isSolved())) &&
								((zone4[2].isSolved() && zone4[2].getValue()!=j) || !(zone4[2].isSolved())) &&
								((zone4[3].isSolved() && zone4[3].getValue()!=j) || !(zone4[3].isSolved())) &&
								((zone4[4].isSolved() && zone4[4].getValue()!=j) || !(zone4[4].isSolved())) &&
								((zone4[5].isSolved() && zone4[5].getValue()!=j) || !(zone4[5].isSolved())) &&
								((zone4[6].isSolved() && zone4[6].getValue()!=j) || !(zone4[6].isSolved())) &&
								((zone4[7].isSolved() && zone4[7].getValue()!=j) || !(zone4[7].isSolved())) &&
								((zone4[8].isSolved() && zone4[8].getValue()!=j) || !(zone4[8].isSolved()))) {
							zone4[i].setValue(j);
							if (checkCell(zone4[i])) {
								zone4[i].addPossible();
							}
						}
					}
				}
			}
			//Compute all possibilities for zone5
			//For each cell in zone5
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone5[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone5[0].isSolved() && zone5[0].getValue()!=j) || !(zone5[0].isSolved())) &&
								((zone5[1].isSolved() && zone5[1].getValue()!=j) || !(zone5[1].isSolved())) &&
								((zone5[2].isSolved() && zone5[2].getValue()!=j) || !(zone5[2].isSolved())) &&
								((zone5[3].isSolved() && zone5[3].getValue()!=j) || !(zone5[3].isSolved())) &&
								((zone5[4].isSolved() && zone5[4].getValue()!=j) || !(zone5[4].isSolved())) &&
								((zone5[5].isSolved() && zone5[5].getValue()!=j) || !(zone5[5].isSolved())) &&
								((zone5[6].isSolved() && zone5[6].getValue()!=j) || !(zone5[6].isSolved())) &&
								((zone5[7].isSolved() && zone5[7].getValue()!=j) || !(zone5[7].isSolved())) &&
								((zone5[8].isSolved() && zone5[8].getValue()!=j) || !(zone5[8].isSolved()))) {
							zone5[i].setValue(j);
							if (checkCell(zone5[i])) {
								zone5[i].addPossible();
							}
						}
					}
				}
			}
			//Compute all possibilities for zone6
			//For each cell in zone6
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone6[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone6[0].isSolved() && zone6[0].getValue()!=j) || !(zone6[0].isSolved())) &&
								((zone6[1].isSolved() && zone6[1].getValue()!=j) || !(zone6[1].isSolved())) &&
								((zone6[2].isSolved() && zone6[2].getValue()!=j) || !(zone6[2].isSolved())) &&
								((zone6[3].isSolved() && zone6[3].getValue()!=j) || !(zone6[3].isSolved())) &&
								((zone6[4].isSolved() && zone6[4].getValue()!=j) || !(zone6[4].isSolved())) &&
								((zone6[5].isSolved() && zone6[5].getValue()!=j) || !(zone6[5].isSolved())) &&
								((zone6[6].isSolved() && zone6[6].getValue()!=j) || !(zone6[6].isSolved())) &&
								((zone6[7].isSolved() && zone6[7].getValue()!=j) || !(zone6[7].isSolved())) &&
								((zone6[8].isSolved() && zone6[8].getValue()!=j) || !(zone6[8].isSolved()))) {
							zone6[i].setValue(j);
							if (checkCell(zone6[i])) {
								zone6[i].addPossible();
							}
						}
					}
				}
			}
			//Compute all possibilities for zone7
			//For each cell in zone7
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone7[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone7[0].isSolved() && zone7[0].getValue()!=j) || !(zone7[0].isSolved())) &&
								((zone7[1].isSolved() && zone7[1].getValue()!=j) || !(zone7[1].isSolved())) &&
								((zone7[2].isSolved() && zone7[2].getValue()!=j) || !(zone7[2].isSolved())) &&
								((zone7[3].isSolved() && zone7[3].getValue()!=j) || !(zone7[3].isSolved())) &&
								((zone7[4].isSolved() && zone7[4].getValue()!=j) || !(zone7[4].isSolved())) &&
								((zone7[5].isSolved() && zone7[5].getValue()!=j) || !(zone7[5].isSolved())) &&
								((zone7[6].isSolved() && zone7[6].getValue()!=j) || !(zone7[6].isSolved())) &&
								((zone7[7].isSolved() && zone7[7].getValue()!=j) || !(zone7[7].isSolved())) &&
								((zone7[8].isSolved() && zone7[8].getValue()!=j) || !(zone7[8].isSolved()))) {
							zone7[i].setValue(j);
							if (checkCell(zone7[i])) {
								zone7[i].addPossible();
							}
						}
					}
				}
			}
			//Compute all possibilities for zone8
			//For each cell in zone8
			for (int i=0; i<9; i++) {
				//If the cell is not solved
				if (!zone8[i].isSolved()) {
					//Test the cell for each possible value 1-9
					for (int j=1; j<=9; j++) {
						//As long as the value is not already in a solved cell in the zone
						if (((zone8[0].isSolved() && zone8[0].getValue()!=j) || !(zone8[0].isSolved())) &&
								((zone8[1].isSolved() && zone8[1].getValue()!=j) || !(zone8[1].isSolved())) &&
								((zone8[2].isSolved() && zone8[2].getValue()!=j) || !(zone8[2].isSolved())) &&
								((zone8[3].isSolved() && zone8[3].getValue()!=j) || !(zone8[3].isSolved())) &&
								((zone8[4].isSolved() && zone8[4].getValue()!=j) || !(zone8[4].isSolved())) &&
								((zone8[5].isSolved() && zone8[5].getValue()!=j) || !(zone8[5].isSolved())) &&
								((zone8[6].isSolved() && zone8[6].getValue()!=j) || !(zone8[6].isSolved())) &&
								((zone8[7].isSolved() && zone8[7].getValue()!=j) || !(zone8[7].isSolved())) &&
								((zone8[8].isSolved() && zone8[8].getValue()!=j) || !(zone8[8].isSolved()))) {
							zone8[i].setValue(j);
							if (checkCell(zone8[i])) {
								zone8[i].addPossible();
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

			//Check to see if the entire puzzle is solved
			if (checkPuzzle()) {
				puzzleSolved = true;
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
