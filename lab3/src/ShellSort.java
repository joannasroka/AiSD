import java.util.ArrayList;

public class ShellSort implements Sort {
    private int[] gaps;

    public void sort(int [] sequence){
        if(gaps == null) throw new IllegalArgumentException("empty gaps");
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

    public int[] getGaps() {
        return gaps;
    }

    public void setGaps(int[] gaps) {
        this.gaps = gaps;
    }
}
