/*
 * Aidan Kwok
 * Abstract class which player algorithm classes will extend
 */
abstract class AbstractPlayerAlgorithm {
    // Needs to be public for others algorithms to use
    public Graph graph;
    private Vertex curVertex;

    public AbstractPlayerAlgorithm(Graph graph) {
        this.graph = graph;
        curVertex = null;

    }

    /*
     * Returns graph
     */
    public Graph getGraph() {
        return graph;
    }

    /*
     * Returns current vertex of player
     */
    public Vertex getCurrentVertex() {
        return curVertex;
    }

    /*
     * Updates curVertex of the player to passed in argument
     */
    public void setCurrentVertex(Vertex vertex) {
        curVertex = vertex;
    }

    /*
     * Returns Vertex for player to start at and updates the current Vertex to that
     * location
     */
    public abstract Vertex chooseStart();

    /*
     * Returns Vertex for player to start at based on where other player started
     * and updates the current Vertex to that location
     */
    public abstract Vertex chooseStart(Vertex other);

    /*
     * Returns Vertex for player to move to based on where other player currently is
     * and updates the current Vertex to that location
     */
    public abstract Vertex chooseNext(Vertex otherPlayer);
}
