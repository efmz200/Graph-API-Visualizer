package Logic;

public class NodoG <T>{
    private T dato;
    private Lista<Arista> aristas;

    public NodoG(T dato){
        this.aristas = new Lista<>();
        this.dato = dato;
    }
    public void addArista(Arista arista){
        aristas.add(arista);
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Lista<Arista> getAristas() {
        return aristas;
    }
}
