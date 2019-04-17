import java.util.ArrayList;
import java.util.Comparator;

public class ShellSort implements Sort {
    private Gaps gapsType = null;

    public void sort(int[] sequence) {
        if (gapsType == null) setGapsType(Gaps.PAPERNOV);

        int[] gaps = getGaps(sequence.length);
        for (int gap : gaps) {
            for (int elementIndex = gap; elementIndex < sequence.length; elementIndex++) {
                int valueToInsert = sequence[elementIndex];// bierze kolejne elementy do wstawienia
                int insertIndex = elementIndex; // szuka miejsca do wstawienia

                //bierze z prawej i przeskakuje o kolejne odstepy w lewo i zamienia, jesli po lewej jest wiekszy
                while (insertIndex >= gap && sequence[insertIndex - gap] > valueToInsert) {
                    sequence[insertIndex] = sequence[insertIndex - gap];
                    insertIndex -= gap;
                }

                sequence[insertIndex] = valueToInsert;
            }
        }
    }

    public Gaps getGapsType() {
        return gapsType;
    }

    public void setGapsType(Gaps gapsType) {
        this.gapsType = gapsType;
    }

    private int[] getGaps(int length) {
        if (gapsType.equals(Gaps.PAPERNOV)) return papernov(length);
        else return sedgewick(length);
    }

    //2^k + 1, na poczatku 1
    int[] papernov(int length) {
        ArrayList<Integer> gaps = new ArrayList<>();
        int biggestGap = 1;
        int k = 1;
        while (biggestGap < length) { // zeby nie przeskoczylo
            gaps.add(biggestGap);
            k *= 2;
            biggestGap = 1 + k;
        }
        int[] result = new int[gaps.size()];
        //bo musi byc posortowane odwrotnie
        gaps.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        for (int i = 0; i < result.length; i++) {
            result[i] = gaps.get(i);
        }
        return result;
    }

    //4^k + 3*2^(k-1) + 1, na poczatku 1
    private int[] sedgewick(int length) {
        ArrayList<Integer> gaps = new ArrayList<>();
        int biggestGap = 1;
        int k = 1;
        while (biggestGap < length) {
            gaps.add(biggestGap);
            k += 1;
            biggestGap = (int) (Math.pow(4, k) + 3 * Math.pow(2, k - 1) + 1);
        }
        gaps.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        int[] result = new int[gaps.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = gaps.get(i);
        }
        return result;

    }

    enum Gaps {
        PAPERNOV, SEDGEWICK
    }

    @Override
    public String toString() {
        return "Shell|" + gapsType.toString();
    }
}
