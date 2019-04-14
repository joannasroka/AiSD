import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static String[] algorithmOptions =
            {"MENU",
                    "A = Quicksort",
                    "B = Shell-sort",
                    "C = Merge-sort",
                    "E = wyjdz z programu",
                    "Wybieram: "};
    private static String[] dataTypeOptions = {
            "Typ posortowania ciagu testowego:",
            "A = W pelni losowy",
            "B = posortowany w 50%",
            "C = w pelni posortowany",
            "D = posortowany odwrotnie",
            "Wybieram: "};

    private static String[] dataCountOptions = {"Ilosc danych: ",
            "A = 100 tysiecy",
            "B = 500 tysiecy",
            "C = 1 milion",
            "D = 2 miliony",
            "Wybieram: "};

    public static int[] random(int size) {
        Random rand = new Random();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt(1000000);
        }
        return result;
    }

    public static int[] halfSorted(int size) {
        Random rand = new Random();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt(1000000);
        }
        QuickSort quickSort = new QuickSort();
        quickSort.sort(result, size / 2, size - 1);
        return result;
    }

    public static int[] sorted(int size) {
        Random rand = new Random();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt(1000000);
        }
        QuickSort quickSort = new QuickSort();
        quickSort.sort(result);
        return result;
    }

    public static int[] sortedReverse(int size) {
        Random rand = new Random();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt(1000000);
        }
        QuickSort quickSort = new QuickSort();
        quickSort.sort(result, size - 1, 0);//??????????????????
        return result;
    }

    static String askUserFor(String[] options, Scanner scanner) {
        for (String option : options) {
            System.out.println(option);
        }
        return scanner.next().toUpperCase();
    }

    static Sort chooseSort(String userInput) {
        Sort chosenSort;
        switch (userInput) {
            case "A":
                chosenSort = new QuickSort();
                break;
            case "B":
                chosenSort = new ShellSort();
                break;
            case "C":
                chosenSort = new MergeSort();
                break;
            case "E":
                return null;
            default:
                throw new BadInputException("Zle dane podczas wybierania algorytmu");
        }
        return chosenSort;
    }

    static int chooseDataCount(String userInput) {
        int dataCount;
        switch (userInput) {
            case "A":
                dataCount = 100000;
                break;
            case "B":
                dataCount = 500000;
                break;
            case "C":
                dataCount = 1000000;
                break;
            case "D":
                dataCount = 2000000;
                break;
            default:
                throw new BadInputException("Zle dane podczas wybierania rozmiaru danych");
        }
        return dataCount;
    }

    static int[] chooseDataSet(String userInput, int dataCount) {
        int[] data;
        switch (userInput) {
            case "A":
                data = random(dataCount);
                break;
            case "B":
                data = halfSorted(dataCount);
                break;
            case "C":
                data = sorted(dataCount);
                break;
            case "D":
                data = sortedReverse(dataCount);
                break;
            default:
                throw new BadInputException("Zle dane podczas wybierania typu danych");
        }
        return data;
    }

    static double performExperiment(Sort sort, int[] data) {
        long before = System.nanoTime();
        sort.sort(data);
        long after = System.nanoTime();
        return (double) (after - before) / 1_000_000_000;
    }

    static double[] performExperiment(Sort sort, int dataCount, String dataType, int experimentsCount) {
        double[] results = new double[experimentsCount];
        for (int i = 0; i < experimentsCount; i++) {
            int[] data = chooseDataSet(dataType, dataCount);
            results[i] = performExperiment(sort, data);
        }
        return results;
    }

    static void runInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String algorithmTypeInput = askUserFor(algorithmOptions, scanner);
                if (algorithmTypeInput.equals("E")) break;
                Sort chosenSort = chooseSort(algorithmTypeInput);

                String dataCountInput = askUserFor(dataCountOptions, scanner);
                int dataCount = chooseDataCount(dataCountInput);

                String dataTypeInput = askUserFor(dataTypeOptions, scanner);
                int[] data = chooseDataSet(dataTypeInput, dataCount);

                double elapsed = performExperiment(chosenSort, data);

                System.out.println("Sortowanie trwalo " + elapsed + " sekund");
            } catch (BadInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        //runInterface();

        double[] results = performExperiment(new MergeSort(), 100000, "A", 100);
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }

    }
}