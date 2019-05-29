import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

public class Main {
    public static void main(String[] args) {




        Tree tree1 = new Tree(2);
        Tree tree1_2 = new Tree(1);
        Tree tree2 = new Tree(0);
        Tree tree3 = new Tree (0);

        //tree1.draw();
        //tree2.print();


        Heap heap1 = new Heap();
        heap1.insert(tree1);
        heap1.insert(tree1_2);
        heap1.draw();
        // heap1.insert(tree1_2);

        //System.out.println(heap1.minTree().getRoot().getKey());
        //heap1.print();
        heap1.DecreaseKey(0,-4);

        //System.out.println(heap1.minTree().getRoot().getKey());
        //heap1.print();
        //System.out.println(heap1.size());



    }
}
