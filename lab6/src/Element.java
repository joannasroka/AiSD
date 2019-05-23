import java.util.ArrayList;

public class Element {
    private int key;
    private Element parent;
    private Element sibling;
    private ArrayList <Element> children;
    private int degree;

    public Element(int key) {
        this.key = key;
        children = new ArrayList<>();
    }
    public Element (int key, int degree){
        if(degree<0) throw new IllegalArgumentException();
        this.key = key;
        children = new ArrayList<>();
        if(degree>0){
            children.add (new Element(-(degree-1), degree-1));
            children.add (new Element(-(degree-1), degree-1));
        }


    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Element getParent() {
        return parent;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }

    public Element getSibling() {
        return sibling;
    }

    public void setSibling(Element sibling) {
        this.sibling = sibling;
    }

    public ArrayList<Element> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Element> children) {
        this.children = children;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
