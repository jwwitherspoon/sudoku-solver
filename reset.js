function reset() {
	for (i=0; i<9; i++) {
		for (j=0; j<9; j++) {
			document.getElementById(i + "-" + j).value = "";
			document.getElementById(i + "-" + j).style.color = "black";
		}
	}
}