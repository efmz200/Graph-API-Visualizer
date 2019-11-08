package Logic;

import com.fasterxml.jackson.databind.ObjectMapper;

public class test {
    public static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws Exception{
        CSVreader reader = new CSVreader();
        Graph g1 = reader.readCSVFile("src/Resources/Grafo1.csv");
        Graph g2 = reader.readCSVFile("src/Resources/Grafo2.csv");

        System.out.println("-------------------Grafo 2------------------------");

        // g1.breadthFirstTraversal();
        // g1.adyacencyList();
        String grafo1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(g2.sortedNodes("ASC"));
        System.out.println(grafo1);

        System.out.println("--------------------------------------------------");

        // System.out.println("-------------------Grafo 2------------------------");

        // g2.breadthFirstTraversal();
        // g2.adyacencyList();
        // String grafo2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(g2);
        // System.out.println(grafo2);

        // System.out.println("--------------------------------------------------");

        /*
        Graph G = new Graph(1);
        String numero1  = "2551";
        String numero2 = "7202";
        String numero3 = "1073";
        String numero4 = "8822";
        String numero5 = "2288";
        String numero6 = "0272";

        G.addNode(numero1);
        G.addNode(numero2);
        G.addNode(numero3);
        G.addNode(numero4);
        G.addNode(numero5);
        G.addNode(numero6);

        G.addEdge(numero1,numero2,1);
        G.addEdge(numero2,numero3,2);
        G.addEdge(numero3,numero2,3);
        G.addEdge(numero2,numero4,4);
        G.addEdge(numero4,numero5,5);
        G.addEdge(numero3,numero6,6);
        G.addEdge(numero5,numero6,7);
        G.addEdge(numero3,numero4,8);

        //
        //           1
        //  "2551" ---->_   "7202"        _  "2288"
        //              /|   /            /|     |
        //          3 /   / 2           / 5      | 7
        //          /   /             /          |
        //        /  |/_     8       /           V
        //      "1073" -------- > "8822"      "0272"
        //         \____________________________/
        //                  6
        //
        //  Representación del grafo formado anteriormente...
        //
       /* System.out.println("");
        G.adyacencyList();
        System.out.println("");
        G.addEdge(numero2,numero4,6,9);

        // G.breadthFirstTraversal();
        // System.out.println('\n' + '\n');
        G.adyacencyList();

       Dijkstra algoritmo = new Dijkstra(G);
       algoritmo.execute(1);
       String path = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(algoritmo.getPath(6));
       System.out.println(path);

       G.removeEdge(6);

       algoritmo = new Dijkstra(G);
       algoritmo.execute(1);
       path = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(algoritmo.getPath(6));
       System.out.println(path);

       */
    }
}
