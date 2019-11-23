package Logic;

/**
 * Clase encargada de que se retorne una unica instancia de la clase CSVreader.
 * @author Sebastian Moya
 */
public class Singleton {
    public static  CSVreader reader;

    private Singleton(){
    }

    /**
     * metodo encargado de crear o retornar la instancia de la clase ejecutar.
     * @return instancia de la clase ejecutar.
     */
    public static CSVreader getInstancia(){
        if (reader == null){
            reader = new CSVreader();
        }
        return reader;
    }
}
