/*
 * Aidan Kwok
 * Tests MoveAwayPlayerAlgorithm
 */
public class TestsMoveAwayPlayerAlgorithm {
    public static void main(String[] args) {
        // case 1: testing chooseStart()
        {
            // setup
            Graph g1 = new Graph(0, 0);
            g1.addVertex();
            g1.addVertex();
            g1.addVertex();
            g1.addVertex();
            g1.addEdge(g1.getVertices().get(0), g1.getVertices().get(1), 1);
            g1.addEdge(g1.getVertices().get(1), g1.getVertices().get(2), 1);
            g1.addEdge(g1.getVertices().get(2), g1.getVertices().get(3), 1);
            g1.addEdge(g1.getVertices().get(3), g1.getVertices().get(0), 1);
            MoveAwayPlayerAlgorithm m1 = new MoveAwayPlayerAlgorithm(g1);
            Vertex temp = m1.chooseStart();
            // verify
            System.out.println(temp + " == " + m1.getCurrentVertex());

            // test
            assert temp.toString().equals(m1.getCurrentVertex().toString())
                    : "Error in MoveAwayPlayerAlgorithm::chooseStart()";

        }
        // case 2: testing chooseStart (Vertex other)
        {
            // setup
            Graph g1 = new Graph(0, 0);
            g1.addVertex();
            g1.addVertex();
            g1.addVertex();
            g1.addVertex();
            g1.addEdge(g1.getVertices().get(0), g1.getVertices().get(1), 1);
            g1.addEdge(g1.getVertices().get(1), g1.getVertices().get(2), 1);
            g1.addEdge(g1.getVertices().get(2), g1.getVertices().get(3), 1);
            g1.addEdge(g1.getVertices().get(3), g1.getVertices().get(0), 1);
            MoveAwayPlayerAlgorithm m1 = new MoveAwayPlayerAlgorithm(g1);
            // verify;
            Vertex temp = m1.chooseStart(g1.vertices().get(2));
            System.out.println(g1.distanceFrom(g1.vertices().get(2)).get(temp) + " == 2.0");
            ;
            // test
            assert g1.distanceFrom(g1.vertices().get(2)).get(temp) == 2.0
                    : "Error in MoveAwayPlayerAlgorithm::chooseStart(Vertex other)";

        }
        // case 3: testing chooseNext()
        {
            // setup
            Graph g1 = new Graph(0, 0);
            g1.addVertex();
            g1.addVertex();
            g1.addVertex();
            g1.addVertex();
            g1.addEdge(g1.getVertices().get(0), g1.getVertices().get(1), 1);
            g1.addEdge(g1.getVertices().get(1), g1.getVertices().get(2), 1);
            g1.addEdge(g1.getVertices().get(2), g1.getVertices().get(3), 1);
            g1.addEdge(g1.getVertices().get(3), g1.getVertices().get(0), 1);
            // verify;
            MoveAwayPlayerAlgorithm m1 = new MoveAwayPlayerAlgorithm(g1);
            m1.chooseStart(g1.vertices().get(2));
            // test
            assert m1.chooseNext(g1.vertices().get(3)).toString().equals(g1.vertices().get(1).toString())
                    : "Error in MoveAwayPlayerAlgorithm::chooseNext()";

        }
        System.out.println("Finished");
    }

}
