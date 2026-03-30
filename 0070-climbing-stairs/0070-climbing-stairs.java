class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return usingdp(n, dp);
    }

    public int usingdp(int n, int[] dp){
        //base case
        if(n==0){
            dp[0] = 1;
            return 1;
        }
        if(n==1){
            dp[1] = 1;
            return 1;
        }
        
        if(dp[n]!=-1){
            return dp[n];
        }
        
        //recurrence relation
        return dp[n] = usingdp(n-1, dp) + usingdp(n-2, dp);
    }
}