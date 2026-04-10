class Solution {

    public int solve(int[][] grid, int i, int j, int n, int[][] dp){
        if(j<0 || j>=n){
            return Integer.MAX_VALUE;
        }
        if(i==n-1){
            return grid[i][j];
        }
        if(dp[i][j]!=Integer.MAX_VALUE){
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for(int x = 0; x < n; x++){
            if(x != j){
                int next = solve(grid, i + 1, x, n, dp);
                if(next != Integer.MAX_VALUE){
                    min = Math.min(min, grid[i][j] + next);
                }
            }
        }
        return dp[i][j] = min;
    }

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = Integer.MAX_VALUE;
        int[][] dp = new int[n][m];
        for(int[] i : dp){
            Arrays.fill(i, Integer.MAX_VALUE);
        }
        for(int i = 0; i<n; i++){
            ans = Math.min(ans, solve(grid, 0, i, n, dp));
        }
        return ans;
    }
}