class Solution {

    public class DSU{
        int[] parent;
        int[] rank;
        DSU(int n){
            parent= new int[n];
            rank = new int[n];
            for(int i =0; i<n; i++){
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
            if(px==py) return;
            if(rank[px]>rank[py]){
                parent[py] = px;
            } else if(rank[px]<rank[py]){
                parent[px] = py;
            } else{
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        DSU obj = new DSU(n);
        obj.union(0, firstPerson);
        Arrays.sort(meetings, (a,b) -> Integer.compare(a[2], b[2]));
        int i = 0;
        while(i < meetings.length){
            int j = i;
            while(j < meetings.length && meetings[j][2] == meetings[i][2]) j++;
            for(int k = i; k < j; k++){
                obj.union(meetings[k][0], meetings[k][1]);
            }
            for(int k = i; k < j; k++){
                int p1 = meetings[k][0], p2 = meetings[k][1];
                if(obj.find(p1) != obj.find(0)){
                    obj.parent[p1] = p1;
                    obj.rank[p1] = 0;
                }
                if(obj.find(p2) != obj.find(0)){
                    obj.parent[p2] = p2;
                    obj.rank[p2] = 0;
                }
            }
            i = j;
        }
        List<Integer> ans = new ArrayList<>();
        for(int k = 0; k < n; k++){
            if(obj.find(k) == obj.find(0)){
                ans.add(k);
            }
        }
        return ans;
    }
}