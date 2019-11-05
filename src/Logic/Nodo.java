package Logic;

/**
 * Clase encargarda de alamcenar los datos asignados y asociarlos o apuntarlos otro obejto de la misma clase.
 * @author sebas
 * @param <T> parámetro que representa el tipo de dato que se va a almacenar.
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> next;

    /**
     * Método constructor de la clase
     * @param dato tipo de dato que alamcenará el nodo.
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.next = null;
    }

    /**
     * Método que toma ek nodo que está siendo apuntado por el nodo actual
     * @return el siguiente nodo
     */
    public Nodo<T> getNext() {
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
    public void setNext(Nodo<T> next) {
        this.next = next;
    }
    public void setDato(T dato){
        this.dato = dato;
    }
}
