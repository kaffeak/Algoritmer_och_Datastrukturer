                    /*
                    * Author: Alex Kaufmann
                    * Written: 2021-10-10
                    * The code takes a graph and outputs the shortest path between too nodes using Depth first search.
                    * The code takes all standard inputs, it is rekomended to input actual node names.
                    * The code was written by myself and with help from code from Algorithms, 4th edition, Sedgewick and Wayne.
                    * */
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFS_del1 {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DFS_del1(Graph G, int s){
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G,s);
    }
    private void dfs(Graph G, int v){
        marked[v] = true;
        for (int i : G.adj(v) ){
            if (!marked[i]){
                edgeTo[i] = v;
                dfs(G,i);
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != s; i = edgeTo[i]){
            path.push(i);
        }
        path.push(s);
        return path;
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        SymbolGraph symbolGraph = new SymbolGraph("C:/Users/kaffe/Desktop/KTH/Algoritmer och datastrukturer/Labbar/Labb4/src/Data.txt");

        System.out.println("From: ");
        String from = in.nextLine().toUpperCase();
        int start = symbolGraph.indexOf(from);
        System.out.println("To: ");
        String to = in.nextLine().toUpperCase();
        int end = symbolGraph.indexOf(to);

        DFS_del1 dfs = new DFS_del1(symbolGraph.graph(),start);

        if (dfs.hasPathTo(end)){
            for (int s : dfs.pathTo(end)){
                if (s == end) System.out.print(symbolGraph.nameOf(s));
                else System.out.print(symbolGraph.nameOf(s)+"--");
            }
        }
        else System.out.println("Path non existent.");
    }
}
