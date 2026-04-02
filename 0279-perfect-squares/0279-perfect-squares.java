class Solution {
    public int numSquares(int n) {
        int max = (int)Math.sqrt(n);
        int[] squares = new int[max];
        for(int i = 1; i <= max; i++){
            squares[i-1] = i*i;
        }
        int[][] dp = new int[max][n+1];
        for(int i = 0; i < max; i++){
            Arrays.fill(dp[i], -1);
        }
        return solve(0, n, squares, dp);
    }
    
    int solve(int i, int target, int[] squares, int[][] dp){
        if(target == 0) return 0;
        if(i >= squares.length) return (int)1e9; 
        if(dp[i][target] != -1){
            return dp[i][target];
        }
        int notTake = solve(i + 1, target, squares, dp);
        int take = (int)1e9;
        if(squares[i] <= target){
            take = 1 + solve(i, target - squares[i], squares, dp);
        }
        return dp[i][target] = Math.min(take, notTake);
    }
}