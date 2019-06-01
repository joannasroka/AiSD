import java.util.Objects;

public class UndirectedEdge extends Edge {

    public UndirectedEdge(Node node1, Node node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedEdge that = (UndirectedEdge) o;
        return (Objects.equals(node1, that.node1) &&
                Objects.equals(node2, that.node2)) || (Objects.equals(node1, that.node2) &&
                Objects.equals(node2, that.node1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(node1, node2)+Objects.hash(node2, node1);
    }

    @Override
    public String toString() {
        return "Undirected" + super.toString();
    }

    @Override
    public boolean isComingOutOf(String id) {
        return (node1.getId().equals(id) || node2.getId().equals(id));
    }

    @Override
    public boolean isComingIn(String id) {
        return isComingOutOf(id);
    }

    @Override
    public Node getNeighbour(String id) {
        if(node1.getId().equals(id)) return node2;
        if (node2.getId().equals(id)) return node1;
        return null;
    }
}
