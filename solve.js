function solve() {
	var field = createField();
	var zone = createZone(field);
	//Test puzzle functionality
	if (document.getElementById("0-0").value == 't') {
		createPuzzle();
		return 0;
	}

	//Reset message board
	document.getElementById("message").innerHTML = "";

	//Add values to cells
	for (var i=0; i<9; i++) {
		for (var j=0; j<9; j++) {
			//Get value of each cell
			var temp = document.getElementById(i + "-" + j).value;

			//If cell input is correct, fill cell with number.
			if (temp.trim() == "") {
				field[i][j].value = 0;
			}
			else if (temp > 0 && temp < 10) {
				field[i][j].value = parseInt(temp);
				field[i][j].solved = true;
			}
			else {
				document.getElementById("message").innerHTML = "Please enter only values 1-9 in cells.";
				return 0;
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
		while (!current.tempSolved && next <= 9 && current.value == prev) {
			if (checkCell(current, next, field, zone)) {
				current.value = next;
				current.tempSolved = true;
			}
			next++;
		}
		//If the value does not violate the Sudoku condition
		if (current.tempSolved) {
			//If current cell is last cell, puzzle is solved
			if (currentnum == enumeration.length - 1) {
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
			if (currentnum == 0) {
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
		document.getElementById("message").innerHTML = puzzleToText(field);
	}
	else {
		document.getElementById("message").innerHTML = "This puzzle has no solution.";
	}
}

//Function to create a puzzle in the grid for test purposes
function createPuzzle() {
	document.getElementById("0-0").value = 5; document.getElementById("0-1").value = 3; document.getElementById("0-5").value = 4; document.getElementById("0-7").value = 1;
	document.getElementById("1-1").value = 8; document.getElementById("1-2").value = 6; document.getElementById("1-3").value = 7; document.getElementById("1-7").value = 5;
	document.getElementById("2-1").value = 1; document.getElementById("2-3").value = 3; document.getElementById("2-4").value = 5; document.getElementById("2-6").value = 9; document.getElementById("2-7").value = 8;
	document.getElementById("3-0").value = 8; document.getElementById("3-4").value = 9; document.getElementById("3-6").value = 1; document.getElementById("3-7").value = 4; document.getElementById("3-8").value = 3;
	document.getElementById("5-0").value = 4; document.getElementById("5-1").value = 5; document.getElementById("5-2").value = 1; document.getElementById("5-4").value = 3; document.getElementById("5-8").value = 7;
	document.getElementById("6-1").value = 7; document.getElementById("6-2").value = 8; document.getElementById("6-4").value = 2; document.getElementById("6-5").value = 5; document.getElementById("6-7").value = 3;
	document.getElementById("7-1").value = 4; document.getElementById("7-5").value = 1; document.getElementById("7-6").value = 8; document.getElementById("7-7").value = 6;
	document.getElementById("8-1").value = 6; document.getElementById("8-3").value = 4; document.getElementById("8-7").value = 7; document.getElementById("8-8").value = 1;
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
