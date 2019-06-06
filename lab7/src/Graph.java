import java.util.*;

public class Graph {
    private Map<String, Node> nodes = new HashMap<>();
    private Set<UndirectedEdge> undirectedEdges = new HashSet<>();
    private Set<DirectedEdge> directedEdges = new HashSet<>();

    public void addNode(Node node) {
        if (node == null) throw new IllegalArgumentException("null value");
        if (nodes.containsKey(node.getId())) return;
        nodes.put(node.getId(), node);
    }

    public void removeNode(String id) {
        if (!nodes.containsKey(id)) throw new IllegalArgumentException("does not contain id: " + id);
        nodes.remove(id);
    }

    public void areNodesCorrect(Node node1, Node node2) {
        if (node1 == null || node2 == null) throw new IllegalArgumentException("null value");
        if (!(nodes.containsKey(node1.getId()) && nodes.containsKey(node2.getId())))
            throw new IllegalArgumentException("these nodes are not present in graph");
    }

    public void addEdge(Node node1, Node node2, int weight, boolean isDirected) {
        areNodesCorrect(node1, node2);

        if (undirectedEdges.contains(new UndirectedEdge(node1, node2, weight)))
            throw new IllegalArgumentException("duplicate edges");
        if (directedEdges.contains(new DirectedEdge(node1, node2, weight)))
            throw new IllegalArgumentException("duplicate edges");

        if (!isDirected) {
            if (directedEdges.contains(new DirectedEdge(node2, node1, weight)))
                throw new IllegalArgumentException("duplicate edges");
        }

        if (isDirected) directedEdges.add(new DirectedEdge(node1, node2, weight));
        else undirectedEdges.add(new UndirectedEdge(node1, node2, weight));
    }

    public void addUndirectedEdge(UndirectedEdge edge) {
        if (edge == null) throw new IllegalArgumentException("edge is null");
        addEdge(edge.getNode1(), edge.getNode2(), edge.getWeight(), false);
    }

    public void removeEdge(Node node1, Node node2) {
        areNodesCorrect(node1, node2);
        undirectedEdges.remove(new UndirectedEdge(node1, node2, 0));
        directedEdges.remove(new DirectedEdge(node1, node2, 0));
    }

    public Edge getEdge(Node node1, Node node2) {
        areNodesCorrect(node1, node2);

        if (undirectedEdges.contains(new UndirectedEdge(node1, node2, 0))) {
            for (UndirectedEdge edge : undirectedEdges) {
                if (edge.equals(new UndirectedEdge(node1, node2, 0))) return edge;
            }
        } else if (directedEdges.contains(new DirectedEdge(node1, node2, 0))) {
            for (DirectedEdge edge : directedEdges) {
                if (edge.equals(new DirectedEdge(node1, node2, 0))) return edge;
            }
        } else return null;
        throw new IllegalStateException();
    }

    public void changeEdgeWeight(Node node1, Node node2, int newWeight) {
        Edge edge = getEdge(node1, node2);
        if (!(edge == null)) {
            edge.setWeight(newWeight);
        } else throw new IllegalArgumentException("this edge does not exist");
    }

    public void changeNodeValue(String id, int newValue) {
        if (!nodes.containsKey(id)) throw new IllegalArgumentException("this node does not exist");
        nodes.get(id).setValue(newValue);
    }

    public String printEdges() {
        StringBuilder result = new StringBuilder();
        for (UndirectedEdge undirectedEdge : undirectedEdges) {
            result.append(undirectedEdge.toString());
            result.append("; ");
        }
        for (DirectedEdge directedEdge : directedEdges) {
            result.append(directedEdge.toString());
            result.append("; ");
        }
        return result.toString();
    }

    public String printNodes() {
        StringBuilder result = new StringBuilder();
        for (Node node :
                nodes.values()) {
            result.append(node.toString());
        }
        return result.toString();
    }

    public int getNodeOutDegree(String id) {
        if (!(nodes.containsKey(id))) throw new IllegalArgumentException("node does not exist");
        int degree = 0;
        for (UndirectedEdge undirectedEdge : undirectedEdges) {
            if (undirectedEdge.isComingOutOf(id)) degree++;
        }
        for (DirectedEdge directedEdge : directedEdges) {
            if (directedEdge.isComingOutOf(id)) degree++;
        }
        return degree;
    }

    public int getNodeInDegree(String id) {
        if (!(nodes.containsKey(id))) throw new IllegalArgumentException("node does not exist");
        int degree = 0;
        for (UndirectedEdge undirectedEdge : undirectedEdges) {
            if (undirectedEdge.isComingIn(id)) degree++;
        }
        for (DirectedEdge directedEdge : directedEdges) {
            if (directedEdge.isComingIn(id)) degree++;
        }
        return degree;
    }

    public Set<Node> getNeighbours(String id) {
        if (!(nodes.containsKey(id))) throw new IllegalArgumentException("node does not exist");
        Set<Node> neighbours = new HashSet<>();
        Node neighbour;

        for (UndirectedEdge undirectedEdge : undirectedEdges) {
            neighbour = undirectedEdge.getNeighbour(id);
            if (neighbour != null) neighbours.add(neighbour);
        }
        for (DirectedEdge directedEdge : directedEdges) {
            neighbour = directedEdge.getNeighbour(id);
            if (neighbour != null) neighbours.add(neighbour);
        }

        return neighbours;
    }

