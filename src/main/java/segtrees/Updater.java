package segtrees;

interface Updater<T, U> {
    T applyUpdate(T value, U update, int len);
    U compose(U a, U b);
    U identity();

    static Updater<Long, Long> addLongs() {
        return new Updater<>() {
            @Override
            public Long applyUpdate(Long value, Long update, int len) {
                return value + update*len;
            }

            @Override
            public Long compose(Long a, Long b) {
                return a + b;
            }

            @Override
            public Long identity() {
                return 0L;
            }
        };
    }

    static Updater<Long, Long> assignLongs() {
        return new Updater<>() {
            @Override
            public Long applyUpdate(Long value, Long update,int len) {
                return update*len;
            }

            @Override
            public Long compose(Long a, Long b) {
                return b;
            }

            @Override
            public Long identity() {
                return null;
            }
        };
    }

}