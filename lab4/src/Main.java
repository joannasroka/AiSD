public class Main {
    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(7);
        binaryTree.add(5);
        binaryTree.add(3);
        binaryTree.add(10);
        binaryTree.add(8);
        binaryTree.add(12);

        //binaryTree.showInOrder();
        //binaryTree.showPostOrder();
        //binaryTree.showPreOrder();

        //System.out.println(binaryTree.min());
        //binaryTree.showInOrder();

        System.out.println(binaryTree.root().getKey());

        binaryTree.remove(7);
        //binaryTree.showInOrder();

        System.out.println(binaryTree.root().getKey());
        //System.out.println(binaryTree.min());

        binaryTree.add(7);
        System.out.println(binaryTree.root().getKey());
        //binaryTree.showInOrder();
        //System.out.println(binaryTree.min());

        System.out.println(binaryTree.subTree(7));

    }
}
