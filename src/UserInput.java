/**
 * This class holds the users input as an array based stack and gives them the
 * ability to add an item, look at the top item, or delete an item. They can
 * also access the row and column associated with the top item. (Even though I
 * changed it to an array based stack, I wanted to keep the nodes so I could
 * easily have a reference to the row and column of the item for when I am
 * adding and removing from the game board)
 * 
 * @author madisonbadalamente 4/26/20
 */

public class UserInput {

	private class Node {
		int item;
		int row;
		int col;

		public Node() {
			this.item = 0;
			this.row = 0;
			this.col = 0;
		}
	}

	private Node[] input;

	public UserInput() {
		input = new Node[0];
	}

	// returns if the stack is empty or not
	public boolean isEmpty() {
		if (input.length == 0) {
			return true;
		}
		return false;
	}

	// adds a new node to the array that includes it's item and where it is stored
	public void push(int item, int r, int c) {
		Node newNode = new Node();
		newNode.item = item;
		newNode.row = r;
		newNode.col = c;
		Node[] newArray = new Node[input.length + 1];
		for (int i = 0; i < input.length; i++) {
			newArray[i] = input[i];
		}
		input = newArray;
		input[input.length - 1] = newNode;
	}

	// removes and returns the top item in the stack / last item in the array
	public int pop() throws Exception {
		if (input.length == 0) {
			throw new Exception();
		} else {
			Node[] newArray = new Node[input.length - 1];
			Node top = input[input.length - 1];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = input[i];
			}
			input = newArray;
			return top.item;
		}
	}

	// returns the top item in the stack / last item in the array 
	public int peek() throws Exception {
		if (input.length == 0) {
			throw new Exception();
		} else {
			return input[input.length - 1].item;
		}
	}

	// returns the row of the top item in the stack / last item in the array
	public int topRow() {
		return input[input.length - 1].row;
	}

	// returns the column of the top item in the stack / last item in the array
	public int topCol() {
		return input[input.length - 1].col;
	}
}
