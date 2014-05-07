import java.awt.*;
/**
 * @author Nikita Chrystephan
 * @version 0.1
 * @assignment Shapes part 1
 * The circle class is a subclass of Abstract Shape, but it has been customized with circle-specific behaviors.
 * 
 */
public class Circle extends AbstractShape {
 
 //Math class is all coming back to me now!
 private int radius;
 
 /**
  * This constructs a default circle with a radius of 10px at the specified coordinates.
  * @param int x
  * @param int y
  */
 public Circle(int x, int y) {
  super(x-5,y-5);
  name = "Circle";
  width = 2*5;
  height = 2*5;
  radius = 5;
 }
 
 /**
  * This constructs a circle where you can specify the size using r.
  * @param int x
  * @param int y
  * @param int r
  */
 public Circle(int x, int y, int r) {
  super(x-r,y-r);
  name = "Circle";
  width = 2*r;
  height = 2*r;
  radius = r;
 }
 
 /**
  * This is just like the three parameter constructor, except you can select the color.
  * @param int x
  * @param int y
  * @param int r
  * @param Color c
  */
 public Circle(int x, int y, int r, Color c) {
  super(x-r,y-r);
  color = c;
  name = "Circle";
  width = 2*r;
  height = 2*r;
  radius = r;
 }

 /**
  * This is an updated collision detection scheme that tells if a supplied coordinate is within the radius!
  * returns a boolean value.
  * @param int x
  * @param int y
  * @return boolean
  */
 public boolean isOn (int x, int y) {
  boolean on = false;
  int xPos = xPosition + radius;
  int yPos = yPosition + radius;
  double tempX = Math.pow(x-xPos, 2);
  double tempY = Math.pow(y-yPos, 2);
  
  double dist = Math.sqrt(tempX + tempY);
  if(dist <= radius) {
   on = true;
  }
  return on;
 }
 
 /**
  * I overrode the moveTo method because the origin of the circle
  * is supposed to be it's center. This method moves the circle as
  * requested, but corrects for the center location.
  * @param int newX
  * @param int newY
  */
 public void moveTo(int newX, int newY) {
  xPosition = newX-radius;
  yPosition = newY-radius;
 }
 
 /**
  * The updated toString method returns information about the current state of the circle.
  */
 public String toString() {
  String str = "";
  str += "Shape: " + name + "\n";
  str += "Center: " + "(" + (xPosition+radius) + "," + (yPosition+radius) + ")" + "\n";
  str += "Radius: " + radius + "\n";
  str += "Color: " + color + "\n";
  str += "Selected: " + isSelected + "\n";
  return str;
 }
 
}
