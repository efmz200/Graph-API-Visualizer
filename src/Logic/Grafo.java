package Logic;

public class Grafo {
    private Lista<NodoG> nodos;

    public Grafo(){
        this.nodos = new Lista<>();
    }
    public void addNodo(NodoG nodo){
        nodos.add(nodo);
    }
    /*public void print(){
        Nodo temp = nodos.getHead();
        for (int i = 0; i<nodos.getLargo();i++){
            NodoG temp2 = (NodoG) temp.getDato();
            System.out.println(temp2.getDato());
            Lista listaA = temp2.getAristas();
            Nodo aux = listaA.getHead();
            for (int j = 0; j<listaA.getLargo(); j++){
                Arista a = (Arista) aux.getDato();
                a.getDatos();
                aux = aux.getNext();
            }
            temp = temp.getNext();
        }
    }*/
}
