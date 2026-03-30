class Solution {

    public int solve(int[] cost, int curr, int n, int[] dp){
        if(dp[curr]!= -1){
            return dp[curr];
        }
        if(curr>=n){
            dp[curr] = 0;
            return 0;
        }
        int opt1 = solve(cost, curr+1, n, dp);
        int opt2 = solve(cost, curr+2, n, dp);
        return dp[curr] = cost[curr] + Math.min(opt1,opt2);
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+2];
        Arrays.fill(dp, -1);
        int ans = Math.min(solve(cost, 0, n, dp), solve(cost,1,n,dp));
        return ans;
    }
}