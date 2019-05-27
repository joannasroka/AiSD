import java.util.ArrayList;

public class Node implements Comparable <Node> {
    private int x;
    private int y;
    private int value; // wartosc wezla
    private Node lastNode; // ostatni wezel, z którego doszlismy do aktualnego wezla
    private int cost;

    public Node(int x, int y, int value, int cost, Node lastNode) {
        this.x=x;
        this.y=y;
        this.value = value;
        this.cost = cost;
        this.lastNode = lastNode;
    }

    @Override
    public int compareTo(Node o) {
        return this.getCost()-o.getCost();
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


}
