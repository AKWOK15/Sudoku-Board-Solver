/*
 * Aidan Kwok
 * Tests Cell.java
 */
public class CellTests {
    /*
     * Testing Cell.java class
     */
    public static void main(String[] args) {
        // case 1: testing getRow()
        {
            // setup
            Cell c1 = new Cell(1, 2, 2);

            // verify
            System.out.println(c1.getRow() + " == 1");

            // test
            assert c1.getRow() == 1 : "Error in Cell::getRow()";
        }
        // case 2: testing getCol()
        {
            // setup
            Cell c1 = new Cell(10, 5, 2);

            // verify
            System.out.println(c1.getCol() + " == 5");

            // test
            assert c1.getCol() == 5 : "Error in Cell::getCol()";
        }
        // case 3: testing getValue()
        {
            // setup
            Cell c1 = new Cell(10, 5, 20);

            // verify
            System.out.println(c1.getValue() + " == 20");

            // test
            assert c1.getValue() == 20 : "Error in Cell::getValue())";
        }
        // case 4: testing setValue()
        {
            // setup
            Cell c1 = new Cell();
            c1.setValue(5);

            // verify
            System.out.println(c1.getValue() + " == 5");

            // test
            assert c1.getValue() == 5 : "Error in Cell::setValue()";
        }
        // case 5: testing isLocked()
        {
            // setup
            Cell c1 = new Cell(1, 1, 1, false);
            // verify
            System.out.println(c1.isLocked() + " == false");

            // test
            assert c1.isLocked() == false : "Error in Cell::isLocked()";
        }
        // case 6: testing setLocked()
        {
            // setup
            Cell c1 = new Cell(1, 1, 1, false);
            c1.setLocked(true);
            // verify
            System.out.println(c1.isLocked() + " == true");

            // test
            assert c1.isLocked() == true : "Error in Cell::setLocked()";
        }
        System.out.println("Finished!!!!");
    }
}
