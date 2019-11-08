package Logic;

import java.io.*;

public class CSVreader {
    private static int graphCounter = 0;

    public CSVreader(){
    }

    public static Graph readCSVFile(String filePath) throws Exception{
        File file = new File(filePath);
        String line;
        Graph g = new Graph(graphCounter);
        graphCounter++;
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        while((line = br.readLine()) != null){
            String[] container = line.split(",");
            String source = container[0];
            String target = container[1];
            String duration = container[2];
            addToGraph(g, source, target, duration);
        }
        br.close();

        return g;
    }

    private static void addToGraph(Graph g, String source, String target, String duration){
        int time = Integer.parseInt(duration);
        g.addNode(source);
        g.addNode(target);
        g.addEdge(source,target,time);
    }
}