    public String printNeighbours(String id) {
        Set<Node> neighbours = getNeighbours(id);
        StringBuilder result = new StringBuilder();

        for (Node node : neighbours) {
            result.append(node.toString());
            result.append("; ");
        }

        return result.toString();
    }

    public Node findFirstUnvisited(Set<Node> neighbours, Set<Node> visited, Stack<Node> dfsStack) throws CycleIsFoundException {
        if (neighbours == null || visited == null) throw new IllegalArgumentException("null arguments");
        for (Node neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                return neighbour;
            } else if (dfsStack.contains(neighbour) && !neighbour.equals(getSecondFromStack(dfsStack)))
                throw new CycleIsFoundException();
        }
        return null;
    }


    public Node getSecondFromStack(Stack<Node> stack) {
        if (stack == null || stack.size() == 1 || stack.isEmpty()) return null;
        Node temp = stack.pop();
        Node result = stack.peek();
        stack.push(temp);
        return result;
    }

    public boolean findDirectedCycle() {
        if (nodes.isEmpty()) return false;
        Map<Node, Character> coloredNodes = new HashMap<>();
        for (Node node :
                nodes.values()) {
            coloredNodes.put(node, 'w');
        }

        for (Node node :
                coloredNodes.keySet()) {
            if (!coloredNodes.get(node).equals('w')) continue;
            if (findDirectedCycle(node, coloredNodes)) return true;
        }
        return false;

    }

    private boolean findDirectedCycle(Node node, Map<Node, Character> coloredNodes) {
        coloredNodes.replace(node, 'g'); // zamieniam kolor na szary
        Set<Node> neighbours = getNeighbours(node.getId());
        for (Node neighbour : neighbours) {
            if (coloredNodes.get(neighbour).equals('b')) continue;
            if (coloredNodes.get(neighbour).equals('g')) return true;
            else if (findDirectedCycle(neighbour, coloredNodes)) return true;
        }
        coloredNodes.replace(node, 'b');
        return false;
    }

    private PathNode getFromPriorityQueue(PathNode node, PriorityQueue<PathNode> queue) {
        if (queue == null || queue.isEmpty()) return null;
        for (PathNode pathNode : queue) {
            if (pathNode.equals(node)) return pathNode;
        }
        return null;
    }

    Set <PathNode> dijkstra(Node begin) {
        PriorityQueue<PathNode> queue = new PriorityQueue<>();
        Set<PathNode> visited = new HashSet<>();

        PathNode first = new PathNode(begin, 0, null);
        queue.add(first);

        while (!queue.isEmpty()) {
            PathNode top = queue.poll();
            visited.add(top);

            for (Node neighbour : getNeighbours(top.getNode().getId())) {
                PathNode actualPathNode = new PathNode(neighbour, top.getPathCost() + getEdge(top.getNode(), neighbour).getWeight(), top);
                if (!visited.contains(actualPathNode)) {
                    if (!queue.contains(actualPathNode)) queue.add(actualPathNode);
                    else {
                        PathNode old = getFromPriorityQueue(actualPathNode, queue);
                        if (old.getPathCost() > actualPathNode.getPathCost()) {
                            old.setPathCost(actualPathNode.getPathCost());
                            old.setPrevious(top);
                            queue.remove(old);
                            queue.add(old);
                        }
                    }
                }
            }
        }
        return visited;


    }

    public void printDijkstra (Node begin){
        Set <PathNode> result = new HashSet<>();
        result = dijkstra(begin);
        for (PathNode pathNode : result) {
            System.out.println(pathNode.toString());
            System.out.print("; ");
        }
    }

    public boolean findUndirectedCycle(Node begin) { // algorytm DFS
        if (nodes.isEmpty()) return false;
        Set<Node> visited = new HashSet<>();
        Stack<Node> stackDFS = new Stack<>();
        Node actualNode = begin;
        Node unvisitedNode;
        stackDFS.push(actualNode);

        while (!stackDFS.isEmpty()) {
            actualNode = stackDFS.peek();
            visited.add(actualNode);

            Set<Node> neighbours = getNeighbours(actualNode.getId());
            try {
                unvisitedNode = findFirstUnvisited(neighbours, visited, stackDFS);
                if (unvisitedNode == null) stackDFS.pop();
                else stackDFS.push(unvisitedNode);

            } catch (CycleIsFoundException e) {
                return true;
            }

        }
        return false;

    }

    public Graph minimumSpannigTree() {
        PriorityQueue<UndirectedEdge> edges = new PriorityQueue<>(undirectedEdges);
        Graph result = new Graph();

        for (UndirectedEdge edge : edges) {
            result.addNode(edge.getNode1());
            result.addNode(edge.getNode2());
            result.addUndirectedEdge(edge);
            if (result.findUndirectedCycle(edge.node1)) result.removeEdge(edge.getNode1(), edge.getNode2());
        }
        return result;

    }


}
