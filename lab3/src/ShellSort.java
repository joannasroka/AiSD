import java.util.ArrayList;

public class ShellSort {

    public void sort(int [] sequence, int [] gaps){
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

}
