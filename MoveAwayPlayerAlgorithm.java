import java.util.Random;

/*
 * Aidan Kwok
 * Handles evader and finding next vertex that is farthest away from pursuer 
 */
public class MoveAwayPlayerAlgorithm extends AbstractPlayerAlgorithm {
    private Random rand;

    public MoveAwayPlayerAlgorithm(Graph graph) {
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
     * Returns vertex with greatest distance from passed in vertex
     */
    @Override
    public Vertex chooseStart(Vertex other) {
        HashMap<Vertex, Double> map = graph.distanceFrom(other);
        double longestPath = 0;
        Vertex next = null;
        for (Vertex vertex : graph.vertices()) {
            if (map.get(vertex) > longestPath) {
                longestPath = map.get(vertex);
                next = vertex;
            }
        }
        setCurrentVertex(next);
        return next;
    }

    /*
     * Either stays in current location or moves to the neighbor that is fartheset
     * away from passed in vertex
     */
    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        HashMap<Vertex, Double> map = graph.distanceFrom(otherPlayer);
        double maxDistance = map.get(getCurrentVertex());
        Vertex next = getCurrentVertex();
        for (Vertex vertex : getCurrentVertex().adjacentVertices()) {
            if (map.get(vertex) > maxDistance) {
                maxDistance = map.get(vertex);
                next = vertex;
            }

        }
        setCurrentVertex(next);
        return next;
    }

}
