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

    public Node getNode(String telefono){
        for (Node i: nodes){
            if (i.getEntity().equals(telefono)){
                return i;
            }
        }
        return null;
    }

    public Node getNode(int id){
        for(Node i: nodes){
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }

    public void addEdge(String origen, String destino, int peso, int id){
        for(Edge i: edges){
            if (i.getStartId() == getNode(origen).getId() && i.getEndId() == getNode(destino).getId()) {
                // i.getStart().getEntity().equals(origen) && i.getEnd().getEntity().equals(destino)
                i.addWeight(peso);
                return;
            }
        }
        int caller = 0;
        int receiver = 0;
        for(Node i: nodes){
            if (i.getEntity().equals(origen)){
                caller = i.getId();
            }
            if (i.getEntity().equals(destino)){
                receiver = i.getId();
            }
        }
        edges.add(new Edge(id, peso, caller, receiver));
    }

    public void adyacencyList(){
        for(Node i: nodes){
            ArrayList<String> adyList = new ArrayList<String>();
            for(Edge j: edges){
                if (getNode(j.getStartId()) == i){
                    adyList.add("|"+ getNode(j.getEndId()).getEntity() + "|" + j.getWeight() + "|");
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
                if (getNode(i.getStartId()).getEntity().equals(current)){
                    if (!visited.contains(getNode(i.getEndId()).getEntity())){
                        if(!queue.contains(getNode(i.getEndId()).getEntity())){
                            queue.add(getNode(i.getEndId()).getEntity());
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
