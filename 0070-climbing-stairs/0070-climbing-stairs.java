class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+2];
        Arrays.fill(dp, -1);
        return usingdp(0, n, dp);
    }

    public int usingdp(int curr, int n, int[] dp){
        if(curr == n){
            dp[curr]=1;
            return 1;
        }
        if(curr>n){
            dp[curr]=0;
            return 0;
        }
        if(dp[curr]!=-1){
            return dp[curr];
        }
        int take1 = usingdp(curr+1, n, dp);
        int take2 = usingdp(curr+2, n, dp);
        return dp[curr] = take1+take2;
    }
}