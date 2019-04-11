import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {
    QuickSort sort = new QuickSort();

    @Test
    public void sort() {
        int [] data = {6,2,3,1,7,8,0,19,5,2,8,1};
        int [] result = {0, 1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 19};
        sort.sort(data);
        assertArrayEquals(result,data);
    }

    @Test
    public void sortReverse() {
        int [] data = {6,2,3,1,7,8,0,19,5,2,8,1};
        int [] result = {19, 8, 8, 7, 6, 5, 3, 2, 2, 1, 1, 0};

        //sort.sortReverse(data);
        //assertArrayEquals(result, data);
    }
}