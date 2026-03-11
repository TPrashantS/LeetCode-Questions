class Solution {

    public class DSU{
        int[] parent;
        int[] rank;
        DSU(int n){
            parent= new int[n];
            rank = new int[n];
            for(int i=0; i<n; i++){
                parent[i] = i;
            }
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
        int find(int x){
            if(parent[x]==x) return x;
            return parent[x] = find(parent[x]);
        }
    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList,(a,b)->a[2]-b[2]);
        int q = queries.length;
        int[][] newQueries = new int[q][4];
        for(int i=0;i<q;i++){
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = queries[i][2];
            newQueries[i][3] = i;
        }
        Arrays.sort(newQueries,(a,b)->a[2]-b[2]);
        boolean[] ans = new boolean[q];
        DSU obj = new DSU(n);
        int j = 0;
        for(int i=0;i<q;i++){
            int p1 = newQueries[i][0];
            int p2 = newQueries[i][1];
            int limit = newQueries[i][2];
            int index = newQueries[i][3];
            while(j < edgeList.length && edgeList[j][2] < limit){
                obj.union(edgeList[j][0],edgeList[j][1]);
                j++;
            }
            ans[index] = obj.find(p1) == obj.find(p2);
        }
        return ans;
    }
}