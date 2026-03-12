class Solution {
    
    private int[] parent;
    private int[] rank;
    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    private boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        if (rank[px] > rank[py]) {
            parent[py] = px;
        } else if (rank[px] < rank[py]) {
            parent[px] = py;
        } else {
            parent[py] = px;
            rank[px]++;
        }
        return true;
    }

    private boolean feasible(int n, int[][] edges, int k, int T) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int edgeCount = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            if (must == 1) {
                if (s < T) return false;
                if (!union(u, v)) return false;
                edgeCount++;
            }
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            if (must == 0 && s >= T) {
                if (union(u, v)) edgeCount++;
            }
        }

        int upgradesUsed = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            if (must == 0 && s < T && s * 2 >= T) {
                if (upgradesUsed < k && union(u, v)) {
                    edgeCount++;
                    upgradesUsed++;
                }
            }
        }
        return edgeCount == n - 1;
    }

    public int maxStability(int n, int[][] edges, int k) {
        TreeSet<Integer> candidateSet = new TreeSet<>();
        for (int[] edge : edges) {
            candidateSet.add(edge[2]);
            candidateSet.add(edge[2] * 2);
        }
        List<Integer> candidates = new ArrayList<>(candidateSet);

        if (!feasible(n, edges, k, 1)) return -1;

        int lo = 0, hi = candidates.size() - 1, ans = 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (feasible(n, edges, k, candidates.get(mid))) {
                ans = candidates.get(mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}