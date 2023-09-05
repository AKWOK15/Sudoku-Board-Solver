/*
 * Aidan Kwok
 * Tests for grap class
 */
public class GraphTests {
    public static void main(String[] args) {

        // case 1: testing distanceFrom()
        {
            // setup
            Graph g1 = new Graph(0, 0);
            g1.addVertex();
            g1.addVertex();
            g1.addVertex();
            g1.addEdge(g1.vertices().get(0), g1.vertices().get(1), 1);
            g1.addEdge(g1.vertices().get(0), g1.vertices().get(2), 7);
            g1.addEdge(g1.vertices().get(1), g1.vertices().get(2), 1);

            // verify
            g1.getVertices().get(0);
            System.out.println(g1.distanceFrom(g1.vertices().get(0)));
            System.out.println(g1.distanceFrom(g1.vertices().get(0)).get(g1.getVertices().get(0)) + " == 0.0");
            System.out.println(g1.distanceFrom(g1.vertices().get(0)).get(g1.getVertices().get(1)) + " == 1.0");
            System.out.println(g1.distanceFrom(g1.vertices().get(0)).get(g1.getVertices().get(2)) + " == 2.0");

            // // test
            // Get specific element of map
            assert g1.distanceFrom(g1.vertices().get(0)).get(g1.getVertices().get(0)) == 0.0
                    : "Error in Graph::distanceFrom()";
            assert g1.distanceFrom(g1.vertices().get(0)).get(g1.getVertices().get(1)) == 1.0
                    : "Error in Graph::distanceFrom()";
            assert g1.distanceFrom(g1.vertices().get(0)).get(g1.getVertices().get(2)) == 2.0
                    : "Error in Graph::distanceFrom()";

        }
        // case 2: testing remove(Vertex vertex)
        {
            // setup
            Graph g1 = new Graph(0, 0);
            g1.addVertex();
            g1.addVertex();
            g1.addEdge(g1.getVertices().get(0), g1.getVertices().get(1), 1);
            g1.remove(g1.getVertices().get(0));

            // verify
            // Should be index 0 because we got rid of original index 0
            System.out.println(g1.getVertices().get(0).incidentEdges().size() + "== 0");

            // test
            assert g1.getVertices().get(0).incidentEdges().size() == 0 : "Error in Graph::remove(Vertex vertex)";

        }
        // case 3: testing remove(Edge edge)
        {
        // setup
        Graph g1 = new Graph(0, 0);
        g1.addVertex();
        g1.addVertex();
        g1.addEdge(g1.getVertices().get(0), g1.getVertices().get(1), 1);
        Boolean temp = g1.remove(g1.getEdge(g1.getVertices().get(0), g1.getVertices().get(1)));
        System.out.println(temp + "== true");
        System.out.println(g1.getEdges().size() + "== 0");

        // verify
        System.out.println(temp + "== true");
        System.out.println(g1.getEdges().size() + "== 0");
        // Checks to make sure that edge gets removed from Array List in graph.java and
        // is no longer in formerly adjacent vertex objects
        System.out.println(g1.getVertices().get(0).incidentEdges().size() + " == 0");
        System.out.println(g1.getVertices().get(1).incidentEdges().size() + " == 0");

        // test
        assert temp == true : "Error in Graph::remove(Edge edge)";
        assert g1.getEdges().size() == 0 : "Error in Graph::remove(Edge edge)";
        assert g1.getVertices().get(0).incidentEdges().size() == 0 : "Error in Graph::remove(Edge edge)";
        assert g1.getVertices().get(1).incidentEdges().size() == 0 : "Error in Graph::remove(Edge edge)";

    }
    System.out.println("Finished");

}
}
