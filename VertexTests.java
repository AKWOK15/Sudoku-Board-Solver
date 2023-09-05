/*
 * Aidan Kwok
 * Tests for Vertex.java
 */
public class VertexTests {

    public static void main(String[] args) {

        // case 1: testing getEdgeTo()
        {
            // setup
            Vertex v1 = new Vertex();
            Vertex v2 = new Vertex();
            Edge e1 = new Edge(v1, v2, 1);
            v1.addEdge(e1);
            v2.addEdge(e1);

            // verify
            System.out.println(v1.getEdgeTo(v2) + "==" + e1);
            System.out.println(v2.getEdgeTo(v1) + "==" + e1);

            // test
            assert v1.getEdgeTo(v2) == e1 : "Error in Vertex::getEdgeTo()";
            assert v2.getEdgeTo(v1) == e1 : "Error in Vertex::getEdgeTo()";

        }
        // case 1: testing removeEdge()
        {
            // setup
            Vertex v1 = new Vertex();
            Vertex v2 = new Vertex();
            Edge e1 = new Edge(v1, v2, 1);
            v1.addEdge(e1);
            v2.addEdge(e1);
            Boolean temp = v1.removeEdge(e1);

            // verify

            System.out.println(temp + " == true");

            // test
            assert temp == true : "Error in Vertex::removeEdge()";

        }
        System.out.println("Finished");
    }
    
}
