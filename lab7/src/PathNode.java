import java.nio.file.Path;
import java.util.Objects;

public class PathNode implements Comparable <PathNode> {
    private Node node;
    private int pathCost;
    private Node previous;

    public PathNode(Node node, int pathCost, Node previous) {
        this.node = node;
        this.pathCost = pathCost;
        this.previous = previous;
    }

    public Node getNode() {
        return node;
    }


    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    @Override
    public int compareTo(PathNode o) {
        return this.getPathCost() - o.getPathCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathNode pathNode = (PathNode) o;
        return Objects.equals(node, pathNode.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
