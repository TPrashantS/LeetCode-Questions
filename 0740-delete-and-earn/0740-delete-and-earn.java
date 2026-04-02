class Solution {
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for(int i : nums){
            max = Math.max(i, max);
        }
        int[] freq = new int[max+1];
        for(int num : nums){
            freq[num]++;
        }
        int[] dp = new int[freq.length];
        Arrays.fill(dp, -1);
        return solve(0,freq, dp);
    }

    int solve(int curr, int[] freq, int[] dp){
        if(curr>=freq.length){
            return 0;
        }
        if(dp[curr]!=-1){
            return dp[curr];
        }
        int take = freq[curr]*curr + solve(curr+2, freq, dp);
        int nottake = solve(curr+1, freq, dp);

        return dp[curr] = Math.max(take, nottake);
    }
}