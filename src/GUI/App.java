package GUI;

import Logic.*;
import com.google.gson.Gson;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

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

import static Logic.Singleton.getInstancia;

/**
 * Clase encargada de conectar la interfaz con el API. Tambien visualiza los grafos.
 * @author Sebastian Moya
 */
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
    private mxGraph actualGraph;
    private Gson gson;
    private int cont = 1;
    private String var;
    private JMenu btnGrafos;
    private JMenuItem delGrafos;
    private JMenuItem allVertices;
    private JMenuItem delVetices;
    private JMenuItem allAristas;
    private JMenuItem delAristas;
    private JMenuItem actAristas;
    private JMenuItem actNodo;
    private JMenuItem delGrafo;
    private JMenuItem delgrafo;
    private Lista<GraphData> listaGrafos;
    private mxGraphComponent component;

    /**
     * Constructor de la clase.
     */
    public App() {
        listaGrafos = new Lista<>();
        Graph a = new Graph(1);
        mxGraph b =new mxGraph();
        JMenuItem item = new JMenuItem("item");
        btnGrafos.add(item);
        listaGrafos.add(new GraphData(b,a,new HashMap(),item));
        gson = new Gson();
        hash = new HashMap();
        mxGraph graph = new mxGraph();

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
            }
        });
        delGrafos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarGrafos();
            }
        });
        allVertices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarNodos();
            }
        });
        delVetices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delNodos();
            }
        });
        allAristas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getEdges();
            }
        });
        delAristas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleAristas();
            }
        });
        actAristas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actArista();
            }
        });
        actNodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarVertice();
            }
        });
        draw.add(component);
        delgrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarGraf();
            }
        });
    }

    /**
     * Metodo encargado de eliminar un grafo en especifico.
     */
    private void eliminarGraf(){
        try {
            GraphData data = buscarData();
            Graph grafo = data.getGrafo();
            String ruta = "http://localhost:4000/api/graphs/" +grafo.getId() ;
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            component = new mxGraphComponent(new mxGraph());
            btnGrafos.remove(data.getItem());
            actualGraph = null;
            cont--;
            listaGrafos.eliminar(listaGrafos.getPos(data));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo encargado de tomar el archivo seleccionado y enviarlo a leer.
     */
    private void chooser(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("CSV filter", "csv"));
        chooser.showOpenDialog(null);
        File archivo = chooser.getSelectedFile();
        CSVreader csVreader = getInstancia();
        dibujarGrafo(csVreader.readCSVFile(archivo.getAbsolutePath()));
    }

    /**
     * Metodo encargado de redibujar un grafo
     * @param data objeto que guarda todos los datos del grafo que se desea dibujar
     */
    private void redraw(GraphData data){
        mxGraph grafico = new mxGraph();
        actualGraph = grafico;
        component.setGraph(grafico);
        Graph graf = data.getGrafo();
        Edge[] ed  = graf.getEdges();
        Node [] nodes = graf.getNodes();
        Object parent = grafico.getDefaultParent();
        grafico.getModel().beginUpdate();
        for(Node w: nodes){
            Object v = grafico.insertVertex(parent,null,w.getEntity(),(int)(Math.random()*500)+30,(int)(Math.random()*500)+20,100,50 );
            hash.put(w.getEntity(),v);
        }
        for(Edge e: ed){
            Object v1 = hash.get(e.getsEntity());
            Object v2 = hash.get(e.geteEntity());
            grafico.insertEdge(parent,null,e.getWeight(),v1,v2);
        }
        grafico.getModel().endUpdate();
    }

    /**
     * Metodo que solicita un id de un grafo al cliente para hacer la solicitud de un grafo.
     */
    private void getI(){
        try {
            String h = JOptionPane.showInputDialog("id del grafo");
            String ruta = "http://localhost:4000/api/graphs/" +h;
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String contentGraph = content.toString();
            Graph graph = gson.fromJson(contentGraph, Graph.class);
            GraphData data = buscarData(graph);
            redraw(data);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Metodo que busca los datos correctos de un grafo
     * @param grafo grafo al que se le desea buscar sus datos en la lista
     * @return datos del grafo
     */
    private GraphData buscarData(Graph grafo){
        NodoL temp = listaGrafos.getHead();
        GraphData g = (GraphData) temp.getDato();
        for(int i=0; i<listaGrafos.getLargo(); i++){
            g = (GraphData) temp.getDato();
            if(g.getGrafo().getId() == grafo.getId()){
                g.getGrafo().modificarGrafo(grafo.getNodes(),grafo.getEdges(),grafo.getNodeCounter(),grafo.getEdgeCounter());
                break;
            }
            temp = temp.getNext();
        }
        return g;
    }

    /**
     * Comprueba que el item seleccionado concuerde con los datos del grafo.
     * @param var texto del item.
     */
    private void comprobar(String var){
        NodoL temp = listaGrafos.getHead();
        GraphData data = (GraphData) temp.getDato();
        for (int i = 0; i<listaGrafos.getLargo(); i++){
            data = (GraphData) temp.getDato();
            if (data.getItem().getText().equals(var)){
                break;
            }
            temp = temp.getNext();
        }
        actualGraph = data.getGrafoG();
        generalGet(data.getGrafo().getId());
    }

    /**
     * Metodo encargado de mostrar gráficamente un grafo.
     * @param graf grafo que se desea representar.
     */
    private void dibujarGrafo(Graph graf){
        mxGraph grafo = new mxGraph();
        JMenuItem menuItem = new JMenuItem("grafo " + cont);
        cont++;
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var = menuItem.getText();
                comprobar(var);
            }
        });
        btnGrafos.add(menuItem);
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
        GraphData data = new GraphData(grafo,graf,map,menuItem);
        listaGrafos.add(data);
        postGraph(graf);

    }

    /**
     * Metodo encargado de cargar un nuevo grafo al API;
     * @param grafo grafo que se desea cargar.
     */
    private void postGraph(Graph grafo){// arreglar;
        int id = grafo.getId();
        Node[] n = grafo.getNodes();
        String[] j = new String[n.length];
        int cont = 0;
        for(Node no: n ){
            j[cont] = gson.toJson(no);

            cont++;
        }
        Edge[] e = grafo.getEdges();
        cont =0;
        String[] m = new String[e.length];
        for (Edge r: e){
            m[cont] = gson.toJson(r);
            cont++;
        }
        String A1= "[" + j[0];
        for (int i =1; i<j.length;i++){
            A1+= ","+ j[i] ;
        }
        A1 += "]";
        String A2 = "[" + m[0];
        for (int i =1; i<m.length;i++){
            A2+= "," +j[i] ;
        }
        A2+= "]";
        try {
            String ruta = "http://localhost:4000/api/graphs/?num1=" +A1 +"&num2="+A2 ;
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");


        }catch (Exception f){
            System.out.println(f);
        }

    }

    /**
     * Metodo que elimina todos los grafos del API
     */
    private void eliminarGrafos(){
        try {
            String ruta = "http://localhost:4000/api/graphs/";
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            component = new mxGraphComponent(new mxGraph());
            btnGrafos.removeAll();
            listaGrafos.reset();
            cont = 0;
            actualGraph = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que hace la solicitud de los nodos de un grafo al API.
     */
    private void mostrarNodos(){
        try {
            GraphData data = buscarData();
            Graph grafo = data.getGrafo();
            String ruta = "http://localhost:4000/api/graphs/"+grafo.getId()+"/nodes";
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String contentGraph = content.toString();
            Node[] n  = gson.fromJson(contentGraph, Node[].class); // ni idea de si funciona
            printNodos(n);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que muestra en interfaz los nodos de un grafo
     * @param nodes array con los nodos de un grafo
     */
    private void printNodos(Node[] nodes){
        String resultado = "";
        for (Node n: nodes){
            resultado += "Telénofo: "+n.getEntity()+"\n";
        }
        JOptionPane.showMessageDialog(null,resultado);
    }
    /**
     * Metodo encargado de buscar en la lista de grafos el grafo con el que se desea trabajar
     * @return
     */
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

    /**
     * Metodo encargado de solicitar al API de crear un nuevo vertice en el grafo con el numero solicitado.
     */
    private void addVertice(){
        String numero = JOptionPane.showInputDialog("Introduzca el número de teléfono"); // valor que se le asigna al query
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
            String direccion = "http://localhost:4000/api/graphs/" + String.valueOf(grafo.getId())+"/nodes/?num=" + numero; // agregar el valor al query
            URL url = new URL(direccion);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            generalGet(grafo.getId());
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Metodo que realiza una solicitud al API despues de cada accion que se realice con un grafo.
     * @param id id del grafo que se desea obtener
     */
    private void generalGet(int id){
        try {
            String ruta = "http://localhost:4000/api/graphs/" + id;
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String contentGraph = content.toString();
            Graph graph = gson.fromJson(contentGraph, Graph.class);
            GraphData data = buscarData(graph);
            redraw(data);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Metodo encargado de notificar al API de que se desea eliminar un vertice.
     */
    private void eliminarVertice(){
        String numero = JOptionPane.showInputDialog("Introduzca el número de teléfono que desea eliminar");
        GraphData data = buscarData();
        Graph grafo = data.getGrafo();
        Node nodo = grafo.getNode(numero);
        String ruta = "http://localhost:4000/api/graphs/" + "0" + "/nodes/" + "0";
        try {
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            generalGet(grafo.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        /*HashMap hash = data.getHash();
        grafo.removeNode(grafo.getNode(numero).getId());
        Object v = hash.get(numero);
        Object[] o = actualGraph.getEdges(v);
        actualGraph.removeCells(o);
        actualGraph.getModel().remove(v);
        hash.remove(numero);*/
    }

    /**
     * Metodo que envia al API la información que del vertice que se desea modificar.
     */
    private void actualizarVertice(){
        try {
            String numero = JOptionPane.showInputDialog("Inserte el número del vertice que desea modificar"); // variable que se desea mandar
            GraphData data = buscarData();
            Graph grafo = data.getGrafo();
            Node n = grafo.getNode(numero);
            String ruta = "http://localhost:4000/api/graphs/" + grafo.getId() + "/nodes/" +n.getId()+"/?num=" +numero; // agregar el query con el numero.
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            generalGet(grafo.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que realiza una solicitud al API de eliminar todos los nodos de un grafo.
     */
    private void delNodos(){
        try {
            GraphData data = buscarData();
            Graph grafo = data.getGrafo();
            String ruta = "http://localhost:4000/api/graphs/" + grafo.getId() + "/nodes/" ;
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            generalGet(grafo.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que hace una solicitud al API de todas las aristas de un grafo.
     */
    private void getEdges(){
        try {
            GraphData data = buscarData();
            Graph grafo = data.getGrafo();
            String ruta = "http://localhost:4000/api/graphs/" + grafo.getId() + "/edges/";
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String contentGraph = content.toString();
            Edge[] e  = gson.fromJson(contentGraph, Edge[].class); // ni idea de si funciona
            printEdges(e);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo encargado de mostrar todas las arista en pantalla.
     * @param e
     */
    private void printEdges(Edge[] e){
        String resultado = "";
        for (Edge ed: e){
            resultado += "Comienzo: "+ed.getsEntity()+"      Final: "+ ed.geteEntity() + "    Peso: " + ed.getWeight() + "\n";
        }
        JOptionPane.showMessageDialog(null,resultado);
    }

    /**
     * Metodo encargado de mostrar la ruta mas corta entre dos vertices.
     */
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

    /**
     * Metodo encargado de notifiacar al API de la arista que se desea eliminar del modelo.
     */
    private void eliminarArista(){
        String numero1 = JOptionPane.showInputDialog("Introduzca el vertice que se tiene al inicio");
        String numero2 = JOptionPane.showInputDialog("Introduzca el vertice de llegada");
        GraphData data = buscarData();
        Graph  grafo = data.getGrafo();
        Edge[] edges = grafo.getEdges();
        Edge ed = null;
        for(Edge e: edges){
            if (e.getsEntity().equals(numero1)&e.geteEntity().equals(numero2)){
                ed = e;
                break;
            }
        }
        try {
            String ruta = "http://localhost:4000/api/graphs/"+grafo.getId()+"/edges/" + ed.getId();
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            generalGet(grafo.getId());

        }catch (Exception e){
            e.printStackTrace();
        }
        /*HashMap hashMap = data.getHash();
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
        actualGraph.removeCells(ed);*/
    }

    /**
     * Metodo encargado de crear una nueva arista y enviarla al API. Tambien refresaca el grafo
     */
    private void addArista(){
        String numero1 = JOptionPane.showInputDialog("Vertice inicial");
        String numero2 = JOptionPane.showInputDialog("Vertice Final"); // datos a pasar en forma de query
        String numero3 = JOptionPane.showInputDialog("Agregar Peso");
        GraphData data = buscarData();
        Graph  grafo = data.getGrafo();
        try {
            String ruta = "http://localhost:4000/api/graphs/"+grafo.getId()+"/edges/"+"?num1=" + numero1 +"&num2=" +numero3+"&num3=" + numero3 ; //agregar los query respectivos
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            generalGet(grafo.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        /*HashMap hashMap = data.getHash();
        Object v1 = hashMap.get(numero1);
        Object v2 = hashMap.get(numero2);
        actualGraph.getModel().beginUpdate();
        actualGraph.insertEdge(actualGraph.getDefaultParent(),null,Integer.parseInt(numero3),v1,v2);
        grafo.addEdge(numero1,numero2,Integer.parseInt(numero3));
        actualGraph.getModel().endUpdate();*/

    }

    /**
     * Metodo encargado de hacer la solicitud al Api de eliminar todas las aristas de un grafo.
     */
    private void deleAristas(){
        try {
            GraphData data = buscarData();
            Graph grafo = data.getGrafo();
            String ruta = "http://localhost:4000/api/graphs/" + grafo.getId() + "/edges/" ;
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            generalGet(grafo.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo encargado de solicitar al API de que realice una  actualización de una arista.
     */
    private void actArista(){
        String numero1 = JOptionPane.showInputDialog("Introduzca el vertice que se tiene al inicio");
        String numero2 = JOptionPane.showInputDialog("Introduzca el vertice de llegada");
        String numero3 = JOptionPane.showInputDialog("Introduzca el número de donde se quiere inicar");// atributos que se desean agregar al query
        String numero4 = JOptionPane.showInputDialog("Introduzca el número de donde se quiere llegar");
        String numero5 = JOptionPane.showInputDialog("introduzca el nuevo peso");
        GraphData data = buscarData();
        Graph  grafo = data.getGrafo();
        Edge[] edges = grafo.getEdges();
        Edge ed = null;
        for(Edge e: edges){
            if (e.getsEntity().equals(numero1)&e.geteEntity().equals(numero2)){
                ed = e;
                break;
            }
        }
        try {
            String ruta = "http://localhost:4000/api/graphs/" + grafo.getId() + "/edges/" +ed.getId()+"/?num1=" + numero3 +"&num2=" + numero4+ "&num3=" + numero5 ; // agregar el query con el numero.
            URL url = new URL(ruta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            generalGet(grafo.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
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
