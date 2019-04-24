import java.util.ArrayList;

public class Node {
    private Node  left;
    private Node   right;
    private Node parent;
    private int key;
    private int id;

    public Node (Node parent, int key, int id){
        this.parent = parent;
        this.key = key;
        this.id = id;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isLeftChild(){
        return(this.getParent().getKey()>this.getKey());
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
