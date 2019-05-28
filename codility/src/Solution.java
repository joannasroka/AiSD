import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {

    public int getZeros(int value) {
        int zeros = 0;
        if (value == 0) return 1;
        while (value % 10 == 0) {
            zeros++;
            value = value / 10;
        }
        return zeros;
    }

    public int solution(int[][] A) {


        PriorityQueue<Node> availablePaths = new PriorityQueue<>();

        Node[][] nodeArray = new Node[A.length][A[0].length];

        int x = 0;
        int y = 0;
        int actualCost;
        nodeArray[0][0] = new Node(0, 0, A[0][0], getZeros(A[0][0]), null);
        if (nodeArray[0][0].getValue() == 0) nodeArray[0][0].setWasItZero(true);

        while (x != A.length || y != A[0].length) {
            if (x + 1 < A.length) {
                if (nodeArray[x + 1][y] == null) {

                    if (nodeArray[x][y].isWasItZero()) {
                        nodeArray[x + 1][y] = new Node(x + 1, y, A[x + 1][y], 1, nodeArray[x][y], true);
                    } else if (A[x + 1][y] == 0) {
                        nodeArray[x + 1][y] = new Node(x + 1, y, A[x + 1][y], getZeros(A[x + 1][y]), nodeArray[x][y], true);
                    } else {
                        nodeArray[x + 1][y] = new Node(x + 1, y, A[x + 1][y], nodeArray[x][y].getCost() + getZeros(A[x + 1][y]), nodeArray[x][y]);
                    }
                    availablePaths.add(nodeArray[x + 1][y]);
                } else {

                    if (nodeArray[x][y].isWasItZero()) {
                        actualCost = 1;
                    } else {
                        actualCost = nodeArray[x][y].getCost() + getZeros(A[x + 1][y]);
                    }
                    if (actualCost < nodeArray[x + 1][y].getCost()) {
                        availablePaths.remove(nodeArray[x + 1][y]);
                        nodeArray[x + 1][y].setCost(actualCost);
                        nodeArray[x + 1][y].setLastNode(nodeArray[x][y]);

                        if (nodeArray[x][y].isWasItZero()) nodeArray[x + 1][y].setWasItZero(true);

                        availablePaths.add(nodeArray[x + 1][y]);
                    }
                }
            }
            if (y + 1 < A[0].length) {
                if (nodeArray[x][y+1] == null) {

                    if (nodeArray[x][y].isWasItZero()) {
                        nodeArray[x][y+1] = new Node(x, y+1, A[x][y+1], 1, nodeArray[x][y], true);
                    } else if (A[x][y+1] == 0) {
                        nodeArray[x][y+1] = new Node(x, y+1, A[x][y+1], getZeros(A[x][y+1]), nodeArray[x][y], true);
                    } else {
                        nodeArray[x][y+1] = new Node(x, y+1, A[x][y+1], nodeArray[x][y].getCost() + getZeros(A[x ][y+1]), nodeArray[x][y]);
                    }
                    availablePaths.add(nodeArray[x][y+1]);
                } else {

                    if (nodeArray[x][y].isWasItZero()) {
                        actualCost = 1;
                    } else {
                        actualCost = nodeArray[x][y].getCost() + getZeros(A[x][y+1]);
                    }
                    if (actualCost < nodeArray[x ][y+1].getCost()) {
                        availablePaths.remove(nodeArray[x][y+1]);
                        nodeArray[x ][y+1].setCost(actualCost);
                        nodeArray[x ][y+1].setLastNode(nodeArray[x][y]);

                        if (nodeArray[x][y].isWasItZero()) nodeArray[x][y+1].setWasItZero(true);

                        availablePaths.add(nodeArray[x][y+1]);
                    }
                }
            }
            availablePaths.poll();
            x = availablePaths.peek().getX();
            y = availablePaths.peek().getY();


        }

        return nodeArray[A.length - 1][A[0].length - 1].getCost();
    }
}
