import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    Random random = new Random();

    public void sort (int [] sequence, int begin, int end) {
            int div = div(sequence, begin, end);
            if (begin != div) sort(sequence, begin, div);
            if ((div + 1) != end) sort(sequence, div + 1, end);
    }

    public int div (int [] sequence, int begin, int end){
        int pivot = sequence[random.nextInt(end-begin)+begin];
        int findBigger = begin;
        int findSmaller = end;
        while(true){
            while(sequence[findBigger]<pivot) findBigger++;
            while(sequence[findSmaller]>pivot) findSmaller--;

            if(findBigger>=findSmaller) {
                return findSmaller;
            }
            int temp = sequence[findBigger];
            sequence[findBigger] = sequence[findSmaller];
            sequence[findSmaller] = temp;
        }
    }


    public void sort (int [] sequence){
        sort(sequence,0,sequence.length-1);
    }
}
