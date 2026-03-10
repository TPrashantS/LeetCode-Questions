class Solution {

    class DSU{
        int parent[];
        int rank[];
        DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i< n; i++){
                parent[i] = i;
            }
        }
        void union(int x, int y){
            int px = find(x);
            int py = find(y);
            if(px==py) return;
            if(rank[px]>rank[py]){
                parent[py] = px;
            } else if(rank[px]<rank[py]){
                parent[px] = py;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
        int find(int x){
            if(parent[x]==x) return x;
            return parent[x] = find(parent[x]);
        }
    } 
    public int removeStones(int[][] stones) {
        int n = stones.length;
        DSU obj = new DSU(n);
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    obj.union(i, j);
                }
            }
        }
        int comps = 0;
        for(int i = 0; i < n; i++){
            if(obj.find(i) == i){
                comps++;
            }
        }
        return n - comps;
    }
}