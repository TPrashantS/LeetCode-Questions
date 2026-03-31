class Solution {

    public int solve(int curr, int end, int[] nums, int[] dp){
        if(curr > end){
            return 0;
        }
        if(dp[curr] != -1){
            return dp[curr];
        }
        int opt1 = solve(curr + 1, end, nums, dp);
        int opt2 = nums[curr] + solve(curr + 2, end, nums, dp);
        return dp[curr] = Math.max(opt1, opt2);
    }

    public int rob(int[] nums) {
        int n = nums.length;

        if(n == 1) return nums[0];

        // removed last
        int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);
        int case1 = solve(0, n-2, nums, dp1);

        // removed first
        int[] dp2 = new int[n];
        Arrays.fill(dp2, -1);
        int case2 = solve(1, n-1, nums, dp2);

        return Math.max(case1, case2);
    }
}