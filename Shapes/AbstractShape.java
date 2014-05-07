import java.awt.*;

/**
 * This is the abstract class implementation of the Shape interface. Currently, it doesn't have any abstract methods,
 * but I still don't want anyone instantiating this object, since it would act like a rectangle, but doesn't have the name.
 * i could have made the isOn method abstract, but I chose not to. Having a default interaction based on the rectangular outer
 * dimensions of a shape could be useful later on, even for other shapes.
 * @author Nikita Chrystephan
 * @version 0.1
 * @assignment Shapes part 1
 */
public abstract class AbstractShape implements Shape {

	//variables that contain the state of the object! nothing too special here.
	//width and height might not be absolutely crucial, since some shapes only have one accessable parameter,
	//or have other parameters that describe their size, but this way every shape exists inside of a rectangle
	//for itself, which could be useful.
	protected String name;
	protected int xPosition;
	protected int yPosition;
	protected int width;
	protected int height;
	protected Color color;
	protected boolean isSelected;
	
	/**
	 * This is the two-parameter constructor for AbstractShape. It specifies a position,
	 *  denoted by the x and y parameters, but does not specify a size.
	 *  a default size of 10px by 10px is selected. 
	 * @param int x
	 * @param int y
	 */
	public AbstractShape(int x, int y) {
		xPosition = x;
		yPosition = y;
		width = 10;
		height = 10;
		color = Color.black;
		isSelected = false;
		name = "AbstractShape";
	}
	
	/**
	 * the four parameter constructor differs from the above constructor in that a width and height are also selected.
	 * x and y are as above, w is the width, h is the height.
	 * @param int x
	 * @param int y
	 * @param int w
	 * @param int h
	 */
	public AbstractShape(int x, int y, int w, int h) {
		xPosition = x;
		yPosition = y;
		width = w;
		height = h;
		color = Color.black;
		isSelected = false;
		name = "AbstractShape";
	}
	
	/**
	 * The five parameter constructor is just like the four parameter constructor, but a color may be selected
	 * when the object is created.
	 * @param int x
	 * @param int y
	 * @param int w
	 * @param int h
	 * @param Color c
	 */
	public AbstractShape(int x, int y, int w, int h, Color c) {
		xPosition = x;
		yPosition = y;
		width = w;
		height = h;
		color = c;
		isSelected = false;
		name = "AbstractShape";
	}
	
	/**
	 * This is the default (rectangular) collision detection method. It simply checks to see if provided
	 * coordinates are inside the minimum and maximum coordinates of the box created by the position, width,
	 * and height. If the supplied coordinates are inside, returns true. if they are outside, returns false.
	 * @param int x
	 * @param int y
	 * @return boolean
	 */
	public boolean isOn (int x, int y) {
		boolean on = false;
		int xMax = xPosition + width;
		int yMax = yPosition + height;
		int xMin = xPosition;
		int yMin = yPosition;
		if(x >= xMin && x <= xMax && y >= yMin && y <= yMax) {
			on = true;
		}
		return on;
	}
	
	/**
	 * This is a simple method that changes the value of the boolean "isSelected" property to the one
	 * specified. requires a boolean value.
	 * @param boolean b
	 */
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	/**
	 * this is a simple method that returns a boolean value representing the selection state of the shape.
	 * @return boolean
	 */
	public boolean isSelected() {
		return isSelected;
	}
	
	/**
	 * This method returns the color of the current shape.
	 * @return Color 
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * This method sets the color to a specified color object.
	 * @param Color c
	 */
	public void setColor(Color c) {
		color = c;
	}
	
	/**
	 * This allows you to change the location of the shape to whatever you specify.
	 * an integer x and y coordinate is required.
	 * @param int newX
	 * @param int newY
	 */
	public void moveTo(int newX, int newY) {
		xPosition = newX;
		yPosition = newY;
	}
	
	/**
	 * This method allows you to update the location of the current shape by whatever values
	 * you wish (may be positive or negative). integer values for x and y are required.
	 * @param deltaX
	 * @param deltaY
	 */
	public void shiftBy(int deltaX, int deltaY) {
		xPosition += deltaX;
		yPosition += deltaY;
	}
	
	/**
	 * Returns a string that contains details about the current state of the object.
	 * @return String
	 */
	public String toString() {
		String str = "";
		str += "Shape: " + name + "\n";
		str += "Position: " + "(" + xPosition + "," + yPosition + ")" + "\n";
		str += "Size: " + width + " by " + height + "\n";
		str += "Color: " + color + "\n";
		str += "Selected: " + isSelected + "\n";
		return str;
	}
	
}
