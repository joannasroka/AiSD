public class Heap {
    private Tree head;
    private int size;

    public Heap(Tree head) {
        this.head = head;
    }

    public Heap(){this.head = null;} // konstruktor bez parametru

    public void clear(){
        head= null;
    }

    public int size(){
        if(head==null) return 0;
        while(head!=null){
            size+=1;
            head=head.getNext();
        }
        return size;
    }
    public boolean isEmpty(){
        return (head==null);
    }

    public void print (){
        if(head==null) return;
        while(head!=null){
            head.print();
            head=head.getNext();
        }
    }

    public Tree minTree (){
        return minTree(head);
    }

    private Tree minTree (Tree tree){
        int min = Integer.MAX_VALUE;
        Tree minTree = null;
        while (tree!= null){
            if(tree.getRoot().getKey()<min){
                min = tree.getRoot().getKey();
                minTree = tree;
            }
            tree = tree.getNext();
        }
        return minTree;
    }

    public Heap mergeHeaps (Heap other){
        Heap result = new Heap(null);
        while(head!=null && other.head!=null){
            if(head.getDegree()<=other.head.getDegree()){
                result.head.setNext(head); //????
                head = head.getNext();
            }
            else{
                result.head.setNext(other.head);
                other.head = other.head.getNext();
            }
        }
        while (head!=null){
            result.head.setNext(head); //????
            head = head.getNext();
        }
        while(other.head!=null){
            result.head.setNext(other.head);
            other.head = other.head.getNext();
        }
        return result;
    }

    public Heap unionHeaps (Heap result){
        if (result.head== null) return result;
        Tree prev = null;
        Tree act = result.head;
        Tree next = result.head.getNext();
        while(next!=null){
            if(act.getDegree()!= next.getDegree() || (next.getNext()!=null && next.getNext().getDegree()==act.getDegree())){
                prev = act;
                act = prev.getNext();
                next = act.getNext();
            }
            else
            {
                if(act.getRoot().getKey()<=next.getRoot().getKey()){
                    act.getRoot().setSibling(next.getRoot().getSibling());
                    act.merge(next);
                }
                else{
                    if(prev ==null) result.head = next;
                    else {
                        prev.getRoot().setSibling(next.getRoot());
                    }
                    act.merge(next);
                    act = act.getNext();
                }
            }
            next = act.getNext();
        }
        return result;
    }

    public void DecreaseKey (int actualKey, int newKey){
        if(newKey>actualKey) return;
        if(newKey==actualKey) return;
        if (head== null) return;
        Element changedElement = null;
        while(head.getRoot().getKey()!=actualKey){
            for (Element element :
                    head.getRoot().getChildren()) {
                if (element.getKey() == actualKey){
                    element.setKey(newKey);
                    changedElement = element;
                }
            }
        }
        while(changedElement.getParent()!=null && changedElement.getKey()<changedElement.getParent().getKey()){
            Element temp = changedElement.getParent();
            changedElement.getParent().setKey(changedElement.getKey());
            changedElement.setKey(temp.getKey());
            // tutaj cos jeszcze, ale nie wiem:
            // if y and z have satellite fields, exchange them, too
            // y:=z
            // z:=p[y]
        }
    }
    

}
