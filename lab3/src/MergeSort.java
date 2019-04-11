public class MergeSort implements Sort {

    private void merge(int [] sequence, int left, int middle, int right){
        int begin1=left;
        int begin2=middle+1;
        int tempIndex = left;
        int [] temp = new int [sequence.length];

        while(begin1<=middle && begin2<=right){
            if(sequence[begin1]<=sequence[begin2]){
                temp[tempIndex]=sequence[begin1];
                tempIndex++;
                begin1++;
            }
            else{
                temp[tempIndex]=sequence[begin2];
                tempIndex++;
                begin2++;
            }

        }
        if(begin1==middle+1){
            while(begin2<=right){
                temp[tempIndex]=sequence[begin2];
                tempIndex++;
                begin2++;
            }

        }else{
            while(begin1<=middle){
                temp[tempIndex]=sequence[begin1];
                tempIndex++;
                begin1++;
            }
        }
        for(int i=left; i<=right; i++){
            sequence[i]=temp[i];
        }
    }

    private void sort (int [] sequence, int left, int right){
        if(right>left){
            int middle = (left+right)/2;
            sort(sequence,left, middle);
            sort(sequence, middle+1,right);

            merge (sequence,left,middle,right);

        }
    }

    public void sort (int [] sequence){
        sort (sequence, 0, sequence.length-1);
    }

}
