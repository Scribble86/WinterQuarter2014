
package TicTacToe;

/**
 * This is the GameState class, which contains the logic for the tic-tac-toe
 * game itself. the rules for the game, the win (or draw) conditions, the current
 * game progress, whose turne it is, and whether or not the game is in progress is
 * all stored in here. 
 * @author scribble
 */
public class GameState {

    private char[][] board;
    private boolean turn;
    private char tic;
    private char notic;
    private boolean game;

    /**
     * the constructor creates an array to store the current game progress,
     * a boolean value for whether the game is in progress, and sets the initial
     * turn. It also stores the characters for whose turn it is and isn't.
     */
    public GameState() {
        board = new char[3][3];
        turn = false;
        tic = 'x';
        notic = 'o';
        game = true;
    }

    /**
     * this is a deprecated method that i am no longer using. it has been superceded
     * by the move method which is much simpler and works better.
     * @param position
     * @return String
     */
    public String add(int position) {
        int xPosition = position / 3;
        int yPosition = position % 3;
        // use '\u0000' if 0 doesn't work as expected
        if (board[yPosition][xPosition] == 0) {
            System.out.println(String.valueOf(board[yPosition][xPosition]));
            board[yPosition][xPosition] = tic;
            if (turn == false) {
                turn = true;
                tic = 'o';
                notic = 'x';
                System.out.println("tic:" + String.valueOf(tic));
            } else {
                turn = false;
                tic = 'x';
                notic = 'o';
                System.out.println(String.valueOf(tic));
            }
        } else if (board[yPosition][xPosition] != 0) {
            System.out.println("The space is full!");
        }
        System.out.println("Nothing happened.");
        return "";
    }

    /**
     * this method lets the controller know if the selected cell contains
     * a mark already, or is still empty.
     * @param position
     * @return 
     */
    public boolean isEmpty(int position) {
        int xPosition = position / 3;
        int yPosition = position % 3;
        return board[yPosition][xPosition] == 0;
    }

    /**
     * this returns the symbol for whose turn it is.
     * @return 
     */
    public String getTurn() {
        return String.valueOf(tic);
    }

    /**
     * this is the method that accepts input from the controller and updates the
     * game with the current mark, depending on whose turn it is. it also validates
     * that the chosen cell is empty before making a move. if the cell is not empty,
     * no move is made, and the current turn is not turned over. note that the state
     * is in charge of whose turn it is, not the controller.
     * @param position
     * @return 
     */
    public boolean move(int position) {
        int xPosition = position / 3;
        int yPosition = position % 3;
        // use '\u0000' if 0 doesn't work as expected
        if (board[yPosition][xPosition] == 0) {
            board[yPosition][xPosition] = tic;
            System.out.println(String.valueOf(board[yPosition][xPosition]));
            char temp = tic;
            tic = notic;
            notic = temp;
            return true;
        } else {
            System.out.println("The space is full!");
            return false;
        }
    }

    /**
     * this is the logic for checking whether the game has ended. if the game has ended,
     * it sets the game variable to false, signifying that the game is over. this prompts
     * the controller to reset the game state for a new game.
     * @return 
     */
    public String win() {
        for (int i = 0; i < 3; i++) {

            if ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][0] != 0)) {
                game = false;
                return ("Player " + String.valueOf(board[i][0]).toUpperCase() + " wins!");
            } else if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && (board[0][i] != 0)) {
                game = false;
                return ("Player " + String.valueOf(board[0][i]).toUpperCase() + " wins!");
            } else if ((board[0][0] == board[1][1]) && (board[2][2] == board[1][1]) && (board[0][0] != 0)) {
                game = false;
                return ("Player " + String.valueOf(board[0][0]).toUpperCase() + " wins!");
            } else if ((board[0][2] == board[1][1]) && (board[2][0] == board[1][1]) && (board[1][1] != 0)) {
                game = false;
                return ("Player " + String.valueOf(board[1][1]).toUpperCase() + " wins!");
            } else if (isCatsGame()) {
                game = false;
                return ("Meow! Cat's game!");
            }
        }
        return "";
    }

    /**
     * this is separate logic that tests if there is a cat's game. it is only called by the win method.
     * @return 
     */
    private boolean isCatsGame() {
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {
                if (board[i][n] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * lets other classes or methods know if the game is currently in progress.
     * @return 
     */
    public boolean isGameRunning() {
        return game;
    }
}
