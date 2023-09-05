/*
 * Aidan Kwok
 * Tests RandomPlayer.java
 */
public class TestsRandomPlayer {
    /*
     * Tests
     */
    public static void main(String[] args) {

        // case 1: testing chooseStart() and chooseStart(Vertex other), they are the same thing
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
          assert temp.toString().equals(m1.getCurrentVertex().toString()) : "Error in RandomPlayer::chooseStart()";

        }
          // case 2: testing chooseNext()
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
            Vertex start = m1.chooseStart();
            Vertex next = m1.chooseNext(g1.getVertices().get(0));
            // verify
            System.out.println(start.adjacentVertices().contains(next) + "== true");
  
            //test
            assert start.adjacentVertices().contains(next) == true : "Error in RandomPlayer::chooseNext()";
  
          }
        System.out.println("Finished");
}
}
