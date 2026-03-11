class Solution {

    public class DSU{
        int[] parent;
        int[] rank;
        DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i< n; i++){
                parent[i] = i;
            }
        }
        int find(int x){
            if(parent[x] == x){
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        void union(int x, int y){
            int px = find(x);
            int py = find(y);
            if(px == py) return;
            if(rank[px]>rank[py]){
                parent[py] = px;
            } else if(rank[px]< rank[py]){
                parent[px] = py;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        DSU obj = new DSU(n);
        ArrayList<int[]> edges = new ArrayList<>();
        for(int i = 0; i< n; i++){
            for(int j = i+1; j<n; j++){
                int u = i;
                int v = j;
                int dist = Math.abs(points[i][0]-points[j][0]) + Math.abs(points[i][1]-points[j][1]);
                edges.add(new int[]{i, j, dist});
            }
        }

        edges.sort((a,b) -> a[2]-b[2]);
        int sum = 0;
        
        for(int[] e : edges){
            int from = e[0];
            int to = e[1];
            int weight = e[2];
            if(obj.find(from) != obj.find(to)){
                sum += weight;
                obj.union(from, to);
            }
        }
        return sum;
    }
}