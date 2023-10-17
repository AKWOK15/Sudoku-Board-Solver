/*
 * Aidan Kwok
 * Tests Board.java
 */
public class BoardTests {
    /*
     * Tests
     */
    public static void main(String[] args) {
        // case 1: testing getRows()
        {
            // setup
            Board b1 = new Board();

            // verify
            System.out.println(b1.getRows() + " == 9");

            // test
            assert b1.getRows() == 9 : "Error in Board::getRows()";
        }
        // case 2: testing getCols()
        {
            // setup
            Board b1 = new Board();

            // verify
            System.out.println(b1.getCols() + " == 9");

            // test
            assert b1.getCols() == 9 : "Error in Board::getCols()";
        }
        // case 3: testing get()
        {
            // setup
            Board b1 = new Board();

            // verify
            // calling get calls cell's to string method
            System.out.println(b1.get(0, 0) + " == Cell's value is: 0");

            // test
            assert b1.get(0, 0) instanceof Cell : "Error in Board::get()";
        }
        // case 4: testing isLocked()
        {
            // setup
            Board b1 = new Board();
            b1.get(0, 0).setLocked(true);
            b1.get(8, 8).setLocked(true);

            // verify
            System.out.println(b1.isLocked(0, 0) + " == true");
            System.out.println(b1.isLocked(8, 8) + " == true");

            // test
            assert b1.isLocked(0, 0) == true : "Error in Board::isLocked()";
            assert b1.isLocked(8, 8) == true : "Error in Board::isLocked()";
        }
        // case 5: testing numLocked()
        {
            // setup
            Board b1 = new Board(20);
            Board b2 = new Board(30);

            // verify
            System.out.println(b1.numLocked() + " == 20");
            System.out.println(b2.numLocked() + " == 30");

            // test
            assert b1.numLocked() == 20 : "Error in Board::numLocked()";
            assert b2.numLocked() == 30 : "Error in Board::numLocked()";
        }
        // case 5: testing set(int r, int c, int value) and value(int r, inc c)
        {
            // setup
            Board b1 = new Board(0);
            b1.set(0, 0, 9);
            b1.set(5, 5, 4);

            // verify
            System.out.println(b1.value(0, 0) + " == 9");
            System.out.println(b1.value(1, 1) + " == 0");
            System.out.println(b1.value(5, 5) + " == 4");

            // test
            assert b1.value(0, 0) == 9 : "Error in Board::set(int r, intc, int value) or value(int r, inc c)";
            assert b1.value(1, 1) == 0 : "Error in Board::set(int r, intc, int value) or value(int r, inc c)";
            assert b1.value(5, 5) == 4 : "Error in Board::set(int r, intc, int value) or value(int r, inc c)";
        }
        // case 6: testing set(int r, int c, int value, boolean locked)
        {
            // setup
            Board b1 = new Board(20);
            b1.set(0, 0, 9, true);
            b1.set(5, 5, 4, false);

            // verify
            System.out.println(b1.value(0, 0) + " == 9");
            System.out.println(b1.isLocked(0, 0) + " == true");
            System.out.println(b1.value(5, 5) + " == 4");
            System.out.println(b1.isLocked(5, 5) + " == false");
            // test
            assert b1.value(0, 0) == 9 : "Error in Board::set(int r, intc, int value, boolean locked)";
            assert b1.isLocked(0, 0) == true : "Error in Board::set(int r, intc, int value, boolean locked)";
            assert b1.value(5, 5) == 4 : "Error in Board::set(int r, intc, int value, boolean locked)";
            assert b1.isLocked(5, 5) == false : "Error in Board::set(int r, intc, int value, boolean locked)";
        }
        // case 7: testing validValue()
        {
            // setup
            Board b1 = new Board(0);
            b1.set(0, 0, 1, true);

            // verify
            System.out.println(b1.validValue(5, 0, 1) + " == false");
            System.out.println(b1.validValue(0, 8, 1) + " == false");
            System.out.println(b1.validValue(2, 2, 1) + " == false");
            System.out.println(b1.validValue(2, 3, 1) + " == true");
            // test
            assert b1.validValue(5, 0, 1) == false : "Error in Board::validValue()";
            assert b1.validValue(0, 8, 1) == false : "Error in Board::validValue()";
            assert b1.validValue(2, 2, 1) == false : "Error in Board::validValue()";
            assert b1.validValue(2, 3, 1) == true : "Error in Board::validValue()";
        }
        // case 8: testing validSolution()
        {
            // setup
            Board b1 = new Board();
            b1.read("solved.txt");
            Board b2 = new Board();
            b2.read("board1.txt");

            // verify
            System.out.println("solved.txt");
            System.out.println(b1.toString());
            System.out.println("board1.txt");
            System.out.println(b2.toString());

            // test
            assert b1.validSolution() == true : "Error in Board::validSolution()";
            assert b2.validSolution() == false : "Error in Board::validSolution()";
        }
        System.out.println("Finished!!!");

    }
}
