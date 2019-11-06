package Logic;

public class Edge {
    private int id;
    private Node start;
    private Node end;
    private int weight;
    public Edge(int numero, int peso, Node origen, Node destino){
        this.start = origen;
        this.end = destino;
        this.weight = peso;
        this.id = numero;
    }

    public boolean isPath(Node origen, Node destino){
        return (origen == this.start && destino == this.end);
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

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
