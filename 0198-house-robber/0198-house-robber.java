class Solution {

    public int solve(int curr, int n, int[] nums, int[] dp){
        if(dp[curr]!=-1){
            return dp[curr];
        }
        if(curr>=n){
            return 0;
        }
        int opt1 = solve(curr + 1, n, nums, dp);
        int opt2 =nums[curr] + solve(curr+2, n, nums, dp);
        return dp[curr] = Math.max(opt1,opt2);
    }

    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+2];
        Arrays.fill(dp, -1);
        int ans = Math.max(solve(0, n, nums, dp),solve(1,n,nums,dp));
        return ans;
    }
}
