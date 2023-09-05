import java.util.Random;
/*
 * Aidan Kwok
 * Moves player from vertex to vertex randomly 
 */

public class RandomPlayer extends AbstractPlayerAlgorithm {
    private Random rand;

    public RandomPlayer(Graph graph) {
        super(graph);
        rand = new Random();
    }

    /*
     * Returns random Vertex for player to start at and updates the current Vertex
     * to that
     * location
     */
    @Override
    public Vertex chooseStart() {
        Vertex start = graph.vertices().get(rand.nextInt(graph.vertices().size()));
        setCurrentVertex(start);
        return start;
    }

    /*
     * Returns random Vertex for player to start at and updates the current Vertex
     * to that location
     */
    @Override
    public Vertex chooseStart(Vertex other) {
        Vertex start = graph.vertices().get(rand.nextInt(graph.vertices().size()));
        setCurrentVertex(start);
        return start;
    }

    /*
     * Returns adjacent vertex for player to move to and updates the current Vertex
     * to that location
     */
    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        Vertex current = getCurrentVertex().adjacentVertices()
                .get(rand.nextInt(getCurrentVertex().adjacentVertices().size()));
        setCurrentVertex(current);
        return current;
    }

}
