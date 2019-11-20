package Logic;

/**
 * Esta clase corresponde a los nodos del grafo
 */
public class Node {
    private int id;
    private int inDegree;
    private int outDegree;
    private int avgDegree;
    private String entity;

    /**
     * Constructor del nodo
     * @param numero Número de identificación del nodo
     * @param telefono Teléfono del nodo
     */
    public Node(int numero, String telefono){
        this.entity = telefono;
        this.id = numero;
        this.inDegree = 0;
        this.outDegree = 0;
    }

    /**
     * Retorna el promedio de grados de inputs y outputs del nodo
     * @return Grado de inputs y outputs del nodo
     */
    public int getAvgDegree(){
        this.avgDegree = (this.inDegree + this.outDegree) / 2;
        return avgDegree;
    }

    /**
     * Aumenta el grado de inputs del nodo
     */
    public void addIn(){
        inDegree++;
    }

    /**
     * Disminuye el grado de inputs del nodo
     */
    public void subtractIn(){
        inDegree--;
    }

    /**
     * Aumenta el grado de outputs del nodo
     */
    public void addOut(){
        outDegree++;
    }

    /**
     * Disminuye el grado de outputs del nodo
     */
    public void subtractOut(){
        outDegree--;
    }

    /**
     * Retorna el ID del nodo
     * @return ID del nodo
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del nodo
     * @param id ID del nodo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna el grado de inputs del nodo
     * @return Grado de inputs del nodo
     */
    public int getInDegree() {
        return inDegree;
    }

    /**
     * Establece el grado de inputs del nodo
     * @param inDegree
     */
    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    /**
     * Retorna el grado de outputs del nodo
     * @return Grado de outputs del nodo
     */
    public int getOutDegree() {
        return outDegree;
    }

    /**
     * Establece el grado de outputs del nodo
     * @param outDegree
     */
    public void setOutDegree(int outDegree) {
        this.outDegree = outDegree;
    }

    /**
     * Retorna el número almacenado del nodo
     * @return Número almacenado del nodo
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Establece el número almacenado del nodo
     * @param entity Número almacenado del nodo
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }
}
