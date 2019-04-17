public class MergeSort implements Sort {

    private void merge(int[] sequence, int left, int middle, int right) {
        int begin1 = left;
        int begin2 = middle + 1;
        int tempIndex = 0;
        int[] temp = new int[right-left+1];

        while (begin1 <= middle && begin2 <= right) {
            if (sequence[begin1] <= sequence[begin2]) {
                temp[tempIndex] = sequence[begin1];
                tempIndex++;
                begin1++;
            } else {
                temp[tempIndex] = sequence[begin2];
                tempIndex++;
                begin2++;
            }

        }//dopisuje pozostala reszte jednej z tablic, gdy druga sie skonczy
        if (begin1 == middle + 1) {
            while (begin2 <= right) {
                temp[tempIndex] = sequence[begin2];
                tempIndex++;
                begin2++;
            }

        } else {
            while (begin1 <= middle) {
                temp[tempIndex] = sequence[begin1];
                tempIndex++;
                begin1++;
            }
        }
        //wpisuje posortowana czesc tablicy, zaczynam od miejsca, w ktorym zaczelam sortowac
        for (int i = 0; i < temp.length; i++) {
            sequence[i+left] = temp[i];
        }
    }

    private void sort(int[] sequence, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort(sequence, left, middle);
            sort(sequence, middle + 1, right);
            //dzieli tak dlugo az dojdzie do jednoelementowych tablic i zaczyna je laczyc
            merge(sequence, left, middle, right);

        }
    }

    public void sort(int[] sequence) {
        sort(sequence, 0, sequence.length - 1);
    }

    @Override
    public String toString() {
        return "Merge";
    }
}
