import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Node nodeA = new Node("A",13);
        Node nodeB = new Node("B",2);
        Node nodeC = new Node ("C", 10);
        Node nodeD = new Node("D", 33);
        Node nodeE = new Node("E", 132);


        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);

        graph.addEdge(nodeA, nodeC, 10, true);
        graph.addEdge(nodeA, nodeD, 8, true);
        graph.addEdge(nodeA, nodeE, 15, true);
        graph.addEdge(nodeD,nodeE,2,true);
        graph.addEdge(nodeD,nodeB,7,true);


        graph.printDijkstra(nodeA);
        //System.out.println(graph.printNodes());
        //System.out.println(graph.getNeighbours("C"));

        graph.changeNodeValue("A",99);

       // System.out.println(graph.printNodes());
        //System.out.println(graph.printEdges());
        System.out.println(graph.findDirectedCycle());
       // System.out.println(graph.findUndirectedCycle());
       // System.out.println(graph.minimumSpannigTree().printEdges());
    }
}
