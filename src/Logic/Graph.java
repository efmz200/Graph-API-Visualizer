package Logic;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    private int id;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    public Graph(int numero){
        this.id = numero;
        this.nodes = new ArrayList<Node>();
        this.edges = new ArrayList<Edge>();
    }


    public void addNode(int numero, String telefono){
        for (Node i: nodes){
            if (i.getEntity().equals(telefono)){
                return;
            }
        }
        nodes.add(new Node(numero, telefono));
    }

    public void addEdge(String origen, String destino, int peso, int numero){
        for(Edge i: edges){
            if (i.getStart().getEntity().equals(origen) && i.getEnd().getEntity().equals(destino)){
                i.addWeight(peso);
                return;
            }
        }
        Node caller = null;
        Node receiver = null;
        for(Node i: nodes){
            if (i.getEntity().equals(origen)){
                caller = i;
            }
            if (i.getEntity().equals(destino)){
                receiver = i;
            }
        }
        edges.add(new Edge(numero, peso, caller, receiver));
    }

    public void adyacencyList(){
        for(Node i: nodes){
            ArrayList<String> adyList = new ArrayList<String>();
            for(Edge j: edges){
                if (j.getStart() == i){
                    adyList.add("|"+ j.getEnd().getEntity() + "|" + j.getWeight() + "|");
                }
            }
            System.out.println(i.getEntity() + "->" + adyList.toString());
        }
    }

    public void breadthFirstTraversal(){
        ArrayList<String> visited = new ArrayList<String>();
        LinkedList<String> queue = new LinkedList<String>();
        while(visited.size() != nodes.size()){
            if (visited.size() == 0){
                queue.add(nodes.get(0).getEntity());
            }
            System.out.println("Queue: " + queue.toString() + " ; Visited: " + visited.toString());
            String current = queue.poll();
            visited.add(current);
            for (Edge i: edges){
                if (i.getStart().getEntity().equals(current)){
                    if (!visited.contains(i.getEnd().getEntity())){
                        if(!queue.contains(i.getEnd().getEntity())){
                            queue.add(i.getEnd().getEntity());
                        }

                    }
                }
            }

        }
        System.out.println("Queue: " + queue.toString() + " ; Visited: " + visited.toString());

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
