package segtrees;

import java.util.function.BinaryOperator;

interface Combiner<T> extends BinaryOperator<T> {
    T identity();
    static Combiner<Long> sumLongs() {
        return new Combiner<>() {
            @Override
            public Long apply(Long a, Long b) {
                return a + b;
            }

            @Override
            public Long identity() {
                return 0L;
            }
        };
    }
    static Combiner<Long> minLongs() {
        return new Combiner<>() {
            @Override
            public Long apply(Long a, Long b) {
                return Math.min(a, b);
            }

            @Override
            public Long identity() {
                return Long.MAX_VALUE;
            }
        };
    }
}