package GUI;

import Logic.CSVreader;
import Logic.Graph;
import Logic.Lista;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class App {
    private JPanel panel1;
    private JLabel scroll;
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
    private mxGraph grafo;
    private mxGraphComponent component;
    private Lista<Graph> listaGrafos;


    public App() {
        listaGrafos = new Lista<>();
        grafo = new mxGraph();
        component = new mxGraphComponent(grafo);
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
        component.setPreferredSize(new Dimension(640,610));
        draw.add(component);
        grafo.getModel().beginUpdate();
        Object parent = grafo.getDefaultParent();
        grafo.insertVertex(parent,null,"hola",30,80,100,50 );
        grafo.getModel().endUpdate();
    }

    private void chooser(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("CSV filter", "csv"));
        chooser.showOpenDialog(null);
        File archivo = chooser.getSelectedFile();
        CSVreader csVreader = new CSVreader();
        listaGrafos.add(csVreader.readCSVFile(archivo.getAbsolutePath()));
        System.out.println(archivo.getPath());
        System.out.println(listaGrafos.getLargo());
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
