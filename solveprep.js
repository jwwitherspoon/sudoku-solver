function Cell(row, column, zone) {
	this.row = row;
	this.column = column;
	this.zone = zone;
	this.value = 0;
	this.solved = false;
	this.tempSolved = false;
}

function createField() {
	var i, j;
	var field = [
		[new Cell(0,0,0), new Cell(0,1,0), new Cell(0,2,0), new Cell(0,3,1), new Cell(0,4,1), new Cell(0,5,1), new Cell(0,6,2), new Cell(0,7,2), new Cell(0,8,2)],
		[new Cell(1,0,0), new Cell(1,1,0), new Cell(1,2,0), new Cell(1,3,1), new Cell(1,4,1), new Cell(1,5,1), new Cell(1,6,2), new Cell(1,7,2), new Cell(1,8,2)],
		[new Cell(2,0,0), new Cell(2,1,0), new Cell(2,2,0), new Cell(2,3,1), new Cell(2,4,1), new Cell(2,5,1), new Cell(2,6,2), new Cell(2,7,2), new Cell(2,8,2)],
		[new Cell(3,0,3), new Cell(3,1,3), new Cell(3,2,3), new Cell(3,3,4), new Cell(3,4,4), new Cell(3,5,4), new Cell(3,6,5), new Cell(3,7,5), new Cell(3,8,5)],
		[new Cell(4,0,3), new Cell(4,1,3), new Cell(4,2,3), new Cell(4,3,4), new Cell(4,4,4), new Cell(4,5,4), new Cell(4,6,5), new Cell(4,7,5), new Cell(4,8,5)],
		[new Cell(5,0,3), new Cell(5,1,3), new Cell(5,2,3), new Cell(5,3,4), new Cell(5,4,4), new Cell(5,5,4), new Cell(5,6,5), new Cell(5,7,5), new Cell(5,8,5)],
		[new Cell(6,0,6), new Cell(6,1,6), new Cell(6,2,6), new Cell(6,3,7), new Cell(6,4,7), new Cell(6,5,7), new Cell(6,6,8), new Cell(6,7,8), new Cell(6,8,8)],
		[new Cell(7,0,6), new Cell(7,1,6), new Cell(7,2,6), new Cell(7,3,7), new Cell(7,4,7), new Cell(7,5,7), new Cell(7,6,8), new Cell(7,7,8), new Cell(7,8,8)],
		[new Cell(8,0,6), new Cell(8,1,6), new Cell(8,2,6), new Cell(8,3,7), new Cell(8,4,7), new Cell(8,5,7), new Cell(8,6,8), new Cell(8,7,8), new Cell(8,8,8)]
	];
	return field;
}

function createZone(field) {
	var zone = [
		[field[0][0], field[0][1], field[0][2], field[1][0], field[1][1], field[1][2], field[2][0], field[2][1], field[2][2]],
		[field[0][3], field[0][4], field[0][5], field[1][3], field[1][4], field[1][5], field[2][3], field[2][4], field[2][5]],
		[field[0][6], field[0][7], field[0][8], field[1][6], field[1][7], field[1][8], field[2][6], field[2][7], field[2][8]],
		[field[3][0], field[3][1], field[3][2], field[4][0], field[4][1], field[4][2], field[5][0], field[5][1], field[5][2]],
		[field[3][3], field[3][4], field[3][5], field[4][3], field[4][4], field[4][5], field[5][3], field[5][4], field[5][5]],
		[field[3][6], field[3][7], field[3][8], field[4][6], field[4][7], field[4][8], field[5][6], field[5][7], field[5][8]],
		[field[6][0], field[6][1], field[6][2], field[7][0], field[7][1], field[7][2], field[8][0], field[8][1], field[8][2]],
		[field[6][3], field[6][4], field[6][5], field[7][3], field[7][4], field[7][5], field[8][3], field[8][4], field[8][5]],
		[field[6][6], field[6][7], field[6][8], field[7][6], field[7][7], field[7][8], field[8][6], field[8][7], field[8][8]]
	];
	return zone;
}

function createEnumeration(field) {
	var i, j;
	var enumeration = [];
	for (i=0; i<9; i++) {
		for (j=0; j<9; j++) {
			if (field[i][j].solved == false) {
				enumeration.push(field[i][j]);
			}
		}
	}
	return enumeration;
}

//Returns a string version of the puzzle that should then be set as the innerHTML of an HTML element
function puzzleToText(field) {
	var i, j;
	var text = "";
	for (i=0; i<9; i++) {
		for (j=0; j<9; j++) {
			text += field[i][j].value + " ";
		}
		text += "<br>";
	}
	return text;
}