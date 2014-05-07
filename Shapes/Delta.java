import java.awt.*;

/**
 * This is the Delta subclass! It creates an Isosceles triangle with an equal base and height.
 * It is based on the AbstractShape class. I did something different with the triangle, I am storing the
 * three corner points in two-dimensional arrays. This is just to satisfy my own sense of elegance. 
 * @author Nikita Chrystephan
 * @version 0.1
 * @assignment Shapes part 1
 */
public class Delta extends AbstractShape {
  
  //triangles are described using three points!
  public int[] p1;
  public int[] p2;
  public int[] p3;
  public float area;
  
  /**
   * This is the basic two-parameter constructor that creates a triangle at the specified location
   *  with base 10px and is 10px high. The "origin" of the triangle is the middle top, or the upper "point."
   * @param int x
   * @param int y
   */
  public Delta(int x, int y) {
    super(x - 5,y);
    name = "Delta";
    width = 10;
    height = 10;
    p1 = new int[2];
    p2 = new int[2];
    p3 = new int[2];
    p1[0] = x;
    p1[1] = y;
    p2[0] = x - (width / 2);
    p2[1] = y + height;
    p3[0] = x + (width / 2);
    p3[1] = y + height;
    area = calcArea(p1,p2,p3);
  }
  
  /**
   * The three parameter constructor lets you choose what size the triangle should be when you create it
   * in addition to the location.
   * @param int x
   * @param int y
   * @param int h
   */
  public Delta(int x, int y, int h) {
    super(x - (h / 2),y);
    if(h % 2 != 0) {
      throw new IllegalArgumentException("Height must be an even integer!");
    }  else {
      name = "Delta";
      width = h;
      height = h;
      p1 = new int[2];
      p2 = new int[2];
      p3 = new int[2];
      p1[0] = x;
      p1[1] = y;
      p2[0] = x - (h / 2);
      p2[1] = y + h;
      p3[0] = x + (h / 2);
      p3[1] = y + h;
      area = calcArea(p1,p2,p3);
    }
  }
  
  /**
   * The four parameter constructor is the same as the three parameter constructor except you can select the color.
   * @param int x
   * @param int y
   * @param int h
   * @param int c
   */
  public Delta(int x, int y, int h, Color c) {
    super(x - (h / 2),y);
    if(h % 2 != 0) {
      throw new IllegalArgumentException("Height must be an even integer!");
    }  else {
      name = "Delta";
      color = c;
      width = h;
      height = h;
      p1 = new int[2];
      p2 = new int[2];
      p3 = new int[2];
      p1[0] = x;
      p1[1] = y;
      p2[0] = x - (h / 2);
      p2[1] = y + h;
      p3[0] = x + (h / 2);
      p3[1] = y + h;
      area = calcArea(p1,p2,p3);
    }
  }
  
  /**
   * The isOn method has been updated to reflect the needs of a triangle. It calculates the area of the three triangles
   * made up by the points of the triangle and the supplied point. if the area of the three triangles is the same as the
   * area of the Delta, the point is inside the triangle.
   * @param int x
   * @param int y
   * @return boolean
   */
  public boolean isOn(int x, int y) {
    boolean inside = false;
    int[] p4 = new int[] {x,y};
    float a1 = calcArea(p1, p2, p4);
    float a2 = calcArea(p1, p3, p4);
    float a3 = calcArea(p2, p3, p4);
    float testArea = a1 + a2 + a3;
    
    if(Math.round(testArea) == Math.round(area)) {
      inside = true;
    }
    return inside;
  }
  
  //Find the distance between two points! also gives the side of a triangle.
  private float distance(int[] pointA,int[] pointB) {
    float x1 = pointA[0];
    float y1 = pointA[1];
    float x2 = pointB[0];
    float y2 = pointB[1];
    double d1 = Math.pow((x2-x1),2);
    double d2 = Math.pow((y2-y1),2);
    double d3 = (d1+d2);
    float d =(float) Math.sqrt(d3);
    return d;
  }
  
  //this is a method that calculates the area of a triangle, given three x,y points encapsulated
  //in two-dimensional arrays.
  private float calcArea(int[] a, int[] b, int[] c) {
    float s1 = distance(a,b);
    float s2 = distance(a,c);
    float s3 = distance(b,c);
    float semiP = (s1 + s2 + s3)/2;
    
    float area = (float) Math.sqrt(semiP*(semiP-s1)*(semiP-s2)*(semiP-s3));
    
    return area;  
  }
  
  public void moveTo(int newX, int newY) {
    super.moveTo(newX - (width /2), newY);
    p1[0] = newX;
    p1[1] = newY;
    p2[0] = newX - (width / 2);
    p2[1] = newY + height;
    p3[0] = newX + (width / 2);
    p3[1] = newY + height;
  }
  
  public void shiftBy(int deltaX, int deltaY) {
    super.shiftBy(deltaY, deltaY);
    p1[0] += deltaX;
    p1[1] += deltaY;
    p2[0] += deltaX;
    p2[1] += deltaY;
    p3[0] += deltaX;
    p3[1] += deltaY;
  }
  
  /**
   * The toString method has been updated to return state information about a Delta object.
   */
  public String toString() {
    String str = "";
    str += "Shape: " + name + "\n";
    str += "Apex: " + "(" + p1[0] + "," + p1[1] + ")" + "\n";
    str += "Area: " + area + "\n";
    str += "Color: " + color + "\n";
    str += "Selected: " + isSelected + "\n";
    return str;
  }
}
