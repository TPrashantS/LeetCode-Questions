class Solution {

    public class Pair{
        int row;
        int col;
        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public boolean isValid(int currRow, int currCol, int n, int m){
        return (currRow>=0 && currRow<n && currCol>=0 && currCol<m);
    } 

    int[] rowD = {-1,1,0,0};
    int[] colD = {0,0,1,-1};

    public int nearestExit(char[][] maze, int[] entrance) {
        int n = maze.length;
        int m = maze[0].length;
        int count = 0;
        boolean[][] isVis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(entrance[0], entrance[1]));
        isVis[entrance[0]][entrance[1]] = true;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size;i++){
                Pair curr = q.poll();
                int currRow = curr.row;
                int currCol = curr.col;
                if((currRow == 0 || currRow == n-1 || currCol == 0 || currCol == m-1) &&
                !(currRow == entrance[0] && currCol == entrance[1])){
                    return count;
                }
                for(int x = 0; x< 4; x++){
                    int nextRow = currRow + rowD[x];
                    int nextCol = currCol + colD[x];
                    if(isValid(nextRow, nextCol, n,m) && !isVis[nextRow][nextCol]
                    && maze[nextRow][nextCol]=='.'){
                        q.add(new Pair(nextRow, nextCol));
                        isVis[nextRow][nextCol] = true;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}