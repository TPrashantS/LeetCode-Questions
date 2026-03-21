class Solution {
    int[] tree;
    int size = 20001;
    public List<Integer> countSmaller(int[] nums) {
        tree = new int[4 * size];
        int n = nums.length;
        Integer[] res = new Integer[n];
        for(int i = n - 1; i >= 0; i--) {
            int index = nums[i] + 10000;
            res[i] = query(0, 0, size - 1, 0, index - 1);
            update(0, 0, size - 1, index);
        }
        return Arrays.asList(res);
    }

    int query(int node, int start, int end, int l, int r) {
        if(r < start || end < l) return 0;
        if(l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return query(2 * node + 1, start, mid, l, r)
             + query(2 * node + 2, mid + 1, end, l, r);
    }

    void update(int node, int start, int end, int idx) {
        if(start == end) {
            tree[node]++;
            return;
        }
        int mid = (start + end) / 2;
        if(idx <= mid)
            update(2 * node + 1, start, mid, idx);
        else
            update(2 * node + 2, mid + 1, end, idx);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }
}