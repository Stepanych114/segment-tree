package segtrees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

public class SumPlusAddTest {

    @Test
    void testPointRange() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        Assertions.assertEquals(2, st.query(1, 1));
    }

    @Test
    void testFullRangeQuery() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        Assertions.assertEquals(15, st.query(0, 4));
    }

    @Test
    void smallFixedScenario() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        Assertions.assertEquals(9, st.query(1, 3)); // 2+3+4
        st.update(0, 4, 3L); // +3 ко всем
        Assertions.assertEquals(15, st.query(0, 2)); // 4+5+6
    }

    @Test
    void testBorder() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        st.update(0, 0, 3L);
        Assertions.assertEquals(4, st.query(0, 0));
        st.update(4, 4, 2L);
        Assertions.assertEquals(7, st.query(4, 4));
    }
    @Test
    void testSmallRandomArray() {
        int n = 20;
        Long[] arr = new Long[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = (long) random.nextInt(100);
        }
        SegmentTree<Long,Long> st = new SegmentTree<>(arr, Combiner.sumLongs(), Updater.addLongs());

        for (int i = 0; i < 5; i++) {
            int l = random.nextInt(n);
            int r = random.nextInt(n);

            if(l > r) {
                int temp =l;
                l = r;
                r = temp;
            }
            long k = (long) (random.nextInt(20) - 10);

            st.update(l, r, k);

            for(int j = l; j <= r; j++) {
                arr[j] += k;
            }

            long sum =0;
            for(int j =0; j < n; j++)
                sum += arr[j];

            Assertions.assertEquals(sum, st.query(0, n-1));
        }
    }
    @Test
    void largeRandomArrayTest() {
        int n = 1000;
        Long[] arr = new Long[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = (long) random.nextInt(100);
        }
        SegmentTree<Long,Long> st = new SegmentTree<>(arr, Combiner.sumLongs(), Updater.addLongs());

        for (int i = 0; i < 5; i++) {
            int l = random.nextInt(n);
            int r = random.nextInt(n);

            if(l > r) {
                int temp =l;
                l = r;
                r = temp;
            }
            long k = (long) (random.nextInt(20) - 10);

            st.update(l, r, k);

            for(int j = l; j <= r; j++)
                arr[j] += k;

            long sum = 0;
            for(int j =0; j < n; j++)
                sum += arr[j];

            Assertions.assertEquals(sum, st.query(0, n-1));
        }
    }
}