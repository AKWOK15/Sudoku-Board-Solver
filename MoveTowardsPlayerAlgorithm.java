import java.util.Random;

/*
 * Aidan Kwok
 * Handles the pursuer, moves pursuer to neighboring vertex that is closest to the evader
 */
public class MoveTowardsPlayerAlgorithm extends AbstractPlayerAlgorithm {
    private Random rand;

    public MoveTowardsPlayerAlgorithm(Graph graph) {
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
            if (map.get(vertex) < shortestPath) {
                shortestPath = map.get(vertex);
                next = vertex;
            }

        }
        setCurrentVertex(next);
        return next;
    }

}
