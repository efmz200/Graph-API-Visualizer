package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Esta clase corresponde a la implementación del algoritmo de Dijkstra
 */
public class Dijkstra {
    private Graph g;
    private Node[] nodes;
    private Edge[] edges;
    private Set<Node> settledNodes;
    private Set<Node> unsettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distance;

    /**
     * Método constructor
     * @param graph Grafo
     */
    public Dijkstra (Graph graph){
        this.g = graph;
        this.nodes = g.getNodes();
        this.edges = g.getEdges();
    }

    /**
     * Este método determina el camino más corto desde el nodo ingresado hacia cualquier nodo
     * @param sourceId Nodo ingresado
     */
    public void execute(int sourceId){
        Node source = g.getNode(sourceId);
        settledNodes = new HashSet<Node>();
        unsettledNodes = new HashSet<Node>();
        distance = new HashMap<Node, Integer>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0);
        unsettledNodes.add(source);
        while(unsettledNodes.size() > 0){
            Node node = getMinimum(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimalDistance(node);
        }
    }

    /**
     * Este método retorna el nodo a menor distancia
     * @param nodes Conjunto de nodos que no han sido determinados
     * @return Nodo a menor distancia
     */
    private Node getMinimum(Set<Node> nodes){
        Node minimum = null;
        for(Node node: nodes){
            if (minimum == null){
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)){
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    /**
     * Retorna la distancia hacia un nodo
     * @param node Nodo
     * @return Retorna "infinito" si no hay camino hacia el nodo, de lo contrario retorna la distancia hacia el nodo
     */
    private int getShortestDistance(Node node){
        Integer d = distance.get(node);
        if (d == null){
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /**
     * Determina el nodo hacia el que hay una menor distancia desde el nodo indicado
     * @param node Nodo indicado
     */
    private void findMinimalDistance(Node node){
        List<Node> neighbours = getNeighbours(node);
        for (Node target: neighbours){
            if(getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)){
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unsettledNodes.add(target);
            }
        }
    }

    /**
     * Retorna un objeto Path que contiene un array con los nodos que conforman el camino más corto entre el nodo
     * inicial y el nodo destino, así como el peso total del camino.
     * @param targetId Nodo destino
     * @return Objeto Path con el array de nodos del camino y el peso total, en caso de que el camino exista, de lo
     * contrario, retorna null
     */
    public Path getPath(int targetId){
        Node target = g.getNode(targetId);
        ArrayList<Node> path = new ArrayList<Node>();
        Node step = target;
        if (predecessors.get(step) == null){
            return null;
        }
        path.add(step);
        while(predecessors.get(step) != null){
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        int total = calculatePathWeight(path);
        Path pathObject = new Path(path, total);
        return pathObject;
    }

    /**
     * Retorna una lista con los nodos "vecinos" del nodo indicado, es decir, los nodos que son el punto final
     * de una arista cuyo punto inicial es el nodo indicado.
     * @param node Nodo indicado
     * @return Lista de nodos "vecinos"
     */
    private List<Node> getNeighbours(Node node){
        List<Node> neighbours = new ArrayList<Node>();
        for(Edge edge: edges) {
            if (edge.getStartId() == node.getId() && !isSettled(g.getNode(edge.getEndId()))) {
                neighbours.add(g.getNode(edge.getEndId()));
            }
        }
        return neighbours;
    }

    /**
     * Obtiene la distancia (Peso de la arista) entre el nodo origen y el nodo destino.
     * @param node Nodo origen
     * @param target Nodo destino
     * @return Distancia
     */
    private int getDistance(Node node, Node target){
        for (Edge edge: edges){
            if(edge.getStartId() == node.getId() && edge.getEndId() == target.getId()){
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /**
     * Calcula el peso total del camino más corto determinado entre un nodo y otro
     * @param path Array que contiene el camino más corto de un nodo a otro
     * @return Peso total del camino
     */
    private int calculatePathWeight(ArrayList<Node> path){
        int weight = 0;
        Node tempNode = null;
        for (Node node: path){
            if (tempNode == null){
                tempNode = node;
            } else {
                for(Edge edge: edges){
                    if (edge.getStartId() == tempNode.getId() && edge.getEndId() == node.getId()){
                        weight = weight + edge.getWeight();
                    }
                }
                tempNode = node;
            }
        }
        return weight;
    }

    /**
     * Determina si el camino más corto a un nodo indicado ya ha sido determinado
     * @param node Nodo indicado
     * @return True si el camino ya fue determinado, false en caso contrario
     */
    private boolean isSettled(Node node){
        return settledNodes.contains(node);
    }

    /**
     * Objeto que contiene el array que corresponde al camino más corto entre dos nodos, y el peso total de dicho
     * camino.
     */
    private class Path{
        private ArrayList<Node> path;
        private int totalWeight;
        public Path(ArrayList<Node> camino, int weight){
            this.path = camino;
            this.totalWeight = weight;
        }

        /**
         * Retorna el array del camino entre los dos nodos
         * @return Array del camino entre los dos nodos
         */
        public ArrayList<Node> getPath(){
            return this.path;
        }

        /**
         * Retorna el peso total del camino entre los dos nodos
         * @return Peso total del camino entre los dos nodos
         */
        public int getTotalWeight(){
            return this.totalWeight;
        }
    }
}
