class Solution {

    boolean isPossible(int[] weights, int days, int mid){
        int reqDays = 1;
        int totalWeight = 0;
        for(int i = 0; i < weights.length; i++){
            if(totalWeight + weights[i] <= mid){
                totalWeight += weights[i];
            } else {
                reqDays++;
                totalWeight = weights[i];
            }
        }
        return reqDays <= days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        for(int i = 0; i<weights.length; i++){
            sum += weights[i];
        }
        int start = 0;
        for(int w : weights){
            start = Math.max(start, w);
        }   
        int end = sum;
        int ans = 0;
        while(start<=end){
            int mid = (start+end)/2;
            if(isPossible(weights, days, mid)){
                ans = mid;
                end = mid -1;
            } else {
                start = mid+1;
            }
        }
        return ans;
    }
}