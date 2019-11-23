package GUI;

import Logic.*;
import com.google.gson.Gson;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.sun.jndi.toolkit.url.UrlUtil;
import sun.management.snmp.jvmmib.JvmRTBootClassPathEntryMBean;
import sun.plugin2.message.Message;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private Gson gson;
    private JPanel scroll;
    private Lista<GraphData> listaGrafos;
    private mxGraphComponent component;
    //private Lista<mxGraph>listaGrafica;


    public App() {
        gson = new Gson();
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
                eliminarVertice();
                System.out.println("Eliminé vértice");

            }
        });
        addA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addArista();
                System.out.println("Añadí arista");
            }
        });
        deleteA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarArista();
                System.out.println("borré arista");
            }
        });
        Dijkstra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularDijkstra();
                System.out.println("Dijkstra");
            }
        });
        get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getI();
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
        dibujarGrafo(csVreader.readCSVFile(archivo.getAbsolutePath()));
    }
    private void getI(){
        try {
            String h = JOptionPane.showInputDialog("id");
            String ryta = "http://localhost:4000/graph/?nombre=" + h;
            URL url = new URL(ryta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            System.out.println(content);
            in.close();
            Graph graph = gson.fromJson(gson.toJson(content),Graph.class);
            dibujarGrafo(graph);
            //System.out.println(status);
        }catch (Exception e){
            e.printStackTrace();
        }

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
        HashMap map = hash; // puede ser un problema.
        GraphData data = new GraphData(grafo,graf,map);
        listaGrafos.add(data);
        //postGraph(graf);

    }
    private void postGraph(Graph grafo){
        String json = gson.toJson(grafo);
        try {
            String ruta = "http://localhost:4000/graph/";
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

        }catch (Exception e){
            System.out.println(e);
        }

    }

    private void agregarG(){
        JLabel label = new JLabel("hidj");
        label.setBounds(10,60,50,50);


    }
    private GraphData buscarData(){
        NodoL temp = listaGrafos.getHead();
        GraphData data = (GraphData)temp.getDato();
        while (temp != null){
            data = (GraphData)temp.getDato();
            if (data.getGrafoG().equals(actualGraph)){
                break;
            }else{
                temp = temp.getNext();
            }
        }
        return data;
    }
    private void addVertice(){
        String numero = JOptionPane.showInputDialog("Introduzca el número de teléfono");
        Node n = new Node(numero);
        GraphData data = buscarData();
        Graph grafo = data.getGrafo();
        /*grafo.addNode(numero);
        Object parent = actualGraph.getDefaultParent();
        actualGraph.getModel().beginUpdate();
        Object v = actualGraph.insertVertex(parent,null,numero,(int)(Math.random()*500)+30,(int)(Math.random()*500)+20,100,50);
        data.getHash().put(numero,v);
        actualGraph.getModel().endUpdate();*/
        try {
            String direccion = "http://localhost:4000/graph/" + String.valueOf(grafo.getId())+"/nodes/";
            URL url = new URL(direccion);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private void eliminarVertice(){
        String numero = JOptionPane.showInputDialog("Introduzca el número de teléfono que desea eliminar");
        GraphData data = buscarData();
        Graph grafo = data.getGrafo();
        HashMap hash = data.getHash();
        grafo.removeNode(grafo.getNode(numero).getId());
        Object v = hash.get(numero);
        Object[] o = actualGraph.getEdges(v);
        actualGraph.removeCells(o);
        actualGraph.getModel().remove(v);
        hash.remove(numero);
    }
    private void calcularDijkstra(){
        String numero = JOptionPane.showInputDialog("introduzca el nodo inicial");
        String numero2 = JOptionPane.showInputDialog("introduzca el nodo final");
        GraphData data = buscarData();
        Graph grafo = data.getGrafo();
        Node v1 = grafo.getNode(numero);
        Node v2 = grafo.getNode(numero2);
        Dijkstra di = new Dijkstra(grafo);
        di.execute(v1.getId());
        Path path = di.getPath(v2.getId());
        if (path == null){
            JOptionPane.showMessageDialog(null,"La ruta no existe");
        }else {
            String camino = "[";
            Node[] n = path.getPath();
            for (Node i : n) {
                camino += "," + " " + i.getEntity();
            }
            camino += "]";
            JOptionPane.showMessageDialog(null, "Ruta " + camino + "\n" + "Peso total: " + path.getTotalWeight());
        }
    }

    private void eliminarArista(){
        String numero1 = JOptionPane.showInputDialog("Introduzca el vertice que se tiene al inicio");
        String numero2 = JOptionPane.showInputDialog("Introduzca el vertice de llegada");
        GraphData data = buscarData();
        Graph  grafo = data.getGrafo();
        HashMap hashMap = data.getHash();
        Object v1 =hashMap.get(numero1);
        Object v2 = hashMap.get(numero2);
        Object[] output = actualGraph.getOutgoingEdges(v1);
        Object[] edeges =actualGraph.getEdgesBetween(v1,v2);
        Object[] ed = new Object[1];
        for(Object i: output) {
            for (Object j : edeges) {
                if (i.equals(j)) {
                    ed[0] = j;
                    break;
                }
            }
            if(ed != null){
                break;
            }
        }
        System.out.println(ed);
        actualGraph.removeCells(ed);
    }

    private void addArista(){
        String numero1 = JOptionPane.showInputDialog("Vertice inicial");
        String numero2 = JOptionPane.showInputDialog("Vertice Final");
        String numero3 = JOptionPane.showInputDialog("Agregar Peso");
        GraphData data = buscarData();
        Graph  grafo = data.getGrafo();
        HashMap hashMap = data.getHash();
        Object v1 = hashMap.get(numero1);
        Object v2 = hashMap.get(numero2);
        actualGraph.getModel().beginUpdate();
        actualGraph.insertEdge(actualGraph.getDefaultParent(),null,Integer.parseInt(numero3),v1,v2);
        grafo.addEdge(numero1,numero2,Integer.parseInt(numero3));
        actualGraph.getModel().endUpdate();

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
