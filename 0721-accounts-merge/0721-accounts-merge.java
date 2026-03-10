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
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DSU dsu = new DSU(n);
        HashMap<String, Integer> emailToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, i);
                } else {
                    dsu.union(i, emailToIndex.get(email));
                }
            }
        }

        HashMap<Integer, List<String>> hm = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int parent = dsu.find(emailToIndex.get(email));
            hm.computeIfAbsent(parent, k -> new ArrayList<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();
        for (int parent : hm.keySet()) {
            List<String> emails = hm.get(parent);
            Collections.sort(emails);
            List<String> account = new ArrayList<>();
            account.add(accounts.get(parent).get(0));
            account.addAll(emails);
            result.add(account);
        }
        return result;
    }
}