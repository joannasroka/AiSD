import java.io.*;
import java.util.*;

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

    static int[] chooseDataType(String userInput, int dataCount) {
        int[] data;
        switch (userInput) {
            case "A":
                data = DataGenerator.random(dataCount);
                break;
            case "B":
                data = DataGenerator.halfSorted(dataCount);
                break;
            case "C":
                data = DataGenerator.sorted(dataCount);
                break;
            case "D":
                data = DataGenerator.sortedReverse(dataCount);
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
            int[] data = chooseDataType(dataType, dataCount);
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
                int[] data = chooseDataType(dataTypeInput, dataCount);

                double elapsed = performExperiment(chosenSort, data);

                System.out.println("Sortowanie trwalo " + elapsed + " sekund");
            } catch (BadInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int[][] loadSequencesFromPath(String path, int arrays) {
        int[][] result = new int[arrays][];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {
            for (int i = 0; i < arrays; i++) {
                String line = bufferedReader.readLine();
                String[] splitLine = line.split(" ");
                int[] array = new int[splitLine.length];
                for (int j = 0; j < array.length; j++) {
                    array[j] = Integer.valueOf(splitLine[j]);
                }
                result[i] = array;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public static int[][] loadSequencesFromPathSorted(String path, int arrays) {
        int[][] result = loadSequencesFromPath(path, arrays);
        for (int[] ints : result) {
            DataGenerator.sorted(ints);
        }
        return result;
    }

    public static int[][] loadSequencesFromPathHalfSorted(String path, int arrays) {
        int[][] result = loadSequencesFromPath(path, arrays);
        for (int[] ints : result) {
            DataGenerator.halfSorted(ints);
        }
        return result;
    }

    public static int[][] loadSequencesFromPathReverseSorted(String path, int arrays) {
        int[][] result = loadSequencesFromPath(path, arrays);
        for (int[] ints : result) {
            DataGenerator.sortedReverse(ints);
        }
        return result;
    }


    public static void performExperimentAndSaveRandom(Sort[] sorts, String inPath, String outPath, int arrays) {
        for (Sort sort : sorts) {
            int[][] random = loadSequencesFromPath(inPath, arrays);
            double[] results = new double[random.length];
            for (int i = 0; i < random.length; i++) {
                results[i] = performExperiment(sort, random[i]);
            }
            System.out.println(sort + inPath + "random complete");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outPath), true))) {
                writer.write("\n");
                writer.write(sort + ": " + Arrays.toString(results));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void performExperimentAndSaveSorted(Sort[] sorts, String inPath, String outPath, int arrays) {
        for (Sort sort : sorts) {
            int[][] random = loadSequencesFromPathSorted(inPath, arrays);
            double[] results = new double[random.length];
            for (int i = 0; i < random.length; i++) {
                results[i] = performExperiment(sort, random[i]);
            }
            System.out.println(sort + inPath + "sorted complete");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outPath), true))) {
                writer.write("\n");
                writer.write(sort + ": " + Arrays.toString(results));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void performExperimentAndSaveHalfSorted(Sort[] sorts, String inPath, String outPath, int arrays) {
        for (Sort sort : sorts) {
            int[][] random = loadSequencesFromPathHalfSorted(inPath, arrays);
            double[] results = new double[random.length];
            for (int i = 0; i < random.length; i++) {
                results[i] = performExperiment(sort, random[i]);
            }
            System.out.println(sort + inPath + "halfSorted complete");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outPath), true))) {
                writer.write("\n");
                writer.write(sort + ": " + Arrays.toString(results));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void performExperimentAndSaveReverseSorted(Sort[] sorts, String inPath, String outPath, int arrays) {
        for (Sort sort : sorts) {
            int[][] random = loadSequencesFromPathReverseSorted(inPath, arrays);
            double[] results = new double[random.length];
            for (int i = 0; i < random.length; i++) {
                results[i] = performExperiment(sort, random[i]);
            }
            System.out.println(sort + inPath + "reverseSorted complete");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outPath), true))) {
                writer.write("\n");
                writer.write(sort + ": " + Arrays.toString(results));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //runInterface();

        ShellSort sedgewick = new ShellSort();
        sedgewick.setGapsType(ShellSort.Gaps.SEDGEWICK);
        Sort[] sorts = {new ShellSort(), sedgewick};
        String[] files = {"random100k", "random500k", "random1m", "random2m"};
        String in = "./lab3/sequences/";
        String out = "./lab3/results/";

        for (String file : files) {
             performExperimentAndSaveRandom(
                    sorts,
                    in + file,
                    out + file,
                    100);
        }

        for (String file : files) {
            performExperimentAndSaveSorted(
                    sorts,
                    in + file,
                    out + file + "_sorted",
                    100);
        }
        for (String file : files) {
            performExperimentAndSaveHalfSorted(
                    sorts,
                    in + file,
                    out + file + "_halfSorted",
                    100);
        }
        for (String file : files) {
            performExperimentAndSaveReverseSorted(
                    sorts,
                    in + file,
                    out + file + "_reverseSorted",
                    100);
        }

    }
}