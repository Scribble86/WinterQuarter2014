import java.awt.*;

public class Tee extends AbstractShape {
  
  private int[] tee;
  private int[] bar;
  int teeHeight;
  int teeWidth;
  int barHeight;
  int offset;
  
  public Tee(int x, int y) {
    super(x,y,10,10);
    bar = new int[4];
    tee = new int[4];
    name = "Tee";
    teeHeight = height/5;
    teeWidth = width;
    tee[0] = x;
    tee[1] = y;
    tee[2] = x + teeWidth;
    tee[3] = y + teeHeight;
    barHeight = height;
    offset = width / 10;
    bar[0] = x + (width / 2) - offset;
    bar[1] = y;
    bar[2] = x + (width / 2) + offset;
    bar[3] = y + barHeight;
  }
  
  public Tee(int x, int y, int h) {
    super(x,y,h,h);
    if(h % 10 != 0) {
      throw new IllegalArgumentException("Height must be a multiple of ten!");
    }  else {
      bar = new int[4];
      tee = new int[4];
      name = "Tee";
      teeHeight = height/20;
      teeWidth = width;
      tee[0] = x;
      tee[1] = y;
      tee[2] = x + teeWidth;
      tee[3] = y + teeHeight;
      barHeight = height;
      offset = width / 10;
      bar[0] = x + (width / 2) - offset;
      bar[1] = y;
      bar[2] = x + (width / 2) + offset;
      bar[3] = y + barHeight;
    }
  }
  
  public Tee(int x, int y, int h, Color c) {
    super(x,y,h,h);
    if(h % 10 != 0) {
      throw new IllegalArgumentException("Height must be a multiple of ten!");
    }  else {
      bar = new int[4];
      tee = new int[4];
      name = "Tee";
      color = c;
      int teeHeight = height/5;
      int teeWidth = width;
      tee[0] = x;
      tee[1] = y;
      tee[2] = x + teeWidth;
      tee[3] = y + teeHeight;
      int barHeight = height;
      int offset = width / 10;
      bar[0] = x + (width / 2) - offset;
      bar[1] = y;
      bar[2] = x + (width / 2) + offset;
      bar[3] = y + barHeight;
    }
  }
  
  public boolean isOn(int x, int y) {
    boolean inside = false;
    if(x >= tee[0] && x <= tee[2]) {
      if(y >= tee[1] && y <= tee[3]) {
        inside = true;
      }
    }
    if(x >= bar[0] && x <= bar[2]) {
      if(y >= bar[1] && y <= bar[3]) {
        inside = true;
      }
    }
    return inside;
  }
  
  public void moveTo(int newX, int newY) {
    super.moveTo(newX, newY);
    tee[0] = newX;
    tee[1] = newY;
    tee[2] = newX + teeWidth;
    tee[3] = newY + teeHeight;
    bar[0] = ((newX / 2) - offset);
    bar[1] = newY;
    bar[2] = ((newX / 2) + offset);
    bar[3] = newY + barHeight;
  }
  
  public void shiftBy(int deltaX, int deltaY) {
    super.shiftBy(deltaX, deltaY);
    tee[0] += deltaX;
    tee[1] += deltaY;
    tee[2] += deltaX;
    tee[3] += deltaY;
    bar[0] += deltaX;
    bar[1] += deltaY;
    bar[2] += deltaX;
    bar[3] += deltaY;
  }
  
  public String toString() {
    String str = "";
    str += "Shape: " + name + "\n";
    str += "Position: " + "(" + xPosition + "," + yPosition + ")" + "\n";
    str += "Size: " + width + " square " + "\n";
    str += "Color: " + color + "\n";
    str += "Selected: " + isSelected + "\n";
    return str;
  }
  
}
