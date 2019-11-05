package Logic;

public class Arista {
    private NodoG origen;
    private NodoG destino;
    private double peso;

    public Arista(NodoG origen, NodoG destino, double peso){
        this.destino= destino;
        this.origen = origen;
        this.peso = peso;
    }

    public NodoG getOrigen() {
        return origen;
    }

    public void setOrigen(NodoG origen) {
        this.origen = origen;
    }

    public NodoG getDestino() {
        return destino;
    }

    public void setDestino(NodoG destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    public void getDatos(){
        System.out.println(origen.getDato());
        System.out.println(destino.getDato());
        System.out.println(peso);
    }
}
