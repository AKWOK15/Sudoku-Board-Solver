import java.util.Random;
/*
 * Aidan Kwok
 * Solves a sudoku board by using a stack data structure
 */
public class Sudoku {
    private Board sudoku;
    private LandscapeDisplay display;
    /*
     * Constructor that creates board object with random amount of locked cells
     */
    public Sudoku(int initialLocked){
        // rand = new Random();
        // sudoku = new Board(rand.nextInt(0, 15));
        sudoku = new Board(initialLocked);
        display = new LandscapeDisplay(sudoku);
    }
    /*
     * Constructor that takes in file name
     */
    public Sudoku(String fileName){
        sudoku = new Board(0);
        sudoku.read(fileName);
        display = new LandscapeDisplay(sudoku);
    }
     /*
     * Constructor for creating boards with different dimensions
     */
    public Sudoku(int row, int col, int numLocked){
        sudoku = new Board(row, col, numLocked);
        display = new LandscapeDisplay(sudoku);
    }
    /*
     * Constructor used for SudokuTests.java
     */
    public Sudoku(Board board){
        this.sudoku = board;
        display = new LandscapeDisplay(sudoku);
    }
      /*
   * Gets finished field
   */
  public boolean getFinished(){
    return sudoku.getFinished();
  }
    /*
     * Finds a valid value for a cell, skips over the current value that didnt't work and all numbers before current value
     */
    public int findNextValue(Cell cell){
        for (int x = cell.getValue() + 1; x < 10; x++){
            if (sudoku.validValue(cell.getRow(), cell.getCol(), x)){
                return x;
            }
        }
        return 0;

    }
    /*
     * First, finds a cell that has a 0 value and then attempts to replace that 0 value with a valid value
     */
    public Cell findNextCell(){
        for (int x= 0; x < sudoku.getRows(); x++){
            for (int y = 0; y < sudoku.getCols(); y++){
                if (sudoku.value(x, y) == 0){
                    int newValue = findNextValue(sudoku.get(x,y));
                    sudoku.set(x, y, newValue);
                    if (newValue !=0){
                        return sudoku.get(x,y);
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }
    /*
     * Repeateldy calls findNextCell() to solve sudoku, if it cannot progress further, pops Cells off the stack to attempt
     * different combinations 
     */
    public boolean solve(int delay)throws InterruptedException{
        Stack <Cell> stack = new LinkedList<Cell>();
        while (stack.size() < sudoku.getRows() * sudoku.getCols() - sudoku.numLocked()){
            if (delay > 0){
                Thread.sleep(delay);
            }
            if (display != null){
                display.repaint();
            }
            Cell next = findNextCell();
            while (next == null && stack.size()>0){
                Cell undo = stack.pop();
                int findNext = findNextValue(undo);
                if (findNext != 0){
                    next = undo;
                    next.setValue(findNext);
                }
                else{
                    undo.setValue(0);
                }

            }
            if (next == null){
                // running out of items to quickly 
                return false;
            }
            else{
                stack.push(next);
            }
        }
        sudoku.setFinished(true);
        return true;
    }
    /*
     * Formats board to termianl 
     */
    public String toString(){
        return sudoku.toString();
    }
    /*
     * Tests for exploration
     */
    public static void main(String[] args)throws InterruptedException{
    //     // Tests reading in a board to solve
        if (args.length==2){
            Sudoku first = new Sudoku(args[1]);
            first.solve(0);
        }
        else if (args.length==1){
            Sudoku second = new Sudoku(Integer.parseInt(args[0]));
            second.solve(0);
        }
        else{
            System.out.println("For the command line please enter 'text filename' where filename the is text file of the initial board or enter 'x' where x is an integer");
            System.out.println("Ex: java Sudoku board1.txt");
        }
    //     System.out.println(first.toString());

    //    //  Tests creating board with 5 intial values
    //     Sudoku first = new Sudoku(5);
    //     first.solve(0);
    //     System.out.println(first.toString());

    //    //  Exploration tests
    //     long totalTime = 0;
    //     int numFinishBoard = 0;
    //     for (int x = 0; x < 50; x++){
    //         Sudoku first = new Sudoku(30);
    //         long start = System.nanoTime();
    //         first.solve(0);
    //         long end = System.nanoTime();
    //         if (first.getFinished()){
    //             numFinishBoard++;
    //             totalTime+=(end-start);
    //         }
            
    //     }
    //     long avgTotalTime = totalTime/50;
    //     System.out.println("avgTotalTime:" + avgTotalTime);
    //     System.out.println("numFinishBoard:" + numFinishBoard);
        
    //     // Tests extension to create different sized sudoku boards
    //     Sudoku s1 = new Sudoku(3, 3, 2);
    //     s1.solve(0);
    }
    
}
