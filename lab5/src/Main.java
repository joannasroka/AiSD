import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(7);
        heap.add(9);
        heap.add(6);
        heap.show();
        heap.add(12);
        heap.show();
        heap.remove();
        heap.show();

        ArrayList<Integer> sequences = new ArrayList<>();
        sequences.add(10);
        sequences.add(12);
        sequences.add(13);
        sequences.add(11);

        heap.add(sequences);
        heap.show();
    }
}
