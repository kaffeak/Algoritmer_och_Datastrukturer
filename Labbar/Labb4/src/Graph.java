import java.util.NoSuchElementException;
import java.util.Scanner;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
        adj[v] = new Bag<Integer>();
        }
        }

/**
 * Initializes a graph from the specified input stream.
 * The format is the number of vertices <em>V</em>,
 * followed by the number of edges <em>E</em>,
 * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
 *
 * @param  in the input stream
 * @throws IllegalArgumentException if {@code in} is {@code null}
 * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
 * @throws IllegalArgumentException if the number of vertices or edges is negative
 * @throws IllegalArgumentException if the input stream is in the wrong format
 */
public Graph(Scanner in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
        this.V = in.nextInt();
        if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
        adj[v] = new Bag<Integer>();
        }
        int E = in.nextInt();
        if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be non-negative");
        for (int i = 0; i < E; i++) {
        int v = in.nextInt();
        int w = in.nextInt();
        addEdge(v, w);
        }
        }
        catch (NoSuchElementException e) {
        throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
        }


/**
 * Initializes a new graph that is a deep copy of {@code G}.
 *
 * @param  G the graph to copy
 * @throws IllegalArgumentException if {@code G} is {@code null}
 */

/**
 * Returns the number of vertices in this graph.
 *
 * @return the number of vertices in this graph
 */
public int V() {
        return V;
        }

/**
 * Returns the number of edges in this graph.
 *
 * @return the number of edges in this graph
 */
public int E() {
        return E;
        }

/**
 * Adds the undirected edge v-w to this graph.
 *
 * @param  v one vertex in the edge
 * @param  w the other vertex in the edge
 * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
 */
public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
        }


/**
 * Returns the vertices adjacent to vertex {@code v}.
 *
 * @param  v the vertex
 * @return the vertices adjacent to vertex {@code v}, as an iterable
 * @throws IllegalArgumentException unless {@code 0 <= v < V}
 */
public Iterable<Integer> adj(int v) {
        return adj[v];
        }

/**
 * Returns a string representation of this graph.
 *
 * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
 *         followed by the <em>V</em> adjacency lists
 */
public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
        s.append(v + ": ");
        for (int w : adj[v]) {
        s.append(w + " ");
        }
        s.append(NEWLINE);
        }
        return s.toString();
        }


/**
 * Unit tests the {@code Graph} data type.
 *
 * @param args the command-line arguments
 */
public static void main(String[] args) {
        Scanner in = new Scanner("C:\\Users\\kaffe\\Desktop\\KTH\\Algoritmer och datastrukturer\\Labbar\\Labb4\\src\\Data.txt");
        Graph G = new Graph(in);
        System.out.println(G);
        }

        }