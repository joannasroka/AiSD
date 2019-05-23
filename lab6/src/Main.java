public class Main {
    public static void main(String[] args) {

        Tree tree3 =  new Tree(3);
        Tree tree1 = new Tree(1);
        Tree tree2 = new Tree(2);

        Heap heap1 = new Heap();
        heap1.insert(tree1);
        heap1.print();

    }
}
