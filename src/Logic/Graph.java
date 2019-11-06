package Logic;
import java.util.ArrayList;

public class Graph {
    private int id;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    public Graph(int numero){
        this.id = 1000 + numero;
        this.nodes = new ArrayList<Node>();
        this.edges = new ArrayList<Edge>();
    }

    public void addNode(Node vertice){
    }

    public void addEdge(Node origen, Node destino, int peso){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
