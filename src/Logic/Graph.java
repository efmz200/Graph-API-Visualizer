package Logic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Esta clase corresponde a una implementación de un grafo dirigido.
 */
public class Graph {
    private int id;
    private Node[] nodes;
    private Edge[] edges;
    private int nodeCounter;
    private int edgeCounter;

    /**
     * Constructor de la clase grafo.
     * @param numero Corresponde al número de identificación que se le asignará al grafo.
     */
    public Graph(int numero){
        this.id = numero;
        this.nodes = new Node[10];
        this.edges = new Edge[10];
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
            if(arrayFull(this.nodes)){
                this.increaseNodeArray();
            }
            this.addNode(new Node(numero, telefono));
        }
    }

    /**
     * Verifica si el array ingresado esta lleno
     * @param array Array ingresado
     * @return Retorna false si hay algún espacio vacío en el array, false en caso contrario
     */
    private boolean arrayFull(Object[] array){
        for(int pos = 0; pos < array.length; pos++){
            if(array[pos] == null){
                return false;
            }
        }
        return true;
    }

    /**
     * Incrementa el tamaño del array de nodos del grafo
     */
    private void increaseNodeArray(){
        int increasedSize = nodes.length + 5;
        Node[] tempArray = new Node[increasedSize];
        for(int pos = 0; pos < nodes.length; pos++){
            tempArray[pos] = nodes[pos];
        }
        nodes = tempArray;
    }

    /**
     * Incrementa el tamaño del array de aristas del grafo
     */
    private void increaseEdgeArray(){
        int increasedSize = edges.length + 5;
        Edge[] tempArray = new Edge[increasedSize];
        for(int pos = 0; pos < edges.length; pos++){
            tempArray[pos] = edges[pos];
        }
        edges = tempArray;
    }

    /**
     * Agrega el nodo ingresado en el primer espacio vacío del array de nodos del grafo
     * @param node Nodo ingresado
     */
    private void addNode(Node node){
        for(int i = 0; i < nodes.length; i++){
            if(nodes[i] == null){
                nodes[i] = node;
                return;
            }
        }
    }

    /**
     * Verifica si el grafo contiene un nodo con el número especificado.
     * @param telefono Número especificado.
     * @return Retorna true si el nodo ya existe, en caso contrario, retorna false.
     */
    public boolean containsNode(String telefono){
        for(Node i: nodes){
            if(i != null){
                if (i.getEntity().equals(telefono)){
                    return true;
                }
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
            if(i != null){
                if (i.getStartId() == getNode(source).getId() && i.getEndId() == getNode(target).getId()){
                    return true;
                }
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
        this.removeNode(target);

    }

    /**
     * Elimina el nodo ingresado del array de nodos del grafo
     * @param target Nodo ingresado
     */
    private void removeNode(Node target){
        for(int pos = 0; pos < nodes.length; pos++){
            if(nodes[pos] == target){
                nodes[pos] = null;
                return;
            }
        }
    }

    /**
     * Elimina la arista con el ID especificado y reduce el grado de inputs o el grado de outputs
     * de los nodos destino y origen, respectivamente
     * @param id ID especificado
     */
    public void removeEdge(int id){
        Edge target = null;
        for (Edge i: edges){
            if(i != null){
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
        }
        this.removeEdge(target);
    }

    /**
     * Elimina la arista ingresada del array de aristas del grafo
     * @param target Arista ingresada
     */
    private void removeEdge(Edge target){
        for(int pos = 0; pos < edges.length; pos++){
            if(edges[pos] == target){
                edges[pos] = null;
                return;
            }
        }
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
            if(i != null){
                if (i.getStartId() == getNode(origen).getId() && i.getEndId() == getNode(destino).getId()) {
                    i.addWeight(peso);
                    return;
                }
            }
        }
        int caller = 0;
        int receiver = 0;
        for(Node i: nodes){
            if(i != null){
                if (i.getEntity().equals(origen)){
                    i.addOut();
                    caller = i.getId();
                }
                if (i.getEntity().equals(destino)){
                    i.addIn();
                    receiver = i.getId();
                }
            }
        }
        if(this.arrayFull(edges)){
            this.increaseEdgeArray();
        }
        this.addEdge(new Edge(id, peso, caller, receiver));
    }

    /**
     * Agrega la arista ingresada a la primera posición vacía del array de aristas del grafo
     * @param edge Arista ingresada
     */
    private void addEdge(Edge edge){
        for(int pos = 0; pos < edges.length; pos++){
            if(edges[pos] == null){
                edges[pos] = edge;
                return;
            }
        }

    }

    /**
     *
     * Retorna una lista con los nodos ordenados según su promedio de grado de outputs e inputs
     * @param sort Parámetro por el cuál ordenar los nodos
     * @return Lista con los nodos ordenados
     */
    public Node[] sortedNodes(String sort){
        Node[] sortedNodes = nodes;
        int length = sortedNodes.length;
        for(int i = 0; i < length; i++){
            for (int j = 0; j < length-i-1; j++){
                if(sortedNodes[j] != null && sortedNodes[j+1] != null){
                    if(sortedNodes[j].getAvgDegree() > sortedNodes[j+1].getAvgDegree()){
                        Node temp = sortedNodes[j];
                        sortedNodes[j] = sortedNodes[j+1];
                        sortedNodes[j+1] = temp;
                    }
                }
            }
        }
        if (sort.equals("DESC")){
            sortedNodes = this.reverse(sortedNodes);
        }
        return sortedNodes;
    }

    /**
     * Invierte el array ingresado
     * @param array Array ingresado
     * @return Array invertido
     */
    private Node[] reverse(Node[] array){
        Node[] reversedArray = new Node[array.length];
        int newPos = 0;
        for(int pos = array.length-1; pos > 0; pos--){
            reversedArray[newPos] = array[pos];
        }
        return reversedArray;
    }

    /**
     * Imprime el grafo en consola como una lista de adyacencia
     */
    public void adyacencyList(){
        for(Node i: nodes){
            if(i !=null){
                ArrayList<String> adyList = new ArrayList<String>();
                for(Edge j: edges){
                    if(j != null){
                        if (getNode(j.getStartId()) == i){
                            adyList.add("|"+ getNode(j.getEndId()).getEntity() + "|" + j.getWeight() + "|");
                        }
                    }
                }
                System.out.println(i.getEntity() + "->" + adyList.toString());
            }
        }
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
    public Node[] getNodes() {
        return nodes;
    }

    /**
     * Establece el array de nodos del grafo
     * @param nodes Array de nodos del grafo
     */
    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    /**
     * Retorna el array de aristas del grafo
     * @return Array de aristas del grafo
     */
    public Edge[] getEdges() {
        return edges;
    }

    /**
     * Establece el array de aristas del grafo
     * @param edges Array de aristas del grafo
     */
    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }
}
