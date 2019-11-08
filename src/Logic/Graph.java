package Logic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Graph {
    private int id;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private int nodeCounter;
    private int edgeCounter;
    public Graph(int numero){
        this.id = numero;
        this.nodes = new ArrayList<Node>();
        this.edges = new ArrayList<Edge>();
        nodeCounter = 0;
        edgeCounter = 0;
    }

    public void addNode(String telefono){
        if (containsNode(telefono)) {
            return;
        } else {
            addNode(nodeCounter, telefono);
            nodeCounter++;
        }
    }

    private void addNode(int numero, String telefono){
        for (Node i: nodes){
            if (i.getEntity().equals(telefono)){
                return;
            }
        }
        nodes.add(new Node(numero, telefono));
    }

    public boolean containsNode(String telefono){
        for(Node i: nodes){
            if (i.getEntity().equals(telefono)){
                return true;
            }
        }
        return false;
    }

    public boolean containsEdge(String source, String target){
        for(Edge i: edges){
            if (i.getStartId() == getNode(source).getId() && i.getEndId() == getNode(target).getId()){
                return true;
            }
        }
        return false;
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

    public void removeNode(int id){
        Node target = null;
        for(Node i: nodes){
            if (i.getId() == id){
                for(Edge j: edges){
                    if (j.getStartId() == i.getId()){
                        for(Node node: nodes){
                            if (node.getId() == j.getEndId()){
                                node.subtractIn();
                            }
                        }
                        removeEdge(j.getId());
                    } else if( j.getEndId() == i.getId()){
                        for(Node node: nodes){
                            if(node.getId() == j.getStartId()){
                                node.subtractOut();;
                            }
                        }
                        removeEdge(j.getId());
                    }
                }
                target = i;
            }
        }
        nodes.remove(target);

    }

    public void removeEdge(int id){
        Edge target = null;
        for (Edge i: edges){
            if (i.getId() == id){
                for(Node j: nodes){
                    if(j.getId() == i.getStartId()){
                        j.subtractOut();
                    } else if (j.getId() == i.getEndId()){
                        j.subtractIn();
                    }
                }
                target = i;
            }
        }
        edges.remove(target);
    }

    public void addEdge(String origen, String destino, int weight){
        if(this.containsEdge(origen, destino)){
            addEdge(origen, destino, weight,edgeCounter);
        } else{
            addEdge(origen,destino,weight,edgeCounter);
            edgeCounter++;
        }
    }

    private void addEdge(String origen, String destino, int peso, int id){
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
                i.addOut();
                caller = i.getId();
            }
            if (i.getEntity().equals(destino)){
                i.addIn();
                receiver = i.getId();
            }
        }
        edges.add(new Edge(id, peso, caller, receiver));
    }

    public ArrayList<Node> sortedNodes(String sort){
        ArrayList<Node> sortedNodes = new ArrayList<Node>();
        sortedNodes.addAll(nodes);
        int length = sortedNodes.size();
        for(int i = 0; i < length; i++){
            for (int j = 0; j < length-i-1; j++){
                if(sortedNodes.get(j).getAvgDegree() > sortedNodes.get(j+1).getAvgDegree()){
                    Node temp = sortedNodes.get(j);
                    sortedNodes.set(j, sortedNodes.get(j+1));
                    sortedNodes.set(j+1, temp);
                }
            }
        }
        if (sort.equals("DESC")){
            Collections.reverse(sortedNodes);
        }
        return sortedNodes;
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
