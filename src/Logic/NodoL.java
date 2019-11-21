package Logic;

public class NodoL<T> {
    private T dato;
    private NodoL<T> next;

    public NodoL(T dato) {
        this.dato = dato;
        this.next = null;
    }
    /**
     * Método que toma el nodo que está siendo apuntado por el nodo actual
     * @return el siguiente nodo
     */
    public NodoL<T> getNext() {
        return next;
    }

    /**
     * Método que retorna el dato guardado en el nodo
     * @return retorna el atributo dato
     */
    public T getDato() {
        return this.dato;
    }

    /**
     * Método que define a cual nodo va a apuntar el nodo actual
     * @param next Es un objeto de tipo nodo, que tiene un valor asignada para guardar
     */
    public void setNext(NodoL<T> next) {
        this.next = next;
    }
    public void setDato(T dato){
        this.dato = dato;
    }
}
