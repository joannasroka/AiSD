import java.util.Arrays;
import java.util.Random;

public class QuickSort implements Sort {

    Random random = new Random();

    public void sort(int[] sequence, int begin, int end) {
        int div = div(sequence, begin, end);//miejsce, gdzie skonczyl sortowac
        if (begin != div) sort(sequence, begin, div);
        if ((div + 1) != end) sort(sequence, div + 1, end);
    }

    public int div(int[] sequence, int begin, int end) {
        int pivot = sequence[random.nextInt(end - begin) + begin];
        int findBigger = begin - 1;
        int findSmaller = end + 1;
        while (true) {
            do {
                findBigger++;
            } while (sequence[findBigger] < pivot);
            do {
                findSmaller--;
            } while (sequence[findSmaller] > pivot);

            if (findBigger >= findSmaller) {
                return findSmaller;
            }//zwraca poczatek prawej podtablicy

            int temp = sequence[findBigger];
            sequence[findBigger] = sequence[findSmaller];
            sequence[findSmaller] = temp;
            //zamiana wartosci, ktore stoja po zlych stronach
        }
    }

    public void sort(int[] sequence) {
        sort(sequence, 0, sequence.length - 1);
    }

    @Override
    public String toString() {
        return "Quick";
    }
}
