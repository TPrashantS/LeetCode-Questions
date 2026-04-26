class Solution {
    
    public class Pair{
        int row;
        int col;
        int parentrow;
        int parentcol;
        Pair(int row, int col, int parentrow, int parentcol){
            this.row = row;
            this.col = col;
            this.parentrow = parentrow;
            this.parentcol = parentcol;
        }
    }

    int[] rowD = {-1,1,0,0};
    int[] colD = {0,0,1,-1};

    boolean isValid(int currRow, int currCol, int n, int m){
        return (currRow>=0 && currRow<n && currCol>=0 && currCol<m);
    }

    private boolean bfs(char[][] grid, int currRow, int currCol,
                        boolean[][] isVis, int n, int m) {

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(currRow,currCol, -1, -1));
        isVis[currRow][currCol] = true;
        char ch = grid[currRow][currCol];
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int newRow = curr.row + rowD[d];
                int newCol = curr.col + colD[d];
                if (isValid(newRow, newCol, n, m) && grid[newRow][newCol] == ch) {
                    if (!isVis[newRow][newCol]) {
                        isVis[newRow][newCol] = true;
                        q.add(new Pair(newRow, newCol,curr.row, curr.col));
                    }
                    else if (newRow != curr.parentrow || newCol != curr.parentcol) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsCycle(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] isVis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0; i<n; i++){
            for(int j =0; j<m; j++){
                if(isVis[i][j]==false){
                    if(bfs(grid,i,j,isVis,n,m)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}