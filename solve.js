function solve() {
	var field = createField();
	var zone = createZone(field);

	//If the word "test" is written in the text area, put a test puzzle in the box
	//Used for testing purposes only
	if (document.getElementById("input").value == "test") {
		document.getElementById("input").value =
			"5 3 0 0 0 4 0 1 0\n" +
			"0 8 6 7 0 0 0 5 0\n" +
			"0 1 0 3 5 0 9 8 0\n" +
			"8 0 0 0 9 0 1 4 3\n" +
			"0 0 0 0 0 0 0 0 0\n" +
			"4 5 1 0 3 0 0 0 7\n" +
			"0 7 8 0 2 5 0 3 0\n" +
			"0 4 0 0 0 1 8 6 0\n" +
			"0 6 0 4 0 0 0 7 1";
		return 0;
	}

	//If input matches correct syntax, create string representation of input
	//Otherwise, print a message asking for the correct syntax
	var text;
	var pattern = /(?:(?:\d\s){8}\d\n){8}(?:\d\s){8}\d/;
	if (pattern.test(document.getElementById("input").value)) {
		//Create string representation of puzzle input
		text = pattern.exec(document.getElementById("input").value)[0];
	}
	else {
		document.getElementById("solution").innerHTML = "Please use the correct syntax when entering your puzzle.";
		return 0;
	}
	
	//Split puzzle into rows
	var puzzle = text.split("\n");
	//Split rows into values
	for (var i=0; i<9; i++) {
		puzzle[i] = puzzle[i].split(" ");
	}
	//Add values to cells
	for (var i=0; i<9; i++) {
		for (var j=0; j<9; j++) {
			field[i][j].value = parseInt(puzzle[i][j]);
			//If the cell is given a value at the beginning or the puzzle, mark it solved
			if (field[i][j].value != 0) {
				field[i][j].solved = true;
			}
		}
	}
	//Create enumeration of cells in one long line so as to scroll through them better in the solution algorithm
	var enumeration = createEnumeration(field);

	//Solve puzzle using simple solving method
	//Number used to denote which index of enumeration should be used as the current cell
	var currentnum = 0;
	//While the puzzle is not solved and has not been proven impossible, run the solution algorithm
	var puzzleSolved = false, impossible = false;
	while (!puzzleSolved && !impossible) {
		//Determine the current cell
		var current = enumeration[currentnum];
		//Create two vars, one to store the current value and one to store the possible new value (starting with the next untested value)
		var prev = current.value, next = current.value + 1;
		//If the current cell is not tempsolved, try adding numbers into the cell until the entry does not violate the Sudoku condition or all values have been tested
		while (!current.tempSolved && next<=9 && current.value==prev) {
			if (checkCell(current, next, field, zone)) {
				current.value = next;
				current.tempSolved = true;
			}
			next++;
		}
		//If the value does not violate the Sudoku condition
		if (current.tempSolved) {
			//If current cell is last cell, puzzle is solved
			if (currentnum==enumeration.length-1) {
				puzzleSolved = true;
			}
			//If current cell is not last cell, continue solution with next cell as new current cell
			else {
				currentnum++;
			}
		}
		//If all values violate the Sudoku condition
		else {
			//If current cell is first cell, puzzle has no solution
			if (currentnum==0) {
				impossible = true;
			}
			//If current cell is not first cell, erase value of current cell and continue solution with previous unsolved cell as new current cell
			else {
				current.value = 0;
				currentnum--;
				enumeration[currentnum].tempSolved = false;
			}
		}
	}

	//Print puzzle
	if (puzzleSolved) {
		document.getElementById("solution").innerHTML = puzzleToText(field);
	}
	else {
		document.getElementById("solution").innerHTML = "This puzzle has no solution.";
	}
}

function checkCell(cell, value, field, zone) {
	//Create variables to represent the row and column the cell will be checked against
	var r = cell.row, c = cell.column, z = cell.zone, i = 0;

	//Check value against values of the other row members
	for (i=0; i<9; i++) {
		if ((field[r][i].solved || field[r][i].tempSolved) && field[r][i].value==value)
			return false;
	}

	//Check value against values of the other column members
	for (i=0; i<9; i++) {
		if ((field[i][c].solved || field[i][c].tempSolved) && field[i][c].value==value)
			return false;
	}

	//Check value against values of the other zone members
	for (i=0; i<9; i++) {
		if ((zone[z][i].solved || zone[z][i].tempSolved) && zone[z][i].value==value)
			return false;
	}

	//If the cell's value is not equal to any of the values of the solved cells in the same row, column, and zone, return true
	return true;
}