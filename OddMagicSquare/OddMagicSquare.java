/**
 * @author Nikita Chrystephan
 * @assignment OddMagicSquare
 * @version 0.1
 *  The OddMagicSquare class contains both static and non-static methods.
 *  You can create and test a magic square without instantiating the class.
 *  If you run the class as an application, it has command-line prompts to walk you through the
 *  process of creating and testing a magic square. It also displays the square you made.
 */
public class OddMagicSquare {

	int[][] square;
	int magicSquare;
	java.util.Scanner scan;
	boolean external;

	/**
	 * this is a zero parameter constructor for the class.
	 * This constructor is used when the class is run as an application,
	 * and also allows you to construct the class and run it's various
	 * methods separately.
	 */
	public OddMagicSquare() {
		external = false;
		scan = new java.util.Scanner(System.in);
		magicSquare = 1;
	}

	/**
	 * The one-parameter constructor creates a two-dimensional array of the specified size
	 * and then puts a magic square in that two-dimensional array, prints it out, and tests it.
	 * This constructor will throw an IllegalArgumentException if it is supplied with a number less than 1 or an even number.
	 * @param n a positive, odd integer.
	 */
	public OddMagicSquare(int n) {
		external = true;
		scan = new java.util.Scanner(System.in);
		if(testInput(n)) {
			square = new int[n][n];
			magicSquare = n;
			makeSquare(n);
			writeSquare();
			isMagic(square);
		} else throw new IllegalArgumentException("You may enter any positive odd number.");
	}

	/*
	 * This is an internal function that tests to be sure input is valid to make a magic square.
	 */
	private static boolean testInput(int n) {
		boolean valid = false;
		if(n > 0 && n % 2 != 0) {
			valid = true;
		}
		return valid;
	}

	/*
	 * this is a private method that is used if the class is run as an application.
	 * It gets input from the user, validates the input, and returns a validated integer.
	 * @return value an odd integer greater than 0.
	 */
	private int getNumber() {
		Boolean again = true;
		int value = 0;
		int test = 0;
		System.out.println("Today we're making magic boxes!");
		while(again == true) {
			System.out.print("Please choose an odd whole number: ");
			if(scan.hasNextInt()) {
				test = scan.nextInt();
				if(test > 0 && test % 2 != 0) {
					value = test;
					again = false;
				}
			} else {
				System.out.println("\"" + scan.next() + "\"");
				System.out.println("I'm sorry, that was not a valid input.");
			}
		}
		return value;
	}

	/**
	 * This method tests a given two-dimensional array to be sure if it is a magic square.
	 
	 * @param a must be a two-dimensional array
	 * @return boolean
	 */
	/*
	 * First, the method tests to be sure the array that is passed in is the correct shape and size,
	 * then it checks the contents of the array against the "magic number formula" that was given.
	 */
	public static boolean isMagic(int[][] a) {
		boolean magic = true;
		int size = a.length;
		int magicNumber = (size*((size * size) +1))/2;
		int prevLength = a[0].length;
		magic = testInput(a.length);
		for(int i = 1; i < a.length; i++) {
			if(a[i].length != prevLength || a[i].length != a.length) {
				magic = false;
			}
		}
		if(size % 2 != 0 && magic == true) {
			int diagonalOneTotal = 0;
			int diagonalTwoTotal = 0;
			for(int i = 0; i < size; i++) {
				diagonalOneTotal += a[i][i];
				diagonalTwoTotal += a[(size-1)-i][(size-1)-i];
				int rowTotal = 0;
				int columnTotal = 0;
				for(int j = 0; j < size; j++) {
					rowTotal += a[i][j];
					columnTotal +=a[j][i];
				}
				if(rowTotal != magicNumber || columnTotal != magicNumber) {
					magic = false;
					break;
				}
			}
			if(diagonalOneTotal != magicNumber || diagonalTwoTotal != magicNumber) {
				magic = false;
			}
		} else magic = false;
		if(magic) {
			System.out.println("You made a magic Square!");
		} else System.out.println("There is no magic in that square.");
		return magic;
	}

	/*
	 * this is a simple method that tests if an integer is less than zero or greater than a specified
	 * quantity. if it is, it returns a corrected value.
	 * @param int pos an integer
	 * @param int n an integer
	 * @return pos an integer
	 */
	private int validate(int pos, int n){
		if(pos > n-1) {
			pos = 0;
		}
		if(pos < 0) {
			pos = n-1;
		}
		return pos;
	}

	/*
	 * This is the method that constructs the magic square. it does not contain validation code,
	 * so must remain private. there is a separate method to access or create the magic square.
	 * The algorithm is a loop that starts in the middle right and works diagonally down to the right,
	 * wrapping back around if there's danger of going out-of-index. Rather than overwriting filled squares,
	 * the application shifts to the left by one space instead of jumping ahead.
	 * @param n an integer; the length and width of the square.
	 */
	private void makeSquare(int n) {
		int xPos = square.length-1;
		int yPos = square.length/2;
		int filled = 0;
		int area = square.length*square.length;
		while(filled < area) {
			int i = filled + 1;
			xPos = validate(xPos, n);
			yPos = validate(yPos, n);
			if(square[yPos][xPos] == 0) {
				square[yPos][xPos] = i;
				filled +=1;
			}
			if(square[validate(yPos + 1, n)][validate(xPos+1,n)] == 0) {
				yPos += 1;
				xPos += 1;
			} else xPos -= 1;
		}
	}

	/**
	 * this is a public method for creating a new magic square array.
	 * @param n an integer, it should be positive and an odd number.
	 */
	public void makeMagic( int n) {
		if(testInput(n)) {
			square = new int[n][n];
			makeSquare(n);
		} else throw new IllegalArgumentException("You may enter any positive odd number.");
	}

	/**
	 * This is the public accessor to retrieve the magic square array.
	 * @return int[][] returns a two-dimensional integer array containing a magic square.
	 */
	public int[][] getSquare() {
		return square;
	}

	/**
	 * the writeSquare method prints the contents of the magic square two-dimensional array to the console.
	 */
	public void writeSquare() {
		System.out.println("Here is your magic square:");
		for(int i = 0; i < square.length; i++) {
			System.out.print("|");
			for(int j = 0; j < square.length; j++) {
				System.out.print(square[i][j] + "|");
			}
			System.out.println("");
		}
	}

	/**
	 * this is a method that kicks off the textual prompts and controls the flow of the
	 * OddMagicSquare class when it is desirable to run it as an application.
	 */
	public void go() {
		magicSquare = getNumber();
		square = new int[magicSquare][magicSquare];
		makeSquare(magicSquare);
		writeSquare();
		isMagic(square);
	}

	/**
	 * The main method instantiates the class and runs the go method which
	 * controls the flow of the application.
	 * @param args
	 */
	public static void main(String[] args) {
		OddMagicSquare a = new OddMagicSquare();
		if( ! a.external) {
			a.go();
		}
	}
}
