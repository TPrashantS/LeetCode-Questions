class Solution {

    class DSU{
        int[] parent;
        int[] rank;
        DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i<n; i++){
                parent[i] = i;
            }
        }
        int find(int x){
            if(parent[x]==x) return x;
            return parent[x] = find(parent[x]);
        }
        void union(int x, int y){
            int px = find(x);
            int py = find(y);
            if(px == py) return;
            if(rank[px]>rank[py]){
                parent[py] = px;
            } else if(rank[px]<rank[py]){
                parent[px] = py;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public int makeConnected(int n, int[][] connections) {
        DSU obj = new DSU(n);
        HashSet<Integer> hs = new HashSet<>();
        if(connections.length < n-1) return -1;
        for(int i = 0; i<connections.length; i++){
            int from = connections[i][0];
            int to = connections[i][1];
            obj.union(from, to);
        }
        for(int i = 0; i<n; i++){
            hs.add(obj.find(i));
        }
        return hs.size() - 1;
    }
}