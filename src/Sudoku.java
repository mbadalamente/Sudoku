
/**
 * This class runs the game. It generates a board, and then converts it to a game board.
 * It then asks the user for input, stores it in a stack, and then updates the game board accordingly.
 * The user also has the option to undo a move. The game ends when the board is filled and the program
 * tells the user if they have successfully completed the game or not. 
 * 
 * @author madisonbadalamente
 * 4/26/20
 */

import java.util.Random;
import java.util.Scanner;

public class Sudoku {
	public static void main(String[] args) throws Exception {
		System.out.println(
				"Welcome to Sudoku! \nTo play, fill the grid so that every column, row, and 3x3 box contains every number from 1-9.");
		Board board = new Board();
		UserInput input = new UserInput();
		GameBoard gameBoard = new GameBoard(board, input);
		boolean game = true;
		boolean compare = false;
		boolean full = false;
		Scanner console = new Scanner(System.in);

		// loop to run the game
		while (game) {
			// prints the game board and prompts the user for the first number
			System.out.println(gameBoard);
			System.out
					.println("Please enter the row of the cell you'd like to guess or enter 0 to unto your last move.");
			// gets a num and checks for valid input
			int r = console.nextInt();
			while (r < 0 || r > 9) {
				System.out.println("Please enter a valid number from 1-9 or 0 to undo your last move");
				r = console.nextInt();
			}
			// if they enter 0, undoes their last move, if not it prompts them for more
			// numbers
			if (r == 0) {
				gameBoard.undo();
			} else {
				System.out.println("Please enter the column of the cell you'd like to guess");
				// gets a num and checks for valid input
				int c = console.nextInt();
				while (c < 1 || c > 9) {
					System.out.println("Please enter a valid number from 1-9 ");
					c = console.nextInt();
				}
				System.out.println("What number would you like to place at " + r + "," + c + "?");
				// gets a num and checks for valid input
				int num = console.nextInt();
				while (num < 1 || num > 9) {
					System.out.println("Please enter a valid number from 1-9 ");
					num = console.nextInt();
				}
				// add the number to the stack of users guesses
				input.push(num, r, c);
				// adds the users number to the game board
				gameBoard.set(r - 1, c - 1);
			}
			// updates the game variable to hold if the board is correct or not
			compare = gameBoard.compare(board);
			full = gameBoard.isFull();
			if (gameBoard.compare(board) || gameBoard.isFull()) {
				game = false;
			}
		}
		// if the user's game board is the same as the generated one, it tells them
		// they're correct. if the board is full but not correct, it tells them they
		// have not successfully completed the game
		System.out.println(gameBoard);
		if (compare) {
			System.out.println("Congrats! You've finished the game!");

		} else if (full) {
			System.out.println("You've filled the game board, but it is not correct :(");
		}
	}

}
