// Using BFS

class Solution {
    
    public class Pair{
        int row;
        int col;
        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    int[] rowD = {-1,1,0,0};
    int[] colD = {0,0,1,-1};
    
    boolean isValid(int currRow, int currCol, int n, int m){
        return (currRow>=0 && currRow<n && currCol>= 0 && currCol<m);
    }

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] isVis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        int minutes=0;
        int freshcount = 0;
        for(int i=0; i<n;i++){
            for(int j = 0; j<m; j++){
                if(grid[i][j]==2){
                    q.add(new Pair(i,j));
                }
                if(grid[i][j]==1){
                    freshcount++;
                }
            }
        }
        if(freshcount == 0){
            return 0;
        }

        while(!q.isEmpty()){
            int size = q.size();
            boolean flag = false;
            for(int i=0;i<size;i++){
                Pair curr = q.poll();
                for(int d=0; d<4; d++){
                    int newRow = curr.row + rowD[d];
                    int newCol = curr.col + colD[d];
                    if(isValid(newRow,newCol,n,m) && 
                    grid[newRow][newCol] == 1 && isVis[newRow][newCol]==false){
                        flag = true;
                        grid[newRow][newCol] = 2;
                        q.add(new Pair(newRow,newCol));
                        isVis[newRow][newCol]=true;
                        freshcount--;
                    }
                }
            }
            if(flag==true) minutes++;
        }
        if(freshcount>0){
            return -1;
        } else {
            return minutes;
        }
    }
}

//Using dfs

// class Solution {

//     int[] rowDir = {-1, 1, 0, 0};
//     int[] colDir = {0, 0, -1, 1};
//     int n, m;

//     public int orangesRotting(int[][] grid) {
//         n = grid.length;
//         m = grid[0].length;
//         int[][] time = new int[n][m];
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 time[i][j] = Integer.MAX_VALUE;
//             }
//         }
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (grid[i][j] == 2) {
//                     dfs(grid, i, j, 0, time);
//                 }
//             }
//         }
//         int maxTime = 0;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (grid[i][j] == 1 && time[i][j] == Integer.MAX_VALUE) {
//                     return -1;
//                 }
//                 if (grid[i][j] == 1) {
//                     maxTime = Math.max(maxTime, time[i][j]);
//                 }
//             }
//         }
//         return maxTime;
//     }

//     private void dfs(int[][] grid, int row, int col, int currTime, int[][] time) {
//         if (row < 0 || col < 0 || row >= n || col >= m)
//             return;
//         if (grid[row][col] == 0)
//             return;
//         if (currTime >= time[row][col])
//             return;
//         time[row][col] = currTime;
//         for (int d = 0; d < 4; d++) {
//             int newRow = row + rowDir[d];
//             int newCol = col + colDir[d];
//             dfs(grid, newRow, newCol, currTime + 1, time);
//         }
//     }
// }