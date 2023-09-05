/*
 * Aidan Kwok
 * Creates edge object that connects two vertices
 */
public class Edge {
    private Vertex u;
    private Vertex v;
    private double distance;
    private Vertex vertices[];

    public Edge(Vertex u, Vertex v, double distance) {
        this.u = u;
        this.v = v;
        this.distance = distance;
        vertices = new Vertex[2];
        vertices[0] = u;
        vertices[1] = v;

    }

    /*
     * Returns distance of edge
     */
    public double distance() {
        return distance;
    }

    /*
     * If vertex is one of endpoints of edge, return other endpoint
     */
    public Vertex other(Vertex vertex) {
        if (vertex == u) {
            return v;
        } else if (vertex == v) {
            return u;
        }
        return null;
    }

    /*
     * Returns array of the two Vertices making up this edge
     */
    public Vertex[] vertices() {
        return vertices;

    }

}
