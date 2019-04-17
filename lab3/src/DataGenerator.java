import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DataGenerator {
    public static int[] random(int size) {
        Random rand = new Random();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt(1_000_000);
        }
        return result;
    }

    public static void halfSorted(int[] randomSequence) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort(randomSequence, randomSequence.length / 2, randomSequence.length - 1);
    }

    public static int[] halfSorted(int length) {
        int[] sequence = random(length);
        halfSorted(sequence);
        return sequence;
    }

    public static void sorted(int[] randomSequence) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort(randomSequence);
    }

    public static int[] sorted(int length) {
        int[] sequence = random(length);
        sorted(sequence);
        return sequence;
    }


    public static void sortedReverse(int[] randomSequence) {
        Arrays.sort(randomSequence);
        for (int i = 0; i < randomSequence.length / 2; i++) {
            int temp = randomSequence[i];
            randomSequence[i] = randomSequence[randomSequence.length - i - 1];
            randomSequence[randomSequence.length - i - 1] = temp;
        }
    }

    public static int[] sortedReverse(int length) {
        int[] sequence = random(length);
        sortedReverse(sequence);
        return sequence;
    }

    public static void generateSequences(int size, String path) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File(path)))) {
            for (int i = 0; i < 100; i++) {
                int[] sequence = random(size);
                for (int i1 : sequence) {
                    bufferedWriter.write(Integer.toString(i1));
                    bufferedWriter.write(" ");
                }
                bufferedWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateSequences() {
        int[] sizes = {100_000, 500_000, 1_000_000, 2_000_000};
        String[] paths = {
                "./lab3/sequences/random100k",
                "./lab3/sequences/random500k",
                "./lab3/sequences/random1m",
                "./lab3/sequences/random2m"
        };
        for (int i = 0; i < 4; i++) {
            generateSequences(sizes[i], paths[i]);
        }
    }


    public static void main(String[] args) {
       // generateSequences();
    }
}
