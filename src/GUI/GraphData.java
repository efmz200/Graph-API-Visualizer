package GUI;

import Logic.Graph;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.HashMap;

/**
 * Clase encargada de almacenar todos los datos relacionados a un grafo y su componente gráfico.
 * @author Sebastian Moya
 */
public class GraphData {
    private mxGraph grafoG;
    private Graph grafo;
    private HashMap hash;
    private JMenuItem item;

    /**
     * Metodo constructor de la clase
     * @param grafoG representación del grafo
     * @param grafo grafo
     * @param hash hashmap
     * @param item item asociado al grafo
     */
    public GraphData(mxGraph grafoG, Graph grafo , HashMap hash, JMenuItem item){
        this.grafoG = grafoG;
        this.grafo = grafo;
        this.hash = hash;
        this.item = item;
    }

    public mxGraph getGrafoG() {
        return grafoG;
    }

    public Graph getGrafo() {
        return grafo;
    }

    public HashMap getHash() {
        return hash;
    }

    public void setGrafoG(mxGraph grafoG) {
        this.grafoG = grafoG;
    }

    public JMenuItem getItem() {
        return item;
    }
}
