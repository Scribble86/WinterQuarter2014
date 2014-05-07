import java.io.*;
import java.util.*;

/**
 * @author Nikita Chrystephan
 * @assignment Project 3: Recursive maze solver
 * @version 0.1
 * The recursive maze solver application seemed somewhat daunting at first. I was still trying to get my head around the concept of recursive algorithms!
 * I started with the other methods, creating methods to load and print the maze, before starting on the recursive algorithm. I also experimented with
 * some basic exercises that utilize recursive algorithms. This helped me to get my head around the concept a little more, to the point where I felt ready
 * to tackle the recursive algorithm. Since robustness against a malformed input file isn't a requirement, that has not been built in. The recursive algorithm
 * was actually not as hard to write as I anticipated, though making it branch properly without backing all the way up to the beginning did require some thought.
 * I am happy with the solver I have now. It does not always choose the shortest route, though. I would be interested in looking into how to make it choose a
 * shorter route, since I can think of a few ways of the top of my head, but I don't know how exactly to implement them. The first step would be to randomize the
 * direction it chooses when it gets a false return from one of it's other directions, rather than following the same order of instructions every time. Second might
 * be to call the main method multiple times and keep track of the distance each time until a lower bound is discovered, or to check multiple directions at every step
 * while solving, to see which one returns the shortest successful route. Perhaps both techniques could be used.
 */
public class PathFinder
{
  File f;  // to hold the maze file
  int height, width;  // to hold the dimensions of the maze
  char[][] maze;  // to hold the maze itself
  int startH, startW;  // to hold the start coordinates
  
  /**
   * @param file
   * @throws FileNotFoundException
   * The PathFinder constructor requires a file that is formatted in a specific way. It must include a text file where the top and bottom lines
   * consist of only 'X' characters, and the first and last character on any of the lines in between must be an 'X' character. There must be a
   * 'G' character and an 'S' character somewhere inside of these bounds. Other characters besides ' ' will be ignored/are not part of the 
   * specification. 
   * 
   * if I were to add robustness, i would probably add it to the 'readMazeFile' method, so that it would check that the conditions of the above
   * specification were met. an exception would be thrown in that case. However, the instructions were to expect a properly formatted file, so all testing
   * has been done with properly formatted files.
   */
  public PathFinder(File file) throws FileNotFoundException
  {
    //gives the file an internal reference value
    f = file;
    //this sets the height and width. This could probably be accomplished by the readMaze method, but you included it as a function to be filled, so it exists as a separate method.
    getDims();
    //this adds the maze info to the array and sets the startH & startW values.
    readMazeFile();
  }
  
  /**
   * @param args file paths
   * @throws FileNotFoundException
   * this is the method i used to test the application. it has been updated to accept file paths at the command line.
   * The application may accept multiple files at a time.
   */
  public static void main(String[] args) throws FileNotFoundException
  {
    for(int i = 0; i < args.length; i++)
    {
      File text = new File(args[i]);
      PathFinder p = new PathFinder(text);
      p.solve();
    }
  }
  
  /**
   * this is a controller method that calls the other methods and passes in the proper information. 
   * call this method to find a solution to your maze!
   * after the constructor runs, this class is ready to be run, even though it takes no input and returns nothing.
   */
  public void solve()
  {
    findPath(startW, startH);
    maze[startW][startH] = 'S';  // puts the 'S' back at the start location
    printSolution();
  }
  
  // sets the height and width of the maze
  private void getDims() throws FileNotFoundException
  {
    String row = "";
    int h = 0;
    Scanner scan = new Scanner(f);
    while(scan.hasNextLine())
    {
      h += 1;
      row = scan.nextLine();
    }
    maze = new char[row.length()][h];
    height = h;
    width = row.length();
    scan.close();
  }
  
  // creates the array and reads the maze file into it, also finds the start position.
  private void readMazeFile() throws FileNotFoundException
  {
    Scanner lineScan = new Scanner(f);
    int lineCount= 0;
    while(lineScan.hasNextLine()) 
    {
      String row = lineScan.nextLine();
      for(int i = 0; i < row.length(); i++)
      {
        char character = row.charAt(i);
        if(character == 'S')
        {
          startH = lineCount;
          startW = i;
        }
        maze[i][lineCount] = character;
        //test code:
        //System.out.println("maze[" + i + "][" + lineCount + "] contains: " + character);
      }
      lineCount++;
    }
    lineScan.close();
  }
  
  /**
   * @param H width
   * @param W height
   * @return boolean
   * The recursive method that finds the path. weirdly, it seems like i managed to switch the width and height somewhere along the way,
   * so while it looks like it should default to going right, it actually defaults to going down! I tested this with several different mazes
   * and it does work, so I'm going to leave it alone!
   * 
   * 
   */
  private boolean findPath(int H, int W)
  {
    if(maze[H][W] == 'G')
    {
      return true;
    } else if(maze[H][W] == 'X' || maze[H][W] == '.')
    {
      return false;
    } else 
    {
      maze[H][W] = '.';
      //test code
      //printSolution();
    }
    if(findPath(H,W+1))
    {
      return true;
    } else if(findPath(H+1,W))
    {
      return true;
    } else if(findPath(H,W-1))
    {
      return true;
    } else if(findPath(H-1,W))
    {
      return true;
    } else
    {
      maze[H][W] = ' ';
      return false;
    }
  }
  
// prints out the solved maze
  private void printSolution()
  {
    String output = "";
    for(int c = 0; c < height; c++) 
    {
      for(int r = 0; r < width; r++)
      {
        output += maze[r][c];
      }
      output += "\n";
    }
    System.out.print( output);
  }
}