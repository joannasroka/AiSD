import java.util.Objects;

public class DirectedEdge extends Edge {


    public DirectedEdge(Node begin, Node end, int weight) {
        this.node1 = begin;
        this.node2 = end;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedEdge that = (DirectedEdge) o;
        return Objects.equals(node1, that.node1) &&
                Objects.equals(node2, that.node2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node1, node2);
    }


    @Override
    public String toString() {
        return "Directed" + super.toString();
    }

    @Override
    public boolean isComingOutOf(String id) {
        return (node1.getId().equals(id));
    }

    @Override
    public boolean isComingIn(String id) {
        return (node2.getId().equals(id));
    }

    @Override
    public Node getNeighbour(String id) {
        if(node1.getId().equals(id)) return node2;
        return null;
    }
}
