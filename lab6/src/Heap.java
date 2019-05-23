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

    public void delete(int key){
        if(isEmpty()) return;

        Tree prev = head;

        while(prev.getNext() !=null) {
            if (prev.getNext().getRoot().getKey() == key) {
                prev.setNext(prev.getNext().getNext());
            }
            prev = prev.getNext();
        }


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

    public void insert(Tree tree){
        Heap newHeap = new Heap(tree);
        Heap heap = unionHeaps(mergeHeaps(newHeap));
        head = heap.head;
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

    private Tree addHeapHeadToChain(Heap result, Heap heapToAdd, Tree lastInChain) {
        if(lastInChain == null) {
            result.head = heapToAdd.head;
            lastInChain = result.head;
        } else {
            lastInChain.setNext(heapToAdd.head);
            lastInChain = lastInChain.getNext();
        }
        heapToAdd.head = heapToAdd.head.getNext();
        return lastInChain;
    }

    public Heap mergeHeaps (Heap other){
        Heap result = new Heap(null);
        Tree lastInChain = null;
        while(head!=null && other.head!=null){
            if(head.getDegree()<=other.head.getDegree()){
                lastInChain = addHeapHeadToChain(result, this, lastInChain);
            }
            else{
                lastInChain = addHeapHeadToChain(result, other, lastInChain);
            }
        }
        while (head!=null){
            lastInChain = addHeapHeadToChain(result, this, lastInChain);
        }
        while(other.head!=null){
            lastInChain = addHeapHeadToChain(result, other, lastInChain);
        }
        return result;
    }

    public Heap unionHeaps (Heap result){
        if (result.head== null) return result;
        Tree prevX = null;
        Tree x = result.head;
        Tree nextX = result.head.getNext();
        Tree temp;
        while(nextX!=null){
            if((x.getDegree()!= nextX.getDegree()) || (nextX.getNext()!=null && nextX.getNext().getDegree()==x.getDegree())){
                temp = x;
                prevX = x;
                x = temp.getNext();
            }
            else
            {
                if(x.getRoot().getKey()<=nextX.getRoot().getKey()){
                    //x.getRoot().setSibling(nextX.getRoot().getSibling());
                    //x.getRoot().getChildren().add(nextX.getRoot());
                    x.setNext(nextX.getNext());
                    nextX.setNext(null);
                    nextX.getRoot().setSibling(x.getRoot().getChildren().get(0));
                    x.merge(nextX);
                }
                else{
                    if(prevX ==null) result.head = nextX;
                    else {
                        prevX.getRoot().setSibling(nextX.getRoot()); //??
                    }
                    nextX.merge(x);//?
                    x = x.getNext();
                }
            }
            nextX = x.getNext();
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
