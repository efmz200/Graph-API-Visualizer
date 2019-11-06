package Logic;

public class test {
    public static void main(String[] args){
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
        G.addEdge(numero2,numero3,2,1);
        G.addEdge(numero3,numero2,3,1);
        G.addEdge(numero2,numero4,4,1);
        G.addEdge(numero4,numero5,5,1);
        G.addEdge(numero3,numero6,6,1);
        G.addEdge(numero5,numero6,7,1);
        G.addEdge(numero3,numero4,8,1);

        G.breadthFirstTraversal();
        System.out.println('\n' + '\n');
        G.adyacencyList();
    }
}
