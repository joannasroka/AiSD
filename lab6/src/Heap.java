import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.layout.HierarchicalLayout;

public class Heap {
    private Tree head;
    private int size;

    public Heap(Tree head) {
        this.head = head;
    }

    public Heap() {
        this.head = null;
    } // konstruktor bez parametru

    public void clear() {
        head = null;
    }

    public void draw (){
        if (head == null) return;
        Tree head = this.head;
        Graph graph = new SingleGraph("Heap");
        String lastID = null;
        while (head != null) {
       //     graph.addNode(head.getRoot().getId());
            head.drawHeap(graph, lastID);
            lastID = head.getRoot().getId();
            head = head.getNext();
        }

        for (Node node : graph) {
            node.setAttribute("ui.label", node.getAttribute("value").toString());
        }
        Viewer viewer = graph.display(false);
        viewer.enableAutoLayout(new HierarchicalLayout());
    }

    public void delete(int key) {
        if (isEmpty()) return;

        Tree prev = head;
        if (head.getRoot().getKey() == key) {
            if (head.getNext() != null) {
                head = head.getNext();
            }
            head = null;
        }

        while (prev.getNext() != null) {
            if (prev.getNext().getRoot().getKey() == key) {
                prev.setNext(prev.getNext().getNext());
            }
            prev = prev.getNext();
        }


    }

    public int size() {
        Tree head = this.head;
        int size = 0;
        if (head == null) return 0;
        while (head != null) {
            size += 1;
            head = head.getNext();
        }
        return size;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void print() {
        if (head == null) return;
        int i = 0;
        Tree head = this.head;
        while (head != null) {
            System.out.println("drzewo nr: "+ i + ".");
            head.print();
            head = head.getNext();

            System.out.println();
            i++;
        }
    }

    public Tree minTree() {
        return minTree(head);
    }

    public void insert(Tree tree) {
        Heap newHeap = new Heap(tree);
        Heap heap = unionHeaps(mergeHeaps(newHeap));
        head = heap.head;
    }

    private Tree minTree(Tree treeO) {
        Tree tree = treeO;
        int min = Integer.MAX_VALUE;
        Tree minTree = null;
        while (tree != null) {
            if (tree.getRoot().getKey() < min) {
                min = tree.getRoot().getKey();
                minTree = tree;
            }
            tree = tree.getNext();
        }
        delete(min);
        return minTree;
    }

    private Tree addHeapHeadToChain(Heap result, Heap heapToAdd, Tree lastInChain) {
        if (lastInChain == null) {
            result.head = heapToAdd.head;
            lastInChain = result.head;
        } else {
            lastInChain.setNext(heapToAdd.head);
            lastInChain = lastInChain.getNext();
        }
        heapToAdd.head = heapToAdd.head.getNext(); // nastepnik po glowie
        return lastInChain; // ostatni dodany do lancucha wynikowego
    }

    public Heap mergeHeaps(Heap other) {
        Heap result = new Heap(null);
        Tree lastInChain = null; // ostatni dodany do lancucha wynikowego
        while (head != null && other.head != null) {
            if (head.getDegree() <= other.head.getDegree()) {
                lastInChain = addHeapHeadToChain(result, this, lastInChain);
            } else {
                lastInChain = addHeapHeadToChain(result, other, lastInChain);
            }
        }
        while (head != null) {
            lastInChain = addHeapHeadToChain(result, this, lastInChain);
        }
        while (other.head != null) {
            lastInChain = addHeapHeadToChain(result, other, lastInChain);
        }
        return result;
    }

    public Heap unionHeaps(Heap result) {
        if (result.head == null) return result;
        Tree prevX = null;
        Tree x = result.head;
        Tree nextX = result.head.getNext();
        Tree temp;
        while (nextX != null) {
            if ((x.getDegree() != nextX.getDegree()) || (nextX.getNext() != null && nextX.getNext().getDegree() == x.getDegree())) {
                temp = x;
                prevX = x;
                x = temp.getNext();
            } else {
                if (x.getRoot().getKey() <= nextX.getRoot().getKey()) {
                    //x.getRoot().setSibling(nextX.getRoot().getSibling());
                    //x.getRoot().getChildren().add(nextX.getRoot());
                    x.setNext(nextX.getNext());
                    nextX.setNext(null);
                    //nextX.getRoot().setSibling(x.getRoot().getChildren().get(0));
                    x.merge(nextX);
                    x.getRoot().setDegree(x.getRoot().getDegree() + 1);
                } else {
                    if (prevX == null) result.head = nextX;
                    else {
                        x.setNext(null);
                        prevX.setNext(nextX);
                        x.getRoot().setSibling(nextX.getRoot().getChildren().get(0));
                        //prevX.getRoot().setSibling(nextX.getRoot()); //??
                    }
                    nextX.merge(x);//?
                    nextX.getRoot().setDegree(nextX.getRoot().getDegree() + 1);
                    x = x.getNext();
                }
            }
            nextX = x.getNext();
        }
        return result;
    }

    public void DecreaseKey(int actualKey, int newKey) {
        if (newKey > actualKey) return;
        if (newKey == actualKey) return;
        DecreaseKey(this.head.getRoot(), actualKey, newKey);
    }

    private Element DecreaseKey(Element element, int actualKey, int newKey) {

        if (element == null) return null;
        Element changedElement = null;
        if(element.getKey()== actualKey)changedElement = element;
        while (changedElement == null) {
            if (element.getChildren() != null) {
                for (Element child :
                        head.getRoot().getChildren()) {

                    if (child.getKey() == actualKey && changedElement == null) {
                        child.setKey(newKey);
                        changedElement = child;
                    }
                }
            }
            if (changedElement != null) {
                while (changedElement.getParent() != null && changedElement.getKey() < changedElement.getParent().getKey()) {
                    swap(changedElement, changedElement.getParent());
                    changedElement = changedElement.getParent();
                }
            } else if (element.getChildren() != null && changedElement == null) {
                return DecreaseKey(element.getChildren().get(0), actualKey, newKey);
            }
            //head = new Tree (head.getRoot().getChildren().get(0));
            // teraz musimy kolejne dziecko obsluzyc

        }
        return changedElement;

    }

    public Tree getHead() {
        return head;
    }
    public void swap (Element e1, Element e2){
        int temp = e1.getKey();
        e1.setKey(e2.getKey());
        e2.setKey(temp);
    }

    public void setHead(Tree head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
