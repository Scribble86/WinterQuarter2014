package TicTacToe;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.*;
/**
 *
 * @author scribble
 */
public class GameBoard extends JFrame {
    
    JPanel board;
    JPanel top;
    JPanel left;
    JPanel right;
    JPanel bottom;
    Graphics g;
    Dimension dFrame;
    Dimension dBoard;
    Dimension dTop;
    Dimension dSide;
    TicTacToe t;
    JLabel begin;
    JLabel clear;
    BorderLayout layout;
    
    public GameBoard(TicTacToe tic) {
//        dTop = new Dimension(500,50);
//        dSide = new Dimension(50,400);
        dFrame = new Dimension(400, 400);
        dBoard = new Dimension(400, 400);
        right = new JPanel();
        left = new JPanel();
        top = new JPanel();
        bottom = new JPanel();
//        right.setSize(dSide);
//        left.setSize(dSide);
//        top.setSize(dTop);
//        bottom.setSize(dTop);
        
        setName("Tic Tac Toe");
        t = tic;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(t);
        setPreferredSize(dFrame);
        layout = new BorderLayout();
        setLayout(layout);
        setVisible(true);
        setBackground(Color.DARK_GRAY);
        board = new JPanel();
        board.setAlignmentX(CENTER_ALIGNMENT);
        board.setAlignmentY(CENTER_ALIGNMENT);
        board.setSize(dBoard);
        board.setBackground(Color.WHITE);
        g = board.getGraphics();
        
        bottom.setBackground(Color.LIGHT_GRAY);
        top.setBackground(Color.LIGHT_GRAY);
        left.setBackground(Color.LIGHT_GRAY);
        right.setBackground(Color.LIGHT_GRAY);
        
        begin = new JLabel("Start Game");
        begin.setVerticalAlignment(SwingConstants.TOP);
        begin.setHorizontalAlignment(SwingConstants.CENTER);
        clear = new JLabel("Restart");
        clear.setVerticalAlignment(SwingConstants.TOP);
        clear.setHorizontalAlignment(SwingConstants.CENTER);
        top.add(begin);
        top.add(clear);
        add(top);
        add(left);
        add(board);
        add(right);  
        add(bottom);
              
        pack();
        repaint();
    }
    
}
