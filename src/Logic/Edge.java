package Logic;

public class Edge {
    private int id;
    private int startId;
    private int endId;
    private int weight;
    public Edge(int numero, int peso, int origen, int destino){
        this.startId = origen;
        this.endId = destino;
        this.weight = peso;
        this.id = numero;
    }


    public void addWeight(int peso){
        this.weight = this.weight + peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }

    public int getEndId() {
        return endId;
    }

    public void setEndId(int endId) {
        this.endId = endId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
