import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

/*
 * Aidan Kwok
 * Creates an intial board (array) of all 0s and can create locked values on the board as well
 * as check when a board is solved and if a value fits onto the board 
 */
public class Board {
  private Cell[][] board;
  private static final int SIZE = 9;
  private Random rand;
  private boolean finished;

  /*
   * Constructor
   */
  public Board() {
    board = new Cell[9][9];
    finished = false;
    for (int x = 0; x < 9; x++) {
      for (int i = 0; i < 9; i++) {
        board[x][i] = new Cell(x, i, 0);
      }
    }

  }

  /*
   * Constructor for extension to create differently sized boards
   */
  public Board(int row, int col, int numLocked) {
    rand = new Random();
    board = new Cell[row][col];
    finished = false;
    for (int x = 0; x < row; x++) {
      for (int i = 0; i < col; i++) {
        board[x][i] = new Cell(x, i, 0);
      }
    }
    for (int x = 0; x < numLocked; x++) {
      // Random number between 0 and getRows-1
      int r = rand.nextInt(getRows());
      int c = rand.nextInt(getCols());
      // Random number between 1 and 9
      int value = rand.nextInt(9) + 1;
      while (!validValue(r, c, value) || value(r, c) != 0) {
        r = rand.nextInt(getRows());
        c = rand.nextInt(getCols());
        value = rand.nextInt(9) + 1;
      }
      set(r, c, value, true);

    }
  }

  /*
   * Constructor that also calls read
   */
  public Board(String fileName) {
    board = new Cell[9][9];
    finished = false;
    for (int x = 0; x < 9; x++) {
      for (int i = 0; i < 9; i++) {
        board[x][i] = new Cell(x, i, 0);
      }
    }
    read(fileName);
  }

  /*
   * Constructor that specifies number of initial locked cells
   */
  public Board(int numLocked) {
    board = new Cell[9][9];
    finished = false;
    rand = new Random();
    for (int x = 0; x < 9; x++) {
      for (int i = 0; i < 9; i++) {
        board[x][i] = new Cell(x, i, 0);
      }
    }
    for (int x = 0; x < numLocked; x++) {
      // Random number between 0 and 8
      int row = rand.nextInt(9);
      int col = rand.nextInt(9);
      // Random number between 1 and 9
      int value = rand.nextInt(9) + 1;
      while (!validValue(row, col, value) || value(row, col) != 0) {
        row = rand.nextInt(9);
        col = rand.nextInt(9);
        value = rand.nextInt(9) + 1;
      }
      set(row, col, value, true);
    }

  }

  /*
   * Gets finished field
   */
  public boolean getFinished() {
    return finished;
  }

  /*
   * Sets finished field
   */
  public void setFinished(boolean finish) {
    finished = finish;
  }

  /*
   * Returns number of columns
   */
  public int getCols() {
    return board[0].length;
  }

  /*
   * Returns the number of row
   */
  public int getRows() {
    return board.length;
  }

  /*
   * Returns cell at given row and col
   */
  public Cell get(int row, int col) {
    return board[row][col];
  }

  /*
   * Returns whether Cell at r, c, is locked
   */
  public boolean isLocked(int r, int c) {
    return board[r][c].isLocked();
  }

  /*
   * Returns the number of locked Cells on the board
   */
  public int numLocked() {
    int numLocked = 0;
    for (int r = 0; r < getRows(); r++) {
      for (int c = 0; c < getCols(); c++) {
        if (isLocked(r, c)) {
          numLocked += 1;
        }
      }
    }
    return numLocked;
  }

  /*
   * Returns the value of Cell at r,c
   */
  public int value(int r, int c) {
    return board[r][c].getValue();
  }

  /*
   * Sets cell at given row and col to given value
   */
  public void set(int row, int col, int value) {
    board[row][col].setValue(value);
    ;
  }

  /*
   * Sets a cell at given row and col to a value and whether or not to be locked
   */
  public void set(int row, int col, int value, boolean locked) {
    board[row][col].setValue(value);
    board[row][col].setLocked(locked);
  }

  /*
   * Reads file
   */
  public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing
      // filename to the constructor
      FileReader fr = new FileReader(filename);
      // assign to a variable of type BufferedReader a new BufferedReader, passing the
      // FileReader variable to the constructor
      BufferedReader br = new BufferedReader(fr);

