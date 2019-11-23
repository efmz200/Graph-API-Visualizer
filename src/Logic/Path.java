package Logic;


/**
 * Objeto que contiene el array que corresponde al camino más corto entre dos nodos, y el peso total de dicho
 * camino.
 */
public class Path{
    private Node[] path;
    private int totalWeight;
  
    public Path(Node[] camino, int weight){
        this.path = camino;
        this.totalWeight = weight;
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
