import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;

/*
 * Aidan Kwok
 * Creates graph of vectors and edges
 */
public class Graph {
    private int numVertices;
    private double probability;
    private ArrayList<Vertex> verticesArrayList;
    private ArrayList<Edge> edgesArrayList;
    private Random rand;

    public Graph() {
        this(0);
    }

    public Graph(int n) {
        this(n, 0.0);
    }

    public Graph(int n, double probability) {
        numVertices = n;
        this.probability = probability;
        verticesArrayList = new ArrayList<Vertex>();
        edgesArrayList = new ArrayList<Edge>();
        rand = new Random();

        for (int x = 0; x < numVertices; x++) {
            Vertex vertex = new Vertex();
            verticesArrayList.add(vertex);
        }
        for (int x = 0; x < verticesArrayList.size(); x++) {
            Vertex temp = verticesArrayList.get(x);
            for (int y = 0; y < verticesArrayList.size(); y++) {
                if (rand.nextDouble() <= this.probability) {
                    Edge edge = new Edge(temp, verticesArrayList.get(y), 1);
                    temp.addEdge(edge);
                    verticesArrayList.get(y).addEdge(edge);
                    edgesArrayList.add(edge);
                }

            }

        }
    }

    /*
     * Returns number of vertices
     */
    public int size() {
        return verticesArrayList.size();
    }

    /*
     * Returns arraylist of all vertices in graph
     */
    public ArrayList<Vertex> getVertices() {
        return verticesArrayList;
    }

    /*
     * Returns arraylist of all edges in graph
     */
    public ArrayList<Edge> getEdges() {
        return edgesArrayList;
    }

    /*
     * Adds vertex to graph
     */
    public Vertex addVertex() {
        Vertex vertex = new Vertex();
        verticesArrayList.add(vertex);
        return vertex;
    }

    /*
     * Adds edge to graph
     */
    public Edge addEdge(Vertex u, Vertex v, double distance) {
        Edge edge = new Edge(u, v, distance);
        edgesArrayList.add(edge);
        u.addEdge(edge);
        v.addEdge(edge);
        return edge;
    }

    /*
     * Returns edge between two vertices
     */
    public Edge getEdge(Vertex u, Vertex v) {
        return u.getEdgeTo(v);
    }

    /*
     * Removes vertex
     */
    public boolean remove(Vertex vertex) {
        if (verticesArrayList.contains(vertex)) {
            for (Vertex vert : vertex.adjacentVertices()) {
                // Access vert that is adjacent to passed in parameter, and removes edge of vert
                vert.removeEdge(vert.getEdgeTo(vertex));
            }
            verticesArrayList.remove(vertex);
            return true;
        }
        return false;
    }

    /*
     * Removes edge
     */
    public boolean remove(Edge edge) {
        if (edgesArrayList.contains(edge)) {
            // Removes edge from first vertex
            edge.other(edge.vertices()[0]).removeEdge(edge);
            // Removes edge from second vertex
            edge.vertices()[0].removeEdge(edge);
            return edgesArrayList.remove(edge);
        }
        return false;
    }

    /*
     * Calculates minimal distance from all vertices to source
     */
    public HashMap<Vertex, Double> distanceFrom(Vertex source) {
        HashMap<Vertex, Double> map = new HashMap<Vertex, Double>(4);
        Comparator<Vertex> comparator = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex u, Vertex v) {
                return map.get(u).compareTo(map.get(v));
            }
        };
        PriorityQueue<Vertex> pq = new Heap<Vertex>(comparator);
        // Adds all vertices to hash map and all vertices except source have value of
        // positive infinity
        for (Vertex vertex : verticesArrayList) {
            if (vertex == source) {
                map.put(vertex, 0.0);
                pq.offer(vertex);
                continue;
            }
            map.put(vertex, Double.POSITIVE_INFINITY);
            pq.offer(vertex);
        }
        // Makes source root of hash since root has value of 0
        pq.updatePriority(source);

        while (pq.size() > 0) {
            Vertex curVertex = pq.poll();
            // Checks if adjacent vertices of polled vertex have a more efficient distance
            // than their current value
            for (Vertex vertex : curVertex.adjacentVertices()) {
                Double distance = map.get(curVertex) + curVertex.getEdgeTo(vertex).distance();
                if (distance < map.get(vertex)) {
                    map.put(vertex, distance);
                    pq.updatePriority(vertex);
                }
            }

        }
        return map;

    }

    /*
     * Used for tests
     */
    public ArrayList<Vertex> vertices() {
        return verticesArrayList;
    }

    /*
     * Used for tests
     */
    public ArrayList<Edge> edges() {
        return edgesArrayList;
    }

}