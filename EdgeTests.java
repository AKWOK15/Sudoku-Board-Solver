/*
 * Aidan Kwok
 * Tests for edge class
 */
public class EdgeTests {
    public static void main(String[] args) {

        // case 1: testing other()
        {
            // setup
            Vertex v1 = new Vertex();
            Vertex v2 = new Vertex();
            Edge e1 = new Edge(v1, v2, 1);

            // verify
            System.out.println(e1.other(v1) + "==" + v2);
            System.out.println(e1.other(v2) + "==" + v1);

            // test
            assert e1.other(v1) != v1 : "Error in Edge::other()";
            assert e1.other(v2) != v2 : "Error in Edge::other()";

        }
          // case 2: testing vertices()
          {
            // setup
            Vertex v1 = new Vertex();
            Vertex v2 = new Vertex();
            Edge e1 = new Edge(v1, v2, 1);

            // verify
            System.out.println(e1.vertices()[0]);
            System.out.println(e1.vertices()[1]);
            

            // test
            assert e1.vertices().length == 2 : "Error in Edge::vertices()";


        }
        System.out.println("Finsihed");
    }
    
}