import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Heap {

    private int lastIndex;

    private ArrayList<Integer> heap;

    public Heap() {
        heap = new ArrayList<>();
        lastIndex = -1; // czyli tyle zmiesci
    }

    public void repairUp(int index) { // po dodaniu elementu, idzie w gore
        if (index == 0) return;
        if (heap.get((index - 1) / 2) < heap.get(index)) {
            int temp = heap.get((index - 1) / 2);
            heap.set((index - 1) / 2, heap.get(index));
            heap.set(index, temp);
            repairUp((index - 1) / 2);
        }
    }


    public void repairDown(int index) { // po usunieciu szczytu, idzie w dol
        if (index == lastIndex) return;
        if (index * 2 + 1 > lastIndex) return; // nie ma lewego i prawego

        int left = heap.get(index*2+1);
        int right = heap.get(index*2+2);

        if (index * 2 + 2 <= lastIndex) { // ma lewe i prawe


            if(left>right){
                if (heap.get(index) < left) {
                    int temp = heap.get(index);
                    heap.set(index, heap.get(index * 2 + 1));
                    heap.set(index * 2 + 1, temp);
                    repairDown(index * 2 + 1);
                }
            }
            else {
                if (heap.get(index) < right) {
                    int temp = heap.get(index);
                    heap.set(index, heap.get(index * 2 + 2));
                    heap.set(index * 2 + 2, temp);
                    repairDown(index * 2 + 2);
                }
            }
        }
        else { //ma tylko lewe
            if (heap.get(index) < left) {
                int temp = heap.get(index);
                heap.set(index, heap.get(index * 2 + 1));
                heap.set(index * 2 + 1, temp);
                repairDown(index * 2 + 1);
            }
        }


    }


    public void add(int element) {
        heap.add(element);
        lastIndex++;
        repairUp(lastIndex);
    }

    public void add(ArrayList<Integer> sequences){
        for (Integer element :
                sequences) {
            add(element);
        }
    }

    public void remove() throws NoSuchElementException { // usuwa korzen, czyli z najwieksza wartoscia
        if (heap.size() == 0) throw new NoSuchElementException();

        System.out.println(" Usunieto: "  + heap.get(0));

        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);
        lastIndex--;
        repairDown(0);
    }

    public void show (){
        System.out.println("");
        for (Integer element :
                heap) {
            System.out.print(element+" ");
        }
    }
}
