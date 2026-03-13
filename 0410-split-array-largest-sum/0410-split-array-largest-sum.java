class Solution {

    boolean isPossible(int[] nums, int split, int mid){
        int reqCount = 1;
        int totalSum = 0;
        for(int i = 0; i < nums.length; i++){
            if(totalSum + nums[i] <= mid){
                totalSum += nums[i];
            } else {
                reqCount++;
                totalSum = nums[i];
            }
        }
        return reqCount <= split;
    }

    public int splitArray(int[] nums, int k) {
        int sum = 0;
        for(int i = 0; i<nums.length; i++){
            sum += nums[i];
        }
        int start = 0;
        for(int s : nums){
            start = Math.max(start, s);
        }
        int end = sum;
        int ans = 0;
        while(start<=end){
            int mid = (start+end)/2;
            if(isPossible(nums, k, mid)){
                ans = mid;
                end = mid -1;
            } else {
                start = mid+1;
            }
        }
        return ans; 
    }
}