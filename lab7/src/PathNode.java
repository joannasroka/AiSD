import java.nio.file.Path;
import java.util.Objects;

public class PathNode implements Comparable <PathNode> {
    private Node node;
    private int pathCost;
    private PathNode previous;

    public PathNode(Node node, int pathCost, PathNode previous) {
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

    public void setNode(Node node) {
        this.node = node;
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

    public PathNode getPrevious() {
        return previous;
    }

    public void setPrevious(PathNode previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "PathNode{" +
                "node=" + node +
                ", pathCost=" + pathCost +
                ", previous=" + previous +
                '}';
    }

    public String print (){
        String result = "PathNode{" +
                "node=" + node +
                ", pathCost=" + pathCost + ", path=";
        while(previous!=null){
            result+=previous;
            previous=previous.getPrevious();
        }
        return result;
    }
}
