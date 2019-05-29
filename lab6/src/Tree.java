import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;

public class Tree {
    private Element root;
    private Tree next;

    public Tree(Element element) {
        this.root = element;
    }

    private void draw(Element root, Graph graph) {
        graph.getNode(root.getId()).setAttribute("value", root.getKey());
        if (root.getChildren() != null) {
            for (Element child :
                    root.getChildren()) {
                graph.addNode(child.getId());
                graph.getNode(child.getId()).setAttribute("value", child.getKey());
                graph.addEdge(root.getId() + "," + child.getId(), root.getId(), child.getId());
                draw(child, graph);
            }
        }
    }

    public void drawHeap(Graph graph, String toJoinTo) {
        draw(graph);
        if (toJoinTo != null) {
            Edge edge = graph.addEdge(toJoinTo + "," + root.getId(), toJoinTo, root.getId());
            edge.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
        }
    }

    public void draw() {
        Graph graph = new SingleGraph("Tree");
        graph.addNode(root.getId());
        draw(root, graph);
        for (Node node : graph) {
            node.setAttribute("ui.label", node.getAttribute("value"));
        }
        graph.display();
    }

    public void draw(Graph graph) {
        graph.addNode(root.getId());
        draw(root, graph);
    }

    public Tree(int degree) {
        if (degree == 0) {
            this.root = new Element(0, 0, null);
        } else {
            Tree tree1 = new Tree(degree - 1);
            tree1.getRoot().setKey(-degree + 1);
            Tree tree2 = new Tree(degree - 1);
            tree2.getRoot().setKey(-degree + 2);

            tree1.merge(tree2);
            this.root = tree1.root;


        }

    }

    public int getDegree() {
        if (root != null) return root.getDegree();
        else throw new NullPointerException();
    }

    public void merge(Tree futureChild) {  // zakładamy, że futureChild ma większy klucz niż aktualne drzewo
        if (getDegree() != futureChild.getDegree()) throw new IllegalArgumentException();
        if (root.getKey() <= futureChild.root.getKey()) {
            root.getChildren().add(futureChild.root);
            root.setDegree(getDegree() + 1);
            futureChild.getRoot().setParent(root);
        } else throw new IllegalArgumentException("futureChild has bigger key than this");
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void print() {
        ArrayList<Element> printList = new ArrayList<>();
        if (isEmpty()) return;
        printList.add(root); // najpierw korzen potem dzieci
        while (!printList.isEmpty()) {
            printList.addAll(printList.get(0).getChildren());
            System.out.println(printList.get(0).getKey());
            printList.remove(0);
        }



        /*
        System.out.println(element.getKey() + "\n");
        if(!element.getChildren().isEmpty()) {
            for (Element child :
                    element.getChildren()) {
                print(child);
            }
        }
        */

    }

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element root) {
        this.root = root;
    }

    public Tree getNext() {
        return next;
    }

    public void setNext(Tree next) {
        this.next = next;
    }
}
