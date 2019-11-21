package GUI;

import Logic.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;


public class App {
    private JPanel panel1;
    private HashMap hash;
    private JPanel draw;
    private JMenuItem cargar;
    private JMenuItem addV;
    private JMenuItem deleteV;
    private JMenuItem addA;
    private JMenuItem deleteA;
    private JMenuItem Dijkstra;
    private JMenuItem get;
    private JMenuItem post;
    private JMenuItem put;
    private mxGraph actualGraph;
    private JPanel scroll;
    private Lista<Graph> listaGrafos;
    private mxGraphComponent component;
    private Lista<mxGraph>listaGrafica;


    public App() {
        listaGrafica = new Lista<>();
        hash = new HashMap();
        mxGraph graph = new mxGraph();
        listaGrafos = new Lista<>();
        component = new mxGraphComponent(graph);
        component.setPreferredSize(new Dimension(640,610));
        cargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser();
                System.out.println("Cargué archivo");
            }
        });
        addV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVertice();
                System.out.println("Nuevo vertice");
            }
        });
        deleteV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Eliminé vértice");

            }
        });
        addA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Añadí arista");
            }
        });
        deleteA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("borré arista");
            }
        });
        Dijkstra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Dijkstra");
            }
        });
        get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("get");
            }
        });
        post.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("post");
            }
        });
        put.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("put");
            }
        });
        draw.add(component);
        agregarG();
    }

    /**
     * Metodo encargado de tomar el archivo seleccionado y enviarlo a leer.
     */
    private void chooser(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("CSV filter", "csv"));
        chooser.showOpenDialog(null);
        File archivo = chooser.getSelectedFile();
        CSVreader csVreader = new CSVreader();
        listaGrafos.add(csVreader.readCSVFile(archivo.getAbsolutePath()));
        dibujarGrafo(csVreader.readCSVFile(archivo.getAbsolutePath()));
    }

    /**
     * Metodo encargado de mostrar gráficamente un grafo.
     * @param graf grafo que se desea representar.
     */
    private void dibujarGrafo(Graph graf){
        mxGraph grafo = new mxGraph();
        actualGraph = grafo;
        component.setGraph(grafo);
        draw.add(component);
        Edge[] ed  = graf.getEdges();
        Node [] nodes = graf.getNodes();
        Object parent = grafo.getDefaultParent();
        grafo.getModel().beginUpdate();
        for(Node w: nodes){
            Object v = grafo.insertVertex(parent,null,w.getEntity(),(int)(Math.random()*500)+30,(int)(Math.random()*500)+20,100,50 );
            hash.put(w.getEntity(),v);
        }
        for(Edge e: ed){
            Object v1 = hash.get(e.getsEntity());
            Object v2 = hash.get(e.geteEntity());
            grafo.insertEdge(parent,null,e.getWeight(),v1,v2);
        }
        grafo.getModel().endUpdate();
        listaGrafica.add(grafo);
    }

    private void agregarG(){
        JLabel label = new JLabel("hidj");
        label.setBounds(10,60,50,50);


    }
    private void addVertice(){
        //int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un id para el vertice"));
        String numero = JOptionPane.showInputDialog("Introduzca el número de teléfono");
        int pos = listaGrafica.getPos(actualGraph);
        Graph grafo = (Graph) (listaGrafos.buscar(pos).getDato());
        grafo.addNode(numero);
        Object parent = actualGraph.getDefaultParent();
        actualGraph.insertVertex(parent,null,numero,(int)(Math.random()*500)+30,(int)(Math.random()*500)+20,100,50);
    }
    private void eliminarVertice(){
        
    }

    /**
     * Metodo que muestra el grafo seleccionado en pantalla.
     * @param graph grafo que se desea mostrar.
     */
    private void mostrarGrafo(mxGraph graph){
        component.setGraph(graph);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("App");
        jFrame.setSize(900,650);
        jFrame.setContentPane(new App().panel1);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
