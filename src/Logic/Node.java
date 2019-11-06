package Logic;

public class Node {
    private int id;
    private int inDegree;
    private int outDegree;
    private String entity;

    public Node(int numero, String telefono){
        this.entity = telefono;
        this.id = numero;
        this.inDegree = 0;
        this.outDegree = 0;
    }

    public void addIn(){
        inDegree++;
    }

    public void addOut(){
        outDegree++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(int outDegree) {
        this.outDegree = outDegree;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
