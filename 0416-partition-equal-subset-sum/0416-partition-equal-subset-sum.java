class Solution {

    static Boolean canPartition(int nums[]) {
        int i=nums.length;
        int initsum=0;

        for (i=0; i < nums.length; i++){
            initsum += nums[i];
        }

        if (initsum % 2 != 0) return false;

        int sum = initsum/2;

        int [][] dp = new int[i+1][sum+1];
        for (i = 0; i < nums.length; i++){
            Arrays.fill(dp[i+1], -1);
        }
        if (mainone(i, sum, nums, dp) == 0){
            return true;
        } else{
            return false;
        }
    }
    
    public static int mainone(int i, int sum, int nums[],int dp[][]){
        int pick = 1;
        int notpick = 0;
        
        //base case
        if (sum == 0){
            return 0;
        }
        if (i == 0){
            return 1;
        }
        
        if( dp[i][sum]!=-1){
            return dp[i][sum];
        }
        //recurrence
        if(nums[i-1]<= sum){
            pick =  mainone(i-1, sum-nums[i-1], nums, dp);
        }
        notpick =  mainone(i-1, sum, nums, dp);
        return dp[i][sum] = (pick == 0 || notpick == 0) ? 0 : 1;
    }
}