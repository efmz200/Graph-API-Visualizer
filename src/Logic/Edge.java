package Logic;

/**
 * Esta clase corresponde a las aristas del grafo
 */
public class Edge {
    private int id;
    private int startId;
    private int endId;
    private int weight;
    private String sEntity;
    private String eEntity;

    /**
     * Método constructor de la arista
     * @param numero Número de identificación de la arista
     * @param peso Peso de la arista
     * @param origen ID del nodo origen
     * @param destino ID del nodo destino
     */
    public Edge(int numero, int peso, int origen, int destino,String sEntity,String eEntity){
        this.sEntity =sEntity;
        this.eEntity =eEntity;
        this.startId = origen;
        this.endId = destino;
        this.weight = peso;
        this.id = numero;
    }

    /**
     * Agrega un nuevo peso al peso ya existente
     * @param peso Peso nuevo
     */
    public void addWeight(int peso){
        this.weight = this.weight + peso;
    }

    /**
     * Retorna el ID de la arista
     * @return ID de la arista
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la arista
     * @param id ID de la arista
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna el ID del nodo origen de la arista
     * @return ID del nodo origen de la arista
     */
    public int getStartId() {
        return startId;
    }

    /**
     * Establece el ID del nodo origen de la arista
     * @param startId ID del nodo origen de la arista
     */
    public void setStartId(int startId) {
        this.startId = startId;
    }

    /**
     * Retorna el ID del nodo destino de la arista
     * @return ID del nodo destino de la arista
     */
    public int getEndId() {
        return endId;
    }

    /**
     * Establece el ID del nodo destino de la arista
     * @param endId ID del nodo destino de la arista
     */
    public void setEndId(int endId) {
        this.endId = endId;
    }

    /**
     * Retorna el peso de la arista
     * @return Peso de la arista
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Establece el peso de la arista
     * @param weight Peso de la arista
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getsEntity() {
        return sEntity;
    }

    public String geteEntity() {
        return eEntity;
    }
}
