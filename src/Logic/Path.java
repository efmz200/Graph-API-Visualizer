package Logic;

import java.util.ArrayList;

/**
 * Objeto que contiene el array que corresponde al camino más corto entre dos nodos, y el peso total de dicho
 * camino.
 */
public class Path{
    private Node[] path;
    private int totalWeight;
    public Path(ArrayList<Node> camino, int weight){
        this.path = toArray(camino);
        this.totalWeight = weight;
    }

    private Node[] toArray(ArrayList<Node> camino){
        Node[] path = new Node[camino.size()];
        for(int pos = 0; pos < path.length; pos++){
            path[pos] = camino.get(pos);
        }
        return path;
    }

    /**
     * Retorna el array del camino entre los dos nodos
     * @return Array del camino entre los dos nodos
     */
    public Node[] getPath(){
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
