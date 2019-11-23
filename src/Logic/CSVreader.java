package Logic;

import java.io.*;

/**
 * Corresponde a la clase que se encarga de leer archivos CSV y crear grafos a partir de estos
 */
public class CSVreader {
    private int graphCounter = 0;

    public CSVreader(){
    }
    /**
     * Lee un archivo CSV y crea un grafo a partir de este
     * @param filePath Path hacia el archivo CSV
     * @return Grafo creado a partir del archivo CSV
     */
    public Graph readCSVFile(String filePath){
        try {
            File file = new File(filePath);
            String line;
            Graph g = new Graph(graphCounter);
            graphCounter++;
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            while ((line = br.readLine()) != null) {
                String[] container = line.split(",");
                String source = container[0];
                String target = container[1];
                String duration = container[2];
                addToGraph(g, source, target, duration);
            }
            br.close();

            return g;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * Agrega nodos y aristas al grafo determinado
     * @param g Grafo determinado
     * @param source Número de teléfono origen
     * @param target Número de teléfono destino
     * @param duration Duración de la llamada
     */
    private void addToGraph(Graph g, String source, String target, String duration){
        int time = Integer.parseInt(duration);
        g.addNode(source);
        g.addNode(target);
        g.addEdge(source,target,time);
    }
}
