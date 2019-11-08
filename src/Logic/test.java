package Logic;

import com.fasterxml.jackson.databind.ObjectMapper;

public class test {
    public static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws Exception{
        Graph G = new Graph(1);
        String numero1  = "2551";
        String numero2 = "7202";
        String numero3 = "1073";
        String numero4 = "8822";
        String numero5 = "2288";
        String numero6 = "0272";

        G.addNode(1,numero1);
        G.addNode(2,numero2);
        G.addNode(3,numero3);
        G.addNode(4,numero4);
        G.addNode(5,numero5);
        G.addNode(6,numero6);

        G.addEdge(numero1,numero2,1,1);
        G.addEdge(numero2,numero3,2,2);
        G.addEdge(numero3,numero2,3,3);
        G.addEdge(numero2,numero4,4,4);
        G.addEdge(numero4,numero5,5,5);
        G.addEdge(numero3,numero6,6,6);
        G.addEdge(numero5,numero6,7,7);
        G.addEdge(numero3,numero4,8,8);

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
        G.adyacencyList();*/

       Dijkstra algoritmo = new Dijkstra(G);
       algoritmo.execute(1);
       String path = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(algoritmo.getPath(6));
       System.out.println(path);

       G.removeEdge(6);

       algoritmo = new Dijkstra(G);
       algoritmo.execute(1);
       path = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(algoritmo.getPath(6));
       System.out.println(path);

    }
}
