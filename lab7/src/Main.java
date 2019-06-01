public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Node nodeA = new Node("A",13);
        Node nodeB = new Node("B",2);
        Node nodeC = new Node ("C", 10);
        Node nodeD = new Node("D", 33);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);

        graph.addEdge(nodeA, nodeB, 10, true);
        graph.addEdge(nodeB, nodeC, 10, true);
        graph.addEdge(nodeB, nodeD, 10, true);
        graph.addEdge(nodeA,nodeD,2,true);
        //System.out.println(graph.printNodes());
        //System.out.println(graph.getNeighbours("C"));

        graph.changeNodeValue("A",69);
        graph.changeEdgeWeight(nodeA,  nodeB, 69);
        System.out.println(graph.printNodes());
        System.out.println(graph.printEdges());

        System.out.println(graph.findCycle());
    }
}
