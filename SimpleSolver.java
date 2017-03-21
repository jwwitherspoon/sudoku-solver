/**
 * This is a sudoku solver based on the simple solving algorithm from this link:
 * http://www.math.cornell.edu/~mec/Summer2009/meerkamp/Site/Solving_any_Sudoku_I.html
 * This solver is built for readability, not efficiency.
 * 
 * @author Joel Witherspoon
 */

import java.util.Scanner;

public class SimpleSolver {
	//Create game board as a 2D array
	//Populate field with cells; make row, column, and zone values in cells correspond to row, column, and zone number
	//First dimension is row, second dimension is column
	private static Cell[][] field = {{new Cell(0,0,0), new Cell(0,1,0), new Cell(0,2,0), new Cell(0,3,1), new Cell(0,4,1), new Cell(0,5,1), new Cell(0,6,2), new Cell(0,7,2), new Cell(0,8,2)},
										{new Cell(1,0,0), new Cell(1,1,0), new Cell(1,2,0), new Cell(1,3,1), new Cell(1,4,1), new Cell(1,5,1), new Cell(1,6,2), new Cell(1,7,2), new Cell(1,8,2)},
										{new Cell(2,0,0), new Cell(2,1,0), new Cell(2,2,0), new Cell(2,3,1), new Cell(2,4,1), new Cell(2,5,1), new Cell(2,6,2), new Cell(2,7,2), new Cell(2,8,2)},
										{new Cell(3,0,3), new Cell(3,1,3), new Cell(3,2,3), new Cell(3,3,4), new Cell(3,4,4), new Cell(3,5,4), new Cell(3,6,5), new Cell(3,7,5), new Cell(3,8,5)},
										{new Cell(4,0,3), new Cell(4,1,3), new Cell(4,2,3), new Cell(4,3,4), new Cell(4,4,4), new Cell(4,5,4), new Cell(4,6,5), new Cell(4,7,5), new Cell(4,8,5)},
										{new Cell(5,0,3), new Cell(5,1,3), new Cell(5,2,3), new Cell(5,3,4), new Cell(5,4,4), new Cell(5,5,4), new Cell(5,6,5), new Cell(5,7,5), new Cell(5,8,5)},
										{new Cell(6,0,6), new Cell(6,1,6), new Cell(6,2,6), new Cell(6,3,7), new Cell(6,4,7), new Cell(6,5,7), new Cell(6,6,8), new Cell(6,7,8), new Cell(6,8,8)},
										{new Cell(7,0,6), new Cell(7,1,6), new Cell(7,2,6), new Cell(7,3,7), new Cell(7,4,7), new Cell(7,5,7), new Cell(7,6,8), new Cell(7,7,8), new Cell(7,8,8)},
										{new Cell(8,0,6), new Cell(8,1,6), new Cell(8,2,6), new Cell(8,3,7), new Cell(8,4,7), new Cell(8,5,7), new Cell(8,6,8), new Cell(8,7,8), new Cell(8,8,8)}};
	
	//Create a second 2D array to represent the zones of the Sudoku board
	//Populate zones by connecting each cell from field to its corresponding zone cell
	//Zone layout:
	//	0	1	2
	//	3	4	5
	//	6	7	8
	private static Cell[][] zone = {{field[0][0], field[0][1], field[0][2], field[1][0], field[1][1], field[1][2], field[2][0], field[2][1], field[2][2]},
									{field[0][3], field[0][4], field[0][5], field[1][3], field[1][4], field[1][5], field[2][3], field[2][4], field[2][5]},
									{field[0][6], field[0][7], field[0][8], field[1][6], field[1][7], field[1][8], field[2][6], field[2][7], field[2][8]},
									{field[3][0], field[3][1], field[3][2], field[4][0], field[4][1], field[4][2], field[5][0], field[5][1], field[5][2]},
									{field[3][3], field[3][4], field[3][5], field[4][3], field[4][4], field[4][5], field[5][3], field[5][4], field[5][5]},
									{field[3][6], field[3][7], field[3][8], field[4][6], field[4][7], field[4][8], field[5][6], field[5][7], field[5][8]},
									{field[6][0], field[6][1], field[6][2], field[7][0], field[7][1], field[7][2], field[8][0], field[8][1], field[8][2]},
									{field[6][3], field[6][4], field[6][5], field[7][3], field[7][4], field[7][5], field[8][3], field[8][4], field[8][5]},
									{field[6][6], field[6][7], field[6][8], field[7][6], field[7][7], field[7][8], field[8][6], field[8][7], field[8][8]}};
		
