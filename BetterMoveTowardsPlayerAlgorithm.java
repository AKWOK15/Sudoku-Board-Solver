import java.util.Random;

/*
 * Aidan Kwok
 * Extension that improves MoveTowardsPlayerAlgorithm 
 * Handles the pursuer, moves pursuer to neighboring vertex that is closest to the evader
 */
public class BetterMoveTowardsPlayerAlgorithm extends AbstractPlayerAlgorithm {
    private Random rand;

    public BetterMoveTowardsPlayerAlgorithm(Graph graph) {
        super(graph);
        rand = new Random();

    }

    /*
     * Returns Vertex for player to start at and updates the current Vertex to that
     * location
     */
    @Override
    public Vertex chooseStart() {
        Vertex start = graph.vertices().get(rand.nextInt(graph.vertices().size()));
        setCurrentVertex(start);
        return start;
    }

    /*
     * Starts pursuer where evader is
     */
    @Override
    public Vertex chooseStart(Vertex other) {
        setCurrentVertex(other);
        return other;
    }

    /*
     * Returns Vertex for player to move to based on where other player currently is
     * and updates the current Vertex to that location
     */
    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        HashMap<Vertex, Double> map = graph.distanceFrom(otherPlayer);
        double shortestPath = Double.POSITIVE_INFINITY;
        Vertex next = null;
        for (Vertex vertex : getCurrentVertex().adjacentVertices()) {
            if (map.get(vertex) == shortestPath && vertex.adjacentVertices().size() > next.adjacentVertices().size()) {
                shortestPath = map.get(vertex);
                next = vertex;
            } else if (map.get(vertex) < shortestPath) {
                shortestPath = map.get(vertex);
                next = vertex;
            }

        }
        setCurrentVertex(next);
        return next;
    }
}
