package Logic;

public class Lista<T> {
    private NodoL<T> head;
    private int largo;

    /**
     * Método constructor
     */
    public Lista() {
        this.head =null;
        this.largo = 0;
    }

    /**
     * Método encargado de añadir un nuevo nodo a la lista.
     * @param dato recibe un objeto del tipo definido de la lista
     */
    public void add(T dato){
        if(head == null){
            this.head = new NodoL<T>(dato);
            largo ++;
        }else{
            NodoL temp = this.head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(new NodoL<T>(dato));
            largo ++;
        }
    }

    /**
     * Método encargado de cambiar los datos almacenados en el nodo.
     * @param pos posición donde se encuentra el nodo
     * @param dato nuevo dato que se va a almacenar en el nodo
     */
    public void modificarNodo(int pos,T dato){
        int indice =0;
        NodoL temp = this.head;
        while(indice != pos){
            temp = temp.getNext();
            indice++;
        }
        temp.setDato(dato);

    }
    /**
     * Método encargado de verificar si un dato pertenece a la lista.
     * @param valor dato a verificar.
     * @return un boolean con el valor de verdad.
     */
    public boolean verificar(Object valor){
        NodoL temp = this.head;
        boolean estado = false;
        while (temp != null){
            if (temp.getDato().equals(valor)){
                estado = true;
                break;
            }else{
                temp = temp.getNext();
            }
        }
        return estado;
    }
    public int getPos(Object valor){
        NodoL temp = this.head;
        int indice = 0;
        while (temp != null){
            if (temp.getDato().equals(valor)){
                break;
            }else{
                temp = temp.getNext();
                indice ++;
            }
        }
        return indice;
    }
    /**
     * Método primario que verifica si la posición dada es valida, es decir no es menor a 0 ni mayor al largo de la lista menos 1 .
     * @param posicion recibe un dato de tipo entero que es la posición por la cual se busca.
     * @return retorna a un null si la posición es inválida o al método buscarAux si es válido.
     */
    public NodoL buscar(int posicion){
        if(posicion > largo-1){
            return null;
        }else{
            return buscarAux(posicion);
        }
    }

    /**
     * Método que busca el nodo en la posición indicada.
     * @param posicion Dato de tipo entero, es la posición que se desea buscar
     * @return retorna el nodo en la posición dada.
     */
    private NodoL buscarAux(int posicion){
        int indice =0;
        NodoL temp = this.head;
        while(indice != posicion ){
            temp = temp.getNext();
            indice++;
        }
        return temp;
    }

    /**
     * Método encargado de resetear la lista.
     */
    public void reset(){
        this.head = null;
        largo = 0;
    }

    /**
     * Método que elimina un nodo en una psoición dada y re direcciona el puntero del nodo anterior a este.
     * @param posicion dato de tipo entero, es la posición del nodo a eliminar.
     */
    public void eliminar(int posicion) {
        if(posicion == 0){
            this.head = this.head.getNext();
            largo --;
        }else {
            NodoL prev = this.head;
            NodoL temp = prev.getNext();
            int indice = 1;
            while (indice != posicion) {
                temp = temp.getNext();
                prev = prev.getNext();
                indice++;
            }
            prev.setNext(temp.getNext());
            largo --;
        }
    }

    /**
     * Metodo que obtiene el largo de la lista
     * @return retorna el atributo largo
     */
    public int getLargo() {
        return largo;
    }

    /**
     * Método encargado de devolver el primer nodo de la lista
     * @return Atributo head
     */
    public NodoL getHead() {
        return head;
    }
}
