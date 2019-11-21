package GUI;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

public class Controller extends JFrame {

    private mxGraph grafo;
    private mxGraphComponent component;
    private JToolBar toolBar;

    public Controller(){
        super("Grafos");
        initGUI();
    }

    public void initGUI(){
        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        grafo = new mxGraph();
        toolBar = new JToolBar();
        component = new mxGraphComponent(grafo);
        component.setPreferredSize(new Dimension(400,400));
        getContentPane().add(component);
        grafo.getModel().beginUpdate();
        Object parent = grafo.getDefaultParent();
        grafo.insertVertex(parent,null,"hola",30,80,100,50 );
        grafo.getModel().endUpdate();
    }
}
