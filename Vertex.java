import java.util.ArrayList;

/*
 * Aidan Kwok
 * Creates vertex objects for graph 
 */
public class Vertex {
    private ArrayList<Vertex> adjacentVertices;
    private ArrayList<Edge> incidentEdges;

    public Vertex() {
        adjacentVertices = new ArrayList<Vertex>();
        incidentEdges = new ArrayList<Edge>();

    }

    /*
     * Returns edge that connects passed in vertex and this vertex
     */
    public Edge getEdgeTo(Vertex vertex) {
        for (Edge edge : incidentEdges) {
            if (edge.other(this) == vertex) {
                return edge;
            }
        }
        return null;

    }

    /*
     * Adds edge to arraylist of edges
     */
    public void addEdge(Edge edge) {
        adjacentVertices.add(edge.other(this));
        incidentEdges.add(edge);
    }

    /*
     * Removes edge from arraylist of edges
     */
    public boolean removeEdge(Edge edge) {
        for (Edge item : incidentEdges) {
            if (item == edge) {
                incidentEdges.remove(item);
                return true;
            }

        }
        return false;
    }

    /*
     * Returns arrayList of all the vertices adjacent to this vertex
     */
    public ArrayList<Vertex> adjacentVertices() {
        return adjacentVertices;
    }

    /*
     * Returns an ArrayList of all the edges incident to this vertex
     */
    public ArrayList<Edge> incidentEdges() {
        return incidentEdges;
    }
}
