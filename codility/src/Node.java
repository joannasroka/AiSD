import java.util.ArrayList;

public class Node implements Comparable <Node> {
    private int value; // wartosc wezla
    private Node lastNode; // ostatni wezel, z którego doszlismy do aktualnego wezla
    private int cost;

    public Node(int value, int cost, Node lastNode) {
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
