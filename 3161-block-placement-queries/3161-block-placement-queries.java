import java.util.*;

class Solution {

    static class SegTree {
        int n;
        int[] tree;

        SegTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) >> 1;

            if (idx <= mid)
                update(node * 2, l, mid, idx, val);
            else
                update(node * 2 + 1, mid + 1, r, idx, val);

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int L, int R) {
            if (L > R) return 0;
            return query(1, 0, n - 1, L, R);
        }

        private int query(int node, int l, int r, int L, int R) {
            if (L <= l && r <= R) return tree[node];
            if (r < L || l > R) return 0;

            int mid = (l + r) >> 1;

            return Math.max(
                query(node * 2, l, mid, L, R),
                query(node * 2 + 1, mid + 1, r, L, R)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int MAX = 50000;

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        SegTree seg = new SegTree(MAX + 1);

        Integer prev = null;
        for (int p : obstacles) {
            if (prev != null) {
                seg.update(p, p - prev);
            }
            prev = p;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];

            if (q[0] == 2) {
                int x = q[1];
                int sz = q[2];

                int p = obstacles.floor(x);

                int bestGap = Math.max(
                    seg.query(0, p),
                    x - p
                );

                ans.add(bestGap >= sz);
            } else {
                int cur = q[1];

                int left = obstacles.lower(cur);
                Integer right = obstacles.higher(cur);

                if (right != null) {
                    seg.update(right, right - left);
                }

                seg.update(cur, 0);
                obstacles.remove(cur);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}