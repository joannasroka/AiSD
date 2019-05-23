import java.util.ArrayList;

public class Tree {
    private Element root;
    private Tree next;

    public Tree(Element element) {
        this.root = element;
    }

    public Tree(int degree) {
        if (degree >= 0) {
            this.root = new Element(-degree, degree);
        } else throw new IllegalArgumentException();
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
            futureChild.root.setParent(root);
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
        printList.add(root);
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
