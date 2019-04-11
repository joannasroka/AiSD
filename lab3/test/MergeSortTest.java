import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortTest {
    MergeSort sort = new MergeSort();
    @Test
    public void sort() {
        int [] data = {6,2,3,1,7,8,0,19,5,2,8,1};
        int [] result = {0, 1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 19};
        sort.sort(data);
        assertArrayEquals(result,data);
    }
}