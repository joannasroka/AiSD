import java.util.ArrayList;

public class ShellSort implements Sort {
    private Gaps gapsType;

    public void sort(int [] sequence){
        if(gapsType == null) setGapsType(Gaps.PAPERNOV);

        int[] gaps = getGaps(sequence.length);
        for (int gap : gaps) {
            for(int elementIndex=gap; elementIndex<sequence.length; elementIndex++){
                int valueToInsert = sequence[elementIndex];// bierze kolejne elementy do wstawienia
                int insertIndex=elementIndex; // szuka miejsca do wstawienia
                while(insertIndex>=gap && sequence[insertIndex-gap]>valueToInsert){
                    sequence[insertIndex]=sequence[insertIndex-gap];
                    insertIndex-=gap;
                }
                sequence[insertIndex]=valueToInsert;
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
        if(gapsType.equals(Gaps.PAPERNOV)) return papernov(length);
        else return sedgewick(length);
    }

    int[] papernov(int length) {
        ArrayList<Integer> gaps = new ArrayList<>();
        int biggestGap = 1;
        int k = 1;
        while (biggestGap < length) {
            gaps.add(biggestGap);
            k *= 2;
            biggestGap = 1 + k;
        }
        int[] result = new int[gaps.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = gaps.get(i);
        }
        return result;
    }

    private int[] sedgewick(int length) {
        ArrayList<Integer> gaps = new ArrayList<>();
        int biggestGap = 1;
        int k = 1;
        while (biggestGap < length) {
            gaps.add(biggestGap);
            k += 1;
            biggestGap = (int) (Math.pow(4, k) + 3 * Math.pow(2, k - 1) + 1);
        }
        int[] result = new int[gaps.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = gaps.get(i);
        }
        return result;

    }

    enum Gaps {
        PAPERNOV, SEDGEWICK
    }
}
