import com.sun.javafx.image.IntPixelGetter;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int []gaps1 (int size){
        int [] gaps = new int [size/10];
        for(int i=size/2; i)
    }



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


    public static void main(String[] args) {

        QuickSort quickSort = new QuickSort();
        MergeSort mergeSort = new MergeSort();
        ShellSort shellSort = new ShellSort();

        long before;
        long after;
        long elapsed;

        int[] data;
        int [] gaps1;
        int [] gaps2;

        Scanner scanner = new Scanner(System.in);
        String answer;
        boolean program = true;
        while (program) {
            System.out.println("MENU");
            System.out.println("A = Quicksort");
            System.out.println("B = Shell-sort");
            System.out.println("C = Merge-sort");
            System.out.println("E = wyjdz z programu");
            System.out.println("Wybieram: ");
            answer = scanner.next().toUpperCase();
            switch (answer) {
                case "A":
                    System.out.println("Typ posortowania ciagu testowego:");
                    System.out.println("A = W pelni losowy");
                    System.out.println("B = posortowany w 50%");
                    System.out.println("C = w pelni posortowany");
                    System.out.println("D = posortowany odwrotnie");
                    System.out.println("Wybieram: ");
                    answer = scanner.next().toUpperCase();
                    switch (answer) {
                        case "A":
                            System.out.println("Ilosc danych: ");
                            System.out.println("A = 100 tysiecy");
                            System.out.println("B = 500 tysiecy");
                            System.out.println("C = 1 milion");
                            System.out.println("D = 2 miliony");
                            System.out.println("Wybieram: ");
                            answer = scanner.next().toUpperCase();
                            switch (answer) {
                                case "A":
                                    data = random(100000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "B":
                                    data = random(500000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;

                                    break;
                                case "C":
                                    data = random(1000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "D":
                                    data = random(2000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                default:
                                    System.out.println("Nieprawidlowa odpowiedz.");
                                    break;
                            }
                            break;
                        case "B":
                            System.out.println("Ilosc danych: ");
                            System.out.println("A = 100 tysiecy");
                            System.out.println("B = 500 tysiecy");
                            System.out.println("C = 1 milion");
                            System.out.println("D = 2 miliony");
                            System.out.println("Wybieram: ");
                            answer = scanner.next().toUpperCase();
                            switch (answer) {
                                case "A":
                                    data = halfSorted(100000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "B":
                                    data = halfSorted(500000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;

                                    break;
                                case "C":
                                    data = halfSorted(1000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "D":
                                    data = halfSorted(2000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                default:
                                    System.out.println("Nieprawidlowa odpowiedz.");
                                    break;
                            }
                            break;
                        case "C":
                            System.out.println("Ilosc danych: ");
                            System.out.println("A = 100 tysiecy");
                            System.out.println("B = 500 tysiecy");
                            System.out.println("C = 1 milion");
                            System.out.println("D = 2 miliony");
                            System.out.println("Wybieram: ");
                            answer = scanner.next().toUpperCase();
                            switch (answer) {
                                case "A":
                                    data = sorted(100000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "B":
                                    data = sorted(500000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;

                                    break;
                                case "C":
                                    data = sorted(1000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "D":
                                    data = sorted(2000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                default:
                                    System.out.println("Nieprawidlowa odpowiedz.");
                                    break;
                            }
                            break;
                        case "D":
                            System.out.println("Ilosc danych: ");
                            System.out.println("A = 100 tysiecy");
                            System.out.println("B = 500 tysiecy");
                            System.out.println("C = 1 milion");
                            System.out.println("D = 2 miliony");
                            System.out.println("Wybieram: ");
                            answer = scanner.next().toUpperCase();
                            switch (answer) {
                                case "A":
                                    data = sortedReverse(100000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "B":
                                    data = sortedReverse(500000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;

                                    break;
                                case "C":
                                    data = sortedReverse(1000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "D":
                                    data = sortedReverse(2000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                default:
                                    System.out.println("Nieprawidlowa odpowiedz.");
                                    break;
                            }
                            break;
                        case "E":
                            program = false;
                            break;
                        default:
                            break;
                    }
                case "B": {
                    System.out.println("Typ posortowania ciagu testowego:");
                    System.out.println("A = W pelni losowy");
                    System.out.println("B = posortowany w 50%");
                    System.out.println("C = w pelni posortowany");
                    System.out.println("D = posortowany odwrotnie");
                    System.out.println("Wybieram: ");
                    answer = scanner.next().toUpperCase();
                    switch (answer) {
                        case "A":
                            System.out.println("Ilosc danych: ");
                            System.out.println("A = 100 tysiecy");
                            System.out.println("B = 500 tysiecy");
                            System.out.println("C = 1 milion");
                            System.out.println("D = 2 miliony");
                            System.out.println("Wybieram: ");
                            answer = scanner.next().toUpperCase();
                            switch (answer) {
                                case "A":
                                    data = random(100000);
                                    before = System.nanoTime();
                                    shellSort.sort(data,);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "B":
                                    data = random(500000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;

                                    break;
                                case "C":
                                    data = random(1000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "D":
                                    data = random(2000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                default:
                                    System.out.println("Nieprawidlowa odpowiedz.");
                                    break;
                            }
                            break;
                        case "B":
                            System.out.println("Ilosc danych: ");
                            System.out.println("A = 100 tysiecy");
                            System.out.println("B = 500 tysiecy");
                            System.out.println("C = 1 milion");
                            System.out.println("D = 2 miliony");
                            System.out.println("Wybieram: ");
                            answer = scanner.next().toUpperCase();
                            switch (answer) {
                                case "A":
                                    data = halfSorted(100000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "B":
                                    data = halfSorted(500000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;

                                    break;
                                case "C":
                                    data = halfSorted(1000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "D":
                                    data = halfSorted(2000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                default:
                                    System.out.println("Nieprawidlowa odpowiedz.");
                                    break;
                            }
                            break;
                        case "C":
                            System.out.println("Ilosc danych: ");
                            System.out.println("A = 100 tysiecy");
                            System.out.println("B = 500 tysiecy");
                            System.out.println("C = 1 milion");
                            System.out.println("D = 2 miliony");
                            System.out.println("Wybieram: ");
                            answer = scanner.next().toUpperCase();
                            switch (answer) {
                                case "A":
                                    data = sorted(100000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "B":
                                    data = sorted(500000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;

                                    break;
                                case "C":
                                    data = sorted(1000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "D":
                                    data = sorted(2000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                default:
                                    System.out.println("Nieprawidlowa odpowiedz.");
                                    break;
                            }
                            break;
                        case "D":
                            System.out.println("Ilosc danych: ");
                            System.out.println("A = 100 tysiecy");
                            System.out.println("B = 500 tysiecy");
                            System.out.println("C = 1 milion");
                            System.out.println("D = 2 miliony");
                            System.out.println("Wybieram: ");
                            answer = scanner.next().toUpperCase();
                            switch (answer) {
                                case "A":
                                    data = sortedReverse(100000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "B":
                                    data = sortedReverse(500000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;

                                    break;
                                case "C":
                                    data = sortedReverse(1000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                case "D":
                                    data = sortedReverse(2000000);
                                    before = System.nanoTime();
                                    quickSort.sort(data);
                                    after = System.nanoTime();
                                    elapsed = after - before;
                                    break;
                                default:
                                    System.out.println("Nieprawidlowa odpowiedz.");
                                    break;
                            }
                            break;
                        case "E":
                            program = false;
                            break;
                        default:
                            break;

                    break;
                    case "C":
                        break;
                    case "E":
                        program = false;
                        break;
                    default:
                        System.out.println("Bledna odpowiedz.");
                        break;
                }
            }

        }
    }
