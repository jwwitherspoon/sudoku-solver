import java.util.Scanner;

public class TextSolver {
	static int[][] field = new int[9][9];
	
	public static void main(String[] args) {
		System.out.println("Enter your Sudoku grid. Use the number 0 to denote an empty cell.");
		System.out.println("Separate lines with Enter and numbers with Space.");
		//TODO Improve input process with a better grid
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				int temp = scan.nextInt();
				if (temp!=0)
					field[i][j] = temp;
			}
		}
		scan.close();
		
		//TODO Write solution algorithm
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				
			}
		}
		
		print();
	}
	
	public static void print() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++)
				System.out.print(field[i][j] + " ");
			System.out.println("");
		}
	}
	
}