      // assign to a variable of type String line the result of calling the readLine
      // method of your BufferedReader object.
      String line = br.readLine();
      // start a while loop that loops while line isn't null
      int a = 0;
      while (line != null) {
        // print line
        // System.out.println(line);
        // assign to an array of Strings the result of splitting the line up by spaces
        // (line.split("[ ]+"))
        String[] contents = line.split("[ ]+");
        // print the size of the String array (you can use .length)
        // use the line to set various Cells of this Board accordingly
        for (int x = 0; x < 9; x++) {
          set(a, x, Integer.parseInt(contents[x]));
          if (Integer.parseInt(contents[x]) != 0) {
            board[a][x].setLocked(true);
          }
        }
        a++;
        // assign to line the result of calling the readLine method of your
        // BufferedReader object.
        line = br.readLine();
      }
      br.close();
      return true;
    } catch (FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename);
    } catch (IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  /*
   * Tests if given value is valid at given row and column
   */
  public boolean validValue(int row, int col, int value) {
    if (row < 0 || row > 9 || col < 0 || col > 9) {
      return false;
    }
    // Check row, if there is already a five in there and I want to replace it with
    // a five, will return false
    for (int x = 0; x < getCols(); x++) {
      if (board[row][col].getValue() == value) {
        continue;
      } else if (board[row][x].getValue() == value) {
        return false;
      }
    }
    // Check col
    for (int x = 0; x < getRows(); x++) {
      if (board[row][col].getValue() == value) {
        continue;
      } else if (board[x][col].getValue() == value) {
        return false;
      }
    }
    // Check 3 x 3
    int rowCor = row / 3;
    int colCor = col / 3;
    for (int x = rowCor * 3; x < rowCor * 3 + 3; x++) {
      for (int y = colCor * 3; y < colCor * 3 + 3; y++) {
        if (board[row][col].getValue() == value) {
          continue;
        } else if (board[x][y].getValue() == value) {
          return false;
        }
      }
    }
    return true;

  }

  /*
   * Checks if board is solved
   */
  public boolean validSolution() {
    for (int x = 0; x < getRows(); x++) {
      for (int y = 0; y < getCols(); y++) {
        if (board[x][y].getValue() == 0 || validValue(x, y, board[x][y].getValue()) == false) {
          return false;
        }
      }
    }
    return true;
  }

  /*
   * Draws board by looping over each Cell and then drawing each cell
   */
  public void draw(Graphics g, int scale) {
    for (int i = 0; i < getRows(); i++) {
      for (int j = 0; j < getCols(); j++) {
        get(i, j).draw(g, j * scale + 5, i * scale + 10, scale);
      }
    }
    if (finished) {
      if (validSolution()) {
        g.setColor(new Color(0, 127, 0));
        g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale * 3 + 5, scale * 10 + 10);
      } else {
        g.setColor(new Color(127, 0, 0));
        g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale * 3 + 5, scale * 10 + 10);
      }
    }
  }

  /*
   * Formats board to termianl
   */
  public String toString() {
    String string = "";
    for (int x = 0; x < getRows(); x++) {
      for (int i = 0; i < getCols(); i++) {
        if (i % 3 == 0 && i != 0) {
          string += "|";
        }
        string += board[x][i].getValue() + " ";
      }
      string += "\n";
      if (x + 1 == 3 && x != 0 || x + 1 == 6 && x != 0) {
        string += "-------------------\n";
      }
    }
    return string;
  }

  /*
   * Tests
   */
  public static void main(String[] args) {
    Board board1 = new Board(6, 6, 10);
    System.out.println(board1.toString());
    // Board board1 = new Board(10);
    // Need command line argument, for example: java Board.java board1.txt
    // board1.read(args[0]);
    // System.out.println(board1.validSolution());
    // System.out.println(board1.numLocked());
    // System.out.println("Number of cols: " + board1.getCols());
    // System.out.println("Number of rows: " + board1.getRows());
    // System.out.println("Cell at (0,1): " + board1.get(0, 1));
    // System.out.println("Board lock at (0,0): " + board1.isLocked(0, 0));
    // System.out.println("Board lock at (0,3): " + board1.isLocked(0, 3));
    // System.out.println("Board lock at (0,4): " + board1.isLocked(0, 4));
    // System.out.println("Number of locked cells: " + board1.numLocked());
    // System.out.println("Value of cell at (0,1): " + board1.value(0, 1));
    // board1.set(0, 1, 10);
    // System.out.println("Value of cell at (0,1) after setting value to 10: " +
    // board1.value(0, 1));
    // board1.set(0, 1, true);
    // System.out.println("Is board locked at (0,1)" + board1.isLocked(0, 1));
    // System.out.println(board1.validValue(0, 0, 3));
    // System.out.println(board1.validValue(1, 3, 3));

  }
}
