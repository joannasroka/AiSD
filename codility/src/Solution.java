import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {

    public int getZeros(int value) {
        int zeros = 0;
        while (value % 10 == 0) {
            zeros++;
            value = value / 10;
        }
        return zeros;
    }

    public int solution(int[][] A) {

        // moze przejsc po tej calej tablicy i dla kazdego wezla stworzyc liste sciezek -> w prawo lub w dol
        // az do konca tablicy

        PriorityQueue<Node> availablePaths = new PriorityQueue<>();

        Node[][] nodeArray = new Node[A.length][A[0].length];

        int x = 0;
        int y = 0;
        nodeArray[0][0] = new Node(A[0][0], getZeros(A[0][0]), null);

        while (x != A.length || y != A[0].length) {
            if (x + 1 < A.length) {
                if (nodeArray[x + 1][y] == null) {
                    nodeArray[x + 1][y] = new Node(A[x + 1][y], nodeArray[x][y].getCost() + getZeros(A[x + 1][y]), nodeArray[x][y]);
                }
                else {
                    if((nodeArray[x][y].getCost()+getZeros(A[x+1][y]))<nodeArray[x+1][y].getCost())
                    {
                        availablePaths.remove(nodeArray[x+1][y]);
                        nodeArray[x+1][y].setCost(nodeArray[x][y].getCost()+getZeros(A[x+1][y]));
                        nodeArray[x+1][y].setLastNode(nodeArray[x][y]);
                        availablePaths.add(nodeArray[x+1][y]);
                    }
                }
            }
            if (y + 1 < A[0].length) {
                if (nodeArray[x][y+1] == null) {
                    nodeArray[x][y+1] = new Node(A[x][y+1], nodeArray[x][y].getCost() + getZeros(A[x][y+1]), nodeArray[x][y]);
                }
                else {
                    if((nodeArray[x][y].getCost()+getZeros(A[x][y+1]))<nodeArray[x][y+1].getCost())
                    {
                        availablePaths.remove(nodeArray[x][y+1]);
                        nodeArray[x][y+1].setCost(nodeArray[x][y].getCost()+getZeros(A[x][y+1]));
                        nodeArray[x][y+1].setLastNode(nodeArray[x][y]);
                        availablePaths.add(nodeArray[x][y+1]);
                    }
                }
            }


        }

        return -1;
    }
}
