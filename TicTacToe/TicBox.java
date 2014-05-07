
package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The TicBox class is the display class. It is an extended JFrame, with a lot of
 * things added to its inital state, and a few new implemented methods.
 * @author scribble
 */
public class TicBox extends JFrame {

    private ArrayList<JPanel> boxes;
    private ArrayList<JLabel> labels;
    private JPanel background;
    private TicTacToe t;
    private Label reset;
    private JPanel board;

    /**
     * the constructor for TicBox sets some default behavior, initializes all of the
     * gameboard objects, adds them to the frame, draws the frame, and registers
     * the controller as a listener for the nine squares that make up the tic-tac-toe
     * board, as well as the reset "button" (which is just a label).
     * @param tic 
     */
    public TicBox(TicTacToe tic) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Dimension windowSize = new Dimension(330, 400);
        Dimension boxSize = new Dimension(100, 100);
        setPreferredSize(windowSize);
        background = new JPanel();
        background.setSize(400, 400);
        background.setBackground(Color.WHITE);
        add(background);
        t = tic;

        boxes = new ArrayList();
        labels = new ArrayList();

        reset = new Label("Start Over");
        reset.setName("reset");
        reset.setBackground(Color.ORANGE);
        reset.addMouseListener(t);
        reset.setAlignment(Label.CENTER);

        board = new JPanel();
        GridLayout grid = new GridLayout(3, 3);
        grid.setHgap(5);
        grid.setVgap(5);
        board.setLayout(grid);
        background.add(board);

        background.add(reset);

        /*
        * this is a loop that sets up all of the boxes for the gameboard.
        */
        for (int i = 0; i < 9; i++) {
            boxes.add(i, new JPanel());
            JPanel box = boxes.get(i);
            box = new JPanel();
            box.setName(String.valueOf(i));
            box.addMouseListener(t);
            box.setAlignmentY(TOP_ALIGNMENT);
            box.setAlignmentX(LEFT_ALIGNMENT);
            box.setPreferredSize(boxSize);
            box.setBackground(Color.LIGHT_GRAY);
            JLabel mark = new JLabel("");
            mark.setAlignmentX(Label.CENTER_ALIGNMENT);
            mark.setAlignmentY(Label.CENTER_ALIGNMENT);
            mark.setFont(new Font("Arial", Font.BOLD, 72));
            mark.setForeground(Color.black);
            box.add(mark);
            labels.add(mark);
            board.add(box);
        }

        setVisible(true);
        pack();
        repaint();

    }

    /**
     * this method allows the controller to set the window to redraw itself when
     * updates are passed in to the state.
     */
    public void draw() {
        pack();
        repaint();
    }

    /**
     * this closes the window when it is no longer needed, such as when the
     * game is reset and a new window is created.
     */
    public void close() {
        this.dispose();
    }

    /**
     * these two methods update the label in each of the squares and gives them
     * an x or an o depending on which method it is. the controller selects
     * the method based on feedback from the gamestate.
     * @param targetBox 
     */
    public void setXLabel(int targetBox) {
        JLabel label = labels.get(targetBox);
        label.setText("X");
        label.setForeground(Color.BLUE);

    }

    public void setOLabel(int targetBox) {
        JLabel label = labels.get(targetBox);
        label.setText("O");
        label.setForeground(Color.RED);

    }

}
