                    /*
                     * Author: Alex Kaufmann
                     * Written: 2021-10-11
                     * The code takes a graph and outputs the shortest path between too nodes using breath first search.
                     * The code takes all standard inputs, it is rekomended to input actual node names.
                     * The code was written by myself and with help from code from Algorithms, 4th edition, Sedgewick and Wayne.
                     * */
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BFS_del2 {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path

    /**
     * Computes the shortest path between the source vertex {@code s}
     * and every other vertex in the graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public BFS_del2(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} (or sources) and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }


    // check optimality conditions for single source

    /**
     * Unit tests the {@code BFS_del2} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        SymbolGraph symbolGraph = new SymbolGraph("C:/Users/kaffe/Desktop/KTH/Algoritmer och datastrukturer/Labbar/Labb4/src/Data.txt");

        System.out.println("From: ");
        String from = in.nextLine().toUpperCase();
        int start = symbolGraph.indexOf(from);
        System.out.println("To: ");
        String to = in.nextLine().toUpperCase();
        int end = symbolGraph.indexOf(to);

        BFS_del2 dfs = new BFS_del2(symbolGraph.graph(),start);

        if (dfs.hasPathTo(end)){
            for (int s : dfs.pathTo(end)){
                if (s == end) System.out.print(symbolGraph.nameOf(s));
                else System.out.print(symbolGraph.nameOf(s)+"--");
            }
        }
        else System.out.println("Path non existent.");
    }


}