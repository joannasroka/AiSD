import java.util.PriorityQueue;

public class Solution2 {
    public int getZeros(int value) {
        int zeros = 0;
        if (value == 0) return 1;
        while (value % 10 == 0) {
            zeros++;
            value = value / 10;
        }
        return zeros;
    }

    private int cost(Node u, Node v, int[][] A) {
        return getZeros(u.getMult() * v.getValue());
    }

    Node[][] getNodes(int[][] A) {
        Node[][] result = new Node[A.length][A[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = new Node(i, j, A[i][j], Integer.MAX_VALUE);
            }
        }
        return result;
    }

    void createNodeAtPos(int x, int y, Node[][] nodes, int[][] a) {
        if (x == a.length || y == a[0].length) return;
        if (nodes[x][y] != null) return;
        nodes[x][y] = new Node(x, y, a[x][y], Integer.MAX_VALUE);
    }

    void updateCost(Node currNode, Node dest, int[][] A) {
        int newCost = cost(currNode, dest, A);
        if (newCost < dest.getCost()) {
            dest.setCost(newCost);
            dest.setMult(currNode.getMult() * dest.getValue());
        }
    }

    public int solution(int[][] A) {
        Node[][] nodes = new Node[A.length][A[0].length];
        nodes[0][0] = new Node(0, 0, A[0][0], getZeros(A[0][0]));
        PriorityQueue<Node> availablePaths = new PriorityQueue<>();
        availablePaths.add(nodes[0][0]);

        while (true) {
            Node firstInQueue = availablePaths.poll();
            int x = firstInQueue.getX();
            int y = firstInQueue.getY();
            if (x == A.length && y == A[0].length) return firstInQueue.getCost();
            createNodeAtPos(x + 1, y, nodes, A);
            createNodeAtPos(x, y + 1, nodes, A);
            if(x != A.length) updateCost(firstInQueue, nodes[x+1][y], A);
            if(y != A[0].length) updateCost(firstInQueue, nodes[x][y+1], A);
            if(!availablePaths.contains(nodes[x+1][y])) availablePaths.add(nodes[x+1][y]);
            if(!availablePaths.contains(nodes[x][y+1])) availablePaths.add(nodes[x][y+1]);
        }
    }
}