	public static void main(String[] args) {
		
		//Input Sudoku grid and fill the cells with their respective values
		System.out.println("Enter your Sudoku grid. Use the number 0 to denote an empty cell.");
		System.out.println("Separate lines with Enter and numbers with Space.");
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<9; i++) {
			for (Cell cell : field[i]) {
				int temp = scan.nextInt();
				if (temp!=0) {
					cell.setValue(temp);
					cell.setSolved(true);
				}
				else
					cell.setValue(0);
			}
		}
		scan.close();
		
		//Create enumeration of puzzle from left to right, top to bottom
		Cell[] enumeration = new Cell[81];
		int j=0;
		for (int i=0; i<9; i++) {
			for (Cell cell : field[i]) {
				enumeration[j] = cell;
				j++;
			}
		}
		
		//Solve puzzle using simple solving method
		//Number used to denote which index of enumeration should be used as the current cell
		int currentnum = 0;
		//While the puzzle is not solved and has not been proven impossible, run the solution algorithm
		boolean puzzleSolved = false, impossible = false;
		while (!puzzleSolved && !impossible) {
			//Determine the current cell
			Cell current = enumeration[currentnum];
			//If cell is not already solved
			if (!current.isSolved()) {
				//Create two ints, one to store the current value and one to store the possible new value (starting with the next untested value)
				int x = current.getValue()+1, prev = current.getValue();
				//If the current cell is not tempsolved, try adding numbers into the cell until the entry does not violate the Sudoku condition or all values have been tested
				while (!current.isTempSolved() && x<=9 && current.getValue()==prev) {
					if (checkCell(current, x)) {
						current.setValue(x);
						current.setTempSolved(true);
					}
					x++;
				}

				//If the value does not violate the Sudoku condition
				if (current.isTempSolved()) {
					//If current cell is last cell, puzzle is solved
					if (currentnum==80)
						puzzleSolved = true;
					//If current cell is not last cell, continue solution with next cell as new current cell
					else
						currentnum++;
				}
				//If all values violate the Sudoku condition
				else {
					//If current cell is first cell, puzzle has no solution
					if (currentnum==0)
						impossible = true;
					//If current cell is not first cell, erase value of current cell and continue solution with previous unsolved cell as new current cell
					else {
						current.setValue(0);
						//Continue decrementing currentnum until the current cell is not solved
						//If there are no more unsolved cells, the puzzle is impossible
						try {
							do
								currentnum--;
							while (enumeration[currentnum].isSolved());
							enumeration[currentnum].setTempSolved(false);
						} catch (ArrayIndexOutOfBoundsException e) {
							impossible = true;
						}
					}
				}
			}
			//If the cell is already solved
			else {
				//If current cell is last cell, puzzle is solved
				if (currentnum==80)
					puzzleSolved = true;
				//If current cell is not last cell, continue solution with next cell as new current cell
				else
					currentnum++;
			}
		}
		
		//Print the solved puzzle
		if (puzzleSolved)
			print();
		else
			System.out.println("This puzzle cannot be solved.");
	}
	
	//Helper method to print the puzzle
	public static void print() {
		for (int i=0; i<9; i++) {
			for (Cell cell : field[i])
				System.out.print(cell.getValue() + " ");
			System.out.println("");
		}
	}
	
	//Helper method to check to see if the value of a cell can work for that cell
	public static boolean checkCell(Cell cell, int value) {
		//Create variables to represent the row and column the cell will be checked against
		int r = cell.getRow();
		int c = cell.getColumn();
		int z = cell.getZone();
		
		//Check value against values of the other row members
		for (Cell current : field[r]) {
			if ((current.isSolved() || current.isTempSolved()) && current.getValue()==value)
				return false;
		}
		
		//Check value against values of the other column members
		for (int i=0; i<9; i++) {
			if ((field[i][c].isSolved() || field[i][c].isTempSolved()) && field[i][c].getValue()==value)
				return false;
		}
		
		//Check value against values of the other zone members
		for (Cell current : zone[z]) {
			if ((current.isSolved() || current.isTempSolved()) && current.getValue()==value)
				return false;
		}
		
		//If the cell's value is not equal to any of the values of the solved cells in the same row, column, and zone, return true
		return true;
	}
}
