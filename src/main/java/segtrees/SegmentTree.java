package segtrees;

class SegmentTree<T, U> {
    private final int n;
    private final T[] tree;
    private final U[] lazy;
    private final Combiner<T> combiner;
    private final Updater<T, U> updater;

    @SuppressWarnings("unchecked")
    public SegmentTree(T[] array, Combiner<T> combiner, Updater<T, U> updater) {
        this.n = array.length;
        this.combiner = combiner;
        this.updater = updater;
        this.tree = (T[]) new Object[4 * n];
        this.lazy = (U[]) new Object[4 * n];
        build(array, 0, 0, n - 1);
    }

    private void build(T[] array, int v, int tl, int tr) {
        if (tl == tr)
            tree[v] = array[tl];
        else {
            int tm = (tl + tr) / 2;
            build(array, v * 2 + 1, tl, tm);
            build(array, v * 2 + 2, tm + 1, tr);
            tree[v] = combiner.apply(tree[v * 2 + 1], tree[v * 2 + 2]);
        }
    }

    private void push(int v, int tl, int tr) {
        if (lazy[v] != null) {
            tree[v] = updater.applyUpdate(tree[v], lazy[v], tr - tl + 1);

            if (tl != tr) {
                if (lazy[v * 2 + 1] == null && updater.identity() != null)
                    lazy[v * 2 + 1] = updater.identity();

                if (lazy[v * 2 + 2] == null && updater.identity() != null)
                    lazy[v * 2 + 2] = updater.identity();

                lazy[v * 2 + 1] = updater.compose(lazy[v * 2 + 1], lazy[v]);
                lazy[v * 2 + 2] = updater.compose(lazy[v * 2 + 2], lazy[v]);
            }
            lazy[v] = null;
        }
    }

    public T query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private T query(int v, int tl, int tr, int l, int r) {
        push(v, tl, tr);
        if (l > r)
            return combiner.identity();
        if (l == tl && r == tr)
            return tree[v];
        int tm = (tl + tr) / 2;
        return combiner.apply(
                query(v * 2 + 1, tl, tm, l, Math.min(r, tm)),
                query(v * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r)
        );
    }

    public void update(int l, int r, U value) {
        update(0, 0, n - 1, l, r, value);
    }

    private void update(int v, int tl, int tr, int l, int r, U value) {
        push(v, tl, tr);
        if (l > r)
            return;
        if (l == tl && r == tr) {
            if (updater.identity() != null){
                if (lazy[v] == null){
                    lazy[v] = updater.identity();
                }

            }
            lazy[v] = updater.compose(lazy[v], value);
            push(v, tl, tr);
        } else {
            int tm = (tl + tr) / 2;
            update(v * 2 + 1, tl, tm, l, Math.min(r, tm), value);
            update(v * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r, value);
            tree[v] = combiner.apply(tree[v * 2 + 1], tree[v * 2 + 2]);
        }
    }
}