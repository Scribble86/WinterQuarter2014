import java.awt.*;

/**

 * The Shape interface describes some of the basic methods of a graphical shape object that exist on two dimensions.
 * These methods will give any implemented shape class basic abilities so that it can interact with and be manipulated by the application.
 * @author Nikita Chrystephan
 * @version 0.1
 * @assignment Shapes part 1
 */
public interface Shape {

	/**
	 * This is a method that accepts an integer (x,y) point and returns a boolean (true/false) value.
	 * The method should return true if the point is inside the shape in question, but false if the point is not
	 * inside the shape in question.
	 * @param x an integer for the horizontal position of a point.
	 * @param y an integer for the vertical position of a point.
	 * @return boolean true/false if the point that was passed in is on the shape.
	 */
	public boolean isOn(int x, int y);

	/**
	 * This method returns true if the current object is selected and false if that object is not selected.
	 * @return boolean
	 */
	public boolean isSelected();
	
	/**
	 * This method sets a point to be selected or de-selects a selected point using a boolean operator.
	 * @param b boolean
	 */
	public void setSelected(boolean b);
	
	/**
	 * This method sets the visible color of a shape. 
	 * @param c a Color object.
	 */
	public void setColor(Color c);
	
	/**
	 * This method returns the color object that is assigned to the queried shape.
	 * @return
	 */
	public Color getColor();
	
	/**
	 * This method allows a shape to be moved by a certain defined amount, defined by the integer amount in
	 * deltaX and deltaY. Positive values move the shape to the right and down, negative values move it to the left and up.
	 * @param deltaX
	 * @param deltaY
	 */
	public void shiftBy(int deltaX, int deltaY);
	
	/**
	 * This method sets the position of the shape to the specified integer x and y coordinates.
	 * @param newX
	 * @param newY
	 */
	public void moveTo(int newX, int newY);
	
	/**
	 * This method returns a string that describes the current state of the shape object. It should contain the position, color,
	 * and dimensions of the shape, if applicable.
	 * @return String
	 */
	public String toString();
	
}
