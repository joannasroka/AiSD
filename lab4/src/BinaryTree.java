import java.util.NoSuchElementException;

public class BinaryTree  {

    private Node root = null;
    private int id = 0;

    private BinaryTree(Node root, int id){
        this.root = root;
        this.id = id;
    }


    public void add(int key) throws DuplicateElementException{
        if(root==null) {
            root = new Node(null, key,id++);
            return;
        }
        add(key,root);
    }

    private void add (int key, Node root) throws DuplicateElementException{
        if(key==root.getKey()) throw new DuplicateElementException();
        if(key<root.getKey()){
            if(root.getLeft()==null) root.setLeft(new Node(root, key,id++));
            else
                add(key, root.getLeft());
        }
        else {
            if(root.getRight()==null) root.setRight(new Node(root, key,id++));
            else add(key, root.getRight());
        }
    }

    private Node find (int key, Node root){
        if(root==null) throw new NoSuchElementException();
        if(key==root.getKey()) return root;
        if(key<root.getKey()){
            return find(key,root.getLeft());
        }
        else return find(key,root.getRight());
    }

    private Node find (int key){
        return find(key,root);
    }

    private Node min (Node root){
        if(root.getLeft()==null) return root;
        return min(root.getLeft());
    }

    public Node findNext(int key){
        Node nodeToRemove = find (key, root);
        if(nodeToRemove.getRight()!=null) return min(nodeToRemove);
        if(nodeToRemove.getParent()==null) return null;
        return findNext(nodeToRemove);
    }

    private Node findNext (Node nodeToRemove){
        if(nodeToRemove == null) return null;
        if(nodeToRemove.isLeftChild()) return nodeToRemove.getParent();
        else return findNext(nodeToRemove.getParent());
    }

    private void cutOut (Node nodeToRemove){
        if(nodeToRemove.getLeft()==null){
            if(nodeToRemove.isLeftChild()) nodeToRemove.getParent().setLeft(nodeToRemove.getRight());
            else nodeToRemove.getParent().setRight(nodeToRemove.getRight());
        }
        else {
            if(nodeToRemove.isLeftChild()) nodeToRemove.getParent().setLeft(nodeToRemove.getLeft());
            else nodeToRemove.getParent().setRight(nodeToRemove.getLeft());
        }
    }

    public void remove (int key){
        Node nodeToRemove = find(key,root);
        if(nodeToRemove.getRight()==null && nodeToRemove.getLeft()==null){
            if(nodeToRemove.isLeftChild()) nodeToRemove.getParent().setLeft(null);
            else nodeToRemove.getParent().setRight(null);
        }
        else if (nodeToRemove.getRight()==null || nodeToRemove.getLeft()==null) cutOut(nodeToRemove);
        else {
            Node next =  findNext(nodeToRemove);
            nodeToRemove.setKey(next.getKey());
            nodeToRemove.setId(next.getId());
            cutOut(next);
        }

    }

    private int size (Node root){
        if(root == null) return 0;
        return size(root.getLeft())+size(root.getRight())+1;
    }
    public int size(){
        return size(root);
    }

    public Node root(){
        return root;
    }
    public BinaryTree subTree (int key){
        return new BinaryTree(find(key),id);
    }


}
