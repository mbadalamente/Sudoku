
/**
 * This class creates the completed sudoku board that numbers are then removed from and is used for game play. 
 * I did follow an algorithm that I found online to help me generate the sudoku board and have it be original with
 * one unique solution for each game. It involves creating the basic version of the board that is used every time
 * and then shuffling the rows and columns within their groups (1-3) (4-6) (7-9).
 * Here's the link to what I followed: https://www.algosome.com/articles/create-a-solved-sudoku.html
 * 
 * @author madisonbadalamente
 * 4/26/20
 */

import java.util.Random;

public class Board {
	private int[][] board;

	public Board() {
		board = new int[9][9];
		createBasicBoard();
		shuffle();
	}

	// returns the item at the specific spot in the board
	public int get(int r, int c) {
		return board[r][c];
	}

	// creates the base version of a sudoku board that is then shuffled for the game
	public void createBasicBoard() {
		int num = 1;
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (num <= 9) {
					board[row][col] = num;
					num++;
				} else {
					num = 1;
					board[row][col] = num;
					num++;
				}
			}
			int tempNum = num + 3;
			if (num == 10) {
				tempNum = 4;
			}
			if (tempNum > 9) {
				tempNum = (tempNum % 9) + 1;
			}
			num = tempNum;
		}
	}

	// gets random columns and rows in each of the groups on the game board to
	// shuffle
	public void shuffle() {
		Random rand = new Random();
		// shuffles columns in first group
		int randCol1 = rand.nextInt(3);
		int randCol2 = rand.nextInt(3);
		while (randCol1 == randCol2) {
			randCol2 = rand.nextInt(3);
		}
		shuffleCols(randCol1, randCol2);

		// shuffles columns in second group
		randCol1 = rand.nextInt(3) + 3;
		randCol2 = rand.nextInt(3) + 3;
		while (randCol1 == randCol2) {
			randCol2 = rand.nextInt(3) + 3;
		}
		shuffleCols(randCol1, randCol2);

		// shuffles columns in third group
		randCol1 = rand.nextInt(3) + 6;
		randCol2 = rand.nextInt(3) + 6;
		while (randCol1 == randCol2) {
			randCol2 = rand.nextInt(3) + 6;
		}
		shuffleCols(randCol1, randCol2);

		// shuffles rows in the first group
		int randRow1 = rand.nextInt(3);
		int randRow2 = rand.nextInt(3);
		while (randRow1 == randRow2) {
			randRow2 = rand.nextInt(3);
		}
		shuffleRows(randRow1, randRow2);

		// shuffles rows in the second group
		randRow1 = rand.nextInt(3) + 3;
		randRow2 = rand.nextInt(3) + 3;
		while (randRow1 != randRow2) {
			randRow2 = rand.nextInt(3) + 3;
		}
		shuffleRows(randRow1, randRow2);

		// shuffles rows in the third group
		randRow1 = rand.nextInt(3) + 6;
		randRow2 = rand.nextInt(3) + 6;
		while (randRow1 != randRow2) {
			randRow2 = rand.nextInt(3) + 6;
		}
		shuffleRows(randRow1, randRow2);

	}

	// switches the elements in the two columns passed in
	public void shuffleCols(int col1, int col2) {
		for (int i = 0; i < 9; i++) {
			int num = board[i][col1];
			board[i][col1] = board[i][col2];
			board[i][col2] = num;
		}
	}

	// switches the elements in the the two rows passed in
	public void shuffleRows(int row1, int row2) {
		for (int i = 0; i < 9; i++) {
			int num = board[row1][i];
			board[row1][i] = board[row2][i];
			board[row2][i] = num;
		}
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				string = string + board[i][j] + "  ";
			}
			string = string + "\n";
		}
		return string;

	}

}
