class Solution {
    public int tribonacci(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        int ans = trib(n, dp);
        return ans;
    }

    public int trib(int n,int[] dp){
        if(dp[n]!=-1){
            return dp[n];
        }
        if(n==0 || n==1){
            return n;
        }
        if(n==2){
            return 1;
        }
        return dp[n] = trib(n-1, dp) + trib(n-2, dp) + trib(n-3, dp);
    }
}