abstract public class Edge {
    protected Node node1;
    protected Node node2;
    protected int weight;

    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", weight=" + weight +
                '}';
    }

    public abstract boolean isComingOutOf(String id);

    public abstract boolean isComingIn (String id);

    public abstract Node getNeighbour (String id);
}
