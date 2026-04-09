class Solution {
    public int solve(int[][] matrix, int i, int j, int n, int[][] dp){
        if(j<0 || j>=n) return Integer.MAX_VALUE;
        if(i==n-1) return matrix[i][j];
        if(dp[i][j]!=Integer.MAX_VALUE){
            return dp[i][j];
        }
        int left = solve(matrix, i+1,j-1,n,dp);
        int right = solve(matrix,i+1,j+1,n,dp);
        int down = solve(matrix, i+1, j,n,dp);
        return dp[i][j] = matrix[i][j] + Math.min(left, Math.min(right, down));

    }
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = Integer.MAX_VALUE;
        int[][] dp = new int[n][m];
        for(int[] i : dp){
            Arrays.fill(i, Integer.MAX_VALUE);
        }
        for(int i = 0; i<n; i++){
            ans = Math.min(ans, solve(matrix, 0,i,n,dp));
        }
        return ans;
    }
}