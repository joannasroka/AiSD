import java.util.ArrayList;

public class Element {
    private int key;
    private Element parent;
    private Element sibling;
    private ArrayList <Element> children;
    private int degree;
    private static int nextId;
    private String id = Integer.valueOf(nextId++).toString();

    public Element(int key) {
        this.key = key;
        children = new ArrayList<>();
    }
    public Element (int key, int degree, Element parent){
        if(degree<0) throw new IllegalArgumentException();
        this.key = key;
        children = new ArrayList<>();
        this.parent = parent;
        if(degree>0){
            children.add (new Element(-(degree-1), degree-1, this));
            children.add (new Element(-(degree-1), degree-1,this));
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

    public String getId() {
        return id;
    }
}
