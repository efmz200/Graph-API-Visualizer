package Logic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Esta clase corresponde a una implementación de un grafo dirigido.
 */
public class Graph {
    private int id;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private int nodeCounter;
    private int edgeCounter;

    /**
     * Constructor de la clase grafo.
     * @param numero Corresponde al número de identificación que se le asignará al grafo.
     */
    public Graph(int numero){
        this.id = numero;
        this.nodes = new ArrayList<Node>();
        this.edges = new ArrayList<Edge>();
        nodeCounter = 0;
        edgeCounter = 0;
    }

    /**
     * Agrega un nuevo nodo al grafo.
     * @param telefono Corresponde al número de teléfono que almacenará el nodo.
     */
    public void addNode(String telefono){
        if (containsNode(telefono)) {
            return;
        } else {
            addNode(nodeCounter, telefono);
            nodeCounter++;
        }
    }

    /**
     * Agrega el nodo a la lista de nodos del grafo y le asigna un número de identificación.
     * @param numero Corresponde al número de identificación del grafo.
     * @param telefono Número de teléfono que almacenará el nodo.
     */
    private void addNode(int numero, String telefono) {
        if (this.containsNode(telefono)) {
            return;
        } else {
            nodes.add(new Node(numero, telefono));
        }
    }

    /**
     * Verifica si el grafo contiene un nodo con el número especificado.
     * @param telefono Número especificado.
     * @return Retorna true si el nodo ya existe, en caso contrario, retorna false.
     */
    public boolean containsNode(String telefono){
        for(Node i: nodes){
            if (i.getEntity().equals(telefono)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el grafo contiene la arista especificada.
     * @param source Teléfono correspondiente al nodo de origen.
     * @param target Teléfono correspondiente al nodo destino.
     * @return Retorna true si la arista ya existe, en caso contrario, retorna false.
     */
    public boolean containsEdge(String source, String target){
        for(Edge i: edges){
            if (i.getStartId() == getNode(source).getId() && i.getEndId() == getNode(target).getId()){
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna el nodo con el teléfono especificado
     * @param telefono Teléfono especificado
     * @return Retorna el nodo en caso de que exista, de lo contrario retorna null
     */
    public Node getNode(String telefono){
        for (Node i: nodes){
            if (i.getEntity().equals(telefono)){
                return i;
            }
        }
        return null;
    }

    /**
     * Retorna el nodo con el ID especificado
     * @param id ID especificado
     * @return Retorna el nodo en caso de que exista, de lo contrario retorna null
     */
    public Node getNode(int id){
        for(Node i: nodes){
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }

    /**
     * Elimina el nodo con el ID especificado y cualquier arista que tenga dicho nodo como punto
     * de origen o destino
     * @param id ID especificado
     */
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

    /**
     * Elimina la arista con el ID especificado y reduce el grado de inputs o el grado de outputs
     * de los nodos destino y origen, respectivamente
     * @param id ID especificado
     */
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

    /**
     * Agrega una arista al grafo
     * @param origen Teléfono origen de la arista
     * @param destino Teléfono destino de la arista
     * @param weight Peso de la arista
     */
    public void addEdge(String origen, String destino, int weight){
        if(this.containsEdge(origen, destino)){
            addEdge(origen, destino, weight,edgeCounter);
        } else{
            addEdge(origen,destino,weight,edgeCounter);
            edgeCounter++;
        }
    }

    /**
     * Agrega una arista al grafo, en caso de que la arista ya existe, agrega el peso nuevo al
     * peso de la arista existente.
     * @param origen Teléfono origen
     * @param destino Teléfono destino
     * @param peso Peso
     * @param id Número de identificación de la arista
     */
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

    /**
     *
     * Retorna una lista con los nodos ordenados según su promedio de grado de outputs e inputs
     * @param sort Parámetro por el cuál ordenar los nodos
     * @return Lista con los nodos ordenados
     */
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

    /**
     * Imprime el grafo en consola como una lista de adyacencia
     */
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

    /**
     * Recorre el grafo mediante el método Breadth-First, e imprime cada paso.
     */
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

    /**
     * Retorna el ID del grafo
     * @return ID del grafo
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del grafo
     * @param id ID del grafo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna el array de nodos del grafo
     * @return Array de nodos del grafo
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Establece el array de nodos del grafo
     * @param nodes Array de nodos del grafo
     */
    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Retorna el array de aristas del grafo
     * @return Array de aristas del grafo
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Establece el array de aristas del grafo
     * @param edges Array de aristas del grafo
     */
    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
