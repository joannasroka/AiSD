import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShellSortTest {
    ShellSort sort = new ShellSort();

    @Test
    public void sort() {
        int [] data = {6,2,3,1,7,8,0,19,5,2,8,1};
        int [] gaps = {5,3,1};
        int [] result = {0, 1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 19};
        sort.sort(data,gaps);
        assertArrayEquals(result,data);
    }
}