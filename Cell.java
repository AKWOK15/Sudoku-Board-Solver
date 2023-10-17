import java.awt.Color;
import java.awt.Graphics;

/*
 * Aidan Kwok
 * Creates a cell that has a x, y coordinate, a value, and locked boolean 
 * that the board then uses
 */
public class Cell {
    private int rowIndex;
    private int columnIndex;
    private int value;
    private boolean locked;

    /*
     * Constructer with no parameters
     */
    public Cell() {
        rowIndex = 0;
        columnIndex = 0;
        value = 0;
        locked = false;
    }

    /*
     * Constructor with three paramters
     */
    public Cell(int row, int col, int val) {
        rowIndex = row;
        columnIndex = col;
        value = val;
        locked = false;
    }

    /*
     * Constructor with four paramters
     */
    public Cell(int row, int col, int val, boolean lock) {
        rowIndex = row;
        columnIndex = col;
        value = val;
        locked = lock;
    }

    /*
     * Gets cell's row
     */
    public int getRow() {
        return rowIndex;
    }

    /*
     * Get cell's column
     */
    public int getCol() {
        return columnIndex;
    }

    /*
     * Get Cell's value
     */
    public int getValue() {
        return value;
    }

    /*
     * Set Cell's value
     */
    public void setValue(int val) {
        value = val;
    }

    /*
     * Returns boolean of locked field
     */
    public boolean isLocked() {
        return locked;
    }

    /*
     * Sets locked field to new boolean
     */
    public void setLocked(boolean lock) {
        locked = lock;
    }

    /*
     * Draws cell's number
     */
    public void draw(Graphics g, int x, int y, int scale) {
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked() ? Color.BLUE : Color.RED);
        g.drawChars(new char[] { toDraw }, 0, 1, x, y);
    }

    /*
     * Returns string with Cell's numeric value
     */
    public String toString() {
        return "Cell's value is: " + value;
    }

    /*
     * Tests
     */
    public static void main(String[] args) {
        Cell cell1 = new Cell(1, 2, 8, false);
        System.out.println(cell1.isLocked());
        System.out.println(cell1.getCol());
        System.out.println(cell1.getRow());

    }
}
