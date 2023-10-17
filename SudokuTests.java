/*
 * Aidan Kwok
 * Tests Sudoku.java
 */
public class SudokuTests {
    public static void main(String[] args) throws InterruptedException {
        // case 1: testing findNextValue()
        {
            // setup
            Board board = new Board();
            Sudoku s1 = new Sudoku(board);
            board.set(0, 0, 1);
            board.set(0, 1, 2);
            board.set(0, 2, 3);
            board.set(0, 3, 5);
            s1.findNextValue(board.get(0, 2));
            // verify
            System.out.println(s1.findNextValue(board.get(0, 2)) + " == 4");

            // // test
            assert s1.findNextValue(board.get(0, 2)) == 4 : "Error in Sudoku::findNextValue()";
        }
        // case 2: testing findNextCell()
        {
            // setup
            Board board = new Board();
            Sudoku s1 = new Sudoku(board);
            board.set(0, 0, 1);
            board.set(0, 1, 2);
            board.set(0, 2, 3);
            board.set(0, 4, 5);
            // verify
            Cell temp = s1.findNextCell();
            System.out.println(temp.getValue() + " == 4");
            System.out.println(temp.getRow() + " == 0");
            System.out.println(temp.getCol() + " == 3");

            // test
            assert temp.getValue() == 4 : "Error in Sudoku::findNextCell()";
            assert temp.getRow() == 0 : "Error in Sudoku::findNextCell()";
            assert temp.getCol() == 3 : "Error in Sudoku::findNextCell()";

        }
        // case 3: testing solve()
        {
            // setup
            Board board = new Board();
            board.set(0, 8, 1, true);
            board.set(1, 5, 1, true);
            board.set(2, 2, 2, true);
            board.set(5, 1, 1, true);
            board.set(8, 0, 1, true);
            Sudoku s1 = new Sudoku(board);

            // verify
            System.out.println(s1.toString());

            // test, takes a while to solve, but based on the toString, you can tell it
            // won't work
            assert s1.solve(0) == false : "Error in Sudoku::solve()";

        }
        System.out.println("Finished!!!");
    }
}
