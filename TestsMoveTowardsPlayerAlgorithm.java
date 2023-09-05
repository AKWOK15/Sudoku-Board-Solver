/*
 * Aidan Kwok
 * Tests MoveTowardsPlayerAlgorithm class
 */
public class TestsMoveTowardsPlayerAlgorithm {
  /*
   * Tests
   */
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
          MoveTowardsPlayerAlgorithm m1 = new MoveTowardsPlayerAlgorithm(g1);
          Vertex temp = m1.chooseStart();
          // verify
          System.out.println(temp + " == " + m1.getCurrentVertex());

          //test
          assert temp.toString().equals(m1.getCurrentVertex().toString()) : "Error in MoveTowardsPlayerAlgorithm::chooseStart()";


        }
          // case 2: testing chooseStart(Vertex other)
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
            MoveTowardsPlayerAlgorithm m1 = new MoveTowardsPlayerAlgorithm(g1);
            Vertex temp = m1.chooseStart(g1.getVertices().get(1));
            // verify
            System.out.println(temp + " == " + m1.getCurrentVertex());
  
            //test
            assert temp.toString().equals(m1.getCurrentVertex().toString()) : "Error in MoveTowardsPlayerAlgorithm::chooseStart(Vertex other)";
  
  
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
          MoveTowardsPlayerAlgorithm m1 = new MoveTowardsPlayerAlgorithm(g1);
          m1.chooseStart(g1.vertices().get(0));
          // verify
          Vertex temp = m1.chooseNext(g1.vertices().get(3));
          System.out.println(temp + " == " + m1.getCurrentVertex());

          //test
          assert temp.toString().equals(m1.getCurrentVertex().toString()) : "Error in MoveTowardsPlayerAlgorithm::chooseNext()";


        }
        System.out.println("Finished");
}
}
