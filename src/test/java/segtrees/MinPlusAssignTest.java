package segtrees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

public class MinPlusAssignTest {

    @Test
    void testPointRange() {
        Long[] a = {5L, 4L, 3L, 2L, 1L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        Assertions.assertEquals(4, st.query(1, 1));
    }

    @Test
    void testFullRange() {
        Long[] a = {5L, 4L, 3L, 2L, 1L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        Assertions.assertEquals(1, st.query(0, 4));
    }


    @Test
    void testConsecutiveUpdates() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        st.update(0, 2, 1L);
        st.update(1, 3, 2L);
        Assertions.assertEquals(1, st.query(0, 1));
        Assertions.assertEquals(1, st.query(0,2));
        Assertions.assertEquals(1, st.query(0,4));
    }

    @Test
    void testBoundaryPositions() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        st.update(0, 0, 3L);
        Assertions.assertEquals(3, st.query(0, 0));
        st.update(4, 4, 2L);
        Assertions.assertEquals(2, st.query(3, 4));
    }

    @Test
    void smallRandomArrayTest() {
        int n = 20;
        Long[] arr = new Long[n];
        Random random = new Random();
        for (int i = 0; i < n; i++)
            arr[i] = (long) random.nextInt(100);

        SegmentTree<Long,Long> st = new SegmentTree<>(arr, Combiner.minLongs(), Updater.assignLongs());

        for (int i = 0; i < 5; i++) {
            int l = random.nextInt(n);
            int r = random.nextInt(n);

            if(l > r) {
                int temp =l;
                l = r;
                r = temp;
            }
            long k = (long) random.nextInt(50);

            st.update(l, r, k);

            for(int j = l; j <= r; j++)
                arr[j] = k;

            long min = Long.MAX_VALUE;
            for(int j =0; j < n; j++)
                min = Math.min(min, arr[j]);

            Assertions.assertEquals(min, st.query(0, n-1));
        }
    }

    @Test
    void largeRandomArrayTest() {
        int n = 1000;
        Long[] arr = new Long[n];
        Random random = new Random();
        for (int i = 0; i < n; i++)
            arr[i] = (long) random.nextInt(100);

        SegmentTree<Long,Long> st = new SegmentTree<>(arr, Combiner.minLongs(), Updater.assignLongs());

        for (int i = 0; i < 5; i++) {
            int l = random.nextInt(n);
            int r = random.nextInt(n);

            if(l > r) {
                int temp =l;
                l = r;
                r = temp;
            }
            long k = (long) random.nextInt(50);

            st.update(l, r, k);

            for(int j = l; j <= r; j++)
                arr[j] = k;

            long min = Long.MAX_VALUE;
            for(int j =0; j < n; j++)
                min = Math.min(min, arr[j]);
            Assertions.assertEquals(min, st.query(0, n-1));
        }
    }
}