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
    
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DSU obj = new DSU(n);
        HashSet<Integer> hs = new HashSet<>();
        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                int count = 0;
                for(int k = 0; k < strs[i].length(); k++){
                    if(strs[i].charAt(k) != strs[j].charAt(k)){
                        count++;
                        if(count > 2) break;
                    }
                }
                if(count == 0 || count == 2){
                    obj.union(i, j);
                }
            }
        }
        for(int i = 0; i< n; i++){
            hs.add(obj.find(i));
        }
        return hs.size();
    }
}