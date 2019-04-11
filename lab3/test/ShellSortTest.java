import org.junit.Test;

import static org.junit.Assert.*;

public class ShellSortTest {
    ShellSort sort = new ShellSort();

    @Test
    public void sort() {
        int [] data = {6,2,3,1,7,8,0,19,5,2,8,1};
        int [] data2 = {6,2,3,1,7,8,0,19,5,2,8,1};

        int [] result = {0, 1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 19};
        int [] result2 = {0, 1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 19};
        sort.setGapsType(ShellSort.Gaps.PAPERNOV);
        sort.sort(data);
        assertArrayEquals(result,data);

        sort.setGapsType(ShellSort.Gaps.SEDGEWICK);
        sort.sort(data2);
        assertArrayEquals(result2, data2);
    }
}