class Solution {

    public boolean isPossible(int[] piles, int h, int mid){
        int time = 0;
        for(int i =0; i< piles.length; i++){
            time += Math.ceil((double)piles[i]/mid);
        }
        if(time<=h){
            return true;
        }
        return false;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for(int i = 0; i<piles.length; i++){
            max = Math.max(piles[i], max);
        }
        int start = 1;
        int end = max;
        int ans = 0;
        while(start<=end){
            int mid = (start + end)/2;
            int count = 0;
            if(isPossible(piles, h, mid)){
                ans = mid;
                end = mid-1;
            } else{
                start = mid +1;
            }
        }
        return ans;
    }
}