import java.util.ArrayList;

public class Node implements Comparable <Node> {
    private int x;
    private int y;
    private int value; // wartosc wezla
    private Node lastNode; // ostatni wezel, z którego doszlismy do aktualnego wezla
    private int cost;
    private int mult;
    private boolean wasItZero = false;

    public Node(int x, int y, int value, int cost, Node lastNode) {
        this.x=x;
        this.y=y;
        this.value = value;
        this.cost = cost;
        this.lastNode = lastNode;
        this.wasItZero = false;
        this.mult = value;
    }

    public Node(int x, int y, int value, int cost) {
        this.x=x;
        this.y=y;
        this.value = value;
        this.cost = cost;
    }

    public Node(int x, int y, int value, int cost, Node lastNode, boolean wasItZero) {
        this.x=x;
        this.y=y;
        this.value = value;
        this.cost = cost;
        this.lastNode = lastNode;
        this.wasItZero = wasItZero;
    }


    @Override
    public int compareTo(Node o) {
        if(this.getCost() == o.getCost()){
            // ten, który jest bliżej końca ma większy priorytet
            return (o.getX()+o.getY())-(this.getX()+this.getY());
        }
        else return this.getCost()-o.getCost();
    }

    public boolean isWasItZero() {
        return wasItZero;
    }

    public void setWasItZero(boolean wasItZero) {
        this.wasItZero = wasItZero;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }


    public Node getLastNode() {
        return lastNode;
    }

    public void setLastNode(Node lastNode) {
        this.lastNode = lastNode;
    }

    public int getMult() {
        return mult;
    }

    public void setMult(int mult) {
        this.mult = mult;
    }
}
