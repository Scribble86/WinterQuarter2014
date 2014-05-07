package TicTacToe;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * This is the controller class for the Tic-Tac-Toe application. It instantiates
 * the classes that handle the game-logic and display information. 
 * 
 *
 * @author scribble
 */
public class TicTacToe implements MouseListener {

    TicBox display;
    GameState state;

    /**
     * The constructor
     * for the class instantiates the other two classes and gives a reference to this 
     * class to the display class (so that this class can be registered as a listener).
     */
    public TicTacToe() {
        display = new TicBox(this);
        state = new GameState();
    }

    /**
     * The main method instantiates this class. No other input is needed here,
     * since this is an event-driven program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TicTacToe t = new TicTacToe();
    }

    /**
     * some of these methods just needed to be made. they don't need to do anything,
     * though. perhaps if i wanted to create rollover effects and extra-pretty animations,
     * these methods could be filled in. I'm not going to do those things though,
     * since this assignment is already late.
     * @Override @param e (MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * The mouseReleased method overrides the default mouseListener method that does
     * nothing. it is the one I chose to use for responses to user mouse input.
     * It basically sends information to the state about what square
     * was clicked on, and then updates the internal game model.
     * @param e (MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        System.out.println("Event Captured!");
        Object source = e.getSource();
        String name = "";
        try {
            name = ((java.awt.Component) source).getName();
            /*this catch will catch a theoretical exception where the called
            *object is not a component object, or does not have a getName method.
            */
        } catch (Exception i) {
            System.out.println("Something went wrong.");
        }
        System.out.println("Active object:" + name);
        /* there is probably a better way to implement this that uses fewer lines.
        * renaming 'reset' to a number and then extracting the numeric value of the
        * string and using that as the input for a singular function would be elegant,
        * but switch works just fine.
        */
        switch (name) {

            case "0":
                callUpdate(0);
                break;
            case "1":
                callUpdate(1);
                break;
            case "2":
                callUpdate(2);
                break;
            case "3":
                callUpdate(3);
                break;
            case "4":
                callUpdate(4);
                break;
            case "5":
                callUpdate(5);
                break;
            case "6":
                callUpdate(6);
                break;
            case "7":
                callUpdate(7);
                break;
            case "8":
                callUpdate(8);
                break;
                /* The reset case implements a very easy reset by just throwing
                * away the current objects that maintain state information and
                * starting again with fresh ones.
                */
            case "reset":
                state = new GameState();
                display.close();
                display = new TicBox(this);
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * the switch above was doing the same thing, mostly, for each call. this
     * seemed like a good thing to encapsulate into it's own method, so this
     * method is the main switchboard of the application, translating input toward
     * the state, receiving validation of the input from the state, then updating
     * the display as necessary.
     * @param n 
     */
    private void callUpdate(int n) {
        String s = state.getTurn();
        if (state.move(n)) {
            System.out.println("currentTurn: " + s);
            if (s.equals("x")) {
                display.setXLabel(n);
            } else if (s.equals("o")) {
                display.setOLabel(n);
            }
            display.draw();
            if ( ! state.win().equals("")) {
                JOptionPane.showMessageDialog(display, state.win());
                //resets the board when the game is complete.
                if (!state.isGameRunning()) {
                    state = new GameState();
                    display.close();
                    display = new TicBox(this);
                }
            }
        }
    }
}
