
/**
 * This class holds the game board that is used to play the game. It takes answers from the stack and applies
 * them to the game board, and it also removes items if the user wishes to undo 
 * (To get the colored output, I downloaded ANSI in the eclipse marketplace and added the class with 
 * the constants. I also added another 2D array board, originalBoard, that holds the board with the 0's in place 
 * [the board that was originally made before the user started guessing] that does not change to 
 * help me keep the output colored when the user enters their guess into a spot). 
 * 
 * @author madisonbadalamente
 * 4/26/20
 */
import java.util.Random;

// class to hold the ansi constants that allow the output to have different colors
class ANSIConstants {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
}

public class GameBoard {

	private int[][] gameBoard;
	private int[][] originalBoard;
	private UserInput input;

	// makes the game board based on the item in the real board, but places a 0 at
	// random spots for the user to fill in
	public GameBoard(Board board, UserInput input) {
		this.input = input;
		Random rand = new Random();
		gameBoard = new int[9][9];
		originalBoard = new int[9][9];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (c == rand.nextInt(9)) {
					gameBoard[r][c] = 0;
					originalBoard[r][c] = 0;
				} else {
					gameBoard[r][c] = board.get(r, c);
					originalBoard[r][c] = board.get(r, c);

				}
			}
		}
	}

	// converts the board to a string, displaying the row and column numbers along
	// the outside of the board and replacing the zeros with *'s to make it more
	// clear to the user which spots they are supposed to fill in.
	// it also displays the spots a user is able to guess in a purple color
	public String toString() {
		String string = "     1  2  3     4  5  6     7  8  9\n" + "   -----------------------------------\n";
		for (int i = 0; i < 9; i++) {
			if (i > 0 && i % 3 == 0) {
				string = "\n" + string + "  |-----------------------------------|\n";
			}
			string = string + (i + 1) + " ";
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0) {
					string = string + "|  ";
				}
				if (gameBoard[i][j] == 0) {
					string = string + ANSIConstants.ANSI_PURPLE + "*" + ANSIConstants.ANSI_WHITE + "  ";
				} else if (originalBoard[i][j] == 0) {
					string = string + ANSIConstants.ANSI_PURPLE + gameBoard[i][j] + ANSIConstants.ANSI_WHITE + "  ";

				} else {
					string = string + gameBoard[i][j] + "  ";
				}
			}
			string = string + "|\n";
		}
		string = string + "   -----------------------------------";

		return string;

	}

	// sets the spot of the game board to the top item of the stack, not allowing
	// the user to guess a spot that was not marked by an *
	public void set(int r, int c) throws Exception {
		if (originalBoard[r][c] != 0) {
			System.out.println("You cannot change a number that was already there. Chose a space marked by "
					+ ANSIConstants.ANSI_PURPLE + "*" + ANSIConstants.ANSI_WHITE + " or a " + ANSIConstants.ANSI_PURPLE
					+ "purple number" + ANSIConstants.ANSI_WHITE + ".");
			input.pop();
		} else {
			gameBoard[r][c] = input.peek();
			System.out.println(input.peek() + " was added to the cell located at " + (r + 1) + "," + (c + 1) + ".");
		}
	}

	// checks to see if the users board matches the correct game board
	public boolean compare(Board board) {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (board.get(r, c) != gameBoard[r][c]) {
					return false;
				}
			}
		}
		return true;
	}

	// undoes the users last move, not allowing them to undo if there are no moves
	// to undo
	public void undo() {
		if (input.isEmpty()) {
			System.out.println("No move to undo.");
		} else {
			int r = input.topRow();
			int c = input.topCol();
			gameBoard[r - 1][c - 1] = 0;
			try {
				System.out.print("Removed " + input.peek() + " from " + input.topRow() + "," + input.topCol() + ".");
				input.pop();
			} catch (Exception e) {
				System.out.println("No move to undo.");
			}
		}
	}

	// checks to see if the game board is full, used for the game state
	public boolean isFull() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (gameBoard[r][c] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
