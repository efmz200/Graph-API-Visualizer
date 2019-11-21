package GUI;

import Logic.Graph;
import com.mxgraph.view.mxGraph;

import java.util.HashMap;

public class GraphData {
    private mxGraph grafoG;
    private Graph grafo;
    private HashMap hash;

    public GraphData(mxGraph grafoG, Graph grafo , HashMap hash){
        this.grafoG = grafoG;
        this.grafo = grafo;
        this.hash = hash;
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
}
